package de.midorlo.relentless.domain.combat;

import de.midorlo.relentless.domain.behemoth.BehemothPartType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@Log
public class AttackMove implements IAttackModifier {

    String name;
    DamageType damageType;
    Damage damage = new Damage();
    Damage baseline = new Damage();
    List<BonusAttackMove> bonusAttacks;

    protected AttackMove() {
    }

    public static aName builder() {
        return new AttackMoveBuilder();
    }

    /**
     * Will add the Weapon's Base damage.
     * //todo 2tes damage object: damage und damageBaseline: chainblades machen zb 50% mehr dmg an wounded parts
     *
     * @param attack attack.
     * @return processed attack.
     */
    @Override
    public Attack modify(Attack attack) {
        /* Add initial Damage values to the Attack */
        Damage emptyDamage = attack.getDamage();
        emptyDamage.add(this.damage);
        /* Add contextual Values to the Attack */
        //Blunt:+25% Part damage vs wounds
        Damage bonus = new Damage();
        if (((attack.getAttackMove().getDamageType().equals(DamageType.Blunt)
                || (attack.getAttackMove().getDamageType().equals(DamageType.Piercing)))
                || (attack.getAttackMove().getDamageType().equals(DamageType.Special)))
                && attack.getBehemothPart().isWounded()) {
            bonus.setPartDamageMult(0.25d);
        }
        if (attack.getAttackMove().getDamageType().equals(DamageType.Slashing)
                && attack.getBehemothPart().isWounded()) {
            bonus.setPartDamageMult(0.5d);
        }

        if (attack.getAttackMove().getDamageType().equals(DamageType.Slashing)
                && (attack.getBehemothPart().getType().equals(BehemothPartType.Head)
                || attack.getBehemothPart().getType().equals(BehemothPartType.Horn))) {
            bonus.setPartDamageMult(bonus.getPartDamageMult() + 0.5d);
        }
        if (attack.getAttackMove().getDamageType().equals(DamageType.Piercing)
                && (attack.getBehemothPart().getType().equals(BehemothPartType.Head)
                || attack.getBehemothPart().getType().equals(BehemothPartType.Horn))) {
            bonus.setPartDamageMult(bonus.getPartDamageMult() + 0.25);
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
        AttackMove build();
    }

    static class AttackMoveBuilder implements aType, aBonusAttacks, aDamage, aName, aRdy {
        AttackMove move = new AttackMove();

        private AttackMoveBuilder() {
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
            move.getDamage().setHealthDamageFlat(healthDamage);
            return this;
        }

        @Override
        public aRdy bonusAttacks(Double bonusAttacks, Boolean isCleave) {
            List<BonusAttackMove> bonusAttackMoves = new ArrayList<>();
            for (int i = 0; i < bonusAttacks; i++) {
                BonusAttackMove bonusAttackMove = new BonusAttackMove(move, isCleave);
                bonusAttackMoves.add(bonusAttackMove);
            }
            move.setBonusAttacks(bonusAttackMoves);
            return this;
        }

        @Override
        public AttackMove build() {
            initDamageBaseline();
            return move;
        }

        /**
         * Creates a Damage Object regarding only the AttackMove.
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
            baseline.setPartDamageMult(tPartDmg);
            baseline.setStaggerDamageFlat(tStaggerDmg);
            baseline.setWoundDamageMult(tWoundDmg);
            baseline.setParthDamageFlat(
                    baseline.getPartDamageMult() * baseline.getHealthDamageFlat()
            );
            baseline.setStaggerDamageFlat(
                    baseline.getStaggerDamageMult() * baseline.getHealthDamageFlat()
            );
            baseline.setWoundDamageFlat(
                    baseline.getWoundDamageMult() * baseline.getHealthDamageFlat()
            );
            baseline.setHealthDamageMult(1d);
            baseline.setAttackTypeMult(1d);
            baseline.setCritMult(1d);
            baseline.setPowerMult(1d);
            baseline.setAcidicMult(1d);
            baseline.setMarksmanMult(1d);
            baseline.setHitzoneMult(1d);
        }
    }
}
