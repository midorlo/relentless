package de.midorlo.relentless.domain.behemoth;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode(exclude = "id")
public class Hitzone {

    public static Hitzone head = new Hitzone("Head");
    public static Hitzone horn = new Hitzone("Horn");
    public static Hitzone body = new Hitzone("Body");

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    String name;

    public Hitzone(String name) {
        this.name = name;
    }
}
