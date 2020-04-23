package de.midorlo.relentless.domain.gear;

import de.midorlo.relentless.domain.Element;
import de.midorlo.relentless.domain.cell.CellSocket;
import de.midorlo.relentless.domain.perk.Perk;
import de.midorlo.relentless.domain.perk.PerkEffect;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Gear {
    @Id
    String name;

    @Basic
    String description;

    @Basic
    ItemType type;

    @OneToOne
    Element element;

    @Basic
    Integer level;

    @OneToMany
    List<Perk> perks = new ArrayList<>();

    @OneToMany
    List<CellSocket> cellSockets = new ArrayList<>();

    @OneToMany
    List<PerkEffect> perkEffects = new ArrayList<>();
}
