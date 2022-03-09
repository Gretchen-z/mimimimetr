package ru.gretchen.mimimimetr.service;

import ru.gretchen.mimimimetr.model.dto.CatDto;
import ru.gretchen.mimimimetr.model.entity.CatEntity;

import java.util.List;
import java.util.UUID;

public interface CatService {

    CatEntity get(UUID id);

    List<CatEntity> getAll();

    List<CatDto> getTopCats();

    CatEntity create(CatEntity cat);

    void addVoice(UUID id);
}
