package com.mandelorian.quotation;

import com.mandelorian.Memory;
import com.mandelorian.boat.Boat;
import com.mandelorian.boat.Item;
import com.mandelorian.boat.Option;

import java.util.ArrayList;
import java.util.List;

public class Quotation {

    // Offerte

    private Boat boat;
    private List<Option> optionList;

    public Quotation(Boat boat) {
        this.boat = boat;
        this.optionList = new ArrayList<>();
    }

    public Boat getBoat() {return this.boat;}
    public List<Option> getOptionList() {return this.optionList;}

    public void setBoat(Boat boat) {this.boat = boat;}
    public void addOption(Option option) {this.optionList.add(option);}
    public void removeOption(Option option) {this.optionList.remove(option);}
    public void removeOption(int index) {this.optionList.remove(index);}

    public double getTotalPrice() {
        double total = ((boat == null) ? 0 : Memory.getPriceList().get(boat));

        for(Option currentOption : optionList) {
            total += Memory.getPriceList().get(currentOption);
        }

        return  total;
    }

    public double getPrice(Item item) {
        return Memory.getPriceList().get(item);
    }

}
