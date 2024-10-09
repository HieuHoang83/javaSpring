package vn.AnphaSolution.laptopshop.mapper;

import org.mapstruct.Mapper;

import vn.AnphaSolution.laptopshop.domain.User;
import vn.AnphaSolution.laptopshop.dto.ResponseDto.UserResponseDto;

@Mapper(componentModel = "spring")
public interface LoginMapper {
    UserResponseDto User_To_User_Login(User user);
}
