package de.midorlo.relentless.domain.cell;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * The type of a Cell.
 * Currently of (Technique,Utility,Power,Mobility,Defence,Prismatic)
 */
@Data
@Entity
public class CellType {

    @Id
    String name;

    public CellType() {}

    public CellType(String name) {
        this.name = name;
    }
}

