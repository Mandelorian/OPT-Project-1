package com.mandelorian.klant;


import com.mandelorian.library.Categorie;

import com.mandelorian.product.Option;

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
        klantTypeList.add(new KlantType("Overheid",10));
        klantTypeList.add(new KlantType("Bedrijf",5));
        klantTypeList.add(new KlantType("Particulier",2));
    }

    public static List<KlantType> getKlantTypeList() {return klantTypeList;}




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
