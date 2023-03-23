package com.mandelorian;

import com.mandelorian.boat.Boat;
import com.mandelorian.quotation.Quotation;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Memory.setDefaultCategories();
        Memory.setDefaultOptionList();
        Memory.setDefaultBoatList();
        Memory.setDefaultPriceList();

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

    public Quotation createNewQuotation(Boat boat) {
        if(boat == null) return null;
        Quotation quotation = new Quotation(boat);
        this.quotationList.add(quotation);
        return quotation;
    }
    public Quotation createNewQuotation(String boatName) {
        if(boatName == null) return null;
        Quotation quotation = new Quotation(Memory.getBoatByName(boatName));
        if(quotation == null) return null;
        this.quotationList.add(quotation);
        return quotation;
    }

    public void setCurrentQuotation(Quotation quotation) {
        this.currentQuotation = quotation;
    }

    public void clearScreen() {
        for (int i = 0; i < 200; i++) {System.out.println();}
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }


    public void start() {
        System.out.println("Starting program.");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welkom!");
        System.out.println("Welk type boot wilt u kopen?");
        System.out.println();

        System.out.println("----------------------------------------");
        System.out.println("               Boot lijst               ");
        System.out.println("----------------------------------------");

        Boat boat = null;
        for(int i = 0; i < Memory.getBoatList().size(); i++) {
            System.out.println((i + 1) + ". " + Memory.getBoatList().get(i).getName());
        }

        System.out.println("----------------------------------------");

        System.out.println();
        //Print de bootlijst
        while (boat == null) {
            System.out.print("Kies de bootnummer van de boot die je wil: ");
            int boatNumber = scanner.nextInt();
            System.out.println();

            if (boatNumber >= Memory.getBoatList().size() || Memory.getBoatList().get((boatNumber - 1)) == null) {
                System.out.println("De boot met dit nummer bestaat niet.");
                continue;
            }

            boat = Memory.getBoatList().get((boatNumber - 1));
        }

        this.clearScreen();
        this.setCurrentQuotation(this.createNewQuotation(boat));
        //Print de gekozen boot
        System.out.println("U hebt gekozen voor bootnummer: " + currentQuotation.getBoat().getName());
    }
}
