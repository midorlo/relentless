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
        return "AttackResult { playerName='" + playerName + ", behemothName='" + behemothName + '\'' + ", targetPart=" +
                targetPart + System.lineSeparator()
                + "\t" +
                ", health=" + oldHealth + "-> " + newHealth + "(" + healthDamage + ")"
                + System.lineSeparator()
                + "\t" +
                ", part=" + oldPartHealth + "-> " + newPartHealth + "(" + partDamage + ")" + System.lineSeparator() + "\t"
                + "\t" +
                ", stagger=" + oldStaggerHealth + "-> " + newStaggerHealth + "(" + staggerDamage + ")" + System.lineSeparator() + "\t"
                + "\t" +
                ", wound=" + oldPartWoundHealth + "-> " + newPartWoundHealth + "(" + woundDamage + ")" + System.lineSeparator() + "\t" +
                '}';
    }
}
