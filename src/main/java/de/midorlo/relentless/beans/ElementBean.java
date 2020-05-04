package de.midorlo.relentless.beans;

import de.midorlo.relentless.domain.Element;
import de.midorlo.relentless.repository.ElementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Stream;

@Slf4j
@Configuration
public class ElementBean {

    @Bean
    public CommandLineRunner importElements(
            @Autowired ElementRepository elementRepository
    ) {
        return args -> {
            String[][] strings = {{"Blaze", "Frost"}, {"Terra", "Shock"}, {"Umbral", "Radiant"}, {"Neutral", "Omni"}};
            Stream.of(strings)
                    .forEach(strings1 -> {
                        Element e1 = new Element(strings1[0]);
                        Element e2 = new Element(strings1[1]);
                        elementRepository.save(e1);
                        elementRepository.save(e2);
                        e1.setCounters(e2);
                        e2.setCounters(e1);
                        elementRepository.save(e1);
                        elementRepository.save(e2);
                    });
            log.info(String.format("importElements() -> imported %d Elements.", elementRepository.findAll().size()));
        };
    }
}
