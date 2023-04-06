package com.mandelorian.klant;


import com.google.gson.JsonObject;
import com.mandelorian.library.Categorie;

import com.mandelorian.library.Utility;
import com.mandelorian.product.Option;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class KlantType {
    private String naam;
    private double korting;

    private static List<KlantType> klantTypeList = new ArrayList<>();

    public KlantType(String naam, double korting) {
        this.naam = naam;
        this.korting = korting;
    }

    public static void setDefaultKlantType() {
        if(new File("./saved/clientTypes.json").exists()) {
            JsonObject object = Utility.getJSONData("./saved/", "clientTypes");
            if (object == null) return;
            object.get("client-types").getAsJsonArray().forEach(clientType -> {
                String name = clientType.getAsString().split(":")[0];
                double korting = Double.parseDouble(clientType.getAsString().split(":")[1]);
                klantTypeList.add(new KlantType(name, korting));
            });
        }

        if(!klantTypeList.isEmpty()) return;
        klantTypeList.add(new KlantType("Overheid",10));
        klantTypeList.add(new KlantType("Bedrijf",5));
        klantTypeList.add(new KlantType("Particulier",2));
    }

    public static List<KlantType> getKlantTypeList() {return klantTypeList;}
    public static KlantType getByName(String name) {
        KlantType returned = null;

        for(KlantType type : getKlantTypeList()) {
            if(!type.naam.equalsIgnoreCase(name)) continue;
            returned = type;
            break;
        }

        return returned;
    }

    public static String getKlantTypesJSONString() {
        String fileString = "";

        for (KlantType kl : klantTypeList) {
            fileString += (fileString.equalsIgnoreCase("")) ? "\"clientType\"".replace("clientType", kl.naam + ":" + kl.getKorting()) : "," + "\"clientType\"".replace("clientType", kl.naam + ":" + kl.getKorting());
        }

        return  "{" + "\n"
                + " \"client-types\": "
                + "[" + fileString + "]"
                + "\n"
                + "}";
    }



    // Getters en setters
    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getKorting() {
        return korting;
    }

    public void setKorting(double korting) {
        this.korting = korting;
    }


}
