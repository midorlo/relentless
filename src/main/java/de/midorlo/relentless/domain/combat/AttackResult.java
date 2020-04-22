package de.midorlo.relentless.domain.combat;

import de.midorlo.relentless.domain.behemoth.Hitzone;
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
    Hitzone targetPart;

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


    @Override
    public String toString() {
        return "AttackResult{" + System.lineSeparator() +
                "\tattack='" + playerName + '\'' +
                ",'" + behemothName + '\'' +
                "," + targetPart.getName() + System.lineSeparator() +
                "\thealth=" + oldHealth +
                ", " + healthDamage +
                ", " + newHealth + System.lineSeparator() +
                "\tpart=" + oldPartHealth +
                ", " + partDamage +
                ", " + newPartHealth + System.lineSeparator() +
                "\tstagger=" + oldStaggerHealth +
                ", " + staggerDamage +
                ", " + newStaggerHealth + System.lineSeparator() +
                "\twound=" + oldPartWoundHealth +
                ", " + woundDamage +
                ", " + newPartWoundHealth + System.lineSeparator() +
                '}';
    }
}


