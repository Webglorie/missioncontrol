package com.misson.missioncontrol.repository;

import com.misson.missioncontrol.entity.Astronaut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AstronautRepository extends JpaRepository<Astronaut, Long> {

}
