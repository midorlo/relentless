package de.midorlo.relentless.domain.items;

import lombok.Data;
import lombok.extern.java.Log;

@Data
@Log
public class CellSocket {

    private CellType type;
    private Cell cell;
}
