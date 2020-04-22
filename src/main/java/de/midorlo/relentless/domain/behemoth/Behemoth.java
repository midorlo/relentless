package de.midorlo.relentless.domain.behemoth;

import de.midorlo.relentless.domain.Element;
import de.midorlo.relentless.domain.attack.IAttackConsumer;
import de.midorlo.relentless.domain.attack.Attack;
import de.midorlo.relentless.domain.attack.AttackResult;
import lombok.Data;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static de.midorlo.relentless.util.NumericUtils.round;

/**
 * Represents a Player's Enemy.
 */
@Data
@Log
@Entity
public class Behemoth implements IAttackConsumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    List<BehemothPart> behemothParts = new ArrayList<>();

    private String name;
    private Element element;
    private Integer thread;
    private Integer health;
    private Integer staggerHealth;

    @Override
    public Attack accountFor(Attack attack) {
        return attack;
    }

    @Override
    public AttackResult consume(Attack attack) {

        AttackResult result = new AttackResult(attack);

        Integer healthDamage = round(attack.getAttackDamage().getHealthDamageNetto());
        Integer newHealth = Math.round(getHealth() - healthDamage);
        result.setHealthDamage(healthDamage);
        result.setNewHealth(newHealth);
        setHealth(newHealth);

        Integer staggerDamage = round(attack.getAttackDamage().getStaggerDamageNetto());
        Integer newStaggerHealth = Math.round(getStaggerHealth() - staggerDamage); //todo handle stagger
        result.setStaggerDamage(staggerDamage);
        result.setNewStaggerHealth(newStaggerHealth);
        setStaggerHealth(newStaggerHealth);

        for (BehemothPart behemothPart : getBehemothParts()) {
            if (attack.getBehemothPart().equals(behemothPart)) {
                AttackResult attackResult = behemothPart.consume(attack);
                result = result.fillWith(attackResult);
                break;
            }
        }

        return result;
    }

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
}
