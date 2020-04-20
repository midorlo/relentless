package de.midorlo.relentless;

import de.midorlo.relentless.domain.items.*;
import de.midorlo.relentless.importer.*;
import de.midorlo.relentless.repository.Repository;
import lombok.ToString;
import lombok.extern.java.Log;


@Log
@ToString
public class AppRelentless {

    Repository<Perk> perkRepository = new Repository<>();
    Repository<PerkEffect> perkEffectRepository = new Repository<>();
    Repository<Cell> cellRepository = new Repository<>();
    Repository<Weapon> weaponRepository = new Repository<>();
    Repository<Armor> armorRepository = new Repository<>();
    Repository<Lantern> lanternRepository = new Repository<>();

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
