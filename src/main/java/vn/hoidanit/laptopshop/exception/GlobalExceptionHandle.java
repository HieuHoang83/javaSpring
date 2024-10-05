package vn.hoidanit.laptopshop.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import vn.hoidanit.laptopshop.dto.ResponseDto.ApiResponseDto;

@ControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponseDto> handleRuntimeException(RuntimeException exception) {
        ApiResponseDto response = new ApiResponseDto();
        response.setCode(400);
        response.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponseDto> handleAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponseDto response = new ApiResponseDto();
        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ApiResponseDto response = new ApiResponseDto();
        response.setCode(400);
        response.setMessage(exception.getFieldError().getDefaultMessage());
        return ResponseEntity.badRequest().body(response);
    }

}
