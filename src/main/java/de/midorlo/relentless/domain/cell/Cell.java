package de.midorlo.relentless.domain.cell;

import de.midorlo.relentless.domain.perk.Perk;
import lombok.Data;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Log
@Entity
public class Cell {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;
    Integer level;

    @OneToOne
    CellType cellType;

    @OneToMany
    List<Perk> perks = new ArrayList<>();
}
