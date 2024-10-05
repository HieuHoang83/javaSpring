package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.dto.RequestDto.AuthoticationDto;
import vn.hoidanit.laptopshop.dto.ResponseDto.ApiResponseDto;
import vn.hoidanit.laptopshop.dto.ResponseDto.UserLoginDto;
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

    public ApiResponseDto<UserLoginDto> isAuthenticated(@RequestBody AuthoticationDto authoticationDto) {
        ApiResponseDto<UserLoginDto> response = new ApiResponseDto<>();
        UserLoginDto user = authenticationService.checkPassword(authoticationDto);

        response.setResult(user);
        return response;
    }
}
