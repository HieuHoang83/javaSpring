package vn.AnphaSolution.laptopshop.config;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.AnphaSolution.laptopshop.dto.ResponseDto.ApiResponseDto;
import vn.AnphaSolution.laptopshop.exception.ErrorCode;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        ErrorCode errorCode = ErrorCode.UNAUTHOTICATED;
        response.setStatus(errorCode.getStatusCode().value());
        response.setContentType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE);
        ApiResponseDto<?> responseDto = new ApiResponseDto();
        responseDto.setStatusCode(errorCode.getStatusCode());
        responseDto.setMessage(errorCode.getMessage());
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(responseDto));
        response.flushBuffer();
    }
}
