package de.midorlo.relentless;

import de.midorlo.relentless.domain.perk.PerkEffect;
import de.midorlo.relentless.domain.perk.PerkEffectDescription;
import de.midorlo.relentless.domain.perk.PerkEffectValue;
import de.midorlo.relentless.importer.manual.CellTypeManualImporter;
import de.midorlo.relentless.repository.*;
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
            BehemothRepository behemothRepository,
            BehemothPartRepository behemothPartRepository,
            HitzoneRepository hitzoneRepository,
            PerkEffectRepository perkEffectRepository,
            CellTypeRepository cellTypeRepository) {
        return args -> {
            log.info("Starting");

            CellTypeManualImporter.doImport(cellTypeRepository);

            PerkEffect perkEffect = new PerkEffect();
            perkEffect.setName("perkEffect1");
            perkEffect.setLevel(1);
            perkEffect.getDescriptions().add(new PerkEffectDescription("perkEffect" + perkEffect.getLevel() + "description"));
            perkEffect.getValues().add(new PerkEffectValue("perkEffect" + perkEffect.getLevel() + "value"));
            perkEffectRepository.save(perkEffect);

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