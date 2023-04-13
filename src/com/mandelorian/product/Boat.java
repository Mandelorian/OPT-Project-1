package com.mandelorian.product;

import com.mandelorian.library.Categorie;
import com.mandelorian.quotation.Quotation;

import java.util.ArrayList;
import java.util.List;

public class Boat extends Product {

    private Categorie categorie;
    public Boat(String name, double price, Categorie categorie) {
        super(name, price);
        this.categorie = categorie;
    }

    public Categorie getCategorie() {
        return this.categorie;
    }

    public List<Option> getAvalibleOptions() {
        List<Option> optionList = new ArrayList<>();

        ProductList.getOptionList().forEach(option -> {
            if(!option.getBoat().getName().equalsIgnoreCase(this.getName())) return;
            optionList.add(option);
        });


        return optionList;
    }

    public String getJSONString() {

        return   "  \n  {\n" +
                "    \"name\": \"%name%\",".replace("%name%", this.getName()) + "\n" +
                "      \"price\": %price%,".replace("%price%", this.getPrice() + "") + "\n" +
                "      \"categorie\": \"%categorie%\"".replace("%categorie%", (this.categorie == null) ? "" : this.getCategorie().getName()) +
                "\n  }";
    }





}
