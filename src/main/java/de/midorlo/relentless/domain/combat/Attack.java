package de.midorlo.relentless.domain.combat;

import de.midorlo.relentless.domain.behemoth.Behemoth;
import de.midorlo.relentless.domain.behemoth.BehemothPart;
import de.midorlo.relentless.domain.behemoth.BehemothPartType;
import de.midorlo.relentless.domain.player.Player;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@Log
public class Attack {

    Player player;
    AttackMove attackMove;
    Behemoth behemoth;
    BehemothPart behemothPart;
    Damage damage;

    private Attack() {}

    public AttackResult finish() {
        return behemoth.consume(this);
    }

//----------------------------------------------------------------------------------------------------------------------

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
        bMove targetPart(BehemothPartType behemothPartType);
    }

    public interface bMove {
        bFin attackMove(AttackMove attackMove);
    }

    public interface bFin {
        Attack build();
    }

    private static class AttackBuilder implements bPlayer, bBehemoth, bPart, bMove, bFin {

        private Attack attack = new Attack();

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
        public bMove targetPart(BehemothPartType behemothPartType) {
            return targetPart(attack.getBehemoth().getBehemothParts().stream()
                    .filter(behemothPart1 -> behemothPart1.getType().equals(behemothPartType))
                    .findFirst().orElse(null));
        }

        @Override
        public bFin attackMove(AttackMove attackMove) {
            attack.setAttackMove(attackMove);
            return this;
        }

        @Override
        public Attack build() {
            //Get a damage baseline from the weapon swing
            attack.setDamage(new Damage());
            attack = attack.getAttackMove().modify(attack);
            //Get Armor + Weapon Mutators (Cells, Perks)
            List<IAttackModifier> modifiers = attack.getPlayer().getModifiers();
            for (IAttackModifier modifier : modifiers) {
                attack = modifier.modify(attack);
            }
            //Get behemoth stuff (like skarns special)
            List<IAttackModifier> behemothModifiers = attack.getBehemoth().getModifiers();
            for (IAttackModifier modifier : behemothModifiers) {
                attack = modifier.modify(attack);
            }
            return attack;
        }
    }
}