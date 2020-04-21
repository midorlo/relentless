package de.midorlo.relentless;

import de.midorlo.relentless.domain.behemoth.Hitzone;
import de.midorlo.relentless.repository.HitzoneRepository;
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
}