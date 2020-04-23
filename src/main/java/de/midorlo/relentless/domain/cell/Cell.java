package de.midorlo.relentless.domain.cell;

import de.midorlo.relentless.domain.perk.Perk;
import lombok.Data;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

@Data
@Log
public class Cell {
    String name;
    Integer level;
    CellType cellType;
    List<Perk> perks = new ArrayList<>();
}
