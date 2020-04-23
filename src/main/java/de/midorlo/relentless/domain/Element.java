package de.midorlo.relentless.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class Element {

    @Id
    private String name;

    @OneToOne
    private Element counters;

    public Element() {
    }

    public Element(String name) {
        this.name = name;
    }

    /**
     * Compares 2 Elements for one attacking the other.
     *
     * @param my  attacker
     * @param his defender
     * @return positive = strong, negative = weak, 0 = regular
     */
    public static int compareForAttack(Element my, Element his) {
        int val = 0;
        if (my.equals(his)) {
            val = -1;
        } else if (my.getCounters().equals(his)) {
            val = 1;
        }
        return val;
    }
}
