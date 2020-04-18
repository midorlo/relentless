package de.midorlo.relentless.domain.combat;

import de.midorlo.relentless.domain.behemoth.BehemothPartType;
import de.midorlo.relentless.domain.mutators.IAttackModifier;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * A Weapon's Attack.
 */
@Data
@ToString
@EqualsAndHashCode
@Log
public class WeaponAttack implements IAttackModifier {

    String name;
    DamageType damageType;
    Damage damage = new Damage();
    Damage baseline = new Damage();
    List<BonusWeaponAttack> bonusAttacks;

    protected WeaponAttack() {
    }

    public static aName builder() {
        return new WeaponAttackBuilder();
    }

    /**
     * Will add the Weapon's Base damage.
     * //todo 2tes damage object: damage und damageBaseline: chainblades machen zb 50% mehr dmg an wounded parts
     *
     * @param attack attack.
     * @return processed attack.
     */
    @Override
    public Attack accountFor(Attack attack) {
        //Account for DamageType


        //Apply the Damage
        attack.getDamage().add(this.damage);

        /* Add contextual Values to the Attack */
        //Blunt:+25% Part damage vs wounds
        Damage bonus = new Damage();
        if (((attack.getWeaponAttack().getDamageType().equals(DamageType.Blunt)
                || (attack.getWeaponAttack().getDamageType().equals(DamageType.Piercing)))
                || (attack.getWeaponAttack().getDamageType().equals(DamageType.Special)))
                && attack.getBehemothPart().isWounded()) {
            bonus.setPartDamageFactor(0.25d);
        }
        if (attack.getWeaponAttack().getDamageType().equals(DamageType.Slashing)
                && attack.getBehemothPart().isWounded()) {
            bonus.setPartDamageFactor(0.5d);
        }

        if (attack.getWeaponAttack().getDamageType().equals(DamageType.Slashing)
                && (attack.getBehemothPart().getType().equals(BehemothPartType.Head)
                || attack.getBehemothPart().getType().equals(BehemothPartType.Horn))) {
            bonus.setPartDamageFactor(bonus.getPartDamageFactor() + 0.5d);
        }
        if (attack.getWeaponAttack().getDamageType().equals(DamageType.Piercing)
                && (attack.getBehemothPart().getType().equals(BehemothPartType.Head)
                || attack.getBehemothPart().getType().equals(BehemothPartType.Horn))) {
            bonus.setPartDamageFactor(bonus.getPartDamageFactor() + 0.25);
        }
        attack.getDamage().add(bonus);
        return attack;
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
        aBonusAttacks damage(Double damage);
    }

    public interface aBonusAttacks {
        aRdy bonusAttacks(Double bonusAttacks, Boolean isCleave);
    }

    public interface aRdy {
        WeaponAttack build();
    }

    static class WeaponAttackBuilder implements aType, aBonusAttacks, aDamage, aName, aRdy {
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
            move.setDamageType(type);
            return this;
        }

        @Override
        public aBonusAttacks damage(Double healthDamage) {
            move.getDamage().setHealthDamage(healthDamage);
            return this;
        }

        @Override
        public aRdy bonusAttacks(Double bonusAttacks, Boolean isCleave) {
            List<BonusWeaponAttack> bonusAttackMoves = new ArrayList<>();
            for (int i = 0; i < bonusAttacks; i++) {
                BonusWeaponAttack bonusAttackMove = new BonusWeaponAttack(move, isCleave);
                bonusAttackMoves.add(bonusAttackMove);
            }
            move.setBonusAttacks(bonusAttackMoves);
            return this;
        }

        @Override
        public WeaponAttack build() {
            initDamageBaseline();
            return move;
        }

        /**
         * Creates a Damage Object regarding only the WeaponAttack.
         * Used to get sane initial values, especially for multiplicators.
         */
        protected void initDamageBaseline() {
            Damage baseline = move.getDamage();
            double tPartDmg = 0d, tWoundDmg = 0d, tStaggerDmg = 0d;
            switch (move.getDamageType()) {
                case Slashing:
                    tPartDmg = 1d;
                    tStaggerDmg = 1d;
                    tWoundDmg = 0d;
                    break;
                case Special:
                case Bullet:
                    tPartDmg = 1d;
                    tStaggerDmg = 0d;
                    tWoundDmg = 0d;
                    break;
                case Blunt:
                    tPartDmg = 1d;
                    tStaggerDmg = 1d + (1d / 3d);
                    tWoundDmg = 0d;
                    break;
                case Piercing:
                    tPartDmg = 0.75d;
                    tStaggerDmg = 0d;
                    tWoundDmg = 1d;
                    break;
                default:
                    log.warning(move.getDamageType().toString());
            }
            baseline.setPartDamageFactor(tPartDmg);
            baseline.setStaggerDamage(tStaggerDmg);
            baseline.setWoundDamageFactor(tWoundDmg);
            baseline.setPartDamage(
                    baseline.getPartDamageFactor() * baseline.getHealthDamage()
            );
            baseline.setStaggerDamage(
                    baseline.getStaggerDamageFactor() * baseline.getHealthDamage()
            );
            baseline.setWoundDamage(
                    baseline.getWoundDamageFactor() * baseline.getHealthDamage()
            );
            baseline.setHealthDamageFactor(1d);
            baseline.setAttackTypeFactor(1d);
            baseline.setCritFactor(1d);
            baseline.setPowerFactor(1d);
            baseline.setAcidicFactor(1d);
            baseline.setMarksmanFactor(1d);
            baseline.setHitzoneFactor(1d);
        }
    }
}
