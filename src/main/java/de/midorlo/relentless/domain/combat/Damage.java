package de.midorlo.relentless.domain.combat;

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
        marksmanFactor = 0d;
        critFactor = 0d;
        acidicFactor = 0d;
        hitzoneFactor = 0d;
        healthDamage = 0d;
        powerFactor = 0d;
        partDamage = 0d;
        staggerDamage = 0d;
        woundDamage = 0d;
        healthDamageFactor = 0d;
        partDamageFactor = 0d;
        staggerDamageFactor = 0d;
        woundDamageFactor = 0d;
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
        this.critFactor += damage.critFactor;
        this.acidicFactor += damage.acidicFactor;
        this.powerFactor += damage.powerFactor;
        this.marksmanFactor += damage.marksmanFactor;
        this.hitzoneFactor += damage.hitzoneFactor;
    }

    /**
     * Applies final sanity check and fixes before evaluation.
     */
    public Damage fixate() {
        if (getCritFactor() == 0) {
            setCritFactor(1d);
        }
        if (getMarksmanFactor() == 0) {
            setMarksmanFactor(1d);
        }
        if (getHitzoneFactor() == 0) {
            setHitzoneFactor(1d);
        }
        if (getAcidicFactor() == 0) {
            setAcidicFactor(1d);
        }
        return this;
    }

    public Double getHealthDamageNetto() {
        return healthDamage * critFactor * healthDamageFactor * powerFactor * marksmanFactor;
    }

    public Double getPartDamageNetto() {
        return ((healthDamage * partDamageFactor) + partDamage)  * critFactor * healthDamageFactor * powerFactor * marksmanFactor * acidicFactor;
    }

    public Double getStaggerDamageNetto() {
        return (((healthDamage * critFactor * staggerDamageFactor) - staggerDamageMalus) + staggerDamage) * powerFactor * hitzoneFactor;
    }

    public Double getWoundDamageNetto() {
        return ((healthDamage  * critFactor * woundDamageFactor * healthDamageFactor) + woundDamage) * powerFactor;
    }
}