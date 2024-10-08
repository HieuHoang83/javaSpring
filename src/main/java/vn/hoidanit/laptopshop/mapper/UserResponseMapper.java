package vn.hoidanit.laptopshop.mapper;

import org.mapstruct.Mapper;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.dto.ResponseDto.UserResponseDto;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {
    UserResponseDto User_To_UserResponseDto(User user);
}
