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

    @Deprecated public static AttackType Blunt = new AttackType("Blunt");
    @Deprecated public static AttackType Slashing = new AttackType("Slashing");
    @Deprecated public static AttackType Piercing = new AttackType("Piercing");
    @Deprecated public static AttackType Special = new AttackType("Special");
    @Deprecated public static AttackType valueOf(String name) {
        AttackType type = null;
        for (AttackType t : new AttackType[]{Blunt, Slashing, Piercing, Special}) {
            if (t.getName().contentEquals(name)) {
                type = t;
                break;
            }
        }
        return type;
    }
}
