package vn.hoidanit.laptopshop.dto.ResponseDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginResponseDto {
    private UserResponseDto UserResponseDto;
    private String token;
    private String refreshToken;

    public LoginResponseDto(UserResponseDto UserResponseDto, String token, String refreshToken) {
        this.UserResponseDto = UserResponseDto;
        this.token = token;
        this.refreshToken = refreshToken;
    }
}
