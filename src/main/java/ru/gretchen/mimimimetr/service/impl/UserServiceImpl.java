package ru.gretchen.mimimimetr.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gretchen.mimimimetr.model.dto.UserCreateDto;
import ru.gretchen.mimimimetr.model.entity.PairEntity;
import ru.gretchen.mimimimetr.model.entity.UserEntity;
import ru.gretchen.mimimimetr.model.exception.UserNotFoundException;
import ru.gretchen.mimimimetr.model.mapper.UserMapper;
import ru.gretchen.mimimimetr.repository.UserRepository;
import ru.gretchen.mimimimetr.service.PairService;
import ru.gretchen.mimimimetr.service.UserService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PairService pairService;

    @Override
    public UserEntity get(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public UserEntity create(UserCreateDto createDto) {
        UserEntity user = userMapper.fromCreateDto(createDto);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void assignPair(UUID id, UUID pairId) {
        UserEntity user = get(id);
        PairEntity pair = pairService.get(pairId);
        user.addPare(pair);
        pair.addUser(user);
        userRepository.save(user);
        pairService.create(pair);
    }
}
