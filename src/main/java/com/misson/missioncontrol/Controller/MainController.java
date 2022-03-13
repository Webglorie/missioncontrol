package com.misson.missioncontrol.Controller;

import com.misson.missioncontrol.entity.Astronaut;
import com.misson.missioncontrol.entity.Mission;
import com.misson.missioncontrol.repository.AstronautRepository;
import com.misson.missioncontrol.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Controller
public class MainController {

    @Autowired
    private AstronautRepository astronautRepository;

    @Autowired
    private MissionRepository missionRepository;

    public long sum = 0;

    public List<Astronaut> astronauts = new ArrayList<Astronaut>();

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String ANSI_RED = "\u001B[31m";

    public void mainMenu(){
        System.out.println(ANSI_GREEN);
        System.out.println("*************************************************");
        System.out.println("*             Nasa Mission Control              *");
        System.out.println("*************************************************");
        System.out.println("*     Kies een menu optie:                      *");
        System.out.println("*  1. Reisgenoten opvragenn                     *");
        System.out.println("*  2. Missie aan astronaut toekennen            *");
        System.out.println("*  3. Aantal ruimtedagen van astronaut opvragen *");
        System.out.println("*  4. Nieuwe astronaut toevoegen                *");
        System.out.println("*  5. Nieuwe missie toevoegen                   *");
        System.out.println("*************************************************");
        System.out.println(ANSI_RESET);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Typ uw menu keuze:");
        int menuOption = scanner.nextInt();
        try {
            menuOption = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("U heeft onjuiste gegevens ingevoerd.");
        }
        switch (menuOption) {
            case 1:
                showAllAstronauts();
                Scanner companionsIdInput = new Scanner(System.in);
                System.out.println("Wat is de id van de astronaut?");
                int companionsId = companionsIdInput.nextInt();

                getCompanions(companionsId);

                System.out.println("Druk op enter om terug te gaan naar het hoofdmenu...");
                try
                {
                    System.in.read();
                }
                catch(Exception e)
                {}
                mainMenu();
                break;
            case 2:
                showAllAstronauts();
                Scanner astroIdInput = new Scanner(System.in);
                System.out.println("Wat is de id van de astronaut?");
                int astroId = astroIdInput.nextInt();

                showAllMissions();
                Scanner missionInput = new Scanner(System.in);
                System.out.println("Wat is de id van de missie?");
                int missionId = astroIdInput.nextInt();

                if (addMissionToAstronaut(missionId,astroId)) {
                    System.out.println(ANSI_GREEN + "Astronaut is toegevoegd!" + ANSI_RESET);
                    System.out.println("Druk op enter om terug te gaan naar het hoofdmenu...");
                    try
                    {
                        System.in.read();
                    }
                    catch(Exception e)
                    {}
                    mainMenu();
                } else {
                    System.out.println(ANSI_RED + "Er is iets fout gegaan, mogelijk is de astronaut al toegevoegd aan deze missie." + ANSI_RESET);
                    System.out.println("Druk op enter om terug te gaan naar het hoofdmenu...");
                    try
                    {
                        System.in.read();
                    }
                    catch(Exception e)
                    {}
                    mainMenu();
                }
                break;
            case 3:
                Scanner daysIdInput = new Scanner(System.in);
                System.out.println("Wat is de id van de astronaut?");
                long daysId = daysIdInput.nextLong();


                    System.out.println("De ingevoerde astronaut heeft: " + ANSI_GREEN + getSpaceDays(daysId) + " dagen" + ANSI_RESET + " in de ruimte gezeten.");
                    System.out.println("Druk op enter om terug te gaan naar het hoofdmenu...");
                    try {
                        System.in.read();
                    } catch (Exception e) {


                    break;
                }

                break;
            case 4:
                Scanner addAstronautNameInput = new Scanner(System.in);
                System.out.println("Wat is de naam van de astronaut?");
                String newAstronautName = addAstronautNameInput.nextLine();

                Scanner addAstronautNationalityInput = new Scanner(System.in);
                System.out.println("Wat is de nationaliteit van de astronaut?");
                String newAstronautNationality = addAstronautNationalityInput.nextLine();
                if (addAstronaut(newAstronautName, newAstronautNationality)) {
                    System.out.println(ANSI_GREEN + "Astronaut is toegevoegd!" + ANSI_RESET);
                    System.out.println("Druk op enter om terug te gaan naar het hoofdmenu...");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                    }
                    mainMenu();
                } else {
                    System.out.println(ANSI_RED + "Er is iets fout gegaan bij het toevoegen van de astronaut. Probeer het later nog eens." + ANSI_RESET);
                    System.out.println("Druk op enter om terug te gaan naar het hoofdmenu...");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                    }

                    break;
                }
                break;
            case 5:
                Scanner addMissionInput = new Scanner(System.in);
                System.out.println("Wat is de naam van de missie?");
                String newMission = addMissionInput.nextLine();
                if (addMission(newMission)) {
                    System.out.println(ANSI_GREEN + "Missie is toegevoegd!" + ANSI_RESET);
                    System.out.println("Druk op enter om terug te gaan naar het hoofdmenu...");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                    }
                    mainMenu();
                } else {
                    System.out.println(ANSI_RED + "Er is iets fout gegaan bij het toevoegen van de missie. Probeer het later nog eens." + ANSI_RESET);
                    System.out.println("Druk op enter om terug te gaan naar het hoofdmenu...");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                    }

                    break;
                }
        }
    }


    public boolean addMissionToAstronaut(long missionId, long astronautId){
        Astronaut findAstro = this.astronautRepository.findById(astronautId);
        Mission findMission = this.missionRepository.findById(missionId);

        try {
            findAstro.getMissions().add(findMission);
            findMission.getAstronauts().add(findAstro);
            missionRepository.save(findMission);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean addMission(String name) {

        Mission newMission = new Mission(name);

        try {
            missionRepository.save(newMission);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean addAstronaut(String name, String nationality) {

        Astronaut astronaut = new Astronaut(name, nationality);

        try {
            astronautRepository.save(astronaut);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public long getSpaceDays(long id){
        sum = 0;
        Astronaut astronaut = astronautRepository.findById(id);

        astronaut.getMissions().forEach(mission -> {
            Date startDate = mission.getDeparture();
            Date backOnEarthDate = mission.getBackOnEarth();
            long currentDays = backOnEarthDate.getTime() - startDate.getTime();
            sum = sum + TimeUnit.DAYS.convert(currentDays, TimeUnit.MILLISECONDS);
		});

        System.out.println("Totale dagen in de ruimte:");
        return sum;
    }

    public void getCompanions(long id){
        Astronaut astronaut = astronautRepository.findById(id);

        astronaut.getMissions().forEach(mission -> {
            mission.getAstronauts().forEach(astro -> {
                if (!astronauts.contains(astro) && astro.getId() != id) {
                    System.out.println(ANSI_GREEN + "Reisgenoot: " + astro.getName() + " met nationaliteit: " + astro.getNationality() + ANSI_RESET);
                }
            });
        });

    }

    public void showAllAstronauts(){
        System.out.println(ANSI_GREEN + "Lijst van alle astronauten: " + ANSI_RESET);
        System.out.println(ANSI_RED + "---------------------------------" + ANSI_RESET);
        astronautRepository.findAll().forEach(astronaut -> {
            System.out.println("ID van astronaut: " + astronaut.getId());
            System.out.println("Naam van astronaut: " + astronaut.getName());
            System.out.println(ANSI_RED + "---------------------------------" + ANSI_RESET);
        });
    }
    public void showAllMissions(){
        System.out.println(ANSI_GREEN + "Lijst van alle missies: " + ANSI_RESET);
        System.out.println(ANSI_RED + "---------------------------------" + ANSI_RESET);
        missionRepository.findAll().forEach(mission -> {
            System.out.println("ID van de missie: " + mission.getId());
            System.out.println("Naam van de missie: " + mission.getName());
            System.out.println(ANSI_RED + "---------------------------------" + ANSI_RESET);
        });
    }


}
