package com.mandelorian;

import com.mandelorian.klant.Klant;
import com.mandelorian.klant.KlantType;
import com.mandelorian.library.Categorie;
import com.mandelorian.library.Utility;
import com.mandelorian.menu.MainMenu;
import com.mandelorian.menu.Menu;
import com.mandelorian.product.Boat;
import com.mandelorian.product.Option;
import com.mandelorian.product.ProductList;
import com.mandelorian.quotation.Quotation;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {
    private List<Quotation> quotationList;
    private Quotation currentQuotation;

    public Program() {
        this.quotationList = new ArrayList<>();
    }

    public void start() {
        System.out.println("Welkom!");
        Menu.openMenu(new MainMenu(this));
    }

    public void stop() {
        Utility.saveJSONFile(Categorie.getCatoriesJSONString(), "./saved/", "categories");
        Utility.saveJSONFile(KlantType.getKlantTypesJSONString(), "./saved/", "clientTypes");
        Utility.saveJSONFile(ProductList.getBoatsJSONString(), "./saved/", "boats");
        Utility.saveJSONFile(ProductList.getOptionsJSONString(), "./saved/", "options");
        Quotation.saveQuatationListToFile(this.quotationList, "./saved/quotations/", "quotations");
    }


    public List<Quotation> getQuotationList() {return quotationList;}
    public Quotation getCurrentQuotation() {return currentQuotation;}

    public void setQuotationList(List<Quotation> quotationList) {
        this.quotationList = quotationList;
    }

    public Quotation addQuotation(Boat boat, Klant klant) {
        if (boat == null) return null;
        Quotation quotation = new Quotation(boat, klant);
        this.quotationList.add(quotation);
        return quotation;
    }

    public Quotation createNewQuotation(String boatName, Klant klant) {
        if (boatName == null) return null;
        Quotation quotation = new Quotation(Utility.getBoatByName(boatName), klant);
        if (quotation == null) return null;
        this.quotationList.add(quotation);
        return quotation;
    }

    public void setCurrentQuotation(Quotation quotation) {
        this.currentQuotation = quotation;
    }


}
