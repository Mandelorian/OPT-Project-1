package com.mandelorian.product;

public class Option extends Product {
    private String description;
    private Boat boat;
    private double milieuKorting;

    public Option(String name, double price, String description, double milieuKorting, Boat boat) {
        super(name, price);
        this.description = description;
        this.milieuKorting = milieuKorting;
        this.boat = boat;
    }

    public Option(String name, double price, String description, Boat boat) {
        super(name, price);
        this.description = description;
        this.boat = boat;
    }

    public String getDescription() {return this.description;}

    public void setDescription(String description) {this.description = description;}

    public double getMilieuKorting() {return milieuKorting;}

    public void setMilieuKorting(double milieuKorting) {this.milieuKorting = milieuKorting;}

    public Boat getBoat() {return boat;}
    public void setBoat(Boat boat) {this.boat = boat;}

    public String getJSONString() {
//        System.out.println(this.getName() + " " + this.getPrice() + " " + this.getBoat().getName() + " " + this.getDescription() );


        return   "  \n  {\n" +
                "    \"name\": \"%name%\",".replace("%name%", this.getName()) + "\n" +
                "      \"price\": %price%,".replace("%price%", this.getPrice() + "") + "\n" +
                "      \"boat\": \"%boat%\",".replace("%boat%", (this.getBoat() == null || this.getBoat().getName() == null) ? "" : this.getBoat().getName()) + "\n" +
                "      \"milieuKorting\": \"%milieuKorting%\",".replace("%milieuKorting%", this.milieuKorting + "") + "\n" +
                "      \"description\": \"%description%\"".replace("%description%", (this.getDescription() == null) ? "" : this.getDescription()) +
                "\n  }";
    }
}
