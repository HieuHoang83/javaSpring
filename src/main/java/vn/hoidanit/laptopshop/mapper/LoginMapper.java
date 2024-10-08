package vn.hoidanit.laptopshop.mapper;

import org.mapstruct.Mapper;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.dto.ResponseDto.UserResponseDto;

@Mapper(componentModel = "spring")
public interface LoginMapper {
    UserResponseDto User_To_User_Login(User user);
}
