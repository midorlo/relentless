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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic(optional = false, fetch = FetchType.EAGER)
    String name;

    @Basic(optional = false, fetch = FetchType.EAGER)
    Integer level;
}
