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
        program.menu();
    }

}


class Program {
    private List<Quotation> quotationList;
    private Quotation currentQuotation;

    public Program() {
        this.quotationList = new ArrayList<>();
    }



    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("Welkom!");


        do {
            System.out.println("\nMenu:");
            System.out.println("1. Nieuwe klanttype toevoegen");
            System.out.println("2. Nieuwe boot toevoegen");
            System.out.println("3. Nieuwe optie toevoegen");
            System.out.println("4. Offerte maken");
            System.out.println("5. Afsluiten");
            System.out.print("Kies een optie: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addNewKlantType();
                    break;
                case 2:
                    addNewBoat();
                    break;
                case 3:
                    addNewOption();
                    break;
                case 4:
                    offerteMaken();
                    break;
            }
        } while (choice != 5);
    }






    public void offerteMaken() {
        Scanner scanner = new Scanner(System.in);

        Klant klant= createKlant();


        System.out.println("Welk type boot wilt u kopen?");
        System.out.println();

        System.out.println("———————————————————————————————————————————————————");
        System.out.println("                      Boot lijst                   ");
        System.out.println("———————————————————————————————————————————————————");

        Boat boat = null;
        for(int i = 0; i < ProductList.getBoatList().size(); i++) {
            //System.out.println((i + 1) + ". " + ProductList.getBoatList().get(i).getName());
            System.out.printf("%2d. %s\n", (i + 1), ProductList.getBoatList().get(i).getName());
        }

        System.out.println("———————————————————————————————————————————————————");

        System.out.println();

        while (boat == null) {
            System.out.print("Kies de bootnummer van de boot die je wil: ");
            int boatNumber = scanner.nextInt();
            System.out.println();

            if(boatNumber >= ProductList.getBoatList().size()) {
                System.out.println("De boot met dit nummer bestaat niet.");
                continue;
            }

            boat = ProductList.getBoatList().get((boatNumber - 1));
        }

        //Utility.clearScreen();
        this.setCurrentQuotation(this.createNewQuotation(boat));
        //lijst opties

        System.out.println("Welk optie's wilt u toevoegen");
        System.out.println();

        System.out.println("———————————————————————————————————————————————————");
        System.out.println("                     Optie lijst                   ");
        System.out.println("———————————————————————————————————————————————————");


        for(int i = 0; i < ProductList.getOptionList().size(); i++) {

           if (ProductList.getOptionList().get(i).getBoat().getName().compareTo(boat.getName())==0){
                //System.out.println((i + 1) + ". " + ProductList.getOptionList().get(i).getName() + " Prijs: "+ProductList.getOptionList().get(i).getPrice()+" Beschrijving: " +ProductList.getOptionList().get(i).getDescription());
                //System.out.printf("%2d. %-42s:\n", (i + 1), ProductList.getOptionList().get(i).getName());
               System.out.println((i + 1) + ". " + ProductList.getOptionList().get(i).getName());
               System.out.println("   Prijs: "+ProductList.getOptionList().get(i).getPrice());
               System.out.println("   Beschrijving: " +ProductList.getOptionList().get(i).getDescription());
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
                option =0;
                continue;
            }

                if(optionNummer >= ProductList.getOptionList().size()) {
                    System.out.println("De optie met dit nummer bestaat niet.");
                    continue;
                }
                currentQuotation.addOption(ProductList.getOptionList().get((optionNummer - 1)));
        }


        //totaal prijs

        System.out.println("———————————————————————————————————————————————————");
        System.out.println("               totaal prijs               ");
        System.out.println("———————————————————————————————————————————————————");

        System.out.println();

        System.out.println("Toegepaste Korting percentage: "+klant.getKlanttype().getKorting() +"%");
        System.out.printf("Totaal price: €" + "%.2f",currentQuotation.getTotalPrice()- (currentQuotation.getTotalPrice()*(klant.getKlanttype().getKorting()/100)));
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");

        currentQuotation.printQuotation(klant);
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

        return new Klant(bedrijfsnaam, email, stad, klanttype, postcode,naam,straat);
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

        ProductList.addBoat(boatName,boatPrice,category);
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

        ProductList.addOption(optionName,optionPrice,optionDescription,boat);
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






    public void setQuotationList(List<Quotation> quotationList) {
        this.quotationList = quotationList;
    }

    public Quotation createNewQuotation(Boat boat) {
        if(boat == null) return null;
        Quotation quotation = new Quotation(boat);
        this.quotationList.add(quotation);
        return quotation;
    }
    public Quotation createNewQuotation(String boatName) {
        if(boatName == null) return null;
        Quotation quotation = new Quotation(Utility.getBoatByName(boatName));
        if(quotation == null) return null;
        this.quotationList.add(quotation);
        return quotation;
    }

    public void setCurrentQuotation(Quotation quotation) {
        this.currentQuotation = quotation;
    }





}
