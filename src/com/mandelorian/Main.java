package com.mandelorian;

import com.google.gson.Gson;
import com.mandelorian.library.Categorie;
import com.mandelorian.library.SavedFiles;
import com.mandelorian.product.Boat;
import com.mandelorian.library.Utility;
import com.mandelorian.product.Option;
import com.mandelorian.product.ProductList;
import com.mandelorian.quotation.Quotation;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Categorie.setDefaultCategories();
        ProductList.setDefaultBoatList();
        ProductList.setDefaultOptionList();


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

        System.out.println("Welkom!");
        System.out.println("Welk type boot wilt u kopen?");
        System.out.println();

        System.out.println("———————————————————————————————————————————————————");
        System.out.println("                      Boot lijst                   ");
        System.out.println("———————————————————————————————————————————————————");

        Boat boat = null;
        for(int i = 0; i < ProductList.getBoatList().size(); i++) {
            System.out.println((i + 1) + ". " + ProductList.getBoatList().get(i).getName());
        }
        System.out.println("———————————————————————————————————————————————————");
        System.out.println();

        while (boat == null) {
            System.out.print("Kies de bootnummer van de boot die je wil: ");
            int boatNumber = scanner.nextInt();
            System.out.println();

            if(boatNumber > ProductList.getBoatList().size()) {
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

        for(int i = 0; i < boat.getPossibleOptions().size(); i++) {
            if(i > 0)      System.out.println();
               System.out.println("Optie-nummer: " + (i + 1));
               System.out.println("  Optie: " + boat.getPossibleOptions().get(i).getName());
               if(boat.getPossibleOptions().get(i).getDescription() != null) System.out.println("Beschrijving: €" + Utility.formatPrice(ProductList.getOptionList().get(i).getPrice()));
               System.out.println("  Prijs: € " + Utility.formatPrice(boat.getPossibleOptions().get(i).getPrice()));
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

                if(optionNummer > boat.getPossibleOptions().size()) {
                    System.out.println("De optie met dit nummer bestaat niet.");
                    continue;
                }

                currentQuotation.addOption(boat.getPossibleOptions().get((optionNummer - 1)));;
        }


        //totaal prijs

        System.out.println("———————————————————————————————————————————————————");
        System.out.println("               totaal prijs               ");
        System.out.println("———————————————————————————————————————————————————");

        System.out.println();

        System.out.println("Boat: €" + Utility.formatPrice(this.currentQuotation.getBoat().getPrice()));

        System.out.println();
        System.out.println("Options:");

        currentQuotation.getOptionList().forEach(option1 -> {
            System.out.println( "  " + option1.getName() + ": €" + Utility.formatPrice(option1.getPrice()));
        });

        System.out.println();
        System.out.println("Totaal price: €" + Utility.formatPrice(currentQuotation.getTotalPrice()));

        System.out.println();
        System.out.println("———————————————————————————————————————————————————");
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
