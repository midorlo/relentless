package de.midorlo.relentless.model.behemoth;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;

@Data
@ToString
@EqualsAndHashCode
@Log
public class BehemothPart {

    BehemothPartType type;
    Double health;
    boolean wounded;

    public BehemothPart(BehemothPartType type, Double health) {
        this.type = type;
        this.health = health;
    }

    public boolean isWounded() {
        return wounded;
    }
}
