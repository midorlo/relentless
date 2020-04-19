package de.midorlo.relentless.domain.combat;

import de.midorlo.relentless.domain.behemoth.BehemothPart;
import de.midorlo.relentless.domain.behemoth.BehemothPartType;
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
    Integer damage;
    Integer hits;
    DamageType type;
    boolean isCleave;

    private List<BonusWeaponAttack> bonusWeaponAttacks = new ArrayList<>();

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
        accountForDamageType(attack);
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

    private void accountForDamageType(Attack attack) {
        DamageType damageType = getType();
        Damage damage = new Damage();

        if (damageType.equals(DamageType.Slashing)) {
            damage.setHealthDamageFactor(1d);
            damage.setPartDamageFactor(1d);
            damage.setStaggerDamageFactor(1d);
            damage.setWoundDamageFactor(0d);
        }

        if (damageType.equals(DamageType.Piercing)) {
            damage.setHealthDamageFactor(1d);
            damage.setPartDamageFactor(0.75d);
            damage.setStaggerDamageFactor(0d);
            damage.setWoundDamageFactor(1d);
        }

        if (damageType.equals(DamageType.Special)) {
            damage.setHealthDamageFactor(1d);
            damage.setPartDamageFactor(1d);
            damage.setStaggerDamageFactor(0d);
            damage.setWoundDamageFactor(0d);
        }

        if (damageType.equals(DamageType.Blunt)) {
            damage.setHealthDamageFactor(1d);
            damage.setPartDamageFactor(1d);
            damage.setStaggerDamageFactor(1d + (1d/3d));
            damage.setWoundDamageFactor(0d);
        }
        attack.getDamage().add(damage);
    }

    private void accountForBehemothPart(Attack attack) {
        Damage damage = new Damage();
        BehemothPart part = attack.getBehemothPart();
        DamageType damageType = getType();
                if (part.isWounded()) {
            switch (damageType) {
                case Blunt   :
                case Piercing:
                case Special : damage.setPartDamageFactor(0.25d); break;
                case Slashing: damage.setPartDamageFactor(0.5d); break;
            }
        }
        if (damageType.equals(DamageType.Piercing)
        && (part.getType().equals(BehemothPartType.Head) || part.getType().equals(BehemothPartType.Horn))) {
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
        aDamage type(DamageType type);
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
        public aDamage type(DamageType type) {
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
                move.getBonusWeaponAttacks().add(new BonusWeaponAttack(move));
            }
            return move;
        }
    }
}
