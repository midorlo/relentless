package de.midorlo.relentless.importer;

import de.midorlo.relentless.domain.cell.CellSocket;
import de.midorlo.relentless.domain.combat.WeaponAttack;
import de.midorlo.relentless.domain.item.Lantern;
import de.midorlo.relentless.domain.perk.Perk;
import de.midorlo.relentless.domain.perk.PerkEffect;
import de.midorlo.relentless.repository.dep.Repository;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static de.midorlo.relentless.util.Constants.DIR_DAUNTLESS_BUILDER_LANTERNS;

@SuppressWarnings("ALL")
public class LanternImporter extends YamlFileImporter<Lantern> {

    Repository<Perk> perkRepository;
    Repository<PerkEffect> perkEffectRepository;

    public LanternImporter(Repository<Lantern> repository, Repository<Perk> perkRepository, Repository<PerkEffect> perkEffectRepository) {
        super(repository);
        this.perkRepository = perkRepository;
        this.perkEffectRepository = perkEffectRepository;
    }

    @Override
    protected Lantern parseGameObject(LinkedHashMap map) {
        /*
        name: Broadsides Lantern
        icon: /assets/icons/lanterns/BroadsidesLantern.png
        description: null
        cells: Utility
        lantern_ability:
        instant: Tap to fire a cannonball from above that deals 350 damage or, against airborne Behemoths, deals 450 damage and critically strikes.
        hold: Hold to drop a bomb from above that explodes after 1 second. Deals 650 damage and can interrupt Behemoths.
        */
        String name = (String) map.get("name");
        String icon = (String) map.get("icon");
        String description = (String) map.get("description");
        String cells = (String) map.get("cells");
        Object lanternAbility = map.get("lantern_ability");

        Lantern lantern = new Lantern();
        lantern.setName(name);
        lantern.setDescription(description);
        if (cells != null) {
            CellSocket cellSocket = new CellSocket();
            cellSocket.setType(CellImporter.parseCellType(cells));
            lantern.getCellSockets().add(cellSocket);
        }
        lantern.getMoveSets().add(parseLanternAttackObjects((LinkedHashMap) lanternAbility, lantern));
        return lantern;
    }

    protected List<WeaponAttack> parseLanternAttackObjects(LinkedHashMap map, Lantern parent) {
        String instant = (String) map.get("instant");
        String hold = (String) map.get("hold");

        WeaponAttack instantAttack = new WeaponAttack();
        instantAttack.setName(parent.getName() + "_instant");
        instantAttack.setDescription(instant);
        instantAttack.setCleave(false);
        instantAttack.setDamage(0);
        instantAttack.setHits(0);

        WeaponAttack holdAttack = new WeaponAttack();
        holdAttack.setName(parent.getName() + "_hold");
        holdAttack.setDescription(instant);
        holdAttack.setCleave(false);
        holdAttack.setDamage(0);
        holdAttack.setHits(0);
        return Arrays.asList(new WeaponAttack[]{instantAttack,holdAttack});
    }

    @Override
    protected String getYamlsPath() {
        return DIR_DAUNTLESS_BUILDER_LANTERNS;
    }
}
