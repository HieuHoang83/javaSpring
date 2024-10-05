package vn.hoidanit.laptopshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.dto.RequestDto.CreateUserDto;
import vn.hoidanit.laptopshop.dto.ResponseDto.UserLoginDto;

@Mapper(componentModel = "spring")
public interface LoginMapper {
    UserLoginDto User_To_User_Login(User user);
}
