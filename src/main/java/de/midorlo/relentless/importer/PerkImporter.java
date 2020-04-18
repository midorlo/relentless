package de.midorlo.relentless.importer;

import de.midorlo.relentless.domain.mutators.Perk;
import de.midorlo.relentless.domain.mutators.PerkEffect;
import de.midorlo.relentless.repository.Repository;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings({"rawtypes"})
public class PerkImporter extends AbstractImporter<Perk> {

    private Repository<PerkEffect> perkEffectRepository;

    public PerkImporter(Repository<Perk> repository, Repository<PerkEffect> perkEffectRepository) {
        super(repository);
        this.perkEffectRepository = perkEffectRepository;
    }

    @Override
    public Perk parseGameObject(LinkedHashMap oMap, Object extraData) {

        Perk perk = new Perk();
        perk.setName((String) oMap.get("name"));
        perk.setDescription((String) oMap.get("description"));

        //Only nestes source data available, so create a data source on the fly while parsing Perks.
        PerkEffectImporter perkEffectImporter = new PerkEffectImporter(perkEffectRepository);
        List<LinkedHashMap> perkEffectsDataSource = unwrapListInMap((LinkedHashMap) oMap.get("effects"));
        List<PerkEffect> effects = perkEffectImporter.parseGameObjects(perkEffectsDataSource);

        //Source Data lacks fields for name and level.
        //It will be created from perk.name and effects.indexOf(effect)
        for (int i = 0; i < effects.size(); i++) {
            PerkEffect effect = effects.get(i);
            effect.setLevel(i + 1);
            effect.setName(perk.getName());
        }

        perk.setEffects(effects);
        return perk;
    }

    /**
     * Source Data is inconsistent for Weapons :-/
     *
     * @return existing Perk.
     */
    public Perk parseWeaponPerk(LinkedHashMap map, Object extraData) {
        List<Perk> searchResults = super.repository.findBy(e -> e.getName().contentEquals((String) map.get("name")));
        return searchResults.get(0);
    }

    public List<Perk> parseWeaponPerks(ArrayList<LinkedHashMap> mapList, Object extraData) {
        List<Perk> results = new ArrayList<>();
        if (mapList != null) {
            results.addAll(mapList.stream()
                    .map(e -> parseWeaponPerk(e, extraData))
                    .collect(Collectors.toList()));
            results = distinct(results);
        }
        return results;
    }

    private List<Perk> distinct(List<Perk> perks) {
        Set<Perk> perkSet = new HashSet<>();
        perks.forEach(perkSet::add);
        return new ArrayList<>(perkSet);
    }
}
