package de.midorlo.relentless.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Armor {

    @Id
    String name;

    @Basic(optional = false)
    Integer level;

    @Basic(optional = false)
    String description;

    @Basic(optional = false)
    ItemType type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Element element;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Perk> perks = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    CellType primaryCellSocket;

    public Double getResistance() {
        return 137.5d; //todo handle levelled armor
    }
}
