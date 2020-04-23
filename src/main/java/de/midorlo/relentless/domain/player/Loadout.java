package de.midorlo.relentless.domain.player;

import de.midorlo.relentless.domain.item.Armor;
import de.midorlo.relentless.domain.item.Weapon;
import de.midorlo.relentless.domain.item.Gear;
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
