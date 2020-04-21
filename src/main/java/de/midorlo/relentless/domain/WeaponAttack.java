package de.midorlo.relentless.domain;

import de.midorlo.relentless.domain.behemoth.BehemothPart;
import de.midorlo.relentless.domain.behemoth.Hitzone;
import de.midorlo.relentless.domain.combat.Attack;
import de.midorlo.relentless.domain.combat.AttackType;
import de.midorlo.relentless.domain.combat.Damage;
import de.midorlo.relentless.domain.combat.WeaponExtraAttack;
import de.midorlo.relentless.domain.mutators.IAttackModifier;
import lombok.Data;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * A Weapon's Attack.
 */
@Data
@Log
public class WeaponAttack implements IAttackModifier {

    String name;
    String description;
    Integer damage;
    Integer hits;
    AttackType type;
    boolean isCleave;

    private List<WeaponExtraAttack> weaponExtraAttacks = new ArrayList<>();

    public static aName builder() {
        return new WeaponAttackBuilder();
    }

    /**
     * Accounts for:
     * - healthDamage
     * - woundDamage
     * - PartDamage
     * - StaggerDamage
     *
     * @param attack the attack.
     * @return modified attack.
     */
    @Override
    public Attack accountFor(Attack attack) {
        accountForWeaponAttack(attack);
        accountForAttackType(attack);
        accountForBehemothPart(attack);
        return attack;
    }

    private void accountForWeaponAttack(Attack attack) {
        Damage damage = new Damage();
        damage.setHealthDamage(getDamage().doubleValue());
        damage.setWoundDamage(getDamage().doubleValue());
        damage.setStaggerDamage(getDamage().doubleValue());
        damage.setPartDamage(getDamage().doubleValue());
        attack.getDamage().add(damage);
    }

    private void accountForAttackType(Attack attack) {
        AttackType attackType = getType();
        Damage damage = new Damage();

        if (attackType.equals(AttackType.Slashing)) {
            damage.setHealthDamageFactor(1d);
            damage.setPartDamageFactor(1d);
            damage.setStaggerDamageFactor(1d);
            damage.setWoundDamageFactor(0d);
        }

        if (attackType.equals(AttackType.Piercing)) {
            damage.setHealthDamageFactor(1d);
            damage.setPartDamageFactor(0.75d);
            damage.setStaggerDamageFactor(0d);
            damage.setWoundDamageFactor(1d);
        }

        if (attackType.equals(AttackType.Special)) {
            damage.setHealthDamageFactor(1d);
            damage.setPartDamageFactor(1d);
            damage.setStaggerDamageFactor(0d);
            damage.setWoundDamageFactor(0d);
        }

        if (attackType.equals(AttackType.Blunt)) {
            damage.setHealthDamageFactor(1d);
            damage.setPartDamageFactor(1d);
            damage.setStaggerDamageFactor(1d + (1d / 3d));
            damage.setWoundDamageFactor(0d);
        }
        attack.getDamage().add(damage);
    }

    private void accountForBehemothPart(Attack attack) {
        Damage damage = new Damage();
        BehemothPart part = attack.getBehemothPart();
        AttackType attackType = getType();
        if (part.isWounded()) {
            if (AttackType.Blunt.equals(attackType) || AttackType.Piercing.equals(attackType) || AttackType.Special.equals(attackType)) {
                damage.setPartDamageFactor(0.25d);
            } else if (AttackType.Slashing.equals(attackType)) {
                damage.setPartDamageFactor(0.5d);
            }
        }
        if (attackType.equals(AttackType.Piercing)
                && (part.getType().equals(Hitzone.head) || part.getType().equals(Hitzone.horn))) {
            damage.setPartDamageFactor(damage.getPartDamageFactor() + 0.25);
        }
        attack.getDamage().add(damage);
    }

//Builder
//----------------------------------------------------------------------------------------------------------------------

    public interface aName {
        aType name(String name);
    }

    public interface aType {
        aDamage type(AttackType type);
    }

    public interface aDamage {
        aRdy damage(Integer damage);
    }

    public interface aRdy {
        aRdy bonusAttacks(Integer bonusAttacks);

        aRdy cleave(boolean isCleave);

        WeaponAttack build();
    }

    static class WeaponAttackBuilder implements aType, aDamage, aName, aRdy {
        WeaponAttack move = new WeaponAttack();

        private WeaponAttackBuilder() {
        }

        @Override
        public aType name(String name) {
            move.setName(name);
            return this;
        }

        @Override
        public aDamage type(AttackType type) {
            move.setType(type);
            return this;
        }

        @Override
        public aRdy damage(Integer damage) {
            move.setDamage(damage);
            return this;
        }

        @Override
        public aRdy bonusAttacks(Integer bonusAttacks) {
            move.setHits(bonusAttacks);
            return this;
        }

        @Override
        public aRdy cleave(boolean isCleave) {
            move.setCleave(isCleave);
            return this;
        }

        @Override
        public WeaponAttack build() {
            for (int i = 0; i < move.hits; i++) {
                move.getWeaponExtraAttacks().add(new WeaponExtraAttack(move));
            }
            return move;
        }
    }
}
