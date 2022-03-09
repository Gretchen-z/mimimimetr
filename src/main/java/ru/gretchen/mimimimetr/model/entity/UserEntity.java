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
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(name = "login")
    private String login;

    @Setter(AccessLevel.PRIVATE)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_pairs",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "pair_id")}
    )
    private Set<PairEntity> pairs = new HashSet<>();

    public void addPare(PairEntity pair) {
        this.pairs.add(pair);
    }
}
