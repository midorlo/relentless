package de.midorlo.relentless;

import de.midorlo.relentless.importer.PerkImporter;
import de.midorlo.relentless.model.Perk;
import de.midorlo.relentless.model.PerkEffect;
import de.midorlo.relentless.repository.Repository;
import de.midorlo.relentless.util.FileUtillities;
import lombok.ToString;
import lombok.extern.java.Log;

@Log
@ToString
public class AppRelentlessModel {

    Repository<Perk> perkRepository = new Repository<>();
    Repository<PerkEffect> perkEffectRepository = new Repository<>();

    public AppRelentlessModel() {
        importGameObjects();
    }

    public void importGameObjects() {
        PerkImporter perkImporter = new PerkImporter(perkRepository, perkEffectRepository);
        perkImporter.importGameObjects(FileUtillities.getPerksProtoObjects());
    }

    public static void main(String[] args) {
        AppRelentlessModel app = new AppRelentlessModel();
        System.out.println(app);
    }
}
