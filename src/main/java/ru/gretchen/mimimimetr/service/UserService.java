package ru.gretchen.mimimimetr.service;

import ru.gretchen.mimimimetr.model.dto.UserCreateDto;
import ru.gretchen.mimimimetr.model.entity.UserEntity;

import java.util.UUID;

public interface UserService {

    UserEntity get(UUID id);

    UserEntity create(UserCreateDto createDto);

    void assignPair(UUID id, UUID pareId);
}
