package vn.hoidanit.laptopshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.dto.RequestDto.CreateUserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User createToUser(CreateUserDto createUserDto);

    void userUpdate(@MappingTarget User user, CreateUserDto createUserDto);
}
