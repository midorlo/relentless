package de.midorlo.relentless;

import de.midorlo.relentless.domain.behemoth.BehemothPart;
import de.midorlo.relentless.domain.behemoth.Hitzone;
import de.midorlo.relentless.repository.jpa.BehemothPartRepository;
import de.midorlo.relentless.repository.jpa.HitzoneRepository;
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
    public CommandLineRunner demoHitzone(HitzoneRepository repository) {
        return args -> {
            repository.save(new Hitzone("H1"));
            repository.save(new Hitzone("H2"));
            Iterable<Hitzone> all = repository.findAll();
            all.forEach(hitzone -> log.info(hitzone.toString()));
        };
    }

   @Bean
    public CommandLineRunner demoHitzone(BehemothPartRepository behemothPartRepository, HitzoneRepository hitzoneRepository) {
        return args -> {
            Hitzone head = new Hitzone("Head");
            BehemothPart behemothPart = new BehemothPart();
            behemothPart.setHealth(10000d);
            behemothPart.setHitzone(head);
            behemothPartRepository.save(behemothPart);
            Iterable<BehemothPart> all = behemothPartRepository.findAll();
            System.out.println("--------------- <Parts>  ---------------");
            all.forEach(part -> log.info(part.toString()));
            System.out.println("--------------- </Parts> ---------------");
            System.out.println("--------------- <Hitzones>  ---------------");
            hitzoneRepository.findAll().forEach(h -> log.info(h.toString()));
            System.out.println("--------------- </Hitzones> ---------------");
        };
    }
}