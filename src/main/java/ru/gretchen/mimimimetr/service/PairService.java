package ru.gretchen.mimimimetr.service;

import ru.gretchen.mimimimetr.model.dto.CatDto;
import ru.gretchen.mimimimetr.model.entity.PairEntity;

import java.util.List;
import java.util.UUID;

public interface PairService {
    PairEntity get(UUID id);

    List<PairEntity> getAll();

    PairEntity create(PairEntity pair);

    CatDto getCatOneFromPair(PairEntity pair);

    CatDto getCatTwoFromPair(PairEntity pair);

}
