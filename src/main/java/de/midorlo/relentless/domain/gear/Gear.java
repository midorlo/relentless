package de.midorlo.relentless.domain.gear;

import de.midorlo.relentless.domain.Element;
import de.midorlo.relentless.domain.cell.CellSocket;
import de.midorlo.relentless.domain.perk.Perk;
import de.midorlo.relentless.domain.perk.PerkEffect;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@Log
public class Gear {
    String name;
    String description;
    ItemType type;
    Element element;
    Integer level;
    List<Perk> perks = new ArrayList<>();
    List<CellSocket> cellSockets = new ArrayList<>();
    List<PerkEffect> perkEffects = new ArrayList<>();
}
