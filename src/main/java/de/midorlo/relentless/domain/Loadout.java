package de.midorlo.relentless.domain;

import lombok.Data;
import lombok.extern.java.Log;

import javax.persistence.*;

@Data
@Log
@Entity
public class Loadout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Armor head;
    @ManyToOne(cascade = CascadeType.ALL)
    private Armor chest;
    @ManyToOne(cascade = CascadeType.ALL)
    private Armor arms;
    @ManyToOne(cascade = CascadeType.ALL)
    private Armor legs;
    @ManyToOne(cascade = CascadeType.ALL)
    private Weapon weapon;
}
