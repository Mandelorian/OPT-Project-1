package com.mandelorian.menu;

import com.mandelorian.Program;
import com.mandelorian.library.Categorie;
import com.mandelorian.library.Utility;
import com.mandelorian.product.Boat;
import com.mandelorian.product.ProductList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BoatMenu extends Menu {

    public BoatMenu(Program program) {
        super(program);
    }

    @Override
    public List<String> getChoices() {
        return Arrays.asList("Nieuwe boot toevoegen", "Nieuwe optie toevoegen", " Ga terug", "Afsluiten");
    }

    @Override
    public void executeChoice(int choice) {
        switch (choice) {
            case 1:
                addNewBoat();
                break;
            case 2:
                addNewOption();
                break;
            case 3:
                Menu.openMenu(new MainMenu(program));
                break;
            case 4:
                stopMenu();
                break;
        }
    }

    /// private methods
    public void addNewBoat() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Voer de naam van de nieuwe boot in: ");
        String boatName = scanner.nextLine();

        System.out.print("Voer de prijs van de nieuwe boot in: ");
        double boatPrice = scanner.nextDouble();

        System.out.print("Voer de categorie van de nieuwe boot in: ");
        scanner.nextLine(); // Consume newline left-over
        String categoryName = scanner.nextLine();

        Categorie category = Categorie.getCategoryByName(categoryName);

        //als gevraagd wordt voor nieuw category aan maken dan kunnen we deze toevoegen
       /* if (category == null) {
            System.out.println("Categorie niet gevonden. Een nieuwe categorie wordt aangemaakt.");
            category = new Categorie(categoryName);
            Categorie.addCategory(category);
        }*/

        ProductList.addBoat(boatName, boatPrice, category);
        Utility.saveJSONFile(ProductList.getBoatsJSONString(), "./saved/", "boats");
        System.out.println("Nieuwe boot is succesvol toegevoegd.");
    }

    public void addNewOption() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Voer de naam van de nieuwe optie in: ");
        String optionName = scanner.nextLine();

        System.out.print("Voer de prijs van de nieuwe optie in: ");
        double optionPrice = scanner.nextDouble();

        System.out.print("Voer de beschrijving van de nieuwe optie in: ");
        scanner.nextLine(); // Consume newline left-over
        String optionDescription = scanner.nextLine();

        System.out.print("Voer de naam van de boot in waar deze optie aan gekoppeld moet worden: ");
        String boatName = scanner.nextLine();

        Boat boat = Utility.getBoatByName(boatName);
        if (boat == null) {
            System.out.println("Boot niet gevonden. Voeg eerst de boot toe.");
            return;
        }


        ProductList.addOption(optionName, optionPrice, optionDescription, boat);
        Utility.saveJSONFile(ProductList.getOptionsJSONString(), "./saved/", "options");
        System.out.println("Nieuwe optie is succesvol toegevoegd.");
    }

}
