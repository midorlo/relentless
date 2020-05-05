package de.midorlo.relentless.domain;

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
    String name;

    @ManyToOne(cascade = CascadeType.ALL)
    CellType cellType;

    @ManyToMany(cascade = CascadeType.ALL)
    List<Perk> perks = new ArrayList<>();
}
