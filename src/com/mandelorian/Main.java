package com.mandelorian;

import com.mandelorian.boat.Boat;
import com.mandelorian.quotation.Quotation;

import java.util.ArrayList;
import java.util.List;

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

    public void createNewQuotation(Boat boat) {
        this.quotationList.add(new Quotation(boat));
    }
    public void createNewQuotation(String boatName) {
        this.quotationList.add(new Quotation(Memory.getBoatByName(boatName)));
    }

    public void setCurrentQuotation(Quotation quotation) {
        this.currentQuotation = quotation;
    }


    public void start() {
        System.out.println("Starting program.");
    }
}
