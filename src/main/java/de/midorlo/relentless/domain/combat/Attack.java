package de.midorlo.relentless.domain.combat;

import de.midorlo.relentless.domain.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;

@Data
@ToString
@EqualsAndHashCode
@Log
public class Attack {

    Player player;
    Skill skill;
    Behemoth behemoth;
    BehemothPart behemothPart;
    AttackDamage attackDamage;

    private Attack() {
    }

    private Attack consume(IAttackModifier modifier) {
        return modifier.accountFor(this);
    }

    public AttackResult doAttack() {
        setAttackDamage(getAttackDamage().fixate());
        return behemoth.consume(this);
    }

    //<editor-fold desc="Builder" //>
    public static bPlayer builder() {
        return new AttackBuilder();
    }

    public interface bPlayer {
        bBehemoth player(Player player);
    }

    public interface bBehemoth {
        bPart behemoth(Behemoth behemoth);
    }

    public interface bPart {
        bMove targetPart(Hitzone hitzone);
    }

    public interface bMove {
        bFin attackMove(Skill skill);
    }

    public interface bFin {
        Attack build();
    }

    private static class AttackBuilder implements bPlayer, bBehemoth, bPart, bMove, bFin {

        private final Attack attack = new Attack();

        private AttackBuilder() {
        }

        @Override
        public bBehemoth player(Player player) {
            attack.setPlayer(player);
            return this;
        }

        @Override
        public bPart behemoth(Behemoth behemoth) {
            attack.setBehemoth(behemoth);
            return this;
        }

        private bMove targetPart(BehemothPart behemothPart) {
            attack.setBehemothPart(behemothPart);
            return this;
        }

        @Override
        public bMove targetPart(Hitzone hitzone) {
            return targetPart(attack.getBehemoth().getBehemothParts().stream()
                    .filter(behemothPart1 -> behemothPart1.getHitzone().equals(hitzone))
                    .findFirst().orElse(null));
        }

        @Override
        public bFin attackMove(Skill skill) {
            attack.setSkill(skill);
            return this;
        }

        @Override
        public Attack build() {
            attack.setAttackDamage(new AttackDamage());
            return attack
                    .consume(attack.getPlayer().getLoadout().getWeapon())
                    .consume(attack.getSkill())
                    .consume(attack.getBehemoth());
        }
    }
    //</editor-fold>
}
