package de.midorlo.relentless.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@MappedSuperclass
public class Gear {

    @Id
    String name;

    @Transient
    transient Integer level;

    @Basic
    String description;

    @Basic
    ItemType type;

    @ManyToOne
    Element element;

    @ManyToMany
    List<Perk> perks = new ArrayList<>();

    @OneToMany
    List<CellSocket> cellSockets = new ArrayList<>();
}
