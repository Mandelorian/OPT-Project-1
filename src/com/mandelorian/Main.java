package com.mandelorian;

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

        System.out.printf("Totaal price: €" + "%.2f", currentQuotation.getTotalPrice());
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        currentQuotation.printQuotation();
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
