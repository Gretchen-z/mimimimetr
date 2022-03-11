package ru.gretchen.mimimimetr.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gretchen.mimimimetr.model.dto.CatDto;
import ru.gretchen.mimimimetr.model.entity.CatEntity;
import ru.gretchen.mimimimetr.model.entity.PairEntity;
import ru.gretchen.mimimimetr.model.exception.PairNotFoundException;
import ru.gretchen.mimimimetr.model.mapper.CatMapper;
import ru.gretchen.mimimimetr.repository.PairRepository;
import ru.gretchen.mimimimetr.service.CatService;
import ru.gretchen.mimimimetr.service.PairService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PairServiceImpl implements PairService {
    private final PairRepository pairRepository;
    private final CatService catService;
    private final CatMapper catMapper;

    @Override
    public PairEntity get(UUID id) {
        return pairRepository.findById(id).orElseThrow(() -> new PairNotFoundException(id));
    }

    @Override
    public List<PairEntity> getAll() {
        List<PairEntity> pairs = pairRepository.findAll();
        Collections.shuffle(pairs);
        return pairs;
    }

    @Override
    public PairEntity create(PairEntity pair) {
        return pairRepository.save(pair);
    }

    @Override
    public CatDto getCatOneFromPair(PairEntity pair) {
        return catMapper.toDto(pair.getCatOne());
    }

    @Override
    public CatDto getCatTwoFromPair(PairEntity pair) {
        return catMapper.toDto(pair.getCatTwo());
    }

    @PostConstruct
    private void createPairs() {
        pairRepository.deleteAll();

        List<CatEntity> cats = catService.getAll();
        for (CatEntity cat : cats) {
            for (int i = cats.indexOf(cat) + 1; i < cats.size(); i++) {
                PairEntity pair = new PairEntity();
                pair.setCatOne(cat);
                pair.setCatTwo(cats.get(i));
                pairRepository.save(pair);
            }
        }
    }

}
