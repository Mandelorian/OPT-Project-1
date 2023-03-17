package com.mandelorian;

import com.mandelorian.boat.*;
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
    private Quotation currentQuatation;

    public Program() {
        this.quotationList = new ArrayList<>();
    }

    public void createNewQuatation(Boat boat) {
        this.quotationList.add(new Quotation(boat));
    }
    public void createNewQuatation(String boatName) {
        this.quotationList.add(new Quotation(Memory.getBoatByName(boatName)));
    }

    public void setCurrentQuatation(Quotation quatation) {
        this.currentQuatation = quatation;
    }


    public void start() {
        System.out.println("Starting program.");
        System.out.println(Memory.getOptionList().get(16).getName());
    }
}
