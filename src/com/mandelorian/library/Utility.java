package com.mandelorian.library;

import com.mandelorian.product.Boat;
import com.mandelorian.product.Option;
import com.mandelorian.product.ProductList;

import java.text.DecimalFormat;

public class Utility {

    public static Boat getBoatByName(String name) {
        Boat boat = null;

        for(Boat cb : ProductList.getBoatList()) {
            if(!cb.getName().equalsIgnoreCase(name)) continue;
            boat = cb;
            break;
        }

        return boat;
    }

    public static Option getOptionByName(String name) {
        Option option = null;

        for(Option co : ProductList.getOptionList()) {
            if(!co.getName().equalsIgnoreCase(name)) continue;
            option = co;
            break;
        }

        return option;
    }

    public static Categorie getCategorieByName(String name) {
        Categorie categorie = null;

        for(Categorie cc : Categorie.getCategorieList()) {
            if(!cc.getName().equalsIgnoreCase(name)) continue;
            categorie = cc;
            break;
        }

        return categorie;
    }

    public static String formatPrice(double price) {
        return String.format("%,d", Math.round((float) price)) + ",00";
    }

    public static void clearScreen() {
        for (int i = 0; i < 200; i++) {System.out.println();}
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}
