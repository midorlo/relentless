package de.midorlo.relentless.domain;

import de.midorlo.relentless.domain.gear.Armor;
import de.midorlo.relentless.domain.gear.Weapon;
import de.midorlo.relentless.domain.gear.Gear;
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
