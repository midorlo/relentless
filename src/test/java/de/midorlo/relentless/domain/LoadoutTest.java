package de.midorlo.relentless.domain;

import de.midorlo.relentless.domain.combat.Hunt;
import org.testng.annotations.Test;

public class LoadoutTest {

    @Test
    public void testGetPerks() {
        Hunt hunt = MockedRepository.mockHunt();
        Player player = hunt.getPlayers().get(0);
        Loadout loadout = player.getLoadout();


    }
}