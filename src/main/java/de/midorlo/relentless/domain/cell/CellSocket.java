package de.midorlo.relentless.domain.cell;

import de.midorlo.relentless.domain.gear.Gear;
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

    /** Parent Gear */
    @ManyToOne
    private Gear gear;

    /** Child Cell */
    @OneToOne
    private Cell cell;
}
