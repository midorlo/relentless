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
import de.midorlo.relentless.repository.dep.*;
import de.midorlo.relentless.repository.dep.PerkYamlRepository;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Log
public class AppJPARelentless {

    public static void main(String[] args) {
        SpringApplication.run(AppJPARelentless.class);
    }

    @Bean
    public CommandLineRunner demo(
            ElementRepository elementRepository,
            CellTypeRepository cellTypeRepository,
            BehemothRepository behemothRepository,
            BehemothPartRepository behemothPartRepository,
            HitzoneRepository hitzoneRepository,
            PerkEffectRepository perkEffectRepository,
            PerkRepository perkRepository
    ) {
        return args -> {

            log.info("Parsing Yaml Datasource");
            YamlRepository<PerkEffect> perkEffectYamlRepository = new PerkEffectsRepository();
            YamlRepository<Perk> perkYamlRepository = new PerkYamlRepository(perkEffectYamlRepository);
            YamlRepository<Cell> cellYamlRepository = new CellRepository(perkYamlRepository);
            YamlRepository<Weapon> weaponYamlRepository = new WeaponRepository(perkYamlRepository);
            YamlRepository<Armor> armorYamlRepository = new ArmorRepository(perkYamlRepository);
            YamlRepository<Lantern> lanternYamlRepository = new LanternRepository(perkYamlRepository);
            PerkImporter perkImporter = new PerkImporter(perkYamlRepository, perkEffectYamlRepository);
            perkImporter.importGameObjects();
            CellImporter cellImporter = new CellImporter(cellYamlRepository, perkYamlRepository, perkEffectYamlRepository);
            cellImporter.importGameObjects();
            WeaponImporter weaponImporter = new WeaponImporter(weaponYamlRepository, perkYamlRepository, perkEffectYamlRepository);
            weaponImporter.importGameObjects();
            ArmorImporter armorImporter = new ArmorImporter(armorYamlRepository, perkYamlRepository, perkEffectYamlRepository);
            armorImporter.importGameObjects();
            LanternImporter lanternImporter = new LanternImporter(lanternYamlRepository, perkYamlRepository, perkEffectYamlRepository);
            lanternImporter.importGameObjects();


            log.info("Importing manually defined Objects");
            ElementImporter.doImport(elementRepository);
            CellTypeManualImporter.doImport(cellTypeRepository);


            log.info("Importing yaml Objects");
            perkEffectYamlRepository.findAll().forEach(perkEffectRepository::save);
            perkYamlRepository.findAll().forEach(perkRepository::save);


            log.info("Ending");
        };
    }


//    @Bean
//    public CommandLineRunner demoBehemoth() {
//        return args -> {
//
//            Hitzone hit = new Hitzone("Head");
//
//            BehemothPart part = new BehemothPart();
//            part.setHealth(10000);
//            part.setHitzone(hit);
//
//            Behemoth behemoth = new Behemoth();
//            behemoth.setName("Skarn");
//            behemoth.setHealth(10000);
//            behemoth.setStaggerHealth(2000);
//            behemoth.setThread(30);
//            behemoth.setElement(Element.Terra);
//            behemoth.getBehemothParts().add(part);
//
//            behemothRepository.save(behemoth);
//
//            Iterable<Behemoth> all = behemothRepository.findAll();
//            List<Behemoth> behemoths = new ArrayList<>();
//            all.forEach(behemoths::add);
//
//            behemoths.forEach(b -> log.info("eq: " + b.equals(behemoth)));
//
//
//            log.info("findAll();ToString");
//        };
//    }
}