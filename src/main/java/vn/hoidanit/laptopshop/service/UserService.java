package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.createUserDto;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String getUserName() {
        return "hello";
    }

    public User handleSaveUser(createUserDto userDto) {

        User user = new User();
        user.setEmail(userDto.getEmail());
        String hassPassword = this.passwordEncoder.encode(userDto.getPassword());
        user.setPassword(hassPassword);
        user.setFullName(userDto.getFullName());
        user.setAddress(userDto.getAddress());
        user.setPhone(userDto.getPhone());
        user.setRoleId(null);
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
