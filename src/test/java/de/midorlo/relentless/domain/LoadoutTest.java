package de.midorlo.relentless.domain;

import de.midorlo.relentless.domain.player.Loadout;
import de.midorlo.relentless.domain.player.Player;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoadoutTest {

    @Test
    public void testGetPerks() {
        Hunt hunt = MockedRepository.mockHunt();
        Player player = hunt.getPlayers().get(0);
        Loadout loadout = player.getLoadout();


    }
}