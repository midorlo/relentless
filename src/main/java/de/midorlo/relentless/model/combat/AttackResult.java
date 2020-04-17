package de.midorlo.relentless.model.combat;

import de.midorlo.relentless.model.behemoth.BehemothPartType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@Log
public class AttackResult {

    String playerName;
    String behemothName;
    BehemothPartType targetPart;

    Double oldHealth;
    Double healthDamage;
    Double newHealth;
    Double oldPartHealth;
    Double partDamage;
    Double newPartHealth;

    @Override
    public String toString() {
        return "AttackResult { playerName='" + playerName + ", behemothName='" + behemothName + '\'' + ", targetPart=" +
                targetPart + System.lineSeparator()
                + "\t" +
                    ", health=" + oldHealth + "-> " + newHealth + "(" + healthDamage + ")"
                + System.lineSeparator()
                + "\t" +
                    ", part=" + oldPartHealth + "-> " + newPartHealth + "(" + partDamage + ")" + System.lineSeparator() + "\t" +
                '}';
    }
}
