package de.midorlo.relentless;

import de.midorlo.relentless.domain.Element;
import de.midorlo.relentless.domain.cell.Cell;
import de.midorlo.relentless.domain.cell.CellSocket;
import de.midorlo.relentless.domain.gear.Armor;
import de.midorlo.relentless.domain.gear.Lantern;
import de.midorlo.relentless.domain.gear.Weapon;
import de.midorlo.relentless.domain.perk.Perk;
import de.midorlo.relentless.domain.perk.PerkEffect;
import de.midorlo.relentless.importer.*;
import de.midorlo.relentless.importer.manual.CellTypeManualImporter;
import de.midorlo.relentless.importer.manual.ElementImporter;
import de.midorlo.relentless.repository.*;
import de.midorlo.relentless.repository.CellRepository;
import de.midorlo.relentless.repository.yaml.*;
import de.midorlo.relentless.repository.yaml.PerkYamlRepository;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

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
            CellRepository cellRepository,
            CellSocketRepository cellSocketRepository,
            BehemothRepository behemothRepository,
            BehemothPartRepository behemothPartRepository,
            HitzoneRepository hitzoneRepository,
            PerkEffectRepository perkEffectRepository,
            WeaponRepository weaponRepository,
            PerkRepository perkRepository
    ) {
        return args -> {

            log.info("Parsing Yaml Datasource");

            YamlRepository<PerkEffect> perkEffectYamlRepository = new PerkEffectsRepository();
            YamlRepository<Perk> perkYamlRepository = new PerkYamlRepository(perkEffectYamlRepository);
            YamlRepository<Cell> cellYamlRepository = new CellYamlRepository(perkYamlRepository);

            YamlRepository<Weapon> weaponYamlRepository = new WeaponYamlRepository(perkYamlRepository);
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

            log.info("Importing yaml defined Objects");

            //Perk Effects
            perkEffectRepository.saveAll(perkEffectYamlRepository.findAll());

            //Perks
            perkYamlRepository.findAll().forEach(perk -> {
                List<PerkEffect> newEffects = new ArrayList<>();
                perk.getEffects().forEach(perkEffect -> {
                    newEffects.add(perkEffectRepository.findByNameAndLevel(perkEffect.getName(), perkEffect.getLevel()));
                });
                perk.setEffects(newEffects);
            });
            perkRepository.saveAll(perkYamlRepository.findAll());

            //Cells
            cellYamlRepository.findAll().forEach(cell -> {
                List<Perk> perks = new ArrayList<>();
                cell.getPerks().forEach(perk -> {
                    Perk byName = perkRepository.findByNameAndLevel(perk.getName(), perk.getLevel());
                    perks.add(perk);
                });
                cell.setPerks(perks);
            });
            cellRepository.saveAll(cellYamlRepository.findAll());

            //Weapons
            weaponYamlRepository.findAll().forEach(weapon -> {
                Element element = weapon.getElement();
                weapon.setElement(refreshElement(weapon.getElement(), elementRepository));
                weapon.setPerks(refreshPerks(weapon.getPerks(), perkRepository));
                cellSocketRepository.saveAll(weapon.getCellSockets());
            });
            weaponRepository.saveAll(weaponYamlRepository.findAll());

            log.info("Ending");
        };
    }

    private static List<PerkEffect> refreshPerkEffects(List<PerkEffect> olds, PerkEffectRepository repository) {
        List<PerkEffect> news = new ArrayList<>();
        olds.forEach(o -> news.add(repository.findByNameAndLevel(o.getName(), o.getLevel())));
        if (olds.size() != news.size()) {
            throw new RuntimeException("sizes differ!");
        }
        return news;
    }

    private static List<Perk> refreshPerks(List<Perk> olds, PerkRepository repository) {
        List<Perk> news = new ArrayList<>();
        olds.forEach(o -> news.add(repository.findByNameAndLevel(o.getName(), o.getLevel())));
        if (olds.size() != news.size()) {
            throw new RuntimeException("sizes differ!");
        }
        return news;
    }

    private static Element refreshElement(Element old, ElementRepository repository) {
        return repository.findById(old.getName()).orElse(null);
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