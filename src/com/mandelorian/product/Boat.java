package com.mandelorian.product;

import com.mandelorian.library.Categorie;

public class Boat extends Product {

    private Categorie categorie;
    public Boat(String name, double price, Categorie categorie) {
        super(name, price);
        this.categorie = categorie;
    }

    public Categorie getCategorie() {
        return this.categorie;
    }
}
