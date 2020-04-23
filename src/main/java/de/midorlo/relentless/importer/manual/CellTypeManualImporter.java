package de.midorlo.relentless.importer.manual;

import de.midorlo.relentless.domain.cell.CellType;
import de.midorlo.relentless.repository.CellTypeRepository;

public class CellTypeManualImporter {

    public static void doImport(CellTypeRepository repository) {
        for (String name : new String[]{"Technique","Utility","Power","Mobility","Defence","Prismatic"}) {
            repository.save(new CellType(name));
        }
    }
}
