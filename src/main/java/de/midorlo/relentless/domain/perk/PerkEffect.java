package de.midorlo.relentless.domain.perk;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A Combat effect, distinguished by name and level.
 */
@Data
@Entity
public class PerkEffect {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    Perk perk;

    String name;
    Integer level;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<PerkEffectDescription> descriptions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    List<PerkEffectValue> values = new ArrayList<>();
}
