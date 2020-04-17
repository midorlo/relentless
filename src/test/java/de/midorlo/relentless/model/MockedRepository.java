package de.midorlo.relentless.model;

import de.midorlo.relentless.model.behemoth.Behemoth;
import de.midorlo.relentless.model.behemoth.BehemothPart;
import de.midorlo.relentless.model.behemoth.BehemothPartType;
import de.midorlo.relentless.model.combat.AttackMove;
import de.midorlo.relentless.model.combat.AttackType;
import de.midorlo.relentless.model.combat.Hunt;
import de.midorlo.relentless.model.items.Weapon;
import de.midorlo.relentless.model.items.WearableType;
import de.midorlo.relentless.model.player.Loadout;
import de.midorlo.relentless.model.player.Player;

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
        BehemothPart behemothPart = new BehemothPart(BehemothPartType.Head, 1000d);
        behemoth.getBehemothParts().add(behemothPart);
        return behemoth;
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
        weapon.setName("Inferno's Fangs");
        weapon.setElement(Element.Blaze);
        weapon.setType(WearableType.Chainblades);
        weapon.setMoveSets(mockMovesets());
        return weapon;
    }

    public static List<List<AttackMove>> mockMovesets() {
        List<List<AttackMove>> moveSets = new ArrayList<>();
        moveSets.add(mockMoveset());
        return moveSets;
    }

    public static ArrayList<AttackMove> mockMoveset() {
        ArrayList<AttackMove> moveset = new ArrayList<>();
        AttackMove move = AttackMove.builder()
                .name("Bladed 1(L)")
                .type(AttackType.Slash)
                .damage(60d)
                .bonusAttacks(1d, false)
                .build();
        moveset.add(move);
        return moveset;
    }
}





















