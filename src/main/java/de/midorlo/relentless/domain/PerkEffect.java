package de.midorlo.relentless.domain;

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

    @Basic
    String name;

    @Basic
    Integer level;

    @Transient
    List<PerkEffectDescription> descriptions = new ArrayList<>();

    @Transient
    List<PerkEffectValue> values = new ArrayList<>();
}
