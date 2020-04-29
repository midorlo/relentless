package de.midorlo.relentless.domain.perk;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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

//    @OneToMany()
//    @LazyCollection(LazyCollectionOption.FALSE)
    @Transient
    List<PerkEffectDescription> descriptions = new ArrayList<>();

//    @OneToMany
//    @LazyCollection(LazyCollectionOption.FALSE)
    @Transient
    List<PerkEffectValue> values = new ArrayList<>();
}
