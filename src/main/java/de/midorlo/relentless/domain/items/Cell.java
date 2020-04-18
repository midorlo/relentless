package de.midorlo.relentless.domain.items;

import de.midorlo.relentless.domain.mutators.Perk;
import lombok.Data;
import lombok.extern.java.Log;

import java.util.List;

@Data
@Log
public class Cell {
    String name;
    Integer level;
    CellType cellType;
    List<Perk> perks;
}
