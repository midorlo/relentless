package de.midorlo.relentless.domain.combat;

import lombok.*;

/**
 * Makes extensive use of the formulas at: https://docs.google.com/spreadsheets/d/13lw6ShdGDyelde7u4Dhs95skIQdLQ1LHRE2heFTVBoc/edit#gid=0
 */
@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Damage {

    /* Typed Flat Damage */
    Double healthDamageFlat = 0d;
    Double parthDamageFlat = 0d;
    Double staggerDamageFlat = 0d;
    Double woundDamageFlat = 0d;

    /* Typed Multiplicators */
    Double healthDamageMult = 0d;
    Double partDamageMult = 0d;
    Double staggerDamageMult = 0d;
    Double woundDamageMult = 0d;

    /* Special Multiplicators */
    Double attackTypeMult = 0d;
    Double critMult = 0d;
    Double acidicMult = 0d;
    Double powerMult = 0d;
    Double marksmanMult = 0d;
    Double hitzoneMult = 0d;

    /* Absurdities */
    Double setStaggerMalusFlat = 0d;

    public void add(Damage damage) {
        this.healthDamageFlat += damage.healthDamageFlat;
        this.parthDamageFlat += damage.parthDamageFlat;
        this.staggerDamageFlat += damage.staggerDamageFlat;
        this.woundDamageFlat += damage.woundDamageFlat;
        this.healthDamageMult += damage.healthDamageMult;
        this.partDamageMult += damage.partDamageMult;
        this.staggerDamageMult += damage.staggerDamageMult;
        this.woundDamageMult += damage.woundDamageMult;
        this.attackTypeMult += damage.attackTypeMult;
        this.critMult += damage.critMult;
        this.acidicMult += damage.acidicMult;
        this.powerMult += damage.powerMult;
        this.marksmanMult += damage.marksmanMult;
        this.hitzoneMult += damage.hitzoneMult;
    }

    public Double getHealthDamageNetto() {
        return healthDamageFlat * attackTypeMult * critMult * healthDamageMult * powerMult * marksmanMult;
    }

    public Double getPartDamageNetto() {
        return ((healthDamageFlat * partDamageMult) + parthDamageFlat) * attackTypeMult * critMult * healthDamageMult * powerMult * marksmanMult * acidicMult;
    }

    public Double getStaggerDamageNetto() {
        return (((healthDamageFlat * attackTypeMult * critMult * staggerDamageMult) - setStaggerMalusFlat) + staggerDamageFlat) * powerMult * hitzoneMult;
    }

    public Double getWoundDamageNetto() {
        return ((healthDamageFlat * attackTypeMult * critMult * woundDamageMult * healthDamageMult) + woundDamageFlat) * powerMult;
    }
}