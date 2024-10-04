package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.createUserDto;
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
    public User CreateUser(@RequestBody @Valid createUserDto userDto, BindingResult bindingResult) {
        // TODO: process POST request
        // System.out.println(bindingResult);
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getField() + " - " + error.getDefaultMessage());
        }

        return this.userService.handleSaveUser(userDto);
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
