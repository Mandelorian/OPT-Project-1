package com.mandelorian.boat;

public class Boat extends Item {

    private Categorie categorie;
    public Boat(String name, double price, Categorie categorie) {
        super(name, price);
        this.categorie = categorie;
    }

    public Categorie getCategorie() {
        return this.categorie;
    }
}
