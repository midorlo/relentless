package de.midorlo.relentless.domain.behemoth;

import de.midorlo.relentless.domain.IAttackConsumer;
import de.midorlo.relentless.domain.combat.Attack;
import de.midorlo.relentless.domain.combat.AttackResult;
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
    Integer marginWounded;

    public BehemothPart() {
    }

    public BehemothPart(Hitzone hitzone, Integer health) {
        this.hitzone = hitzone;
        this.health = health;
        this.marginWounded = health;
    }

    public boolean isWounded() {
        return marginWounded <= 0;
    }

    @Override
    public AttackResult consume(Attack attack) {

        AttackResult result = new AttackResult();

        Integer partDamage     = round(attack.getDamage().getPartDamageNetto());
        Integer woundDamage    = round(attack.getDamage().getWoundDamageNetto());
        Integer parthHealthOld = getHealth();
        Integer woundHealthOld = getMarginWounded();
        Integer partHealthNew  = parthHealthOld - partDamage;
        Integer woundHealthNew = woundHealthOld - woundDamage;

        setHealth(partHealthNew);
        setMarginWounded(woundHealthNew);

        //todo handle bonus attacks, with their hitzone displacements
        return new AttackResult();
    }

    @Override
    public Attack accountFor(Attack attack) {
        return attack;
    }
}
