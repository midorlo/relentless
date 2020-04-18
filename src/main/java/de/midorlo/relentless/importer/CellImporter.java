package de.midorlo.relentless.importer;

import de.midorlo.relentless.domain.items.Cell;
import de.midorlo.relentless.domain.items.CellType;
import de.midorlo.relentless.domain.mutators.Perk;
import de.midorlo.relentless.repository.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("rawtypes")
public class CellImporter extends AbstractImporter<Cell> {

    Repository<Perk> perkRepository;

    public CellImporter(Repository<Cell> cellRepository, Repository<Perk> perkRepository) {
        super(cellRepository);
        this.perkRepository = perkRepository;
    }

    @Override
    public Cell parseGameObject(LinkedHashMap map, Object extraData) {
        String name = (String) map.get("name");
        CellType cellType = CellType.valueOf((String) map.get("slot"));

        Cell cell = new Cell();
        cell.setName(name);
        cell.setCellType(cellType);
        cell.setLevel(3);

        List<Perk> cellPerks = perkRepository.findAll()
                .stream()
                .filter(e -> name.contains(e.getName()))
                .collect(Collectors.toList());

        cell.setPerks(cellPerks);

        return cell;
    }
}
