package de.midorlo.relentless;

import de.midorlo.relentless.domain.items.Cell;
import de.midorlo.relentless.domain.items.Weapon;
import de.midorlo.relentless.importer.CellImporter;
import de.midorlo.relentless.importer.PerkImporter;
import de.midorlo.relentless.domain.mutators.Perk;
import de.midorlo.relentless.domain.mutators.PerkEffect;
import de.midorlo.relentless.importer.WeaponImporter;
import de.midorlo.relentless.repository.Repository;
import de.midorlo.relentless.util.FileUtillities;
import lombok.ToString;
import lombok.extern.java.Log;

import java.io.File;


@Log
@ToString
public class AppRelentlessModel {

    Repository<Perk> perkRepository = new Repository<>();
    Repository<PerkEffect> perkEffectRepository = new Repository<>();
    Repository<Cell> cellRepository = new Repository<>();
    Repository<Weapon> weaponRepository = new Repository<>();

    public AppRelentlessModel() {
        importGameObjects();
    }

    public void importGameObjects() {
        PerkImporter perkImporter = new PerkImporter(perkRepository, perkEffectRepository);
        perkImporter.importGameObjects(FileUtillities.getPerksProtoObjects());

        CellImporter cellImporter = new CellImporter(cellRepository, perkRepository);
        cellImporter.importGameObjects(FileUtillities.getCellProtoObjects());

        WeaponImporter weaponImporter = new WeaponImporter(weaponRepository, perkRepository, perkEffectRepository);
        weaponImporter.importGameObjects(FileUtillities.getWeaponsProtoObjects());
    }

    public static void main(String[] args) {
        AppRelentlessModel app = new AppRelentlessModel();
        System.out.println(app);
    }
}
