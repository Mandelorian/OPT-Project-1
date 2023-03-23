package com.mandelorian.product;

public class Option extends Product {
    private String description;
    private Boat boat;

    public Option(String name, double price, String description, Boat boat) {
        super(name, price);
        this.description = description;
        this.boat = boat;
    }

    public String getDescription() {return this.description;}

    public void setDescription(String description) {this.description = description;}

    public Boat getBoat() {return boat;}
    public void setBoat(Boat boat) {this.boat = boat;}
}
