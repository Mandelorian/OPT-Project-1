package com.mandelorian;

import com.mandelorian.klant.Klant;
import com.mandelorian.klant.KlantType;
import com.mandelorian.library.Categorie;
import com.mandelorian.product.Boat;
import com.mandelorian.library.Utility;
import com.mandelorian.product.Option;
import com.mandelorian.product.ProductList;
import com.mandelorian.quotation.Quotation;

import javax.sound.midi.Soundbank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.*;


public class Main {


    public static void main(String[] args) {
        Categorie.setDefaultCategories();
        ProductList.setDefaultBoatList();
        ProductList.setDefaultOptionList();
        KlantType.setDefaultKlantType();

       Program program = new Program();
       program.start();
    }

}


class Program {
    private List<Quotation> quotationList;
    private Quotation currentQuotation;

    public Program() {
        this.quotationList = new ArrayList<>();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("Welkom!");


        do {
            System.out.println("\nMenu:");
            System.out.println("1. Nieuwe klanttype toevoegen");
            System.out.println("2. Boot opties");
            System.out.println("3. Offerte opties");
            System.out.println("4. Afsluiten");
            System.out.print("Kies een optie: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addNewKlantType();
                    break;
                case 2:
                    bootOpties();
                    break;
                case 3:
                    offerteOpties();
                    break;
                case 4:
                    choice = stop();
                    break;
            }
        } while (choice != 100);
    }


    public int stop() {
        Utility.saveJSONFile(Categorie.getCatoriesJSONString(), "./saved/", "categories");
        Utility.saveJSONFile(KlantType.getKlantTypesJSONString(), "./saved/", "clientTypes");
        Utility.saveJSONFile(ProductList.getBoatsJSONString(), "./saved/", "boats");
        Utility.saveJSONFile(ProductList.getOptionsJSONString(), "./saved/", "options");
        Quotation.saveQuatationListToFile(this.quotationList, "./saved/quotations/", "quotations");
        return 100;
    }


    public void bootOpties(){

        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("Kies een boot optie");


        do {
            System.out.println("\nMenu:");
            System.out.println("1. Nieuwe boot toevoegen");
            System.out.println("2. Nieuwe optie toevoegen");
            System.out.println("3. Ga terug");
            System.out.println("4. Afsluiten");
            System.out.print("Kies een optie: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addNewBoat();
                    break;
                case 2:
                    addNewOption();
                    break;
                case 3:
                    start();
                    break;
                case 4:
                    choice = stop();
                    break;
            }
        } while (choice != 100);

    }

    public void offerteOpties(){

        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("Kies een offerte optie");


        do {
            System.out.println("\nMenu:");
            System.out.println("1. Offerte maken");
            System.out.println("2. Offertes laden");
            System.out.println("3. Offerte lijst");
            System.out.println("4. Offerte bewerken");
            System.out.println("5. Ga terug");
            System.out.println("6. Afsluiten");
            System.out.print("Kies een optie: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    offerteMaken();
                    break;
                case 2:
                    offertesLaden();
                    break;
                case 3:
                    offertesPrinten();
                    break;
                case 4:
                    editQuotation();
                    continue;
                case 5:
                    start();
                    break;
                case 6:
                    choice = stop();
                    break;
            }
        } while (choice != 100);

    }


    public void offerteMaken() {
        Scanner scanner = new Scanner(System.in);
        Klant klant = createKlant();


        System.out.println("Welk type boot wilt u kopen?");
        System.out.println();

        System.out.println("———————————————————————————————————————————————————");
        System.out.println("                      Boot lijst                   ");
        System.out.println("———————————————————————————————————————————————————");

        Boat boat = null;
        for (int i = 0; i < ProductList.getBoatList().size(); i++) {
            //System.out.println((i + 1) + ". " + ProductList.getBoatList().get(i).getName());
            System.out.printf("%2d. %s\n", (i + 1), ProductList.getBoatList().get(i).getName());
        }

        System.out.println("———————————————————————————————————————————————————");

        System.out.println();

        while (boat == null) {
            System.out.print("Kies de bootnummer van de boot die je wil: ");
            int boatNumber = scanner.nextInt();
            System.out.println();

            if (boatNumber > ProductList.getBoatList().size()) {
                System.out.println("De boot met dit nummer bestaat niet.");
                continue;
            }

            boat = ProductList.getBoatList().get((boatNumber - 1));
        }

        //Utility.clearScreen();
        this.setCurrentQuotation(this.createNewQuotation(boat, klant));
        //lijst opties

        System.out.println("Welk optie's wilt u toevoegen");
        System.out.println();

        System.out.println("———————————————————————————————————————————————————");
        System.out.println("                     Optie lijst                   ");
        System.out.println("———————————————————————————————————————————————————");


        List<Option> avalibleOptions = boat.getAvalibleOptions();

        for (int i = 0; i < avalibleOptions.size(); i++) {

            if (avalibleOptions.get(i).getBoat().getName().compareTo(boat.getName()) == 0) {
                //System.out.println((i + 1) + ". " + ProductList.getOptionList().get(i).getName() + " Prijs: "+ProductList.getOptionList().get(i).getPrice()+" Beschrijving: " +ProductList.getOptionList().get(i).getDescription());
                //System.out.printf("%2d. %-42s:\n", (i + 1), ProductList.getOptionList().get(i).getName());
                System.out.println((i + 1) + ". " + avalibleOptions.get(i).getName());
                System.out.println("   Prijs: " + avalibleOptions.get(i).getPrice());
                System.out.println("   Beschrijving: " + avalibleOptions.get(i).getDescription());
                System.out.println();
            }

        }

        System.out.println("———————————————————————————————————————————————————");
        System.out.println();

        int option = 1;
        while (option == 1) {

            System.out.print("Als u geen optie meer wilt toevoegen druk op: 0 ");
            System.out.print("Kies de optie die je wilt toevoegen aan de boot: ");
            int optionNummer = scanner.nextInt();
            System.out.println();

            if (optionNummer == 0) {
                option = 0;
                continue;
            }

            if (optionNummer > avalibleOptions.size()) {
                System.out.println("De optie met dit nummer bestaat niet.");
                continue;
            }


            currentQuotation.addOption(avalibleOptions.get((optionNummer - 1)));
        }


        //totaal prijs

        System.out.println("———————————————————————————————————————————————————");
        System.out.println("               totaal prijs               ");
        System.out.println("———————————————————————————————————————————————————");

        System.out.println();


        System.out.println("Toegepaste korting percentage: "+klant.getKlanttype().getKorting() +"%");
        System.out.println("Toegepaste Korting : "+currentQuotation.getTotalPrice()*(klant.getKlanttype().getKorting()/100));
        System.out.printf("Totaal price: €" + "%.2f",currentQuotation.getTotalPrice()- (currentQuotation.getTotalPrice()*(klant.getKlanttype().getKorting()/100)));

        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");

        currentQuotation.printQuotation();

        int save = 2;
        while (save == 2) {
            System.out.println("Wilt u de offerte opslaan? ");
            System.out.println("jaa / nee");

            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("ja") || answer.equalsIgnoreCase("j") || answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
                save = 1;
                continue;
            }

            if (answer.equalsIgnoreCase("nee") || answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no")) {
                save = 0;
                continue;
            }
        }

        if (save == 1) {
            currentQuotation.saveQuoatation("./saved/quotations/", klant.getNaam());
        }
    }

    public void offertesLaden() {
        Scanner scanner = new Scanner(System.in);

        String bestandsNaam = null;
        List<Quotation> quotations = null;

        while (quotations == null) {
            System.out.println("Wat is de naam van het bestand van de offertes die u wilt laden?");
            bestandsNaam = scanner.nextLine();
            if (bestandsNaam.contains(".")) bestandsNaam = bestandsNaam.split("\\.")[0];

            if (bestandsNaam.equalsIgnoreCase("annuleer")) {
                quotations = new ArrayList<>();
                break;
            }

            quotations = Quotation.loadQuatations("./saved/quotations/", bestandsNaam);
            if (quotations == null)
                System.out.println("Kon de quotations niet laden probeer het opnieuw. of annuleer door annuleer te typen.");
        }

        quotationList.addAll(quotations);
    }

    public void offertesPrinten() {
        System.out.println();
        System.out.println("———————————————————————————————————————————————————");
        System.out.println("                 Geladen Offertes                   ");
        System.out.println("———————————————————————————————————————————————————");
        this.quotationList.forEach(
                quotation -> {
                    System.out.println();
                    System.out.println("Boat: " + quotation.getBoat().getName());
                    System.out.println("Klant: " + quotation.getKlant().getNaam());
                    System.out.println();
                }
        );

        System.out.println("———————————————————————————————————————————————————");
    }


    private Klant createKlant() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Voer de klantgegevens in:");
        System.out.print("Uw naam: ");
        String naam = scanner.nextLine();
        System.out.print("Bedrijfsnaam: ");
        String bedrijfsnaam = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Straat: ");
        String straat = scanner.nextLine();
        System.out.print("Stad: ");
        String stad = scanner.nextLine();

        System.out.print("Postcode: ");
        String postcode = scanner.nextLine();

        System.out.println("Kies een klanttype:");
        for (int i = 0; i < KlantType.getKlantTypeList().size(); i++) {
            KlantType klanttype = KlantType.getKlantTypeList().get(i);
            System.out.printf("%d. %s (Korting: %.2f%%)\n", i + 1, klanttype.getNaam(), klanttype.getKorting());
        }
        System.out.print("Kies het klanttype nummer: ");
        int klanttypeIndex = scanner.nextInt() - 1;
        KlantType klanttype = KlantType.getKlantTypeList().get(klanttypeIndex);

        return new Klant(bedrijfsnaam, email, stad, klanttype, postcode, naam, straat);
    }

    private void addNewBoat() {
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

    private void addNewOption() {
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


    private void addNewKlantType() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Voer de naam van het nieuwe klanttype in: ");
        String klantTypeName = scanner.nextLine();

        System.out.print("Voer de korting voor het nieuwe klanttype in (in procenten): ");
        double klantTypeKorting = scanner.nextDouble();

        KlantType newKlantType = new KlantType(klantTypeName, klantTypeKorting);
        KlantType.getKlantTypeList().add(newKlantType);
        System.out.println("Nieuw klanttype is succesvol toegevoegd.");
    }

    public void printCurrentQuotationOption() {
        System.out.println("———————————————————————————————————————————————————");
        System.out.println("                Huidige Optie lijst                ");
        System.out.println("———————————————————————————————————————————————————");

        for (int i = 0; i < currentQuotation.getOptionList().size(); i++) {
            Option option = currentQuotation.getOptionList().get(i);
            if (option != null) {
                System.out.printf("%2d. %-25s: %.2f\n", (i + 1), currentQuotation.getOptionList().get(i).getName(), currentQuotation.getOptionList().get(i).getPrice());
            } else {
                System.out.println("empty");
            }
        }
    }

    public void editQuotation() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welke offerte wilt u bewerken?");
        System.out.println();

        System.out.println("———————————————————————————————————————————————————");
        System.out.println("                   Offerte lijst                   ");
        System.out.println("———————————————————————————————————————————————————");

        for (int i = 0; i < quotationList.size(); i++) {
            System.out.println((i + 1) + ". " + quotationList.get(i).getBoat().getName());
        }

        System.out.println("———————————————————————————————————————————————————");

        System.out.println();

        int quotationNumber = 0;
        try {
            quotationNumber = scanner.nextInt();
            System.out.println();
        } catch (InputMismatchException e) {
            System.out.println();
            System.out.println("U heeft geen geldig nummer ingevoerd.");
            System.out.println();
        }

        if (quotationNumber > quotationList.size()) {
            System.out.println("De offerte met dit nummer bestaat niet.");
            return;
        }

        Quotation quotation = quotationList.get((quotationNumber - 1));
        this.setCurrentQuotation(quotation);
        int optionNumber;

        System.out.println();

        printCurrentQuotationOption();

        System.out.println("———————————————————————————————————————————————————");
        System.out.println();

        System.out.println("Welk optie wilt u toevoegen");

        System.out.println("———————————————————————————————————————————————————");
        System.out.println("               Optie lijst voor " + currentQuotation.getBoat().getName() + "                   ");
        System.out.println("———————————————————————————————————————————————————");

        for (int i = 0; i < ProductList.getOptionList().size(); i++) {
            if (currentQuotation.getBoat().equals(ProductList.getOptionList().get(i).getBoat()))

                System.out.printf("%2d. %-25s: %.2f\n", (i + 1), ProductList.getOptionList().get(i).getName(), ProductList.getOptionList().get(i).getPrice());

        }

        System.out.print("Optie: ");
        optionNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.println();

        if (optionNumber > ProductList.getOptionList().size()) {
            System.out.println("De optie met dit nummer bestaat niet.");
            return;
        }

        for (int i = 0; i < ProductList.getOptionList().size(); i++) {
            if (currentQuotation.getBoat().equals(ProductList.getOptionList().get(i).getBoat()))
                if (optionNumber == (i + 1)) {
                    currentQuotation.addOption(ProductList.getOptionList().get(i));
                    System.out.println("\"" + currentQuotation.getOptionList().get(i).getName() + "\"" + " is toegevoegd aan de offerte.");
                }
        }

        System.out.println();
        System.out.println("Welk optie wilt u verwijderen");

        printCurrentQuotationOption();

        System.out.print("Optie: ");
        optionNumber = scanner.nextInt();
        System.out.println();

        if (optionNumber > currentQuotation.getOptionList().size()) {
            System.out.println("De optie met dit nummer zit niet in de huidige optie lijst.");
            return;
        }

        for (int i = 0; i < currentQuotation.getOptionList().size(); i++) {
            if (optionNumber == (i + 1)) {
                System.out.println("\"" + currentQuotation.getOptionList().get(i).getName() + "\"" + " is verwijderd van de offerte.");
                currentQuotation.removeOption(i);

            }
        }


        printCurrentQuotationOption();

        System.out.println();
        System.out.println("Welke prijs wil jij bewerken?");

        printCurrentQuotationOption();

        System.out.print("Optie: ");
        optionNumber = scanner.nextInt();
        scanner.nextLine();

        if (optionNumber > currentQuotation.getOptionList().size()) {
            System.out.println("De optie met dit nummer zit niet in de huidige optie lijst.");
            return;
        }

        System.out.print("Nieuwe prijs: ");
        double newPrice = scanner.nextDouble();
        scanner.nextLine();

        for (int i = 0; i < currentQuotation.getOptionList().size(); i++) {
            if (optionNumber == (i + 1)) {
                currentQuotation.getOptionList().get(i).setPrice(newPrice);
                System.out.println("De prijs van \"" + currentQuotation.getOptionList().get(i).getName() + "\"" + " is gewijzigd.");
            }
        }

        printCurrentQuotationOption();

        currentQuotation.saveQuoatation("./saved/quotations/", createKlant().getNaam());
    }

        public void setQuotationList(List<Quotation> quotationList) {
        this.quotationList = quotationList;
    }

    public Quotation createNewQuotation(Boat boat, Klant klant) {
        if(boat == null) return null;
        Quotation quotation = new Quotation(boat, klant);
        this.quotationList.add(quotation);
        return quotation;
    }
    public Quotation createNewQuotation(String boatName, Klant klant) {
        if(boatName == null) return null;
        Quotation quotation = new Quotation(Utility.getBoatByName(boatName), klant);
        if(quotation == null) return null;
        this.quotationList.add(quotation);
        return quotation;
    }

    public void setCurrentQuotation(Quotation quotation) {
        this.currentQuotation = quotation;
    }





}
