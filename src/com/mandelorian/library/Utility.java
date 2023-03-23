package com.mandelorian.library;

import com.mandelorian.boat.Boat;
import com.mandelorian.boat.Categorie;
import com.mandelorian.boat.Option;

public class Utility {

    public static Boat getBoatByName(String name) {
        Boat boat = null;

        for(Boat cb : Library.getBoatList()) {
            if(!cb.getName().equalsIgnoreCase(name)) continue;
            boat = cb;
            break;
        }

        return boat;
    }

    public static Option getOptionByName(String name) {
        Option option = null;

        for(Option co : Library.getOptionList()) {
            if(!co.getName().equalsIgnoreCase(name)) continue;
            option = co;
            break;
        }

        return option;
    }

    public static Categorie getCategorieByName(String name) {
        Categorie categorie = null;

        for(Categorie cc : Library.getCategorieList()) {
            if(!cc.getName().equalsIgnoreCase(name)) continue;
            categorie = cc;
            break;
        }

        return categorie;
    }
}
