package de.midorlo.relentless.repository.datasource;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import de.midorlo.relentless.domain.cell.Cell;
import de.midorlo.relentless.domain.item.*;
import de.midorlo.relentless.domain.perk.Perk;
import de.midorlo.relentless.domain.perk.PerkEffect;
import de.midorlo.relentless.importer.*;
import de.midorlo.relentless.repository.dep.*;
import org.testng.annotations.Test;

public class ImporterTest {

    Repository<PerkEffect> perkEffectRepository = new PerkEffectsRepository();
    Repository<Perk> perkRepository = new PerkRepository(perkEffectRepository);
    Repository<Cell> cellRepository = new CellRepository(perkRepository);
    Repository<Weapon> weaponRepository = new WeaponRepository(perkRepository);
    Repository<Armor> armorRepository = new ArmorRepository(perkRepository);
    Repository<Lantern> lanternRepository = new LanternRepository(perkRepository);

    @Test
    public void testImportGameObjects() {
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

        assertThat(perkRepository.findAll().size(), equalTo(56));
        assertThat(cellRepository.findAll().size(), equalTo(56));
        assertThat(weaponRepository.findAll().size(), equalTo(138));
        assertThat(armorRepository.findAll().size(), equalTo(91));
    }
}