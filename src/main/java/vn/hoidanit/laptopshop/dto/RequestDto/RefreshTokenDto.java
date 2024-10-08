package vn.hoidanit.laptopshop.dto.RequestDto;

import jakarta.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RefreshTokenDto {
    @NotEmpty(message = "RefreshToken cannot be empty")
    private String refreshToken;
}
