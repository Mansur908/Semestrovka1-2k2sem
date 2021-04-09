package ru.itis.demo.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.itis.demo.models.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);
}
