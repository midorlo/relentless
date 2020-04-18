package de.midorlo.relentless.domain.combat;

/**
 * Represents a bonus attack; Think of Cleave, spin or
 * anything that isnt a precice single hit.
 */
public class BonusWeaponAttack extends WeaponAttack {

    boolean isCleave;
    WeaponAttack parent;

    public BonusWeaponAttack(WeaponAttack parent, boolean isCleave) {
        super();
        this.parent = parent;
        this.isCleave = isCleave;
    }
}
