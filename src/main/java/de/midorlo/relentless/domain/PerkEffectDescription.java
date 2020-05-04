package de.midorlo.relentless.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PerkEffectDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private String description;

    public PerkEffectDescription() {}

    public PerkEffectDescription(String description) {
        this.description = description;
    }
}
