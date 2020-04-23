package de.midorlo.relentless.importer.manual;

import de.midorlo.relentless.domain.Element;
import org.springframework.data.repository.CrudRepository;

import java.util.Arrays;

public class ElementImporter {

    public static void doImport(CrudRepository<Element, String> repository) {
        Element blaze = new Element("Blaze");
        Element frost = new Element("Frost");
        Element terra = new Element("Terra");
        Element shock = new Element("Shock");
        Element umbral = new Element("Umbral");
        Element radiant = new Element("Radiant");
        Element neutral = new Element("Neutral");

        Arrays.asList(blaze, frost, terra, shock, umbral, radiant, neutral).forEach(repository::save);
        blaze.setCounters(frost);
        frost.setCounters(blaze);
        terra.setCounters(shock);
        shock.setCounters(terra);
        umbral.setCounters(radiant);
        radiant.setCounters(umbral);

        Arrays.asList(blaze, frost, terra, shock, umbral, radiant, neutral).forEach(repository::save);
    }
}
