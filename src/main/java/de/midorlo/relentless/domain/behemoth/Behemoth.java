package de.midorlo.relentless.domain.behemoth;

import de.midorlo.relentless.domain.Element;
import de.midorlo.relentless.domain.combat.Attack;
import de.midorlo.relentless.domain.combat.AttackResult;
import de.midorlo.relentless.domain.IAttackModifier;
import lombok.Data;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Player's Enemy.
 */
@Data
@Log
@Entity
public class Behemoth implements IAttackModifier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;
    Element element;
    Integer thread;
    Integer health;
    Integer staggerHealth;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    List<BehemothPart> behemothParts = new ArrayList<>();

    public Integer getPower() {
        int power = 0;
        //@formatter:off
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
        //@formatter:on
        return power;
    }

    @Override
    public Attack accountFor(Attack attack) {
        return attack;
    }

    static Integer round(Double v) {
        return (int) Math.round(v);
    }

    public AttackResult consume(Attack attack) {

        BehemothPart part = attack.getBehemothPart();

        Integer healthDamage = round(attack.getDamage().getHealthDamageNetto());
        Integer partDamage = round(attack.getDamage().getPartDamageNetto());
        Integer staggerDamage = round(attack.getDamage().getStaggerDamageNetto());
        Integer woundDamage = round(attack.getDamage().getWoundDamageNetto());

        Integer healthOld = getHealth();
        Integer healthNew = (int) Math.round(healthOld - healthDamage);
        Integer parthHealthOld = attack.getBehemothPart().getHealth();
        Integer partHealthNew = parthHealthOld - partDamage;

        Integer woundHealthOld = part.getMarginWounded();
        Integer woundHealthNew = woundHealthOld - woundDamage;

        Integer staggerHealthOld = getStaggerHealth();
        Integer staggerHealthNew = (int) Math.round(getStaggerHealth() - staggerDamage); //todo handle stagger

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
