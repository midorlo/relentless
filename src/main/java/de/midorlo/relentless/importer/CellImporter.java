package de.midorlo.relentless.importer;

import de.midorlo.relentless.domain.cell.Cell;
import de.midorlo.relentless.domain.cell.CellType;
import de.midorlo.relentless.domain.perk.Perk;
import de.midorlo.relentless.domain.perk.PerkEffect;
import de.midorlo.relentless.repository.yaml.YamlRepository;

import java.util.LinkedHashMap;
import java.util.List;

import static de.midorlo.relentless.util.Constants.DIR_DAUNTLESS_BUILDER_CELLS;

@SuppressWarnings("rawtypes")
public class CellImporter extends YamlFileImporter<Cell> {

    YamlRepository<Perk> perkRepository;
    YamlRepository<PerkEffect> perkEffectRepository;

    public CellImporter(YamlRepository<Cell> cellRepository, YamlRepository<Perk> perkRepository, YamlRepository<PerkEffect> perkEffectRepository) {
        super(cellRepository);
        this.perkRepository = perkRepository;
        this.perkEffectRepository = perkEffectRepository;
    }

    @Override
    protected String getYamlsPath() {
        return DIR_DAUNTLESS_BUILDER_CELLS;
    }

    @Override
    protected void importGameObjects(List<LinkedHashMap<Object,Object>> map) {
        super.importGameObjects(map);
        repository.findAll().forEach(cell -> perkRepository.save(cell.getPerks()));
    }

    @Override
    protected Cell parseGameObject(LinkedHashMap map) {
        String name = (String) map.get("name");
        Object slot = map.get("slot");
        Cell cell = new Cell();
        cell.setName(name);
        cell.setCellType(parseCellType(map.get("slot")));
        cell.setLevel(3);
        cell.getPerks().addAll(perkRepository.findBy(e -> name.contains(e.getName())));
        return cell;
    }

    protected static CellType parseCellType(Object mapValue) {
        return new CellType(((String)mapValue));
    }
}
