package de.midorlo.relentless;

import de.midorlo.relentless.domain.behemoth.Hitzone;
import de.midorlo.relentless.repository.HitzoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataJpaApplication {

    private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataJpaApplication.class);
    }

    @Bean
    public CommandLineRunner demoHitzone(HitzoneRepository repository) {
        return args -> {
            repository.save(new Hitzone("H1"));
            repository.save(new Hitzone("H2"));

            Iterable<Hitzone> all = repository.findAll();
            all.forEach(System.out::println);
        };
    }
}