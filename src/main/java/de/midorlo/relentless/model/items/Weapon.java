package de.midorlo.relentless.model.items;

import de.midorlo.relentless.model.combat.AttackMove;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@Log
public class Weapon extends Wearable {
    List<List<AttackMove>> moveSets = new ArrayList<>();
}
