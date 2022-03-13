package com.misson.missioncontrol.repository;

import com.misson.missioncontrol.entity.Astronaut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AstronautRepository extends JpaRepository<Astronaut, Long> {

List<Astronaut> findByName(String name);

Astronaut findById(long id);
}
