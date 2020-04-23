package de.midorlo.relentless.domain.combat;

import de.midorlo.relentless.domain.gear.Weapon;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Moveset {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Parent Weapon.
     */
    @ManyToOne
    Weapon weapon;


    /**
     * Attacks.
     */
    @OneToMany
    List<WeaponAttack> attacks = new ArrayList<>();
}
