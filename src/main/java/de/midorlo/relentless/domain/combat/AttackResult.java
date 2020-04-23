package de.midorlo.relentless.domain.combat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.java.Log;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@Log
public class AttackResult {

    String playerName;
    String behemothName;
    String targetPartName;

    Integer oldHealth;
    Integer healthDamage;
    Integer newHealth;

    Integer oldPartHealth;
    Integer partDamage;
    Integer newPartHealth;

    Integer oldStaggerHealth;
    Integer staggerDamage;
    Integer newStaggerHealth;

    Integer oldWoundHealth;
    Integer woundDamage;
    Integer newWoundHealth;

    public AttackResult() {
    }

    /**
     * New instance. Prefil with "before" values.
     *
     * @param attack the attack.
     */
    public AttackResult(Attack attack) {
        this.playerName = attack.getPlayer().getName();
        this.behemothName = attack.getBehemoth().getName();
        this.targetPartName = attack.getBehemothPart().getHitzone().getName();
        this.oldHealth = attack.getBehemoth().getHealth();
        this.oldPartHealth = attack.getBehemothPart().getHealth();
        this.oldStaggerHealth = attack.getBehemoth().getStaggerHealth();
        this.oldWoundHealth = attack.getBehemothPart().getHealth();
    }

    @Override
    public String toString() {
        return "AttackResult{"
                + playerName + ","
                + behemothName + ","
                + targetPartName + ","
                + oldHealth + ","
                + healthDamage + ","
                + newHealth + ","
                + oldPartHealth + ","
                + partDamage + ","
                + newPartHealth + ","
                + oldStaggerHealth + ","
                + staggerDamage + ","
                + newStaggerHealth + ","
                + oldWoundHealth + ","
                + woundDamage + ","
                + newWoundHealth + "}";
    }

    /**
     * Combines 2 results, filling out null values. Does not sum anything.
     * @param other another attackResult.
     * @return this modified attackResult.
     */
    public AttackResult fillWith(AttackResult other) {
        playerName = oneOf(playerName, other.playerName);
        behemothName = oneOf(behemothName, other.behemothName);
        targetPartName = oneOf(targetPartName, other.targetPartName);
        oldHealth = oneOf(oldHealth, other.oldHealth);
        healthDamage = oneOf(healthDamage, other.healthDamage);
        newHealth = oneOf(newHealth, other.newHealth);
        oldPartHealth = oneOf(oldPartHealth, other.oldPartHealth);
        partDamage = oneOf(partDamage, other.partDamage);
        behemothName = oneOf(behemothName, other.behemothName);
        oldStaggerHealth = oneOf(oldStaggerHealth, other.oldStaggerHealth);
        staggerDamage = oneOf(staggerDamage, other.staggerDamage);
        newStaggerHealth = oneOf(newStaggerHealth, other.newStaggerHealth);
        oldWoundHealth = oneOf(oldWoundHealth, other.oldWoundHealth);
        woundDamage = oneOf(woundDamage, other.woundDamage);
        newWoundHealth = oneOf(newWoundHealth, other.newWoundHealth);
        return this;
    }

    private static String oneOf(String mine, String other) {
        return (mine != null) ? mine : other;
    }

    private static Integer oneOf(Integer mine, Integer other) {
        return (mine != null) ? mine : other;
    }
}


