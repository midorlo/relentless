package de.midorlo.relentless.importer;

import de.midorlo.relentless.domain.items.Cell;
import de.midorlo.relentless.domain.items.CellType;
import de.midorlo.relentless.domain.items.Perk;
import de.midorlo.relentless.domain.items.PerkEffect;
import de.midorlo.relentless.repository.Repository;

import java.util.LinkedHashMap;
import java.util.List;

import static de.midorlo.relentless.util.Constants.DIR_DAUNTLESS_BUILDER_CELLS;

@SuppressWarnings("rawtypes")
public class CellImporter extends YamlFileImporter<Cell> {

    Repository<Perk> perkRepository;
    Repository<PerkEffect> perkEffectRepository;

    public CellImporter(Repository<Cell> cellRepository, Repository<Perk> perkRepository, Repository<PerkEffect> perkEffectRepository) {
        super(cellRepository);
        this.perkRepository = perkRepository;
        this.perkEffectRepository = perkEffectRepository;
    }

    @Override
    protected String getYamlsPath() {
        return DIR_DAUNTLESS_BUILDER_CELLS;
    }

    @Override
    protected Repository<Cell> importGameObjects(List<LinkedHashMap<Object,Object>> map) {
        super.importGameObjects(map);
        repository.findAll().forEach(cell -> {
            perkRepository.save(cell.getPerks());
            cell.getPerks().forEach(perk -> perkEffectRepository.save(perk.getEffects()));
        });
        return repository;
    }

    @Override
    protected Cell parseGameObject(LinkedHashMap map) {
        String name = (String) map.get("name");
        CellType cellType = CellType.valueOf((String) map.get("slot"));
        Cell cell = new Cell();
        cell.setName(name);
        cell.setCellType(cellType);
        cell.setLevel(3);
        cell.getPerks().addAll(perkRepository.findBy(e -> name.contains(e.getName())));
        return cell;
    }
}
