package de.midorlo.relentless.domain.items;

import de.midorlo.relentless.domain.combat.AttackMove;
import de.midorlo.relentless.domain.combat.DamageType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = {"moveSets"}, callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Log
public class Weapon extends Gear {

    List<List<AttackMove>> moveSets = new ArrayList<>();
    DamageType damageType;

    /**
     * Disregard everything but maxxed out weapons for now.
     * @return 550.
     */
    public Integer getPower() {
        return 550;
    }
}
