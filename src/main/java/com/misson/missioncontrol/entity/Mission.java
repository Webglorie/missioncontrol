package com.misson.missioncontrol.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "missions")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "departure_date")
    private Date departure = new Date();

    @Temporal(TemporalType.DATE)
    @Column(name = "backonearth_date")
    private Date backOnEarth = new Date();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "mission_astronauts",
    joinColumns = { @JoinColumn(name = "mission_id")},
    inverseJoinColumns = { @JoinColumn (name = "astronaut_id")})
    private Set<Astronaut> astronauts = new HashSet<>();


    public Mission(){

    }

    public Mission(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
       return String.format(
               "Mission[id=%d, naam='%s']",
               id, name);
    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public Date getDeparture() {
        return departure;
    }

    public Date getBackOnEarth() {
        return backOnEarth;
    }


    public Set<Astronaut> getAstronauts() {
        return astronauts;
    }

}
