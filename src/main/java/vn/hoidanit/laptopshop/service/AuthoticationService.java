package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.AuthoticationDto;
import vn.hoidanit.laptopshop.exception.AppException;
import vn.hoidanit.laptopshop.exception.ErrorCode;
import vn.hoidanit.laptopshop.repository.UserRepository;

public class AuthoticationService {
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthoticationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
}
