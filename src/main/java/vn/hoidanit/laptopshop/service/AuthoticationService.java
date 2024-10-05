package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.dto.RequestDto.AuthoticationDto;
import vn.hoidanit.laptopshop.dto.ResponseDto.UserLoginDto;
import vn.hoidanit.laptopshop.exception.AppException;
import vn.hoidanit.laptopshop.exception.ErrorCode;
import vn.hoidanit.laptopshop.mapper.LoginMapper;
import vn.hoidanit.laptopshop.mapper.UserMapper;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class AuthoticationService {
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoginMapper loginMapper;

    public AuthoticationService(UserRepository userRepository, PasswordEncoder passwordEncoder,
            LoginMapper loginMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loginMapper = loginMapper;
    }

    public UserLoginDto checkPassword(AuthoticationDto authotication) {
        List<User> users = userRepository.findByEmail(authotication.getUsername());
        if (users.isEmpty()) {
            throw new AppException(ErrorCode.USER_PASSWORD_NOT_EXACTLY);
        }
        User user = users.get(0);
        boolean isExactly = passwordEncoder.matches(authotication.getPassword(), user.getPassword());
        if (!isExactly) {
            throw new AppException(ErrorCode.USER_PASSWORD_NOT_EXACTLY);
        }

        UserLoginDto loginDto = this.loginMapper.User_To_User_Login(user);
        return loginDto;
    }
}
