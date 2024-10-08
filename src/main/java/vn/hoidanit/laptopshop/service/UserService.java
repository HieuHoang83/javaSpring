package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.dto.RequestDto.CreateUserDto;
import vn.hoidanit.laptopshop.dto.ResponseDto.UserResponseDto;
import vn.hoidanit.laptopshop.exception.AppException;
import vn.hoidanit.laptopshop.exception.ErrorCode;
import vn.hoidanit.laptopshop.mapper.UserMapper;
import vn.hoidanit.laptopshop.mapper.UserResponseMapper;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserResponseMapper userResponseMapper;

    public UserService(
            RoleService roleService,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            UserMapper userMapper,
            UserResponseMapper userResponseMapper) {
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userResponseMapper = userResponseMapper;
    }

    public String getUserName() {
        return "hello";
    }

    public String hashPassword(String password) {
        return this.passwordEncoder.encode(password);
    }

    public UserResponseDto CreateUser(CreateUserDto userDto) {

        boolean isExistEmail = this.userRepository.existsByEmail(userDto.getEmail());
        if (isExistEmail) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        userDto.setPassword(hashPassword(userDto.getPassword()));
        User user = userMapper.createToUser(userDto);
        // user.setRoleId();
        user.setRoleId(this.roleService.FindByName("User"));
        this.userRepository.save(user);
        UserResponseDto userResponseDto = userResponseMapper.User_To_UserResponseDto(user);
        return userResponseDto;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUsersById(long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        return user;
    }

    public User GetUserByEmail(String email) {
        User user = this.userRepository.findByEmail(email).get(0);

        return user;
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
    // return this.CreateUser(currentUser);
    // }
}
