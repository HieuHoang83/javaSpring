package vn.AnphaSolution.laptopshop.service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import lombok.extern.slf4j.Slf4j;
import vn.AnphaSolution.laptopshop.domain.User;
import vn.AnphaSolution.laptopshop.dto.RequestDto.AuthoticationDto;
import vn.AnphaSolution.laptopshop.dto.ResponseDto.LoginResponseDto;
import vn.AnphaSolution.laptopshop.dto.ResponseDto.UserResponseDto;
import vn.AnphaSolution.laptopshop.exception.AppException;
import vn.AnphaSolution.laptopshop.exception.ErrorCode;
import vn.AnphaSolution.laptopshop.mapper.LoginMapper;
import vn.AnphaSolution.laptopshop.repository.UserRepository;

@Slf4j
@Service
public class AuthoticationService {
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoginMapper loginMapper;
    // @Value("${SIGNER_KEY}")
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    public AuthoticationService(
            UserRepository userRepository, PasswordEncoder passwordEncoder, LoginMapper loginMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loginMapper = loginMapper;
    }

    public User checkPassword(AuthoticationDto authotication) {
        List<User> users = userRepository.findByEmail(authotication.getUsername());
        if (users.isEmpty()) {
            throw new AppException(ErrorCode.USER_PASSWORD_NOT_EXACTLY);
        }
        User user = users.get(0);
        boolean isExactly = passwordEncoder.matches(authotication.getPassword(), user.getPassword());
        if (!isExactly) {
            throw new AppException(ErrorCode.USER_PASSWORD_NOT_EXACTLY);
        }

        return user;
    }

    public String generateToken(User user) {

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issuer("hieu.com")
                .issueTime(new Date())
                .expirationTime(
                        new Date(Instant.now().plus(15, ChronoUnit.MINUTES).toEpochMilli()))
                .claim("scope", user.getRoleId().getName())
                .build();
        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject payloadJWSObject = new JWSObject(header, payload);
        try {
            payloadJWSObject.sign(new MACSigner(this.SIGNER_KEY.getBytes()));
            return payloadJWSObject.serialize();
        } catch (KeyLengthException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (JOSEException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public String generateRefreshToken(User user) {

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issuer("hieu.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.DAYS).toEpochMilli()))
                .claim("scope", user.getRoleId().getName())
                .build();
        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject payloadJWSObject = new JWSObject(header, payload);
        try {
            payloadJWSObject.sign(new MACSigner(this.SIGNER_KEY.getBytes()));
            return payloadJWSObject.serialize();
        } catch (KeyLengthException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (JOSEException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public LoginResponseDto Login(AuthoticationDto authotication) {
        log.info("-------" + this.SIGNER_KEY);
        User user = this.checkPassword(authotication);
        UserResponseDto UserResponseDto = loginMapper.User_To_User_Login(user);
        String token = generateToken(user);
        String refreshToken = generateRefreshToken(user);
        user.setRefreshToken(refreshToken);
        userRepository.save(user);
        LoginResponseDto loginResponseDto = new LoginResponseDto(UserResponseDto, token, refreshToken);

        return loginResponseDto;
    }

    public String instropectRefreshToken(String token) throws JOSEException, ParseException {
        List<User> users = userRepository.findByRefreshToken(token);
        if (users.isEmpty()) {
            throw new AppException(ErrorCode.RefreshToken_Not_Valid);
        }
        JWSVerifier verifier = new MACVerifier(this.SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        boolean Isverifed = signedJWT.verify(verifier);
        Date exprityTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        Boolean Isvalid = Isverifed && exprityTime.after(new Date());
        if (Isvalid) {

            return generateToken(users.get(0));
        } else {
            throw new AppException(ErrorCode.Token_Not_Valid);
        }
    }
}
