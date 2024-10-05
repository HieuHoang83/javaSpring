package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.ApiResponseDto;
import vn.hoidanit.laptopshop.domain.dto.AuthoticationDto;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.AuthoticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AuthoticationController {
    UserRepository userRepository;
    AuthoticationService authenticationService;

    public AuthoticationController(UserRepository userRepository, AuthoticationService authenticationService) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authent/login")

    public ApiResponseDto<User> isAuthenticated(@RequestBody AuthoticationDto authoticationDto) {
        ApiResponseDto<User> response = new ApiResponseDto<>();
        User user = authenticationService.checkPassword(authoticationDto);
        response.setResult(user);
        return response;
    }
}
