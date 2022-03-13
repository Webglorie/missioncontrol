package com.misson.missioncontrol.repository;


import com.misson.missioncontrol.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    Mission findById(long id);
}
