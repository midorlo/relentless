package de.midorlo.relentless.domain;

import de.midorlo.relentless.domain.behemoth.Behemoth;
import de.midorlo.relentless.domain.behemoth.BehemothPart;
import de.midorlo.relentless.domain.behemoth.Hitzone;
import de.midorlo.relentless.domain.combat.AttackType;
import de.midorlo.relentless.domain.combat.Hunt;
import de.midorlo.relentless.domain.combat.Moveset;
import de.midorlo.relentless.domain.combat.WeaponAttack;
import de.midorlo.relentless.domain.gear.ItemType;
import de.midorlo.relentless.domain.gear.Weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockedRepository {


    public static Player mockPlayer() {
        Player player = new Player();
        player.setName("Midorlo");
        player.setLoadout(mockLoadout());
        return player;
    }

    public static Behemoth mockBehemoth() {
        Behemoth behemoth = new Behemoth();
        behemoth.setName("Shrike");
        behemoth.setHealth(30000);
        behemoth.setStaggerHealth(10000);
        behemoth.setElement(new Element("Neutral"));
        behemoth.setThread(5);
        behemoth.setBehemothParts(mockBehemothParts());
        return behemoth;
    }

    public static List<BehemothPart> mockBehemothParts() {
        return Arrays.asList(
                new BehemothPart(Hitzone.head, 1000),
                new BehemothPart(Hitzone.head, 1000),
                new BehemothPart(Hitzone.body, 1000));
    }

    public static Hitzone mockHitzone() {
        return new Hitzone("Head");
    }

    public static Hunt mockHunt() {
        Hunt hunt = new Hunt();
        hunt.getPlayers().add(mockPlayer());
        hunt.getBehemoths().add(mockBehemoth());
        return hunt;
    }

    public static Loadout mockLoadout() {
        Loadout loadout = new Loadout();
        loadout.setWeapon(mockWeapon());
        return loadout;
    }

    public static Weapon mockWeapon() {
        Weapon weapon = new Weapon();
        weapon.setName("Mana Sword");
        weapon.setDescription("It's something");
        weapon.setType(ItemType.Sword);
        weapon.setElement(new Element("Radiant"));
        weapon.setMovesets(mockMovesets());
        return weapon;
    }

    public static List<Moveset> mockMovesets() {
        ArrayList<Moveset> movesets = new ArrayList<>();
        movesets.add(mockMoveset());
        return movesets;
    }

    public static Moveset mockMoveset() {
        Moveset moveset = new Moveset();
        WeaponAttack weaponAttack = WeaponAttack.builder()
                .name("Bladed 1(L)")
                .type(new AttackType("Slashing"))
                .damage(60)
                .cleave(false)
                .bonusAttacks(3)
                .build();
        moveset.getAttacks().add(weaponAttack);
        return moveset;
    }
}





















