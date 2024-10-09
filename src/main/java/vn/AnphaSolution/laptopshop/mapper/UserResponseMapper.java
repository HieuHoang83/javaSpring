package vn.AnphaSolution.laptopshop.mapper;

import org.mapstruct.Mapper;

import vn.AnphaSolution.laptopshop.domain.User;
import vn.AnphaSolution.laptopshop.dto.ResponseDto.UserResponseDto;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {
    UserResponseDto User_To_UserResponseDto(User user);
}
