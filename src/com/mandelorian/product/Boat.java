package com.mandelorian.product;

import com.mandelorian.library.Categorie;

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

    public List<Option> getPossibleOptions() {
        List<Option> optionList = new ArrayList<>();
        ProductList.getOptionList().forEach(option -> {
            if(option.getBoat().getName().equalsIgnoreCase(this.getName())) optionList.add(option);
        });

        return optionList;
    }
}
