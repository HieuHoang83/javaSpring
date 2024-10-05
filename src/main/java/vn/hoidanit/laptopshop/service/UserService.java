package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.CreateUserDto;
import vn.hoidanit.laptopshop.exception.AppException;
import vn.hoidanit.laptopshop.exception.ErrorCode;
import vn.hoidanit.laptopshop.mapper.UserMapper;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public String getUserName() {
        return "hello";
    }

    public User handleSaveUser(CreateUserDto userDto) {

        boolean isExistEmail = this.userRepository.existsByEmail(userDto.getEmail());
        if (isExistEmail) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        userDto.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
        User user = userMapper.createToUser(userDto);

        return this.userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUsersById(long id) {
        return userRepository.findById(id);
    }

    public User deleteById(long id) {
        return userRepository.removeById(id);
    }

    // public User updateUserById(long id, User userdto) {
    // User currentUser = this.getUsersById(id);
    // if (currentUser != null) {

    // if (userdto.getFullName() != null) {
    // currentUser.setFullName(userdto.getFullName());
    // }
    // if (userdto.getPhone() != null) {
    // currentUser.setPhone(userdto.getPhone());
    // }
    // if (userdto.getAddress() != null) {
    // currentUser.setAddress(userdto.getAddress());
    // }

    // }
    // return this.handleSaveUser(currentUser);
    // }
}
