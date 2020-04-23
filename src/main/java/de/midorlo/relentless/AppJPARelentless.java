package de.midorlo.relentless;

import de.midorlo.relentless.domain.item.Element;
import de.midorlo.relentless.domain.behemoth.Behemoth;
import de.midorlo.relentless.domain.behemoth.BehemothPart;
import de.midorlo.relentless.domain.behemoth.Hitzone;
import de.midorlo.relentless.repository.BehemothPartRepository;
import de.midorlo.relentless.repository.BehemothRepository;
import de.midorlo.relentless.repository.HitzoneRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Log
public class AppJPARelentless {

    @Autowired
    BehemothPartRepository behemothPartRepository;
    @Autowired
    BehemothRepository behemothRepository;
    @Autowired
    HitzoneRepository hitzoneRepository;

    public static void main(String[] args) {
        SpringApplication.run(AppJPARelentless.class);
    }


//    @Bean
//    public CommandLineRunner demoBehemoth(
//            PerkEffectRepository perkEffectRepository) {
//
//    }


    @Bean
    public CommandLineRunner demoBehemoth() {
        return args -> {

            Hitzone hit = new Hitzone("Head");

            BehemothPart part = new BehemothPart();
            part.setHealth(10000);
            part.setHitzone(hit);

            Behemoth behemoth = new Behemoth();
            behemoth.setName("Skarn");
            behemoth.setHealth(10000);
            behemoth.setStaggerHealth(2000);
            behemoth.setThread(30);
            behemoth.setElement(Element.Terra);
            behemoth.getBehemothParts().add(part);

            behemothRepository.save(behemoth);

            Iterable<Behemoth> all = behemothRepository.findAll();
            List<Behemoth> behemoths = new ArrayList<>();
            all.forEach(behemoths::add);

            behemoths.forEach(b -> log.info("eq: " + b.equals(behemoth)));


            log.info("findAll();ToString");
        };
    }
}