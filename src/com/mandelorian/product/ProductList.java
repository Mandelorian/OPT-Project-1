package com.mandelorian.product;

import com.mandelorian.library.Utility;

import java.util.ArrayList;
import java.util.List;

public class ProductList {


    private static List<Boat> boatList = new ArrayList<>();
    private static List<Option> optionList = new ArrayList<>();


    public static List<Boat> getBoatList() {return boatList;}
    public static List<Option> getOptionList() {return optionList;}




    // Add here the categories


    // Add here all the different types of boats
    public static void setDefaultBoatList() {
        // boatList.add(new Boat("Example", getCategorie("ExampleCategorie")));
        boatList = new ArrayList<>();

        //grote boten
        boatList.add(new Boat("Jacht", 48000000, Utility.getCategorieByName("Grote boten")));
        boatList.add(new Boat("Cruise schip", 900000000 ,Utility.getCategorieByName("Grote boten")));
        boatList.add(new Boat("Containerschip",180000000, Utility.getCategorieByName("Grote boten")));
        boatList.add(new Boat("Tanker",  180000000, Utility.getCategorieByName("Grote boten")));
        boatList.add(new Boat("Veerboot", 400_000_000, Utility.getCategorieByName("Grote boten")));

        //middelgrote boten
        boatList.add(new Boat("Speedboot",20_000_000, Utility.getCategorieByName("Middelgrote boten")));
        boatList.add(new Boat("Vissersboot",500_000_000, Utility.getCategorieByName("Middelgrote boten")));
        boatList.add(new Boat("Pontonboot", 1_000_000, Utility.getCategorieByName("Middelgrote boten")));
        boatList.add(new Boat("Sleepboot", 1_500_000, Utility.getCategorieByName("Middelgrote boten")));
        boatList.add(new Boat("Motorjacht", 3_000_000, Utility.getCategorieByName("Middelgrote boten")));

        //Kleine boten
        boatList.add(new Boat("Kano",500, Utility.getCategorieByName("Kleine boten")));
        boatList.add(new Boat("Kayak", 700,Utility.getCategorieByName("Kleine boten")));
        boatList.add(new Boat("Roeiboot",1000,Utility.getCategorieByName("Kleine boten")));
        boatList.add(new Boat("Zeilbootje",5000,Utility.getCategorieByName("Kleine boten")));




    }

    // set here all the options
    public static void setDefaultOptionList() {
        // optionList.add(new Option("Example", "description"));
        optionList = new ArrayList<>();

        // Jacht
        optionList.add(new Option("Airconditioning-jacht", 500_000,null, Utility.getBoatByName("Jacht")));
        optionList.add(new Option("jacuzzi", 200_000,null, Utility.getBoatByName("Jacht")));
        optionList.add(new Option("helikopterplatform", 1_500_000,null, Utility.getBoatByName("Jacht")));
        optionList.add(new Option("sauna", 100_000,null, Utility.getBoatByName("Jacht")));
        optionList.add(new Option("bioscoop", 350_000,null, Utility.getBoatByName("Jacht")));

        optionList.add(new Option("zwembad", 10_000_000,null, Utility.getBoatByName("Cruise schip")));
        optionList.add(new Option("spa", 35_000_000,null, Utility.getBoatByName("Cruise schip")));
        optionList.add(new Option("theater", 4_000_000,null, Utility.getBoatByName("Cruise schip")));
        optionList.add(new Option("casino", 68_000_000,null, Utility.getBoatByName("Cruise schip")));
        optionList.add(new Option("fitnessruimte", 5_000_000,null, Utility.getBoatByName("Cruise schip")));

        optionList.add(new Option("meer laadvermogen", 48_000_000,null, Utility.getBoatByName("Containerschip")));
        optionList.add(new Option("meer container capaciteit", 80_000_000,null, Utility.getBoatByName("Containerschip")));
        optionList.add(new Option("nieuwste professionele navigatieapparatuur", 4_800_000,null, Utility.getBoatByName("Containerschip")));

        optionList.add(new Option("extra tankcapaciteit", 40_000_000,null, Utility.getBoatByName("Tanker")));
        optionList.add(new Option("meer laadpompen", 10_000_000,null, Utility.getBoatByName("Tanker")));
        optionList.add(new Option("extra geavanceerd brandblusinstallatie", 4_000_000,null, Utility.getBoatByName("Tanker")));


        optionList.add(new Option("meer passagierscapaciteit", 2_000_000,null, Utility.getBoatByName("Veerboot")));
        optionList.add(new Option("auto- en vrachtcapaciteit", 3_000_000,null, Utility.getBoatByName("Veerboot")));
        optionList.add(new Option("entertainment voorzieningen", 1_000_000,null, Utility.getBoatByName("Veerboot")));

        optionList.add(new Option("krachtigere motor", 50_000,null, Utility.getBoatByName("Speedboot")));
        optionList.add(new Option("audio systeem", 10_000,null, Utility.getBoatByName("Speedboot")));
        optionList.add(new Option("GPS-navigatie", 5000,null, Utility.getBoatByName("Speedboot")));

        optionList.add(new Option("kraan", 50_000,null, Utility.getBoatByName("Vissersboot")));
        optionList.add(new Option("navigatie-apparatuur", 20_000,null, Utility.getBoatByName("Vissersboot")));
        optionList.add(new Option("koelopslag", 30_000,null, Utility.getBoatByName("Vissersboot")));

        optionList.add(new Option("ligstoelen", 5000,null, Utility.getBoatByName("Pontonboot")));
        optionList.add(new Option("tafels", 2000,null, Utility.getBoatByName("Pontonboot")));
        optionList.add(new Option("audio systeem", 10_000,null, Utility.getBoatByName("Pontonboot")));
        optionList.add(new Option("barbeque", 3000,null, Utility.getBoatByName("Pontonboot")));

        optionList.add(new Option("sleephaken", 100_000,null, Utility.getBoatByName("Sleepboot")));
        optionList.add(new Option("boegschroef", 50_000,null, Utility.getBoatByName("Sleepboot")));
        optionList.add(new Option("navigatie-verlichting", 10_000,null, Utility.getBoatByName("Sleepboot")));

        optionList.add(new Option("keukenapparatuur", 50_000,null, Utility.getBoatByName("Motorjacht")));
        optionList.add(new Option("airconditioning-motorjacht", 100_000,null, Utility.getBoatByName("Motorjacht")));
        optionList.add(new Option("navigatie-apparatuur", 20_000,null, Utility.getBoatByName("Motorjacht")));

        optionList.add(new Option("extra peddels kano", 50,null, Utility.getBoatByName("Kano")));
        optionList.add(new Option("extra zitplaatsen kano", 20,null, Utility.getBoatByName("Kano")));
        optionList.add(new Option("waterdichte opbergvakken kano", 10,null, Utility.getBoatByName("Kano")));

        optionList.add(new Option("extra peddels kayak", 50,null, Utility.getBoatByName("Kayak")));
        optionList.add(new Option("extra zitplaatsen kayak", 20,null, Utility.getBoatByName("Kayak")));
        optionList.add(new Option("waterdichte opbergvakken kayak", 10,null, Utility.getBoatByName("Kayak")));

        optionList.add(new Option("roer", 50,null, Utility.getBoatByName("Roeiboot")));
        optionList.add(new Option("extra peddels", 50,null, Utility.getBoatByName("Roeiboot")));
        optionList.add(new Option("extra zitplaatsen", 20,null, Utility.getBoatByName("Roeiboot")));

        optionList.add(new Option("extra zeilen", 500,null, Utility.getBoatByName("Zeilbootje")));
        optionList.add(new Option("navigatie-verlichting", 100,null, Utility.getBoatByName("Zeilbootje")));
        optionList.add(new Option("noodmotor", 1000,null, Utility.getBoatByName("Zeilbootje")));

        //opties voor jacht
        //optionList.add(new Option("Airconditioning", null, getBoatByName("Jacht")));
    }

}
