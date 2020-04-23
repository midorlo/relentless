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

    /** Parent Perk. */
    @OneToOne
    Perk perk;

    @Basic
    String name;

    @Basic
    Integer level;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<PerkEffectDescription> descriptions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    List<PerkEffectValue> values = new ArrayList<>();
}
