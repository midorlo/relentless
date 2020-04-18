package de.midorlo.relentless.domain.items;

import lombok.Data;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

@Data
@Log
public class Perk {
    String name;
    String description;
    List<PerkEffect> effects = new ArrayList<>();
}
