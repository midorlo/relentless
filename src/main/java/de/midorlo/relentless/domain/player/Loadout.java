package de.midorlo.relentless.domain.player;

import de.midorlo.relentless.domain.items.Armor;
import de.midorlo.relentless.domain.items.Weapon;
import de.midorlo.relentless.domain.items.Gear;
import lombok.Data;
import lombok.extern.java.Log;

@Data
@Log
public class Loadout {

    Armor head;
    Armor chest;
    Armor arms;
    Armor legs;
    Gear lantern;
    Weapon weapon;
}
