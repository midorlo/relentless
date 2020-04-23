package de.midorlo.relentless.domain.behemoth;

import de.midorlo.relentless.domain.combat.Attack;
import de.midorlo.relentless.domain.combat.AttackResult;
import de.midorlo.relentless.domain.combat.IAttackConsumer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.java.Log;

import javax.persistence.*;

import static de.midorlo.relentless.util.NumericUtils.round;

@Data
@Log
@Entity
@EqualsAndHashCode(exclude = "id")
public class BehemothPart implements IAttackConsumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    Hitzone hitzone;

    Integer health;
    Integer woundHealth;

    public BehemothPart() {
    }

    public BehemothPart(Hitzone hitzone, Integer health) {
        this.hitzone = hitzone;
        this.health = health;
        this.woundHealth = health;
    }

    public boolean isWounded() {
        return woundHealth <= 0;
    }

    @Override
    public AttackResult consume(Attack attack) {

        AttackResult result = new AttackResult();

        result.setOldPartHealth(getHealth());
        result.setOldWoundHealth(getWoundHealth());

        Integer partDamage = round(attack.getAttackDamage().getPartDamageNetto());
        Integer woundDamage = round(attack.getAttackDamage().getWoundDamageNetto());

        result.setPartDamage(partDamage);
        result.setWoundDamage(woundDamage);

        Integer newPartHealth = getHealth() - partDamage;
        Integer newWoundHealth = getWoundHealth() - woundDamage;

        result.setNewPartHealth(newPartHealth);
        result.setNewWoundHealth(newWoundHealth);

        setHealth(newPartHealth);
        setWoundHealth(newWoundHealth);

        //todo handle bonus attacks, with their hitzone displacements
        return result;
    }

    @Override
    public Attack accountFor(Attack attack) {
        return attack;
    }
}
