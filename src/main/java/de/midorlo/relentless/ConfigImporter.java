package de.midorlo.relentless;

import de.midorlo.relentless.domain.cell.Cell;
import de.midorlo.relentless.domain.gear.Armor;
import de.midorlo.relentless.domain.gear.Lantern;
import de.midorlo.relentless.domain.gear.Weapon;
import de.midorlo.relentless.domain.perk.Perk;
import de.midorlo.relentless.domain.perk.PerkEffect;
import de.midorlo.relentless.importer.*;
import de.midorlo.relentless.importer.manual.CellTypeManualImporter;
import de.midorlo.relentless.importer.manual.ElementImporter;
import de.midorlo.relentless.repository.*;
import de.midorlo.relentless.repository.yaml.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ConfigImporter {

    final static YamlRepository<PerkEffect> perkEffectYamlRepository = new PerkEffectsRepository();
    final static YamlRepository<Perk> perkYamlRepository = new PerkYamlRepository(perkEffectYamlRepository);
    final static YamlRepository<Cell> cellYamlRepository = new CellYamlRepository(perkYamlRepository);
    final static YamlRepository<Weapon> weaponYamlRepository = new WeaponYamlRepository(perkYamlRepository);
    final static YamlRepository<Armor> armorYamlRepository = new ArmorYamlRepository(perkYamlRepository);
    final static YamlRepository<Lantern> lanternYamlRepository = new LanternRepository(perkYamlRepository);

    static {
        importYamlFiles();
    }

    private static void importYamlFiles() {
        log.info("importYamlFiles");
        new PerkImporter(perkYamlRepository, perkEffectYamlRepository).importGameObjects();
        new CellImporter(cellYamlRepository, perkYamlRepository, perkEffectYamlRepository).importGameObjects();
        new WeaponImporter(weaponYamlRepository, perkYamlRepository, perkEffectYamlRepository).importGameObjects();
        new ArmorImporter(armorYamlRepository, perkYamlRepository, perkEffectYamlRepository).importGameObjects();
        new LanternImporter(lanternYamlRepository, perkYamlRepository, perkEffectYamlRepository).importGameObjects();
    }

    @Bean
    public CommandLineRunner importElements(ElementRepository elementRepository) {
        log.info("importElements");
        return args -> ElementImporter.doImport(elementRepository);
    }

    @Bean
    public CommandLineRunner importCellTypes(CellTypeRepository cellTypeRepository) {
        log.info("importCellTypes");
        return args -> CellTypeManualImporter.doImport(cellTypeRepository);
    }

    @Bean
    public CommandLineRunner importPerkEffects(PerkEffectRepository perkEffectRepository) {
        log.info("importPerkEffects");
        return args -> perkEffectRepository.saveAll(perkEffectYamlRepository.findAll());
    }

    @Bean
    public CommandLineRunner importPerks(PerkRepository perkRepository) {
        log.info("importPerks");
        return args -> perkRepository.saveAll(perkYamlRepository.findAll());
    }

    @Bean
    public CommandLineRunner importCells(CellRepository cellRepository) {
        log.info("importCells");
        return args -> cellRepository.saveAll(cellYamlRepository.findAll());
    }

    @Bean
    public CommandLineRunner importCellSockets(CellSocketRepository cellSocketRepository) {
        return args -> {
            log.info("importCellSockets");
            weaponYamlRepository.findAll().forEach(weapon -> weapon.getCellSockets().forEach(cellSocketRepository::save));
            armorYamlRepository.findAll().forEach(weapon -> weapon.getCellSockets().forEach(cellSocketRepository::save));
        };
    }

    @Bean
    public CommandLineRunner importWeapons(WeaponRepository weaponRepository) {
        log.info("importWeapons");
        return args -> weaponRepository.saveAll(weaponYamlRepository.findAll());
    }

    @Bean
    public CommandLineRunner importArmors(ArmorRepository armorRepository) {
        log.info("importArmors");
        return args -> armorRepository.saveAll(armorYamlRepository.findAll());
    }
}
