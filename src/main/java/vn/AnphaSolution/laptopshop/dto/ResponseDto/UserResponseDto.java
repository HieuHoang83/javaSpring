package vn.AnphaSolution.laptopshop.dto.ResponseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponseDto {

    private long id;
    private String email;
    private String fullName;
    private String address;
    private String phone;
    private String Role;

    @Override
    public String toString() {
        return "id=" + id + ", email=" + email + ", fullName=" + fullName + ", address=" + address + ", phone=" + phone;
    }
}
