package de.midorlo.relentless.domain.combat;

/**
 * Represents a bonus attack; Think of Cleave, spin or
 * anything that isnt a precice single hit.
 */
@SuppressWarnings("unused")
public class WeaponExtraAttack extends WeaponAttack {

    WeaponAttack parent;

    public WeaponExtraAttack(WeaponAttack parent) {
        super();
        this.parent = parent;
    }
}
