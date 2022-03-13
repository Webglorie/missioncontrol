package com.misson.missioncontrol;

import com.misson.missioncontrol.Controller.MainController;
import com.misson.missioncontrol.entity.Astronaut;
import com.misson.missioncontrol.entity.Mission;
import com.misson.missioncontrol.repository.AstronautRepository;
import com.misson.missioncontrol.repository.MissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;

@SpringBootApplication
public class MissionControl implements CommandLineRunner {

	@Autowired
	private AstronautRepository astronautRepository;

	@Autowired
	private MissionRepository missionRepository;

	// Declaring ANSI_RESET so that we can reset the color
	public static final String ANSI_RESET = "\u001B[0m";

	// Declaring the color
	// Custom declaration
	public static final String ANSI_PURPLE = "\u001B[35m";

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	MainController mainController;


	public static void main(String[] args) {
		SpringApplication.run(MissionControl.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println(ANSI_PURPLE + "        _..._");
		System.out.println("      .'     '.");
		System.out.println("     /    .-\"\"-\\   _/ \\ ");
		System.out.println("   .-|   /:.   |  |   |");
		System.out.println("   |  \\  |:.   /.-'-./");
		System.out.println("   | .-'-;:__.'    =/");
		System.out.println("   .'=  *=|NASA _.='");
		System.out.println("  /   _.  |    ;");
		System.out.println(" ;-.-'|    \\   |");
		System.out.println("/   | \\    _\\  _\\");
		System.out.println("\\__/'._;.  ==' ==\\");
		System.out.println("         \\    \\   |");
		System.out.println("         /    /   /");
		System.out.println("         /-._/-._/");
		System.out.println("         \\   `\\  \\");
		System.out.println("          `-._/._/" + ANSI_RESET);
		mainController.mainMenu();

	}
}
