package vn.AnphaSolution.laptopshop.controller;

import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import vn.AnphaSolution.laptopshop.domain.User;
import vn.AnphaSolution.laptopshop.dto.RequestDto.CreateUserDto;
import vn.AnphaSolution.laptopshop.dto.ResponseDto.ApiResponseDto;
import vn.AnphaSolution.laptopshop.dto.ResponseDto.UserResponseDto;
import vn.AnphaSolution.laptopshop.service.UserService;

@Slf4j
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // @GetMapping("user/info")
    // public User GetMyInfo() {
    // SecurityContext context = SecurityContextHolder.getContext();
    // String name = context.getAuthentication().toString();

    // return new User();
    // }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user")
    public ApiResponseDto<User> Getuserbyid(@RequestParam("id") long id) {

        ApiResponseDto<User> response = new ApiResponseDto<>();

        response.setResult(this.userService.getUsersById(id));
        return response;
    }

    @PostMapping("/user/create")
    public ApiResponseDto<UserResponseDto> CreateUser(@RequestBody @Valid CreateUserDto userDto) {

        ApiResponseDto<UserResponseDto> response = new ApiResponseDto<>();

        response.setResult(this.userService.CreateUser(userDto));
        return response;
    }

    // @GetMapping("/")
    // public List<User> getfullUsers() {

    // // model.addAttribute("eric", test);
    // return this.userService.getUsers();
    // }

    // @PostMapping("/user")
    // public User UpdateUserById(@RequestParam("id") long id, @RequestBody User
    // userdto) {

    // // model.addAttribute("eric", test);
    // return this.userService.updateUserById(id, userdto);
    // }
}
