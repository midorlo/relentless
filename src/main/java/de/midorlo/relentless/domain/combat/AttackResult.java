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

    Integer oldPartWoundHealth;
    Integer woundDamage;
    Integer newPartWoundHealth;

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
        this.oldPartWoundHealth = attack.getBehemothPart().getHealth();
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
                + oldPartWoundHealth + ","
                + woundDamage + ","
                + newPartWoundHealth + "}";
    }
}


