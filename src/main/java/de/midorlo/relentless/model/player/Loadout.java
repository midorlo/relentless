package de.midorlo.relentless.model.player;

import de.midorlo.relentless.model.items.Weapon;
import de.midorlo.relentless.model.items.Wearable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;

@Data
@ToString
@EqualsAndHashCode
@Log
public class Loadout {
    Wearable head;
    Wearable chest;
    Wearable arms;
    Wearable legs;
    Wearable lantern;
    Weapon weapon;
}
