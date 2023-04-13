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



    public static void addOption(String name, double price, String description, double milieuKorting, Boat boat) {
        optionList.add(new Option(name, price, description, milieuKorting, boat));
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
        boatList.add(new Boat("Jacht", 48_000_000, Utility.getCategorieByName("Grote boten")));
        boatList.add(new Boat("Cruise schip", 900_000_000 ,Utility.getCategorieByName("Grote boten")));
        boatList.add(new Boat("Containerschip",180_000_000, Utility.getCategorieByName("Grote boten")));
        boatList.add(new Boat("Tanker",  180_000_000, Utility.getCategorieByName("Grote boten")));
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
               double milieuKorting = optionObject.get("milieuKorting").getAsDouble();

               optionList.add(new Option(name, price, description, milieuKorting, Utility.getBoatByName(boatName)));
           });
       }


        if(!optionList.isEmpty()) return;
        // Jacht
        optionList.add(new Option("Airconditioning-jacht", 500_000,null,  Utility.getBoatByName("Jacht")));
        optionList.add(new Option("Jacuzzi", 200_000,null, Utility.getBoatByName("Jacht")));
        optionList.add(new Option("Helikopterplatform", 1_500_000,null, Utility.getBoatByName("Jacht")));
        optionList.add(new Option("Sauna", 100_000,null, Utility.getBoatByName("Jacht")));
        optionList.add(new Option("Bioscoop", 350_000,null, Utility.getBoatByName("Jacht")));
        optionList.add(new Option("Elektrische kookplaten", 20_000,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 10_000, Utility.getBoatByName("Jacht")));
        optionList.add(new Option("Duurzame uitlaat filters", 5_000_000,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 2_500_000, Utility.getBoatByName("Jacht")));
        optionList.add(new Option("Zonnepanelen", 10_000_000,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 5_000_000, Utility.getBoatByName("Jacht")));

        optionList.add(new Option("Zwembad", 10_000_000,null, Utility.getBoatByName("Cruise schip")));
        optionList.add(new Option("Spa", 35_000_000,null, Utility.getBoatByName("Cruise schip")));
        optionList.add(new Option("Theater", 4_000_000,null, Utility.getBoatByName("Cruise schip")));
        optionList.add(new Option("Casino", 68_000_000,null, Utility.getBoatByName("Cruise schip")));
        optionList.add(new Option("Fitnessruimte", 5_000_000,null, Utility.getBoatByName("Cruise schip")));
        optionList.add(new Option("Duurzame uitlaat filters", 10_000_000,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 5_000_000, Utility.getBoatByName("Cruise schip")));
        optionList.add(new Option("Zonnepanelen", 10_000_000,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 5_000_000, Utility.getBoatByName("Cruise schip")));

        optionList.add(new Option("Meer laadvermogen", 48_000_000,null, Utility.getBoatByName("Containerschip")));
        optionList.add(new Option("Meer container capaciteit", 80_000_000,null, Utility.getBoatByName("Containerschip")));
        optionList.add(new Option("Professioneel navigatieapparatuur", 4_800_000,null, Utility.getBoatByName("Containerschip")));
        optionList.add(new Option("Duurzame uitlaat filters", 10_000_000,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 5_000_000, Utility.getBoatByName("Containerschip")));
        optionList.add(new Option("Zonnepanelen", 10_000_000,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 5_000_000, Utility.getBoatByName("Containerschip")));

        optionList.add(new Option("Extra tankcapaciteit", 40_000_000,null, Utility.getBoatByName("Tanker")));
        optionList.add(new Option("Meer laadpompen", 10_000_000,null, Utility.getBoatByName("Tanker")));
        optionList.add(new Option("Extra geavanceerd brandblusinstallatie", 4_000_000,null, Utility.getBoatByName("Tanker")));
        optionList.add(new Option("Duurzame uitlaat filters", 10_000_000,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 5_000_000, Utility.getBoatByName("Tanker")));
        optionList.add(new Option("Zonnepanelen", 10_000_000,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 5_000_000, Utility.getBoatByName("Tanker")));

        optionList.add(new Option("Meer passagierscapaciteit", 2_000_000,null, Utility.getBoatByName("Veerboot")));
        optionList.add(new Option("Auto- en vrachtcapaciteit", 3_000_000,null, Utility.getBoatByName("Veerboot")));
        optionList.add(new Option("Entertainment voorzieningen", 1_000_000,null, Utility.getBoatByName("Veerboot")));
        optionList.add(new Option("Duurzame uitlaat filters", 10_000_000,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 5_000_000, Utility.getBoatByName("Veerboot")));
        optionList.add(new Option("Zonnepanelen", 10_000_000,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 5_000_000, Utility.getBoatByName("Veerboot")));

        optionList.add(new Option("Krachtigere motor", 50_000,null, Utility.getBoatByName("Speedboot")));
        optionList.add(new Option("Audio systeem", 10_000,null, Utility.getBoatByName("Speedboot")));
        optionList.add(new Option("GPS-navigatie", 5000,null, Utility.getBoatByName("Speedboot")));
        optionList.add(new Option("Elektrische motor", 50_000,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 30_000, Utility.getBoatByName("Speedboot")));
        optionList.add(new Option("Duurzamer/gerecycled rubber", 20_000,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 10_000, Utility.getBoatByName("Speedboot")));

        optionList.add(new Option("Kraan", 50_000,null, Utility.getBoatByName("Vissersboot")));
        optionList.add(new Option("Navigatie-apparatuur", 20_000,null, Utility.getBoatByName("Vissersboot")));
        optionList.add(new Option("Koelopslag", 30_000,null, Utility.getBoatByName("Vissersboot")));
        optionList.add(new Option("Elektrische motor", 50_000,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 30_000, Utility.getBoatByName("Vissersboot")));
        optionList.add(new Option("Zonnepanelen", 100_000,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 50_000, Utility.getBoatByName("Vissersboot")));

        optionList.add(new Option("Ligstoelen", 5000,null, Utility.getBoatByName("Pontonboot")));
        optionList.add(new Option("Tafels", 2000,null, Utility.getBoatByName("Pontonboot")));
        optionList.add(new Option("Audio systeem", 10_000,null, Utility.getBoatByName("Pontonboot")));
        optionList.add(new Option("Barbeque", 3000,null, Utility.getBoatByName("Pontonboot")));
        optionList.add(new Option("Elektrische motor", 50_000,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 30_000, Utility.getBoatByName("Pontonboot")));
        optionList.add(new Option("Zonnepanelen", 100_000,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 50_000, Utility.getBoatByName("Pontonboot")));

        optionList.add(new Option("Sleephaken", 100_000,null, Utility.getBoatByName("Sleepboot")));
        optionList.add(new Option("Boegschroef", 50_000,null, Utility.getBoatByName("Sleepboot")));
        optionList.add(new Option("Navigatie-verlichting", 10_000,null, Utility.getBoatByName("Sleepboot")));
        optionList.add(new Option("Zonnepanelen", 100_000,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 50_000, Utility.getBoatByName("Sleepboot")));
        optionList.add(new Option("Duurzame uitlaatfilters", 400_000,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 250_000, Utility.getBoatByName("Sleepboot")));

        optionList.add(new Option("Keukenapparatuur", 50_000,null, Utility.getBoatByName("Motorjacht")));
        optionList.add(new Option("Airconditioning-motorjacht", 100_000,null, Utility.getBoatByName("Motorjacht")));
        optionList.add(new Option("Navigatie-apparatuur", 20_000,null, Utility.getBoatByName("Motorjacht")));
        optionList.add(new Option("Elektrische motor", 50_000,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 30_000, Utility.getBoatByName("Motorjacht")));
        optionList.add(new Option("Elektrische kookplaat", 20_000,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 10_000, Utility.getBoatByName("Motorjacht")));

        optionList.add(new Option("Extra peddels kano", 50,null, Utility.getBoatByName("Kano")));
        optionList.add(new Option("Extra zitplaatsen kano", 20,null, Utility.getBoatByName("Kano")));
        optionList.add(new Option("Waterdichte opbergvakken kano", 10,null, Utility.getBoatByName("Kano")));
        optionList.add(new Option("Duurzamer/gerecycled kunststof", 400,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 200, Utility.getBoatByName("Kano")));

        optionList.add(new Option("Extra peddels kayak", 50,null, Utility.getBoatByName("Kayak")));
        optionList.add(new Option("Extra zitplaatsen kayak", 20,null, Utility.getBoatByName("Kayak")));
        optionList.add(new Option("Waterdichte opbergvakken kayak", 10,null, Utility.getBoatByName("Kayak")));
        optionList.add(new Option("Duurzamer/gerecycled kunststof", 400,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 200, Utility.getBoatByName("Kayak")));

        optionList.add(new Option("Roer", 50,null, Utility.getBoatByName("Roeiboot")));
        optionList.add(new Option("Extra peddels", 50,null, Utility.getBoatByName("Roeiboot")));
        optionList.add(new Option("Extra zitplaatsen", 20,null, Utility.getBoatByName("Roeiboot")));
        optionList.add(new Option("Duurzamer/gerecycled kunststof", 400,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 200, Utility.getBoatByName("Roeiboot")));

        optionList.add(new Option("Extra zeilen", 500,null, Utility.getBoatByName("Zeilbootje")));
        optionList.add(new Option("Navigatie-verlichting", 100,null, Utility.getBoatByName("Zeilbootje")));
        optionList.add(new Option("Noodmotor", 1000,null, Utility.getBoatByName("Zeilbootje")));
        optionList.add(new Option("Elektrische noodmotor", 800,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 500, Utility.getBoatByName("Zeilbootje")));
        optionList.add(new Option("Zonnepanelen", 1500,"Dit is een optie met milieukorting en word afgetrokken van de totaal prijs.", 1000, Utility.getBoatByName("Zeilbootje")));
        //opties voor jacht
        //optionList.add(new Option("Airconditioning", null, getBoatByName("Jacht")));

    }

    public static String getBoatsJSONString() {
       String fileString = "";

        for (Boat boat : boatList) {
            fileString += (fileString.equalsIgnoreCase("")) ? boat.getJSONString() : "," + boat.getJSONString();
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

            fileString += (fileString.equalsIgnoreCase("")) ? option.getJSONString() : "," + option.getJSONString();
        }

        return  "{" + "\n"
                + " \"option-list\": "
                + "[" + fileString + "\n ]"
                + "\n"
                + "}";
    }

}
