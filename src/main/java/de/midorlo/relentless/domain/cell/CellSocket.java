package de.midorlo.relentless.domain.cell;

import lombok.Data;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Typed Value holder for Cells in Gear.
 */
@Data
@Log
@Entity
public class CellSocket {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private CellType type;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cell cell;
}
