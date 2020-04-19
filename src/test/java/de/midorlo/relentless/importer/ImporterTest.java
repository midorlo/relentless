package de.midorlo.relentless.importer;

import de.midorlo.relentless.domain.items.*;
import de.midorlo.relentless.repository.Repository;
import org.testng.annotations.Test;

import static de.midorlo.relentless.util.Constants.*;
import static org.hamcrest.Matchers.*;

import static org.hamcrest.MatcherAssert.assertThat;

public class ImporterTest {

    Repository<Perk> perkRepository = new Repository<>();
    Repository<PerkEffect> perkEffectRepository = new Repository<>();
    Repository<Cell> cellRepository = new Repository<>();
    Repository<Weapon> weaponRepository = new Repository<>();
    Repository<Armor> armorRepository = new Repository<>();
    Repository<Lantern> lanternRepository = new Repository<>();

    @Test
    public void testImportGameObjects() {
        PerkImporter perkImporter = new PerkImporter(perkRepository, perkEffectRepository);
        perkImporter.importGameObjects(DIR_DAUNTLESS_BUILDER_PERKS);
        CellImporter cellImporter = new CellImporter(cellRepository, perkRepository, perkEffectRepository);
        cellImporter.importGameObjects(DIR_DAUNTLESS_BUILDER_CELLS);
        WeaponImporter weaponImporter = new WeaponImporter(weaponRepository, perkRepository, perkEffectRepository);
        weaponImporter.importGameObjects(DIR_DAUNTLESS_BUILDER_WEAPONS);
        ArmorImporter armorImporter = new ArmorImporter(armorRepository, perkRepository, perkEffectRepository);
        armorImporter.importGameObjects(DIR_DAUNTLESS_BUILDER_ARMOR);
        LanternImporter lanternImporter = new LanternImporter(lanternRepository, perkRepository, perkEffectRepository);
        lanternImporter.importGameObjects(DIR_DAUNTLESS_BUILDER_LANTERNS);

        assertThat(perkRepository.findAll().size(), equalTo(56));
        assertThat(cellRepository.findAll().size(), equalTo(56));
        assertThat(weaponRepository.findAll().size(), equalTo(138));
        assertThat(armorRepository.findAll().size(), equalTo(91));
    }
}