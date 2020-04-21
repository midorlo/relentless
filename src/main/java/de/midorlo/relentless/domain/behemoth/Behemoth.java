package de.midorlo.relentless.domain.behemoth;

import de.midorlo.relentless.domain.Element;
import de.midorlo.relentless.domain.combat.Attack;
import de.midorlo.relentless.domain.combat.AttackResult;
import de.midorlo.relentless.domain.mutators.IAttackModifier;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Player's Enemy.
 */
@Data
@ToString
@EqualsAndHashCode
@Log
public class Behemoth implements IAttackModifier {

    String name;
    Integer thread;

    Double health;
    Double staggerHealth;

    Element element;
    List<BehemothPart> behemothParts = new ArrayList<>();

    //@formatter:off
    public Integer getPower() {
        int power = 0;
        switch (thread) {
            case 30:power += 75;
            case 22:power += 75;
            case 19:power += 25;
            case 18:power += 50;
            case 17:power += 50;
            case 16:power += 25;
            case 15:power += 25;
            case 14:power += 25;
            case 13:power += 25;
            case 12:power += 25;
            case 11:power += 25;
            case 10:power += 25;
            case 9: power += 25;
            case 8: power += 25;
            case 7: power += 25;
            case 6: power += 50;
            case 5: power += 50;
            case 4: power += 50;
            case 3: power += 25;
            case 2: power += 25;
            case 1: power += 50;
        }
        return power;
    }
    //@formatter:on

    public void setHealth(Double health) {
        this.health = health; //todo impl
    }

    @Override
    public Attack accountFor(Attack attack) {
        return attack;
    }

    public AttackResult consume(Attack attack) {

        BehemothPart part = attack.getBehemothPart();

        Double healthDamage   = attack.getDamage().getHealthDamageNetto();
        Double partDamage     = attack.getDamage().getPartDamageNetto();
        Double staggerDamage  = attack.getDamage().getStaggerDamageNetto();
        Double woundDamage    = attack.getDamage().getWoundDamageNetto();

        Double healthOld      = getHealth();
        Double healthNew      = healthOld - healthDamage;
        Double parthHealthOld = attack.getBehemothPart().getHealth();
        Double partHealthNew  = parthHealthOld - partDamage;

        Double woundHealthOld = part.getMarginWounded();
        Double woundHealthNew = woundHealthOld - woundDamage;

        Double staggerHealthOld = getStaggerHealth();
        Double staggerHealthNew = getStaggerHealth() - staggerDamage; //todo handle stagger

        setHealth(healthNew);
        setStaggerHealth(staggerHealthNew);

        part.setHealth(partHealthNew);
        part.setMarginWounded(woundHealthNew);

        //todo handle bonus attacks, with their hitzone displacements

        return new AttackResult(
                attack.getPlayer().getName(),
                attack.getBehemoth().getName(),
                attack.getBehemothPart().getHitzone(),
                healthOld,
                healthDamage,
                healthNew,
                parthHealthOld,
                partDamage,
                partHealthNew,
                staggerHealthOld,
                staggerDamage,
                staggerHealthNew,
                woundHealthOld,
                woundDamage,
                woundHealthNew
        );
    }
}
