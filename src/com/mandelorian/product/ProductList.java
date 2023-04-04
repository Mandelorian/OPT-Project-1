package com.mandelorian.product;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mandelorian.library.Categorie;
import com.mandelorian.library.Utility;
import com.mandelorian.quotation.Quotation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProductList {


    private static List<Boat> boatList = new ArrayList<>();
    private static List<Option> optionList = new ArrayList<>();


    public static List<Boat> getBoatList() {return boatList;}
    public static List<Option> getOptionList() {return optionList;}



    //niuew boat en option toevoegen

    public static void  addBoat(String name, double price, Categorie category) {
        boatList.add(new Boat(name, price, category));
    }



    public static void addOption(String name, double price, String description, Boat boat) {
        optionList.add(new Option(name, price, description, boat));
    }




    // Add here the categories


    // Add here all the different types of boats
    public static void setDefaultBoatList() {
        // boatList.add(new Boat("Example", getCategorie("ExampleCategorie")));

        if(new File("./saved/boats.json").exists()) {
            JsonObject object = Utility.getJSONData("./saved/", "boats");
            if (object == null) return;
            object.get("boat-list").getAsJsonArray().forEach(element -> {
                JsonObject boatObject = element.getAsJsonObject();
                String name = boatObject.get("name").getAsString();
                double price = boatObject.get("price").getAsDouble();
                String categorieName = boatObject.get("categorie").getAsString();

                boatList.add(new Boat(name, price, Utility.getCategorieByName(categorieName)));
            });
        }


        if(!boatList.isEmpty()) return;

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

       if(new File("./saved/options.json").exists()) {
           JsonObject object = Utility.getJSONData("./saved/", "options");
           if(object == null) return;
           object.get("option-list").getAsJsonArray().forEach(element -> {
               JsonObject optionObject = element.getAsJsonObject();
               String name = optionObject.get("name").getAsString();
               double price = optionObject.get("price").getAsDouble();
               String boatName = optionObject.get("boat").getAsString();
               String description = optionObject.get("description").getAsString();

               optionList.add(new Option(name, price, description, Utility.getBoatByName(boatName)));
           });
       }


        if(!optionList.isEmpty()) return;
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

    public static String getBoatsJSONString() {
       String fileString = "";

        for (Boat boat : boatList) {
            fileString += (fileString.equalsIgnoreCase("")) ? boat.toString() : "," + boat.toString();
        }

        return  "{" + "\n"
                + " \"boat-list\": "
                + "[" + fileString + "\n ]"
                + "\n"
                + "}";
    }

    public static String getOptionsJSONString() {
        String fileString = "";

        for (Option option : optionList) {

            fileString += (fileString.equalsIgnoreCase("")) ? option.toString() : "," + option.toString();
        }

        return  "{" + "\n"
                + " \"option-list\": "
                + "[" + fileString + "\n ]"
                + "\n"
                + "}";
    }

}
