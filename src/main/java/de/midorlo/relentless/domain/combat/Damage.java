package de.midorlo.relentless.domain.combat;

import de.midorlo.relentless.domain.player.Loadout;
import lombok.*;

/**
 * Makes extensive use of the formulas at: https://docs.google.com/spreadsheets/d/13lw6ShdGDyelde7u4Dhs95skIQdLQ1LHRE2heFTVBoc/edit#gid=0
 */
@Data
@AllArgsConstructor
public class Damage {

    /* Typed Summants */
    Double healthDamage;
    Double partDamage;
    Double staggerDamage;
    Double woundDamage;
    /* Typed Factors */
    Double healthDamageFactor;
    Double partDamageFactor;
    Double staggerDamageFactor;
    Double woundDamageFactor;
    /* Special Factors */
    Double attackTypeFactor;
    Double critFactor;
    Double acidicFactor;
    Double powerFactor;
    Double marksmanFactor;
    Double hitzoneFactor;
    /* Absurdities */
    Double staggerDamageMalus;

    /**
     * New Instance will all empty values (including factors!)
     */
    public Damage() {
        healthDamage = 0d;
        partDamage = 0d;
        staggerDamage = 0d;
        woundDamage = 0d;
        healthDamageFactor = 0d;
        partDamageFactor = 0d;
        staggerDamageFactor = 0d;
        woundDamageFactor = 0d;
        attackTypeFactor = 0d;
        critFactor = 0d;
        acidicFactor = 0d;
        powerFactor = 0d;
        marksmanFactor = 0d;
        hitzoneFactor = 0d;
        staggerDamageMalus = 0d;
    }

    public void add(Damage damage) {
        this.healthDamage += damage.healthDamage;
        this.partDamage += damage.partDamage;
        this.staggerDamage += damage.staggerDamage;
        this.woundDamage += damage.woundDamage;
        this.healthDamageFactor += damage.healthDamageFactor;
        this.partDamageFactor += damage.partDamageFactor;
        this.staggerDamageFactor += damage.staggerDamageFactor;
        this.woundDamageFactor += damage.woundDamageFactor;
        this.attackTypeFactor += damage.attackTypeFactor;
        this.critFactor += damage.critFactor;
        this.acidicFactor += damage.acidicFactor;
        this.powerFactor += damage.powerFactor;
        this.marksmanFactor += damage.marksmanFactor;
        this.hitzoneFactor += damage.hitzoneFactor;
    }

    public Double getHealthDamageNetto() {
        return healthDamage * attackTypeFactor * critFactor * healthDamageFactor * powerFactor * marksmanFactor;
    }

    public Double getPartDamageNetto() {
        return ((healthDamage * partDamageFactor) + partDamage) * attackTypeFactor * critFactor * healthDamageFactor * powerFactor * marksmanFactor * acidicFactor;
    }

    public Double getStaggerDamageNetto() {
        return (((healthDamage * attackTypeFactor * critFactor * staggerDamageFactor) - staggerDamageMalus) + staggerDamage) * powerFactor * hitzoneFactor;
    }

    public Double getWoundDamageNetto() {
        return ((healthDamage * attackTypeFactor * critFactor * woundDamageFactor * healthDamageFactor) + woundDamage) * powerFactor;
    }
}