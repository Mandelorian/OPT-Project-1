package com.mandelorian.boat;

public class Option extends Item {
    private String description;

    public Option(String name, double price, String description, Boat boatByName) {
        super(name, price);
        this.description = description;
    }

    public String getDescription() {return this.description;}

    public void setDescription(String description) {this.description = description;}
}
