package de.midorlo.relentless.repository;

import de.midorlo.relentless.domain.Armor;
import de.midorlo.relentless.domain.Weapon;

import java.util.HashMap;
import java.util.Map;

public class AssetsRepository {
    public static Map<Armor, String> armorAssets = new HashMap<>();
    public static Map<Weapon, String> weaponAssets = new HashMap<>();

    public String findWeaponAsset(Weapon weapon) {
        return weaponAssets.get(weapon);
    }

    public String findArmorAsset(Armor armor) {
        return armorAssets.get(armor);
    }
}
