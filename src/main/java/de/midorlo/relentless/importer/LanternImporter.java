package de.midorlo.relentless.importer;

import de.midorlo.relentless.domain.items.Lantern;
import de.midorlo.relentless.domain.items.Perk;
import de.midorlo.relentless.domain.items.PerkEffect;
import de.midorlo.relentless.repository.Repository;

import java.util.LinkedHashMap;

@SuppressWarnings("ALL")
public class LanternImporter extends AbstractImporter<Lantern> {

    Repository<Perk> perkRepository;
    Repository<PerkEffect> perkEffectRepository;

    public LanternImporter(Repository<Lantern> repository, Repository<Perk> perkRepository, Repository<PerkEffect> perkEffectRepository) {
        super(repository);
        this.perkRepository = perkRepository;
        this.perkEffectRepository = perkEffectRepository;
    }

    @Override
    public Lantern parseGameObject(LinkedHashMap map) {
        /*
        name: Broadsides Lantern
        icon: /assets/icons/lanterns/BroadsidesLantern.png
        description: null
        cells: Utility
        lantern_ability:
        instant: Tap to fire a cannonball from above that deals 350 damage or, against airborne Behemoths, deals 450 damage and critically strikes.
        hold: Hold to drop a bomb from above that explodes after 1 second. Deals 650 damage and can interrupt Behemoths.
        */

        return null; //todo impl
    }
}
