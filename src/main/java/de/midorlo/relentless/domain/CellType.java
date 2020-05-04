package de.midorlo.relentless.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * The type of a Cell.
 * Currently of (Technique,Utility,Power,Mobility,Defence,Prismatic)
 */
@Data
@Entity
public class CellType {

    @Id
    private String name;

    public CellType() {}

    public CellType(String name) {
        setName(name);
    }
}

