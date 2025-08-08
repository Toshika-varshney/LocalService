package com.LocalService.LocalService.Mappers;

import com.LocalService.LocalService.DTO.UserDto;
import com.LocalService.LocalService.DTO.UserResponseDto;
import com.LocalService.LocalService.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface UserMapper {

  User toEntity(UserDto dto);
  UserDto toDto(User entity);
  UserResponseDto toResponseDto(User user);
}
