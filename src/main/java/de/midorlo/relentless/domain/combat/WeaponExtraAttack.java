package de.midorlo.relentless.domain.combat;

/**
 * Represents a bonus attack; Think of Cleave, spin or
 * anything that isnt a precice single hit.
 */
public class WeaponExtraAttack extends WeaponAttack {

    WeaponAttack parent;

    public WeaponExtraAttack(WeaponAttack parent) {
        super();
        this.parent = parent;
    }
}
