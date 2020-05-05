package de.midorlo.relentless.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A Combat effect, distinguished by name and level.
 */
@Data
@Entity
public class PerkEffect {

    @Id
    String name;

    @Basic
    Integer level;
}
