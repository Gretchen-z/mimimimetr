package ru.gretchen.mimimimetr.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gretchen.mimimimetr.model.dto.CatDto;
import ru.gretchen.mimimimetr.model.entity.CatEntity;
import ru.gretchen.mimimimetr.model.exception.CatNotFoundException;
import ru.gretchen.mimimimetr.model.mapper.CatMapper;
import ru.gretchen.mimimimetr.repository.CatRepository;
import ru.gretchen.mimimimetr.service.CatService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {
    private final CatRepository catRepository;
    private final CatMapper catMapper;

    @Override
    public CatEntity get(UUID id) {
        return catRepository.findById(id).orElseThrow(() -> new CatNotFoundException(id));
    }

    @Override
    public List<CatEntity> getAll() {
        return catRepository.findAll();
    }

    @Override
    public List<CatDto> getTopCats() {
        List<CatEntity> cats = catRepository.findTop10ByOrderByVoicesDesc();
        return cats.stream()
                .map(catMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CatEntity create(CatEntity cat) {
        return catRepository.save(cat);
    }

    @Override
    public void addVoice(UUID id) {
        CatEntity cat = get(id);
        cat.setVoices(cat.getVoices() + 1);
        catRepository.save(cat);
    }
}
