package de.midorlo.relentless.domain.gear;

@SuppressWarnings("SameReturnValue")
public class Armor extends Gear {

    @SuppressWarnings("unused")
    public Double getResistance() {
        return  137.5d; //todo handle levelled armor
    }
}
