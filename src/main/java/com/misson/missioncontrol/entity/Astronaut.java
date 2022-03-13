package com.misson.missioncontrol.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "astronauts")
public class Astronaut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "nationality")
    private String nationality;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "astronauts")
    private Set<Mission> missions = new HashSet();

    public Astronaut(){

    }

    public Astronaut(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }



    @Override
    public String toString() {
        return String.format(
                "Astronaut[id=%d, naam='%s', nationality='%s']",
                id, name, nationality);
    }

    public Set<Mission> getMissions() {
        return missions;
    }


    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getNationality() {
        return nationality;
    }

}
