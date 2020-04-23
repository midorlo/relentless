package de.midorlo.relentless.domain.cell;

import lombok.Data;
import lombok.extern.java.Log;

import javax.persistence.*;

/**
 * Typed Value holder for Cells in Gear.
 */
@Data
@Log
@Entity
public class CellSocket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private CellType type;

    /** Child Cell */
    @OneToOne
    private Cell cell;
}
