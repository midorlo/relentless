package de.midorlo.relentless.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class SkillSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Attacks.
     */
    @OneToMany
    List<Skill> attacks = new ArrayList<>();
}
