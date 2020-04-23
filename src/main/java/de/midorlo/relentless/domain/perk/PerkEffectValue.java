package de.midorlo.relentless.domain.perk;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class PerkEffectValue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    Object value;

    public PerkEffectValue(Object value) {
        this.value = value;
    }
}
