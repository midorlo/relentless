package de.midorlo.relentless.beans;

import de.midorlo.relentless.domain.Cell;
import de.midorlo.relentless.repository.CellRepository;
import de.midorlo.relentless.repository.CellTypeRepository;
import de.midorlo.relentless.repository.PerkEffectRepository;
import de.midorlo.relentless.repository.PerkRepository;
import de.midorlo.relentless.util.FileUtillities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

import static de.midorlo.relentless.beans.CellTypeBean.parseCellType;
import static de.midorlo.relentless.util.Constants.DIR_DAUNTLESS_BUILDER_CELLS;

@Configuration
@Slf4j
public class CellBean {

    @Bean
    public CommandLineRunner importCells(
            @Autowired CellRepository cellRepository,
            @Autowired CellTypeRepository cellTypeRepository,
            @Autowired PerkRepository perkRepository,
            @Autowired PerkEffectRepository perkEffectRepository
    ) {
        return args -> {
            FileUtillities.readYamlFiles(DIR_DAUNTLESS_BUILDER_CELLS)
                    .stream()
                    .map(this::parseGameObject)
                    .forEach(cellRepository::save);
            log.info(String.format("importCells() -> imported %d Cells.", cellRepository.findAll().size()));
        };
    }

    @SuppressWarnings("rawtypes")
    protected Cell parseGameObject(LinkedHashMap map) {
        String name = (String) map.get("name");
        Object slot = map.get("slot");
        Cell cell = new Cell();
        cell.setName(name);
        cell.setCellType(parseCellType(map.get("slot")));
//        cell.getPerks().addAll(perkRepository.findBy(e -> name.contains(e.getName())));
        return cell;
    }
}
