package de.midorlo.relentless.importer;

import de.midorlo.relentless.domain.Perk;
import de.midorlo.relentless.domain.PerkEffect;
import de.midorlo.relentless.importer.yaml.YamlRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static de.midorlo.relentless.util.Constants.DIR_DAUNTLESS_BUILDER_PERKS;

@SuppressWarnings({"rawtypes", "unchecked"})
public class PerkImporter extends YamlFileImporter<Perk> {

    private final YamlRepository<PerkEffect> perkEffectRepository;

    public PerkImporter(YamlRepository<Perk> repository, YamlRepository<PerkEffect> perkEffectRepository) {
        super(repository);
        this.perkEffectRepository = perkEffectRepository;
    }

    @Override
    protected Perk parseGameObject(LinkedHashMap oMap) {

        Perk perk = new Perk();
        perk.setName((String) oMap.get("name"));
        perk.setDescription((String) oMap.get("description"));

        //Only nestes source data available, so create a data source on the fly while parsing Perks.
        PerkEffectImporter perkEffectImporter = new PerkEffectImporter(perkEffectRepository);
        List<LinkedHashMap<Object,Object>> perkEffectsDataSource = unwrapListInMap((LinkedHashMap) oMap.get("effects"));
        List<PerkEffect> effects = perkEffectImporter.parseGameObjects(perkEffectsDataSource);

        //Source Data lacks fields for name and level.
        //It will be created from perk.name and effects.indexOf(effect)
        for (int i = 0; i < effects.size(); i++) {
            PerkEffect effect = effects.get(i);
            effect.setLevel(i + 1);
            effect.setName(perk.getName());
            if (!perkEffectRepository.contains(effect)) {
                perkEffectRepository.save(effect);
            }
        }
        perk.setEffects(effects);
        return perk;
    }

    /**
     * Source Data is inconsistent for Weapons :-/
     *
     * @return existing Perk.
     */
    protected Perk parseWeaponPerk(LinkedHashMap map) {
        List<Perk> searchResults = super.repository.findBy(e -> e.getName().contentEquals((String) map.get("name")));
        if (searchResults.isEmpty()) {
            throw new RuntimeException("Import Perks first!");
        }
        return searchResults.get(0);
    }

    protected List<Perk> parseWeaponPerks(ArrayList<LinkedHashMap> mapList) {
        List<Perk> results = new ArrayList<>();
        if (mapList != null) {
            results.addAll(mapList.stream()
                    .map(this::parseWeaponPerk)
                    .collect(Collectors.toList()));
            results = distinct(results);
        }
        return results;
    }

    @Override
    protected String getYamlsPath() {
        return DIR_DAUNTLESS_BUILDER_PERKS;
    }
}
