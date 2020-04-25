package de.midorlo.relentless.domain.cell;

import de.midorlo.relentless.domain.NamedObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * The type of a Cell.
 * Currently of (Technique,Utility,Power,Mobility,Defence,Prismatic)
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class CellType extends NamedObject {


    public CellType() {}

    public CellType(String name) {
        setName(name);
    }
}

