package de.midorlo.relentless.importer;

import de.midorlo.relentless.domain.CellType;
import de.midorlo.relentless.repository.CellTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
@Configuration
@Slf4j
public class CellTypeImporter {

    @Bean
    public CommandLineRunner importCellTypes(
            @Autowired CellTypeRepository cellTypeRepository
    ) {
        return args -> {
            Stream.of("Technique", "Utility", "Power", "Mobility", "Defence", "Prismatic")
                    .map(CellType::new)
                    .forEach(cellTypeRepository::save);
            log.info(String.format("importCellTypes() -> imported %d CellTypes.", cellTypeRepository.findAll().size()));
        };
    }

    protected static CellType parseCellType(Object mapValue) {
        return new CellType(((String) mapValue));
    }

    protected static List<CellType> parseCellTypes(String cellSocketString) {
        List<CellType> cellSockets = new ArrayList<>();
        if (cellSocketString != null) {
            CellType cellType = parseCellType(cellSocketString);
        }
        return cellSockets;
    }
}
