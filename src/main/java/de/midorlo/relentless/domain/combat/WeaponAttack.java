package de.midorlo.relentless.domain.combat;

import de.midorlo.relentless.domain.behemoth.BehemothPart;
import de.midorlo.relentless.domain.behemoth.Hitzone;
import lombok.Data;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A Weapon's Attack.
 */
@Data
@Log
@Entity
public class WeaponAttack implements IAttackModifier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;
    String description;
    Integer damage;
    Integer hits;

    @OneToOne
    AttackType type;

    boolean isCleave;

    private transient List<WeaponExtraAttack> weaponExtraAttacks = new ArrayList<>();

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
        AttackDamage attackDamage = new AttackDamage();
        attackDamage.setHealthDamage(getDamage().doubleValue());
        attackDamage.setWoundDamage(getDamage().doubleValue());
        attackDamage.setStaggerDamage(getDamage().doubleValue());
        attackDamage.setPartDamage(getDamage().doubleValue());
        attack.getAttackDamage().add(attackDamage);
    }

    private void accountForAttackType(Attack attack) {
        AttackType attackType = getType();
        AttackDamage attackDamage = new AttackDamage();

        if (attackType.getName().contentEquals("Slashing")) {
            attackDamage.setHealthDamageFactor(1d);
            attackDamage.setPartDamageFactor(1d);
            attackDamage.setStaggerDamageFactor(1d);
            attackDamage.setWoundDamageFactor(0d);
        }

        if (attackType.getName().contentEquals("Piercing")) {
            attackDamage.setHealthDamageFactor(1d);
            attackDamage.setPartDamageFactor(0.75d);
            attackDamage.setStaggerDamageFactor(0d);
            attackDamage.setWoundDamageFactor(1d);
        }

        if (attackType.getName().contentEquals("Special")) {
            attackDamage.setHealthDamageFactor(1d);
            attackDamage.setPartDamageFactor(1d);
            attackDamage.setStaggerDamageFactor(0d);
            attackDamage.setWoundDamageFactor(0d);
        }

        if (attackType.getName().contentEquals("Blunt")) {
            attackDamage.setHealthDamageFactor(1d);
            attackDamage.setPartDamageFactor(1d);
            attackDamage.setStaggerDamageFactor(1d + (1d / 3d));
            attackDamage.setWoundDamageFactor(0d);
        }
        attack.getAttackDamage().add(attackDamage);
    }

    private void accountForBehemothPart(Attack attack) {
        AttackDamage attackDamage = new AttackDamage();
        BehemothPart part = attack.getBehemothPart();
        AttackType attackType = getType();
        if (part.isWounded()) {
            if ("Blunt".contentEquals(attackType.getName()) || "Piercing".contentEquals(attackType.getName()) || "Special".contentEquals(attackType.getName())) {
                attackDamage.setPartDamageFactor(0.25d);
            } else if ("Slashing".contentEquals(attackType.getName())) {
                attackDamage.setPartDamageFactor(0.5d);
            }
        }
        if (attackType.getName().contentEquals("Piercing")
                && (part.getHitzone().equals(Hitzone.head) || part.getHitzone().equals(Hitzone.horn))) {
            attackDamage.setPartDamageFactor(attackDamage.getPartDamageFactor() + 0.25);
        }
        attack.getAttackDamage().add(attackDamage);
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
