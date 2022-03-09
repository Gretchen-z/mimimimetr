package ru.gretchen.mimimimetr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gretchen.mimimimetr.model.entity.CatEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface CatRepository extends JpaRepository<CatEntity, UUID> {
    List<CatEntity> findTop10ByOrderByVoicesDesc();
}
