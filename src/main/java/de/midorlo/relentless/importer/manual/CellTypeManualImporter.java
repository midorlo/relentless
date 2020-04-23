package de.midorlo.relentless.importer.manual;

import de.midorlo.relentless.domain.cell.CellType;
import org.springframework.data.repository.CrudRepository;

public class CellTypeManualImporter {

    public static void doImport(CrudRepository<CellType, Long> repository) {
        for (String name : new String[]{"Technique","Utility","Power","Mobility","Defence","Prismatic"}) {
            repository.save(new CellType(name));
        }
    }
}
