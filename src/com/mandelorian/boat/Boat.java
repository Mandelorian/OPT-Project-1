package com.mandelorian.boat;

public class Boat extends Item {

    private Categorie categorie;
    public Boat(String name, Categorie categorie) {
        super(name);
        this.categorie = categorie;
    }

    public Categorie getCategorie() {
        return this.categorie;
    }
}
