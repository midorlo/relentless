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
    @ManyToOne
    private Armor head;
    @ManyToOne
    private Armor chest;
    @ManyToOne
    private Armor arms;
    @ManyToOne
    private Armor legs;
    @ManyToOne
    private Weapon weapon;
}
