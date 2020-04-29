package de.midorlo.relentless;

import de.midorlo.relentless.domain.cell.Cell;
import de.midorlo.relentless.domain.gear.Armor;
import de.midorlo.relentless.domain.gear.Lantern;
import de.midorlo.relentless.domain.gear.Weapon;
import de.midorlo.relentless.domain.perk.Perk;
import de.midorlo.relentless.domain.perk.PerkEffect;
import de.midorlo.relentless.importer.*;
import de.midorlo.relentless.repository.yaml.*;
import lombok.ToString;
import lombok.extern.java.Log;


@Log
@ToString
public class AppImporter {

    YamlRepository<PerkEffect> perkEffectRepository = new PerkEffectsRepository();
    YamlRepository<Perk> perkRepository = new PerkYamlRepository(perkEffectRepository);
    YamlRepository<Cell> cellRepository = new CellYamlRepository(perkRepository);
    YamlRepository<Weapon> weaponRepository = new WeaponYamlRepository(perkRepository);
    YamlRepository<Armor> armorRepository = new ArmorYamlRepository(perkRepository);
    YamlRepository<Lantern> lanternRepository = new LanternRepository(perkRepository);

    public AppImporter() {
        importGameObjects();
    }

    public void importGameObjects() {
        PerkImporter perkImporter = new PerkImporter(perkRepository, perkEffectRepository);
        perkImporter.importGameObjects();

        CellImporter cellImporter = new CellImporter(cellRepository, perkRepository, perkEffectRepository);
        cellImporter.importGameObjects();

        WeaponImporter weaponImporter = new WeaponImporter(weaponRepository, perkRepository, perkEffectRepository);
        weaponImporter.importGameObjects();

        ArmorImporter armorImporter = new ArmorImporter(armorRepository, perkRepository, perkEffectRepository);
        armorImporter.importGameObjects();

        LanternImporter lanternImporter = new LanternImporter(lanternRepository, perkRepository, perkEffectRepository);
        lanternImporter.importGameObjects();
    }

    public static void main(String[] args) {
        AppImporter app = new AppImporter();
        System.out.println(app);
    }
}
