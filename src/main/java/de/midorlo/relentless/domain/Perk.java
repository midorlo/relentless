package de.midorlo.relentless.domain;

import lombok.Data;
import lombok.extern.java.Log;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Log
@Entity
public class Perk {

    public Perk() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @LazyToOne(LazyToOneOption.NO_PROXY)
    @Basic(optional = false,fetch = FetchType.EAGER)
    private String name;

    @LazyToOne(LazyToOneOption.NO_PROXY)
    @Basic(optional = false)
    private String description;

    @LazyToOne(LazyToOneOption.NO_PROXY)
    @Basic(optional = false)
    private Integer level;

    @LazyCollection(LazyCollectionOption.EXTRA)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<PerkEffect> effects = new ArrayList<>();

    public List<PerkEffect> getEffects(Integer level) {
        return effects.stream()
                .filter(e -> e.getLevel().equals(level))
                .collect(Collectors.toList());
    }
}
