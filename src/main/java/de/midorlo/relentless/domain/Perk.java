package de.midorlo.relentless.domain;

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

    @Basic(optional = false)
    private String name;
    @Basic(optional = false)
    private String description;
    @Basic(optional = false)
    private Integer level;

    @OneToMany(fetch = FetchType.LAZY)
    List<PerkEffect> effects = new ArrayList<>();

    public List<PerkEffect> getEffects(Integer level) {
        return effects.stream()
                .filter(e -> e.getLevel().equals(level))
                .collect(Collectors.toList());
    }
}
