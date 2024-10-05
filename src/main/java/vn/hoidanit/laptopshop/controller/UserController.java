package vn.hoidanit.laptopshop.controller;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.dto.RequestDto.CreateUserDto;
import vn.hoidanit.laptopshop.dto.ResponseDto.ApiResponseDto;
import vn.hoidanit.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping("/user/create")
    public ApiResponseDto<User> CreateUser(@RequestBody @Valid CreateUserDto userDto) {

        ApiResponseDto<User> response = new ApiResponseDto<>();

        response.setResult(this.userService.handleSaveUser(userDto));
        return response;
    }

    @GetMapping("/")
    public List<User> getfullUsers() {

        // model.addAttribute("eric", test);
        return this.userService.getUsers();
    }

    @GetMapping("/user")
    public User Getuserbyid(@RequestParam("id") long id) {

        // model.addAttribute("eric", test);
        return this.userService.getUsersById(id);
    }

    // @PostMapping("/user")
    // public User UpdateUserById(@RequestParam("id") long id, @RequestBody User
    // userdto) {

    // // model.addAttribute("eric", test);
    // return this.userService.updateUserById(id, userdto);
    // }
}
