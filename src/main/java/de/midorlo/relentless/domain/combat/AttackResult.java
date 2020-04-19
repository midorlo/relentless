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

    Double oldHealth;
    Double healthDamage;
    Double newHealth;

    Double oldPartHealth;
    Double partDamage;
    Double newPartHealth;

    Double oldStaggerHealth;
    Double staggerDamage;
    Double newStaggerHealth;

    Double oldPartWoundHealth;
    Double woundDamage;
    Double newPartWoundHealth;

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


