package com.mandelorian;

import com.mandelorian.boat.Boat;
import com.mandelorian.boat.Categorie;
import com.mandelorian.boat.Item;
import com.mandelorian.boat.Option;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Saved {


    private static List<Categorie> categorieList = new ArrayList<>();
    private static HashMap<Item, Double> priceList = new HashMap<>();
    private static List<Boat> boatList = new ArrayList<>();
    private static List<Option> optionList = new ArrayList<>();

    public static List<Categorie> getCategorieList() {return categorieList;}
    public static HashMap<Item, Double> getPriceList() {return priceList;}
    public static List<Boat> getBoatList() {return boatList;}

    // Add here the categories
    public static void setDefaultCategories() {
        // categorieList.add(new Categorie("Example"));
    }

    // Add here all the different types of boats
    public static void setDefaultBoatList() {
        // boatList.add(new Boat("Example", getCategorie("ExampleCategorie")));
    }

    // set here all the options
    public static void setDefaultOptionList() {
        // optionList.add(new Option("Example", "description"));
    }

    // Set here the pricelist
    public static void setDefaultPriceList() {
        //priceList.put(getBoatByName("Example"), 100.0);
        // priceList.put(getOptionByName("Example"), 100.0);
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

    public static Categorie getCategorie(String name) {
        Categorie categorie = null;

        for(Categorie cc : categorieList) {
            if(!cc.getName().equalsIgnoreCase(name)) continue;
            categorie = cc;
            break;
        }

        return categorie;
    }
}
