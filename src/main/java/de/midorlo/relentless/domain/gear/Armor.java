package de.midorlo.relentless.domain.gear;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Entity
public class Armor extends Gear {

    public Double getResistance() {
        return 137.5d; //todo handle levelled armor
    }
}
