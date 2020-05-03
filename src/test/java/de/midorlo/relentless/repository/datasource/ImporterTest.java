package de.midorlo.relentless.repository.datasource;

import de.midorlo.relentless.domain.Cell;
import de.midorlo.relentless.domain.Armor;
import de.midorlo.relentless.domain.Weapon;
import de.midorlo.relentless.domain.Perk;
import de.midorlo.relentless.domain.PerkEffect;
import de.midorlo.relentless.importer.*;
import de.midorlo.relentless.importer.yaml.*;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ImporterTest {

    YamlRepository<PerkEffect> perkEffectRepository = new PerkEffectsRepository();
    YamlRepository<Perk> perkRepository = new PerkYamlRepository(perkEffectRepository);
    YamlRepository<Cell> cellRepository = new CellYamlRepository(perkRepository);
    YamlRepository<Weapon> weaponRepository = new WeaponYamlRepository(perkRepository);
    YamlRepository<Armor> armorRepository = new ArmorYamlRepository(perkRepository);

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

        assertThat(perkRepository.findAll().size(), equalTo(56));
        assertThat(cellRepository.findAll().size(), equalTo(56));
        assertThat(weaponRepository.findAll().size(), equalTo(138));
        assertThat(armorRepository.findAll().size(), equalTo(91));
    }
}