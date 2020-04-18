package de.midorlo.relentless;

import de.midorlo.relentless.domain.items.Armor;
import de.midorlo.relentless.domain.items.Cell;
import de.midorlo.relentless.domain.items.Lantern;
import de.midorlo.relentless.domain.items.Weapon;
import de.midorlo.relentless.domain.items.Perk;
import de.midorlo.relentless.domain.items.PerkEffect;
import de.midorlo.relentless.importer.*;
import de.midorlo.relentless.repository.Repository;
import de.midorlo.relentless.util.FileUtillities;
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
        perkImporter.importGameObjects(FileUtillities.getPerksProtoObjects());

        CellImporter cellImporter = new CellImporter(cellRepository, perkRepository, perkEffectRepository);
        cellImporter.importGameObjects(FileUtillities.getCellProtoObjects());

        WeaponImporter weaponImporter = new WeaponImporter(weaponRepository, perkRepository, perkEffectRepository);
        weaponImporter.importGameObjects(FileUtillities.getWeaponsProtoObjects());

        ArmorImporter armorImporter = new ArmorImporter(armorRepository, perkRepository, perkEffectRepository);
        armorImporter.importGameObjects(FileUtillities.getArmorsProtoObjects());

        LanternImporter lanternImporter = new LanternImporter(lanternRepository, perkRepository, perkEffectRepository);
        lanternImporter.importGameObjects(FileUtillities.getLanternProtoObjects());
    }

    public static void main(String[] args) {
        AppRelentless app = new AppRelentless();
        System.out.println(app);
    }
}
