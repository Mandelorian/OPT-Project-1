package com.mandelorian.menu;

import com.mandelorian.Program;
import com.mandelorian.klant.Klant;
import com.mandelorian.klant.KlantType;
import com.mandelorian.product.Boat;
import com.mandelorian.product.Option;
import com.mandelorian.product.ProductList;
import com.mandelorian.quotation.Quotation;

import java.util.*;

public class OptionMenu extends Menu {
    public OptionMenu(Program program) {
        super(program);
    }

    @Override
    public List<String> getChoices() {
        return Arrays.asList("Offerte maken", "Offertes laden", "Offerte lijst", "Offerte bewerken", "Offerte printen", "Ga terug", "Afsluiten");
    }

    @Override
    public void executeChoice(int choice) {
            switch (choice) {
                case 1:
                    createNewQuotation();
                    break;
                case 2:
                    loadQoatations();
                    break;
                case 3:
                    printLoadedQuoations();
                    break;
                case 4:
                    editQuotation();
                    break;
                case 5:
                    selectQuotationToPrint();
                    break;
                case 6:
                    Menu.openMenu(new MainMenu(program));
                    break;
                case 7:
                    stopMenu();
                    break;
            }
    }
    
    // methodes voor de keuzes
    public void printLoadedQuoations() {
        System.out.println();
        System.out.println("———————————————————————————————————————————————————");
        System.out.println("                 Geladen Offertes                   ");
        System.out.println("———————————————————————————————————————————————————");


        for (int i = 0; i < program.getQuotationList().size(); i++) {
            System.out.println((i + 1) + ". " + program.getQuotationList().get(i).getBoat().getName());
        }

        System.out.println("———————————————————————————————————————————————————");
    }

    public void loadQoatations() {
        Scanner scanner = new Scanner(System.in);

        String bestandsNaam = null;
        List<Quotation> quotations = null;

        while (quotations == null) {
            System.out.println("Wat is de naam van het bestand van de offertes die u wilt laden?");
            bestandsNaam = scanner.nextLine();
            if (bestandsNaam.contains(".")) bestandsNaam = bestandsNaam.split("\\.")[0];

            if (bestandsNaam.equalsIgnoreCase("annuleer")) {
                quotations = new ArrayList<>();
                break;
            }

            quotations = Quotation.loadQuatations("./saved/quotations/", bestandsNaam);
            if (quotations == null)
                System.out.println("Kon de quotations niet laden probeer het opnieuw. of annuleer door annuleer te typen.");
        }

        List<Quotation> quotationList = program.getQuotationList();
        program.getQuotationList().addAll(quotations);
       program.setQuotationList(quotationList);
    }
    
    public void createNewQuotation() {
        Scanner scanner = new Scanner(System.in);
        Klant klant = createClient();


        System.out.println("Welk type boot wilt u kopen?");
        System.out.println();

        System.out.println("———————————————————————————————————————————————————");
        System.out.println("                      Boot lijst                   ");
        System.out.println("———————————————————————————————————————————————————");

        Boat boat = null;
        for (int i = 0; i < ProductList.getBoatList().size(); i++) {
            //System.out.println((i + 1) + ". " + ProductList.getBoatList().get(i).getName());
            System.out.printf("%2d. %s\n", (i + 1), ProductList.getBoatList().get(i).getName());
        }

        System.out.println("———————————————————————————————————————————————————");

        System.out.println();

        while (boat == null) {
            System.out.print("Kies de bootnummer van de boot die je wil: ");
            int boatNumber = scanner.nextInt();
            System.out.println();

            if (boatNumber > ProductList.getBoatList().size()) {
                System.out.println("De boot met dit nummer bestaat niet.");
                continue;
            }

            boat = ProductList.getBoatList().get((boatNumber - 1));
        }

        //Utility.clearScreen();
        program.setCurrentQuotation(program.addQuotation(boat, klant));
        //lijst opties

        System.out.println("Welk optie's wilt u toevoegen");
        System.out.println();

        System.out.println("———————————————————————————————————————————————————");
        System.out.println("                     Optie lijst                   ");
        System.out.println("———————————————————————————————————————————————————");


        List<Option> avalibleOptions = boat.getAvalibleOptions();

        for (int i = 0; i < avalibleOptions.size(); i++) {

            if (avalibleOptions.get(i).getBoat().getName().compareTo(boat.getName()) == 0) {
                //System.out.println((i + 1) + ". " + ProductList.getOptionList().get(i).getName() + " Prijs: "+ProductList.getOptionList().get(i).getPrice()+" Beschrijving: " +ProductList.getOptionList().get(i).getDescription());
                //System.out.printf("%2d. %-42s:\n", (i + 1), ProductList.getOptionList().get(i).getName());
                System.out.println((i + 1) + ". " + avalibleOptions.get(i).getName());
                System.out.println("   Prijs: " + avalibleOptions.get(i).getPrice());
                System.out.println("   Beschrijving: " + avalibleOptions.get(i).getDescription());
                System.out.println();
            }

        }

        System.out.println("———————————————————————————————————————————————————");
        System.out.println();

        int option = 1;
        while (option == 1) {

            System.out.print("Als u geen optie meer wilt toevoegen druk op: 0 ");
            System.out.print("Kies de optie die je wilt toevoegen aan de boot: ");
            int optionNummer = scanner.nextInt();
            System.out.println();

            if (optionNummer == 0) {
                option = 0;
                continue;
            }

            if (optionNummer > avalibleOptions.size()) {
                System.out.println("De optie met dit nummer bestaat niet.");
                continue;
            }


            program.getCurrentQuotation().addOption(avalibleOptions.get((optionNummer - 1)));
        }


        //totaal prijs

        System.out.println("———————————————————————————————————————————————————");
        System.out.println("               totaal prijs               ");
        System.out.println("———————————————————————————————————————————————————");

        System.out.println();


        System.out.println("Toegepaste korting percentage: " + klant.getKlanttype().getKorting() + "%");
        System.out.println("Toegepaste Korting : " + program.getCurrentQuotation().getTotalPrice() * (klant.getKlanttype().getKorting() / 100));
        System.out.printf("Totaal price: €" + "%.2f", program.getCurrentQuotation().getTotalPrice() - (program.getCurrentQuotation().getTotalPrice() * (klant.getKlanttype().getKorting() / 100)));

        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");

        program.getCurrentQuotation().printQuotation();

        int save = 2;
        while (save == 2) {
            System.out.println("Wilt u de offerte opslaan? ");
            System.out.println("jaa / nee");

            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("ja") || answer.equalsIgnoreCase("j") || answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
                save = 1;
                continue;
            }

            if (answer.equalsIgnoreCase("nee") || answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no")) {
                save = 0;
                continue;
            }
        }

        if (save == 1) {
            program.getCurrentQuotation().saveQuoatation("./saved/quotations/", klant.getNaam());
        }
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

    public void editQuotation() {
        if (program.getQuotationList() == null || program.getQuotationList().isEmpty()) {
            System.out.println("Er zijn geen offertes om te printen, Laad eerst een offerte in. Of maak een offerte aan.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println();

        printLoadedQuoations();

        System.out.println();

        int quotationNumber = 0;
        try {
            System.out.println("Kies de nummer van de offerte die u wilt veranderen.");
            quotationNumber = scanner.nextInt();
            System.out.println();
        } catch (InputMismatchException e) {
            System.out.println();
            System.out.println("U heeft geen geldig nummer ingevoerd.");
            System.out.println();
        }

        if (quotationNumber > program.getQuotationList().size()) {
            System.out.println("De offerte met dit nummer bestaat niet.");
            return;
        }

        Quotation quotation = program.getQuotationList().get((quotationNumber - 1));
        program.setCurrentQuotation(quotation);
        int optionNumber;

        System.out.println();

        printCurrentQuotationOption();

        System.out.println("———————————————————————————————————————————————————");
        System.out.println();

        System.out.println("Welk optie wilt u toevoegen");

        System.out.println("———————————————————————————————————————————————————");
        System.out.println("               Optie lijst voor " + program.getCurrentQuotation().getBoat().getName() + "                   ");
        System.out.println("———————————————————————————————————————————————————");

        for (int i = 0; i < ProductList.getOptionList().size(); i++) {
            if (program.getCurrentQuotation().getBoat().equals(ProductList.getOptionList().get(i).getBoat()))

                System.out.printf("%2d. %-25s: %.2f\n", (i + 1), ProductList.getOptionList().get(i).getName(), ProductList.getOptionList().get(i).getPrice());

        }

        System.out.print("Optie: ");
        optionNumber = scanner.nextInt();
        System.out.println();

        if (optionNumber > ProductList.getOptionList().size()) {
            System.out.println("De optie met dit nummer bestaat niet.");
            return;
        }

        for (int i = 0; i < ProductList.getOptionList().size(); i++) {
            if (program.getCurrentQuotation().getBoat().equals(ProductList.getOptionList().get(i).getBoat()))
                if (optionNumber == (i + 1)) {
                    program.getCurrentQuotation().addOption(ProductList.getOptionList().get(i));
                    System.out.println("\"" + program.getCurrentQuotation().getOptionList().get(i).getName() + "\"" + " is toegevoegd aan de offerte.");
                }
        }

        System.out.println();
        System.out.println("Welk optie wilt u verwijderen");

        printCurrentQuotationOption();

        System.out.print("Optie: ");
        optionNumber = scanner.nextInt();
        System.out.println();

        if (optionNumber > program.getCurrentQuotation().getOptionList().size()) {
            System.out.println("De optie met dit nummer zit niet in de huidige optie lijst.");
            return;
        }

        for (int i = 0; i < program.getCurrentQuotation().getOptionList().size(); i++) {
            if (optionNumber == (i + 1)) {
                System.out.println("\"" + program.getCurrentQuotation().getOptionList().get(i).getName() + "\"" + " is verwijderd van de offerte.");
                program.getCurrentQuotation().removeOption(i);

            }
        }


        printCurrentQuotationOption();

        System.out.println();
        System.out.println("Welke prijs wil jij bewerken?");

        printCurrentQuotationOption();

        System.out.print("Optie: ");
        optionNumber = scanner.nextInt();
//        scanner.nextLine();

        if (optionNumber > program.getCurrentQuotation().getOptionList().size()) {
            System.out.println("De optie met dit nummer zit niet in de huidige optie lijst.");
            return;
        }

        System.out.print("Nieuwe prijs: ");
        double newPrice = scanner.nextDouble();
//        scanner.nextLine();

        for (int i = 0; i < program.getCurrentQuotation().getOptionList().size(); i++) {
            if (optionNumber == (i + 1)) {
                program.getCurrentQuotation().getOptionList().get(i).setPrice(newPrice);
                System.out.println("De prijs van \"" + program.getCurrentQuotation().getOptionList().get(i).getName() + "\"" + " is gewijzigd.");
            }
        }

        printCurrentQuotationOption();

        program.getCurrentQuotation().saveQuoatation("./saved/quotations/", program.getCurrentQuotation().getKlant().getNaam());
    }

    public void printCurrentQuotationOption() {
        System.out.println("———————————————————————————————————————————————————");
        System.out.println("                Huidige Optie lijst                ");
        System.out.println("———————————————————————————————————————————————————");

        for (int i = 0; i < program.getCurrentQuotation().getOptionList().size(); i++) {
            Option option = program.getCurrentQuotation().getOptionList().get(i);
            if (option != null) {
                System.out.printf("%2d. %-25s: %.2f\n", (i + 1), program.getCurrentQuotation().getOptionList().get(i).getName(), program.getCurrentQuotation().getOptionList().get(i).getPrice());
            } else {
                System.out.println("empty");
            }
        }
    }

    public void selectQuotationToPrint() {

        if (program.getQuotationList() == null || program.getQuotationList().isEmpty()) {
            System.out.println("Er zijn geen offertes om te printen, Laad eerst een offerte in. Of maak een offerte aan.");
            return;
        }

        printLoadedQuoations();
        System.out.println("Kies het nummer van de offerte die u wilt printen.");

        Scanner scanner = new Scanner(System.in);
        int quotationNumber = 0;
        try {
            quotationNumber = scanner.nextInt();
            System.out.println();
        } catch (InputMismatchException e) {
            System.out.println();
            System.out.println("U heeft geen geldig nummer ingevoerd.");
            System.out.println();
        }

        if (quotationNumber > program.getQuotationList().size()) {
            System.out.println("De offerte met dit nummer bestaat niet.");
            return;
        }

        Quotation quotation = program.getQuotationList().get((quotationNumber - 1));
        quotation.printQuotation();
    }

   
}
