package de.midorlo.relentless.domain.perk;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PerkEffectValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    String value;

    public PerkEffectValue(String value) {
        this.value = value;
    }

    public PerkEffectValue() {}
}
