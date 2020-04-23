package de.midorlo.relentless.domain.perk;

import lombok.Data;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Log
@Entity
public class Perk {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private Integer level;

    @OneToMany
    List<PerkEffect> effects = new ArrayList<>();

    protected List<PerkEffect> getEffects() {
        return effects;
    }

    public List<PerkEffect> getEffects(Integer level) {
        return effects.stream()
                .filter(e -> e.getLevel().equals(level))
                .collect(Collectors.toList());
    }
}