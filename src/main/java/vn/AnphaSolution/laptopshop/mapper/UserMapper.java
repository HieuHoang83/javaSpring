package vn.AnphaSolution.laptopshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import vn.AnphaSolution.laptopshop.domain.User;
import vn.AnphaSolution.laptopshop.dto.RequestDto.CreateUserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User createToUser(CreateUserDto createUserDto);

    void userUpdate(@MappingTarget User user, CreateUserDto createUserDto);
}
