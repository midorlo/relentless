package de.midorlo.relentless;

//import de.midorlo.relentless.domain.Element;
//import de.midorlo.relentless.domain.perk.Perk;
//import de.midorlo.relentless.domain.perk.PerkEffect;
//import de.midorlo.relentless.repository.ElementRepository;
//import de.midorlo.relentless.repository.PerkEffectRepository;
//import de.midorlo.relentless.repository.PerkRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import java.util.ArrayList;
//import java.util.List;

@SpringBootApplication
@Slf4j
public class AppJPARelentless {

    public static void main(String[] args) {
        SpringApplication.run(AppJPARelentless.class);
    }

//    private static List<PerkEffect> refreshPerkEffects(List<PerkEffect> olds, PerkEffectRepository repository) {
//        List<PerkEffect> news = new ArrayList<>();
//        olds.forEach(o -> news.add(repository.findByNameAndLevel(o.getName(), o.getLevel())));
//        if (olds.size() != news.size()) {
//            throw new RuntimeException("sizes differ!");
//        }
//        return news;
//    }
//
//    private static List<Perk> refreshPerks(List<Perk> olds, PerkRepository repository) {
//        List<Perk> news = new ArrayList<>();
//        olds.forEach(o -> news.add(repository.findByNameAndLevel(o.getName(), o.getLevel())));
//        if (olds.size() != news.size()) {
//            throw new RuntimeException("sizes differ!");
//        }
//        return news;
//    }
//
//    private static Element refreshElement(Element old, ElementRepository repository) {
//        return repository.findById(old.getName()).orElse(null);
//    }
}