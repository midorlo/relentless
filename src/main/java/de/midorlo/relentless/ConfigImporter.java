package de.midorlo.relentless;

import de.midorlo.relentless.domain.*;
import de.midorlo.relentless.importer.*;
import de.midorlo.relentless.importer.manual.CellTypeManualImporter;
import de.midorlo.relentless.importer.manual.ElementImporter;
import de.midorlo.relentless.importer.yaml.*;
import de.midorlo.relentless.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ConfigImporter {

    final static YamlRepository<PerkEffect> PERK_EFFECT_YAML_REPOSITORY = new PerkEffectsRepository();
    final static YamlRepository<Perk> PERK_YAML_REPOSITORY = new PerkYamlRepository(PERK_EFFECT_YAML_REPOSITORY);
    final static YamlRepository<Cell> CELL_YAML_REPOSITORY = new CellYamlRepository(PERK_YAML_REPOSITORY);
    final static YamlRepository<Weapon> WEAPON_YAML_REPOSITORY = new WeaponYamlRepository(PERK_YAML_REPOSITORY);
    final static YamlRepository<Armor> ARMOR_YAML_REPOSITORY = new ArmorYamlRepository(PERK_YAML_REPOSITORY);
    final static YamlRepository<Lantern> LANTERN_YAML_REPOSITORY = new LanternRepository(PERK_YAML_REPOSITORY);

    static {
        importYamlFiles();
    }

    private static void importYamlFiles() {
        log.info("importYamlFiles");
        new PerkImporter(PERK_YAML_REPOSITORY, PERK_EFFECT_YAML_REPOSITORY).importGameObjects();
        new CellImporter(CELL_YAML_REPOSITORY, PERK_YAML_REPOSITORY, PERK_EFFECT_YAML_REPOSITORY).importGameObjects();
        new WeaponImporter(WEAPON_YAML_REPOSITORY, PERK_YAML_REPOSITORY, PERK_EFFECT_YAML_REPOSITORY).importGameObjects();
        new ArmorImporter(ARMOR_YAML_REPOSITORY, PERK_YAML_REPOSITORY, PERK_EFFECT_YAML_REPOSITORY).importGameObjects();
        new LanternImporter(LANTERN_YAML_REPOSITORY, PERK_YAML_REPOSITORY, PERK_EFFECT_YAML_REPOSITORY).importGameObjects();
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
        return args -> perkEffectRepository.saveAll(PERK_EFFECT_YAML_REPOSITORY.findAll());
    }

    @Bean
    public CommandLineRunner importPerks(PerkRepository perkRepository) {
        log.info("importPerks");
        return args -> perkRepository.saveAll(PERK_YAML_REPOSITORY.findAll());
    }

    @Bean
    public CommandLineRunner importCells(CellRepository cellRepository) {
        log.info("importCells");
        return args -> cellRepository.saveAll(CELL_YAML_REPOSITORY.findAll());
    }

    @Bean
    public CommandLineRunner importCellSockets(CellSocketRepository cellSocketRepository) {
        return args -> {
            log.info("importCellSockets");
            WEAPON_YAML_REPOSITORY.findAll().forEach(weapon -> weapon.getCellSockets().forEach(cellSocketRepository::save));
            ARMOR_YAML_REPOSITORY.findAll().forEach(weapon -> weapon.getCellSockets().forEach(cellSocketRepository::save));
        };
    }

    @Bean
    public CommandLineRunner importWeapons(WeaponRepository weaponRepository) {
        log.info("importWeapons");
        return args -> weaponRepository.saveAll(WEAPON_YAML_REPOSITORY.findAll());
    }

    @Bean
    public CommandLineRunner importArmors(ArmorRepository armorRepository) {
        log.info("importArmors");
        return args -> armorRepository.saveAll(ARMOR_YAML_REPOSITORY.findAll());
    }
}
