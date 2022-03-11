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

    @Column(name = "departure_date")
    private Date departure = new Date();

    @Column(name = "backonearth_date")
    private Date backOnEarth = new Date();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "mission_astronauts",
    joinColumns = { @JoinColumn(name = "mission_id")},
    inverseJoinColumns = { @JoinColumn (name = "astronaut_id")})
    private Set<Astronaut> astronauts = new HashSet<>();


    public Mission(){

    }

    public Mission(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getBackOnEarth() {
        return backOnEarth;
    }

    public void setBackOnEarth(Date backOnEarth) {
        this.backOnEarth = backOnEarth;
    }

    public Set<Astronaut> getAstronauts() {
        return astronauts;
    }

    public void setAstronauts(Set<Astronaut> astronauts) {
        this.astronauts = astronauts;
    }
}
