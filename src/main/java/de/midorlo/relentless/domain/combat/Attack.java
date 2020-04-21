package de.midorlo.relentless.domain.combat;

import de.midorlo.relentless.domain.WeaponAttack;
import de.midorlo.relentless.domain.behemoth.Behemoth;
import de.midorlo.relentless.domain.behemoth.BehemothPart;
import de.midorlo.relentless.domain.behemoth.Hitzone;
import de.midorlo.relentless.domain.mutators.IAttackModifier;
import de.midorlo.relentless.domain.player.Player;
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
    WeaponAttack weaponAttack;
    Behemoth behemoth;
    BehemothPart behemothPart;
    Damage damage;

    private Attack() {
    }

    private Attack consume(IAttackModifier modifier) {
        return modifier.accountFor(this);
    }

    public AttackResult doAttack() {
        setDamage(getDamage().fixate());
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
        bMove targetPart(Hitzone hitzone);
    }

    public interface bMove {
        bFin attackMove(WeaponAttack weaponAttack);
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
                    .filter(behemothPart1 -> behemothPart1.getType().equals(hitzone))
                    .findFirst().orElse(null));
        }

        @Override
        public bFin attackMove(WeaponAttack weaponAttack) {
            attack.setWeaponAttack(weaponAttack);
            return this;
        }

        @Override
        public Attack build() {
            attack.setDamage(new Damage());
            return attack
                    .consume(attack.getPlayer().getLoadout().getWeapon())
                    .consume(attack.getWeaponAttack())
                    .consume(attack.getBehemoth());
        }
    }
}
