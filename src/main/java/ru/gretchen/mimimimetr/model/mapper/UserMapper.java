package ru.gretchen.mimimimetr.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.gretchen.mimimimetr.model.dto.UserCreateDto;
import ru.gretchen.mimimimetr.model.entity.UserEntity;

@Mapper
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pairs", ignore = true)
    UserEntity fromCreateDto(UserCreateDto createDto);
}
