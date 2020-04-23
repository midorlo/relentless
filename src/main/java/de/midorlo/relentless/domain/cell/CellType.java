package de.midorlo.relentless.domain.cell;

import com.sun.istack.Nullable;
import lombok.Data;

import javax.persistence.*;

/**
 * Cell Type. Currently one of: (Technique,Utility,Power,Mobility,Defence,Prismatic)
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

