package de.midorlo.relentless.domain.perk;

import lombok.Data;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Log
@Entity
public class Perk {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    String name;
    String description;

    @OneToMany
    List<PerkEffect> effects = new ArrayList<>();
}
