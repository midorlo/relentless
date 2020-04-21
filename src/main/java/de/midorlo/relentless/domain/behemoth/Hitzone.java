package de.midorlo.relentless.domain.behemoth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Hitzone {

    public static Hitzone head = new Hitzone("Head");
    public static Hitzone horn = new Hitzone("Horn");

    String name;

    public Hitzone(String name) {
        this.name = name;
    }
}
