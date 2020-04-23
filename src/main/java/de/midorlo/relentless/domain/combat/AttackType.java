package de.midorlo.relentless.domain.combat;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class AttackType {

    @Id
    String name;

    public AttackType(String name) {
        this.name = name;
    }
}
