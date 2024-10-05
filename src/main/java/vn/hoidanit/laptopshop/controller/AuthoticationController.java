package vn.hoidanit.laptopshop.controller;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.ApiResponseDto;
import vn.hoidanit.laptopshop.domain.dto.AuthoticationDto;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.AuthoticationService;

public class AuthoticationController {
    UserRepository userRepository;
    AuthoticationService authenticationService;

    public AuthoticationController(UserRepository userRepository, AuthoticationService authenticationService) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    public ApiResponseDto<User> isAuthenticated(AuthoticationDto authoticationDto) {
        ApiResponseDto<User> response = new ApiResponseDto<>();
        User user = authenticationService.checkPassword(authoticationDto);
        response.setResult(user);
        return response;
    }
}
