package de.midorlo.relentless.domain.items;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * A Combat effect, distinguished by name and level.
 */
@Data
public class PerkEffect {

    String name;
    Integer level;
    List<String> descriptions = new ArrayList<>();
    List<String> values = new ArrayList<>();
}