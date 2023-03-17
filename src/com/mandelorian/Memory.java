package com.mandelorian;

import com.mandelorian.boat.Boat;
import com.mandelorian.boat.Categorie;
import com.mandelorian.boat.Item;
import com.mandelorian.boat.Option;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Memory {


    private static List<Categorie> categorieList = new ArrayList<>();
    private static HashMap<Item, Double> priceList = new HashMap<>();
    private static List<Boat> boatList = new ArrayList<>();
    private static List<Option> optionList = new ArrayList<>();

    public static List<Categorie> getCategorieList() {return categorieList;}
    public static HashMap<Item, Double> getPriceList() {return priceList;}
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
        boatList.add(new Boat("Jacht", getCategorieByName("Grote boten")));
        boatList.add(new Boat("Cruise schip", getCategorieByName("Grote boten")));
        boatList.add(new Boat("Containerschip", getCategorieByName("Grote boten")));
        boatList.add(new Boat("Tanker", getCategorieByName("Grote boten")));
        boatList.add(new Boat("Veerboot", getCategorieByName("Grote boten")));

        //middelgrote boten
        boatList.add(new Boat("Speedboot ", getCategorieByName("Middelgrote boten")));
        boatList.add(new Boat("Vissersboot", getCategorieByName("Middelgrote boten")));
        boatList.add(new Boat("Pontonboot", getCategorieByName("Middelgrote boten")));
        boatList.add(new Boat("Sleepboot", getCategorieByName("Middelgrote boten")));
        boatList.add(new Boat("Motorjacht", getCategorieByName("Middelgrote boten")));

        //Kleine boten
        boatList.add(new Boat("Kano", getCategorieByName("Kleine boten")));
        boatList.add(new Boat("Kayak", getCategorieByName("Kleine boten")));
        boatList.add(new Boat("Roeiboot", getCategorieByName("Kleine boten")));
        boatList.add(new Boat("Zeilbootje", getCategorieByName("Kleine boten")));


    }

    // set here all the options
    public static void setDefaultOptionList() {
        // optionList.add(new Option("Example", "description"));

        String[] boten = {"Jacht", "Cruise schip", "Containerschip", "Tanker", "Veerboot", "Speedboot", "Vissersboot", "Pontonboot", "Sleepboot", "Motorjacht", "Kano", "Kayak", "Roeiboot", "Zeilbootje"};
        String[][] botenOpties = {
                {"Airconditioning-jacht", "jacuzzi", "helikopterplatform", "sauna", "bioscoop"},
                {"zwembad", "spa", "theater", "casino", "fitnessruimte"},
                {"meer laadvermogen", "meer container capaciteit", "nieuwste professionele navigatieapparatuur"},
                {"extra tankcapaciteit", "meer laadpompen", "extra geavanceerd brandblusinstallatie"},
                {"meer passagierscapaciteit", "auto- en vrachtcapaciteit", "entertainment voorzieningen"},
                {"krachtigere motor", "audio systeem", "GPS-navigatie"},
                {"kraan", "navigatie-apparatuur", "koelopslag"},
                {"ligstoelen", "tafels", "audio systeem", "barbeque"},
                {"sleephaken", "boegschroef", "navigatie-verlichting"},
                {"keukenapparatuur", "airconditioning-motorjacht", "navigatie-apparatuur"},
                {"extra peddels kano", "extra zitplaatsen kano", "waterdichte opbergvakken kano"},
                {"extra peddels kayak", "extra zitplaatsen kayak", "waterdichte opbergvakken kayak"},
                {"roer", "extra peddels", "extra zitplaatsen"},
                {"extra zeilen", "navigatie-verlichting", "noodmotor"}
        };



        for(int i=0; i<botenOpties.length; i++) {
            for(int j=0; j<botenOpties[i].length; j++) {
                optionList.add(new Option(botenOpties[i][j], null, getBoatByName(boten[i])));
            }
        }


        //opties voor jacht
        //optionList.add(new Option("Airconditioning", null, getBoatByName("Jacht")));




    }

    // Set here the pricelist
    public static void setDefaultPriceList() {
        //priceList.put(getBoatByName("Example"), 100.0);
        // priceList.put(getOptionByName("Example"), 100.0);
        double[] prijzenBoot = {
                48000000, 900000000, 180000000, 400_000_000,
                20_000_000, 500_000_000, 1_000_000, 1_500_000,
                1_500_000, 3_000_000, 500, 700, 1000, 5000
        };
        int i=0;
        for(Boat boat : boatList) {
            priceList.put(boat, prijzenBoot[i]);
            i++;
        }
        i=0;
        double[] prijzenOptie = {
                //Jacht opties
                500_000,200_000,1_500_000, 100_000, 350_000,
                //Cruise schip opties
                10_000_000, 35_000_000, 4_000_000, 68_000_000, 5_000_000,
                //Containerschip opties
                48_000_000, 80_000_000, 4_800_000,
                //Tanker
                40_000_000, 10_000_000, 4_000_000,
                //Veerboot
                2_000_000, 3_000_000, 1_000_000,

                //Speedboot opties
                50_000, 10_000, 5000,
                //Visserboot opties
                50_000, 20_000, 30_000,
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

        for(Option option : optionList) {
            priceList.put(option, prijzenOptie[i]);
            i++;
        }

    }

    public static Boat getBoatByName(String name) {
        Boat boat = null;

        for(Boat cb : boatList) {
            if(!cb.getName().equalsIgnoreCase(name)) continue;
            boat = cb;
            break;
        }

        return boat;
    }

    public static Option getOptionByName(String name) {
        Option option = null;

        for(Option co : optionList) {
            if(!co.getName().equalsIgnoreCase(name)) continue;
            option = co;
            break;
        }

        return option;
    }

    public static Categorie getCategorieByName(String name) {
        Categorie categorie = null;

        for(Categorie cc : categorieList) {
            if(!cc.getName().equalsIgnoreCase(name)) continue;
            categorie = cc;
            break;
        }

        return categorie;
    }
}
