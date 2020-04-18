package de.midorlo.relentless.domain.items;

import de.midorlo.relentless.domain.Element;
import de.midorlo.relentless.domain.mutators.Perk;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;

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
    List<Perk> perks;
    List<CellSocket> cellSockets;
}
