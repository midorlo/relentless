package de.midorlo.relentless.beans;

import de.midorlo.relentless.domain.Perk;
import de.midorlo.relentless.domain.PerkEffect;
import de.midorlo.relentless.repository.PerkEffectRepository;
import de.midorlo.relentless.repository.PerkRepository;
import de.midorlo.relentless.util.FileUtillities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static de.midorlo.relentless.util.Constants.DIR_DAUNTLESS_BUILDER_PERKS;

@Configuration
@Slf4j
public class PerkBean {

    @Bean
    public CommandLineRunner importPerks(
            @Autowired PerkRepository perkRepository,
            @Autowired PerkEffectRepository perkEffectRepository) {
        return args -> {
            log.info("importPerks");
            List<Perk> perks = new ArrayList<>();
            List<LinkedHashMap<Object, Object>> map = FileUtillities.readYamlFiles(DIR_DAUNTLESS_BUILDER_PERKS);
            for (LinkedHashMap<Object, Object> oMap : map) {
                perks.add(parseGameObject(oMap));
            }
            perkRepository.saveAll(perks);
            log.info(String.format("importPerks() -> imported %d Perks.", perkRepository.findAll().size()));
        };
    }


    @SuppressWarnings({"rawtypes", "unchecked"})
    protected Perk parseGameObject(LinkedHashMap oMap) {

        Perk perk = new Perk();
        perk.setName((String) oMap.get("name"));
        perk.setDescription((String) oMap.get("description"));
        PerkEffectBean perkEffectBean = new PerkEffectBean();
        @SuppressWarnings("rawtypes")
        List<LinkedHashMap<Object, Object>> perkEffectsMapMap = unwrapListInMap((LinkedHashMap) oMap.get("effects"));
        List<PerkEffect> perkEffects = perkEffectBean.parseGameObjects(perkEffectsMapMap);

        for (int i = 0; i < perkEffects.size(); i++) {
            PerkEffect effect = perkEffects.get(i);
            effect.setLevel(i + 1);
            effect.setName(perk.getName());
        }
        return perk;
    }

    /**
     * Source Data is inconsistent for Weapons :-/
     *
     * @return existing Perk.
     */
    @SuppressWarnings("rawtypes")
    protected static Perk parseWeaponPerk(LinkedHashMap map, PerkRepository perkRepository) {
        return perkRepository.findByName((String) map.get("name"));
    }

    /**
     * Utillity Method. Converts a Map(k,v) to an ArrayList, sorted by ((int)k)
     *
     * @param map the map.
     * @return the list.
     */
    @SuppressWarnings("unchecked")
    protected static List<LinkedHashMap<Object, Object>> unwrapListInMap(LinkedHashMap<Object, Object> map) {
        List<LinkedHashMap<Object, Object>> list = new ArrayList<>();
        int index = 1;
        while (map.get("" + index) != null) {
            list.add((LinkedHashMap<Object, Object>) map.get("" + (index++)));
        }
        return list;
    }

    protected static List<Perk> parseWeaponPerks(@SuppressWarnings("rawtypes") ArrayList<LinkedHashMap> mapsList, PerkRepository perkRepository) {
        List<Perk> results = new ArrayList<>();
        if (mapsList != null) {
            results.addAll(mapsList.stream()
                    .map(linkedHashMap -> parseWeaponPerk(linkedHashMap, perkRepository))
                    .collect(Collectors.toList()));
        }
        return results;
    }
}
