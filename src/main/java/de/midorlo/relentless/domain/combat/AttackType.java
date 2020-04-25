package de.midorlo.relentless.domain.combat;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;

@Data
@Entity
public class AttackType {

    @Id
    String name;

    public AttackType() {
    }

    public AttackType(String name) {
        this.name = name;
    }
}
