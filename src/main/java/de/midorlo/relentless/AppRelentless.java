package de.midorlo.relentless;

import de.midorlo.relentless.domain.cell.Cell;
import de.midorlo.relentless.domain.gear.*;
import de.midorlo.relentless.domain.perk.Perk;
import de.midorlo.relentless.domain.perk.PerkEffect;
import de.midorlo.relentless.importer.*;
import de.midorlo.relentless.repository.dep.*;
import lombok.ToString;
import lombok.extern.java.Log;


@Log
@ToString
public class AppRelentless {

    YamlRepository<PerkEffect> perkEffectRepository = new PerkEffectsRepository();
    YamlRepository<Perk> perkRepository = new PerkYamlRepository(perkEffectRepository);
    YamlRepository<Cell> cellRepository = new CellRepository(perkRepository);
    YamlRepository<Weapon> weaponRepository = new WeaponRepository(perkRepository);
    YamlRepository<Armor> armorRepository = new ArmorRepository(perkRepository);
    YamlRepository<Lantern> lanternRepository = new LanternRepository(perkRepository);

    public AppRelentless() {
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
        AppRelentless app = new AppRelentless();
        System.out.println(app);
    }
}
