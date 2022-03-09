package ru.gretchen.mimimimetr.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pairs")
public class PairEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cat_one_id")
    private CatEntity catOne;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cat_two_id")
    private CatEntity catTwo;

    @Setter(AccessLevel.PRIVATE)
    @ManyToMany(mappedBy = "pairs",
            fetch = FetchType.LAZY)
    private Set<UserEntity> users = new HashSet<>();

    public void addUser(UserEntity user) {
        this.users.add(user);
    }
}
