package ru.gretchen.mimimimetr.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cats")
public class CatEntity extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "voices")
    private int voices;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JoinColumn(name = "cat_id")
    private List<PairEntity> pairs = new ArrayList<>();

    public void addPareCatOne(PairEntity pair) {
        this.pairs.add(pair);
        pair.setCatOne(this);
    }

    public void addPareCatTwo(PairEntity pair) {
        this.pairs.add(pair);
        pair.setCatTwo(this);
    }
}
