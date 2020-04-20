package de.midorlo.relentless.domain;

import de.midorlo.relentless.domain.behemoth.Behemoth;
import de.midorlo.relentless.domain.behemoth.BehemothPart;
import de.midorlo.relentless.domain.behemoth.Hitzone;
import de.midorlo.relentless.domain.combat.AttackType;
import de.midorlo.relentless.domain.items.Weapon;
import de.midorlo.relentless.domain.items.ItemType;
import de.midorlo.relentless.domain.player.Loadout;
import de.midorlo.relentless.domain.player.Player;

import java.util.ArrayList;
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
        behemoth.setName("Meh-Kel, Holder of the Dark Triangle");
        behemoth.setHealth(2000d);
        behemoth.setStaggerHealth(2000d);
        behemoth.setElement(Element.Umbral);
        behemoth.setThread(24);
        BehemothPart behemothPart = new BehemothPart(mockHitzone(), 1000d);
        behemothPart.setHealthWound(2000d);
        behemoth.getBehemothParts().add(behemothPart);
        return behemoth;
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
        weapon.setAttackType(AttackType.Slashing);
        weapon.setElement(Element.Radiant);
        weapon.setMoveSets(mockMovesets());
        return weapon;
    }

    public static List<List<WeaponAttack>> mockMovesets() {
        List<List<WeaponAttack>> moveSets = new ArrayList<>();
        moveSets.add(mockMoveset());
        return moveSets;
    }

    public static ArrayList<WeaponAttack> mockMoveset() {
        ArrayList<WeaponAttack> moveset = new ArrayList<>();
        WeaponAttack move = WeaponAttack.builder()
                .name("Bladed 1(L)")
                .type(AttackType.Slashing)
                .damage(60)
                .cleave(false)
                .bonusAttacks(3)
                .build();
        moveset.add(move);
        return moveset;
    }
}





















