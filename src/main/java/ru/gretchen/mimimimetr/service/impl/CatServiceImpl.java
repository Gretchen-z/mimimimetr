package ru.gretchen.mimimimetr.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gretchen.mimimimetr.model.dto.CatDto;
import ru.gretchen.mimimimetr.model.entity.CatEntity;
import ru.gretchen.mimimimetr.model.exception.CatNotFoundException;
import ru.gretchen.mimimimetr.model.mapper.CatMapper;
import ru.gretchen.mimimimetr.repository.CatRepository;
import ru.gretchen.mimimimetr.service.CatService;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @PostConstruct
    @SneakyThrows
    private void initCats() {
        long catsCount = catRepository.count();

        if (catsCount == 0) {
            URL photosFolderURL = getClass().getClassLoader().getResource("photos");
            File photosFolder = new File(photosFolderURL.toURI());

            List<CatEntity> catEntities = Stream.of(photosFolder.listFiles())
                    .map(new Function<File, byte[]>() {
                        @SneakyThrows
                        @Override
                        public byte[] apply(File file) {
                            FileInputStream fis = new FileInputStream(file);
                            return fis.readAllBytes();
                        }
                    })
                    .map(bytesOfPhoto -> {
                        int nameIndex = catNames.size() - 1;

                        CatEntity catEntity = new CatEntity();
                        catEntity.setName(catNames.get(nameIndex));
                        catEntity.setPhoto(bytesOfPhoto);

                        catNames.remove(nameIndex);
                        return catEntity;
                    })
                    .collect(Collectors.toList());

            catRepository.saveAll(catEntities);
        }
    }

    private final List<String> catNames = new ArrayList<>() {{
        add("Муська");
        add("Барсик");
        add("Сеня");
        add("Персик");
        add("Матроскин");
        add("Шуршик");
    }};
}
