package de.midorlo.relentless.domain.behemoth;

import lombok.Data;
import lombok.extern.java.Log;

import javax.persistence.*;

@Data
@Log
@Entity
public class BehemothPart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    Hitzone hitzone;

    Double health;
    Double marginWounded;

    public BehemothPart() {}

    public BehemothPart(Hitzone hitzone, Double health) {
        this.hitzone = hitzone;
        this.health = health;
        this.marginWounded = health;
    }

    public void setHealth(Double health) {
        Double diff = health - health;
    }

    public boolean isWounded() {
        return marginWounded <= 0;
    }
}
