package de.midorlo.relentless.model.items;

import de.midorlo.relentless.model.Element;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;

@Data
@ToString
@EqualsAndHashCode
@Log
public class Wearable {
    String name;
    WearableType type;
    Element element;
}
