package com.mandelorian.menu;

import com.mandelorian.Program;
import com.mandelorian.klant.Klant;
import com.mandelorian.klant.KlantType;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainMenu extends Menu {

    public MainMenu(Program program) {
        super(program);
    }

    @Override
    public List<String> getChoices() {
     return Arrays.asList("Nieuwe klanttype toevoegen", "Boot opties (voeg boot toe,voeg optie toe)", "Offerte opties (maken, laden, bewerken)", "Afsluiten");
    }

    @Override
    public void executeChoice(int choice) {
            switch (choice) {
                case 1:
                    addNewKlantType();
                    break;
                case 2:
                    stopMenu();
                    new BoatMenu(program).chooseOption();
                    break;
                case 3:
                    stopMenu();
                    new OptionMenu(program).chooseOption();
                    break;
                case 4:
                   stopMenu();
                    break;
            }
    }

    /// methods
    public void addNewKlantType() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Voer de naam van het nieuwe klanttype in: ");
        String klantTypeName = scanner.nextLine();

        System.out.print("Voer de korting voor het nieuwe klanttype in (in procenten): ");
        double klantTypeKorting = scanner.nextDouble();

        KlantType newKlantType = new KlantType(klantTypeName, klantTypeKorting);
        KlantType.getKlantTypeList().add(newKlantType);
        System.out.println("Nieuw klanttype is succesvol toegevoegd.");
    }




}
