package de.midorlo.relentless.domain.combat;

import lombok.Data;

@Data
public class AttackType {

    public static AttackType Blunt = new AttackType("Blunt");
    public static AttackType Slashing = new AttackType("Slashing");
    public static AttackType Piercing = new AttackType("Piercing");
    public static AttackType Special = new AttackType("Special");

    public static AttackType valueOf(String name) {
        AttackType type = null;
        for (AttackType t : new AttackType[]{Blunt, Slashing, Piercing, Special}) {
            if (t.getName().contentEquals(name)) {
                type = t;
                break;
            }
        }
        return type;
    }

    String name;

    public AttackType(String name) {
        this.name = name;
    }
}
