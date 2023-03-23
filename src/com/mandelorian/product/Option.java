package com.mandelorian.product;

public class Option extends Product {
    private String description;

    public Option(String name, double price, String description, Boat boatByName) {
        super(name, price);
        this.description = description;
    }

    public String getDescription() {return this.description;}

    public void setDescription(String description) {this.description = description;}
}
