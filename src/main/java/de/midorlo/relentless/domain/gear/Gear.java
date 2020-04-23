package de.midorlo.relentless.domain.gear;

import de.midorlo.relentless.domain.Element;
import de.midorlo.relentless.domain.cell.CellSocket;
import de.midorlo.relentless.domain.perk.Perk;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@MappedSuperclass
public class Gear {
    @Id
    String name;

    @Basic
    String description;

    @Basic
    ItemType type;

    @OneToOne
    Element element;

    @Transient
    transient Integer level;

    @ManyToMany
    List<Perk> perks = new ArrayList<>();

    @OneToMany
    List<CellSocket> cellSockets = new ArrayList<>();
}
