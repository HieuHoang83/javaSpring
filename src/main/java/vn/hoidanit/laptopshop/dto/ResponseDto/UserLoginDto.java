package vn.hoidanit.laptopshop.dto.ResponseDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserLoginDto {

    private long id;
    private String email;
    private String fullName;
    private String address;
    private String phone;

}
