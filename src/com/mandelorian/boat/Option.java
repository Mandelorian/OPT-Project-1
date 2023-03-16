package com.mandelorian.boat;

public class Option extends Item {
    private String description;
    private Boat boat;


    public Option(String name, String description, Boat boat) {
        super(name);
        this.description = description;
        this.boat = boat;
    }

    public String getDescription() {return this.description;}

    public void setDescription(String description) {this.description = description;}

    public Boat getBoat() {return boat;}
}
