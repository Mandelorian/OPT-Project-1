package com.mandelorian.library;

import com.mandelorian.boat.Boat;
import com.mandelorian.boat.Categorie;
import com.mandelorian.boat.Item;
import com.mandelorian.boat.Option;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Library {


    private static List<Categorie> categorieList = new ArrayList<>();
    private static List<Boat> boatList = new ArrayList<>();
    private static List<Option> optionList = new ArrayList<>();

    public static List<Categorie> getCategorieList() {return categorieList;}
    public static List<Boat> getBoatList() {return boatList;}
    public static List<Option> getOptionList() {return optionList;}

    // Add here the categories
    public static void setDefaultCategories() {
        // categorieList.add(new Categorie("Example"));
        categorieList.add(new Categorie("Grote boten"));
        categorieList.add(new Categorie("Middelgrote boten"));
        categorieList.add(new Categorie("Kleiner boten"));
    }

    // Add here all the different types of boats
    public static void setDefaultBoatList() {
        // boatList.add(new Boat("Example", getCategorie("ExampleCategorie")));

        //grote boten
        boatList.add(new Boat("Jacht", 48000000, Utility.getCategorieByName("Grote boten")));
        boatList.add(new Boat("Cruise schip", 900000000 ,Utility.getCategorieByName("Grote boten")));
        boatList.add(new Boat("Containerschip",180000000, Utility.getCategorieByName("Grote boten")));
        boatList.add(new Boat("Tanker",  180000000, Utility.getCategorieByName("Grote boten")));
        boatList.add(new Boat("Veerboot", 400_000_000, Utility.getCategorieByName("Grote boten")));

        //middelgrote boten
        boatList.add(new Boat("Speedboot ",20_000_000, Utility.getCategorieByName("Middelgrote boten")));
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

        String[] boten = {"", "Vissersboot", "Pontonboot", "Sleepboot", "Motorjacht", "Kano", "Kayak", "Roeiboot", "Zeilbootje"};
        String[][] botenOpties = {
                {"ligstoelen", "tafels", "audio systeem", "barbeque"},
                {"sleephaken", "boegschroef", "navigatie-verlichting"},
                {"keukenapparatuur", "airconditioning-motorjacht", "navigatie-apparatuur"},
                {"extra peddels kano", "extra zitplaatsen kano", "waterdichte opbergvakken kano"},
                {"extra peddels kayak", "extra zitplaatsen kayak", "waterdichte opbergvakken kayak"},
                {"roer", "extra peddels", "extra zitplaatsen"},
                {"extra zeilen", "navigatie-verlichting", "noodmotor"}
        };

        double[] prijzenOptie = {
                //Pontonboot
                5000, 2000, 10_000, 3000,
                //Sleepboot
                100_000, 50_000, 10_000,
                //Motorjacht
                50_000, 100_000, 20_000,

                //kano opties
                50, 20, 10,
                //Kayak
                50, 20, 10,
                //Roeiboot
                50, 50, 20,
                //Zeilbootje
                500, 100, 1000
        };


        //opties voor jacht
        //optionList.add(new Option("Airconditioning", null, getBoatByName("Jacht")));
    }

}
