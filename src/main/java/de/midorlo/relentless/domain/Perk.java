package de.midorlo.relentless.domain;

import lombok.Data;
import lombok.extern.java.Log;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Log
@Entity
public class Perk {

    public Perk() {
    }

    @Id
    private String name;

    @Basic(optional = false)
    private String description;
}
