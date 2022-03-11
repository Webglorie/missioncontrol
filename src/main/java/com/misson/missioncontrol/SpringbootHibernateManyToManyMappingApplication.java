package com.misson.missioncontrol;

import com.misson.missioncontrol.entity.Astronaut;
import com.misson.missioncontrol.entity.Mission;
import com.misson.missioncontrol.repository.AstronautRepository;
import com.misson.missioncontrol.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootHibernateManyToManyMappingApplication implements CommandLineRunner {

	@Autowired
	private AstronautRepository astronautRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootHibernateManyToManyMappingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Astronaut astronaut = new Astronaut("Arjen", "Nederland");

		Astronaut astronaut1 = new Astronaut("Peter", "Nederland");

		Mission mission1 = new Mission("Adrollo 12");
		Mission mission2 = new Mission("Discovery");


		astronaut.getMissions().add(mission1);
		astronaut.getMissions().add(mission2);

		mission1.getAstronauts().add(astronaut);
		mission1.getAstronauts().add(astronaut1);


		this.astronautRepository.save(astronaut);
		this.astronautRepository.save(astronaut1);

	}
}
