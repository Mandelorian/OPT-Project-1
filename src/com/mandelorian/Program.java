package com.mandelorian;

import com.mandelorian.klant.Klant;
import com.mandelorian.klant.KlantType;
import com.mandelorian.library.Categorie;
import com.mandelorian.library.Utility;
import com.mandelorian.menu.MainMenu;
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
        MainMenu mainMenu = new MainMenu(this);
        mainMenu.chooseOption();

    }

    public void stop() {
        Utility.saveJSONFile(Categorie.getCatoriesJSONString(), "./saved/", "categories");
        Utility.saveJSONFile(KlantType.getKlantTypesJSONString(), "./saved/", "clientTypes");
        Utility.saveJSONFile(ProductList.getBoatsJSONString(), "./saved/", "boats");
        Utility.saveJSONFile(ProductList.getOptionsJSONString(), "./saved/", "options");
        Quotation.saveQuatationListToFile(this.quotationList, "./saved/quotations/", "quotations");
    }

    public Klant createClient() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Voer de klantgegevens in:");
        System.out.print("Uw naam: ");
        String naam = scanner.nextLine();
        System.out.print("Bedrijfsnaam: ");
        String bedrijfsnaam = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Straat: ");
        String straat = scanner.nextLine();
        System.out.print("Stad: ");
        String stad = scanner.nextLine();

        System.out.print("Postcode: ");
        String postcode = scanner.nextLine();

        System.out.println("Kies een klanttype:");
        for (int i = 0; i < KlantType.getKlantTypeList().size(); i++) {
            KlantType klanttype = KlantType.getKlantTypeList().get(i);
            System.out.printf("%d. %s (Korting: %.2f%%)\n", i + 1, klanttype.getNaam(), klanttype.getKorting());
        }
        System.out.print("Kies het klanttype nummer: ");
        int klanttypeIndex = scanner.nextInt() - 1;
        KlantType klanttype = KlantType.getKlantTypeList().get(klanttypeIndex);

        return new Klant(bedrijfsnaam, email, stad, klanttype, postcode, naam, straat);
    }

    public void addNewKlantType() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Voer de naam van het nieuwe klanttype in: ");
        String klantTypeName = scanner.nextLine();

        System.out.print("Voer de korting voor het nieuwe klanttype in (in procenten): ");
        double klantTypeKorting = scanner.nextDouble();

        KlantType newKlantType = new KlantType(klantTypeName, klantTypeKorting);
        KlantType.getKlantTypeList().add(newKlantType);
        System.out.println("Nieuw klanttype is succesvol toegevoegd.");
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
