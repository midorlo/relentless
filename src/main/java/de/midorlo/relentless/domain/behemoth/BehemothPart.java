package de.midorlo.relentless.domain.behemoth;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.java.Log;

import javax.persistence.*;

@Data
@Log
@Entity
@EqualsAndHashCode(exclude = "id")
public class BehemothPart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade=CascadeType.PERSIST)
    Hitzone hitzone;

    Integer health;
    Integer marginWounded;

    public BehemothPart() {}

    public BehemothPart(Hitzone hitzone, Integer health) {
        this.hitzone = hitzone;
        this.health = health;
        this.marginWounded = health;
    }

    public boolean isWounded() {
        return marginWounded <= 0;
    }
}
