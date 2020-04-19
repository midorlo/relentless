package de.midorlo.relentless.domain.behemoth;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;

@Data
@ToString
@EqualsAndHashCode
@Log
public class BehemothPart {

    Hitzone type;
    Double health;
    Double healthWound;
    boolean wounded;

    public BehemothPart(Hitzone type, Double health) {
        this.type = type;
        this.health = health;
    }

    public boolean isWounded() {
        return wounded;
    }
}
