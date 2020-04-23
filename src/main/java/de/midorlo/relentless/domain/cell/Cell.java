package de.midorlo.relentless.domain.cell;

import de.midorlo.relentless.domain.perk.Perk;
import lombok.Data;
import lombok.extern.java.Log;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Data
@Log
@Entity
public class Cell {
    @Id
    String name;

    Integer level;

    @OneToOne
    CellType cellType;

    @ManyToMany
    List<Perk> perks = new ArrayList<>();
}
