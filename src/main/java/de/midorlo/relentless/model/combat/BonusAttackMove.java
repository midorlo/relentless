package de.midorlo.relentless.model.combat;

/**
 * Represents a bonus attack; Think of Cleave, spin or
 * anything that isnt a precice single hit.
 */
public class BonusAttackMove extends AttackMove {

    boolean isCleave;
    AttackMove parent;

    public BonusAttackMove(AttackMove parent, boolean isCleave) {
        super();
        this.parent = parent;
        this.isCleave = isCleave;
    }
}
