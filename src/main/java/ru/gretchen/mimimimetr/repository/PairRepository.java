package ru.gretchen.mimimimetr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gretchen.mimimimetr.model.entity.PairEntity;

import java.util.UUID;

@Repository
public interface PairRepository extends JpaRepository<PairEntity, UUID> {
}
