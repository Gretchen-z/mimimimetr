package ru.gretchen.mimimimetr.model.mapper;

import org.mapstruct.Mapper;
import ru.gretchen.mimimimetr.model.dto.CatDto;
import ru.gretchen.mimimimetr.model.entity.CatEntity;

@Mapper
public interface CatMapper {
    CatDto toDto(CatEntity cat);
}
