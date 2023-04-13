package com.mandelorian.quotation;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mandelorian.klant.Klant;
import com.mandelorian.library.Utility;
import com.mandelorian.product.Boat;
import com.mandelorian.product.Option;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Quotation {

    // Offerte

    private Boat boat;
    private List<Option> optionList;
    private Klant klant;

    public Quotation(Boat boat, Klant klant) {
        this.boat = boat;
        this.optionList = new ArrayList<>();
        this.klant = klant;
    }

    public Boat getBoat() {return this.boat;}
    public List<Option> getOptionList() {return this.optionList;}
    public Klant getKlant() { return klant;}

    public void setBoat(Boat boat) {this.boat = boat;}
    public void addOption(Option option) {if(option != null && !this.optionList.contains(option)) this.optionList.add(option);}
    public void removeOption(Option option) {this.optionList.remove(option);}
    public void removeOption(int index) {this.optionList.remove(index);}


    public double getTotalPrice() {
        double total = ((boat == null) ? 0 : boat.getPrice());

        for(Option currentOption : optionList) {
            total += currentOption.getPrice();
        }

        return  total;
    }

    @Override
    public String toString() {
        return this.klant.getNaam() + " " + this.boat.getName();
    }


    public String optionListAsString() {
        String s = "";

        for (Option option : this.optionList) {
            s += (s == null || s.isBlank() || s.isEmpty()) ? s + option.getName() : s + "," + option.getName();
        }

        return s;
    }

    public String getKlantAsString() {
        JsonObject object = new JsonObject();
        object.addProperty("name", this.klant.getNaam());
        object.addProperty("companyName", this.klant.getBedrijfNaam());
        object.addProperty("city", this.klant.getStad());
        object.addProperty("klantType", this.klant.getKlanttype().getNaam());
        object.addProperty("postcode", this.klant.getPostcode());
        object.addProperty("email", this.klant.getEmail());
        object.addProperty("street", this.klant.getStraat());


        return   "  {\n" +
                "        \"name\": \"%name%\",".replace("%name%", this.klant.getNaam()) + "\n" +
                "        \"companyName\": \"%companyName%\",".replace("%companyName%", this.klant.getBedrijfNaam()) + "\n" +
                "        \"city\": \"%city%\",".replace("%city%", this.klant.getStad()) + "\n" +
                "        \"klantType\": \"%klantType%\",".replace("%klantType%", this.klant.getKlanttype().getNaam()) + "\n" +
                "        \"postcode\": \"%postcode%\",".replace("%postcode%", this.klant.getPostcode()) + "\n" +
                "        \"email\": \"%email%\",".replace("%email%", this.klant.getEmail()) + "\n" +
                "        \"street\": \"%street%\"".replace("%street%", this.klant.getStraat()) +
                "\n     }";
    }


    // STATIC METHODS

    public boolean saveQuoatation(String filepath, String fileName){
        String fileString = "{" + "\n"
                + " \"quotations\": "
                + "[" + getJSONString(this,getBoat().getName()) + "\n ]"
                + "\n"
                + "}";

        return Utility.saveJSONFile(fileString, filepath, fileName);
    }

    //// STATIC METHODS ////

    private static String getJSONString(Quotation quotation, String name) {
        String quotationName = name;
        String boat = quotation.getBoat().getName();
        String boatOptions = quotation.optionListAsString();


        return   "  \n  {\n" +
                "    \"name\": \"%name%\",".replace("%name%", quotationName) + "\n" +
                "      \"boat\": \"%boat%\",".replace("%boat%", boat) + "\n" +
                "      \"options\": \"%options%\",".replace("%options%", boatOptions) + "\n" +
                "      \"klant\": %klant%".replace("%klant%", quotation.getKlantAsString()) +
                "\n  }";
    }

    public static boolean saveQuatationListToFile(List<Quotation> quotationList, String filepath, String fileName) {
        if(filepath == null || filepath.isBlank() || filepath.isEmpty()) filepath = "./";
        String fileString = "";

        for(int i = 0; i < quotationList.size(); i++) {
            Quotation qc = quotationList.get(i);
            String quotationName = "quotation-" + i;
            String s = getJSONString(qc, quotationName);
            fileString += (i == 0) ? s : "," + s;
        }

        fileString = "{" + "\n"
                + " \"quotations\": "
                + "[" + fileString + "\n]"
                + "\n"
                + "}";

        return Utility.saveJSONFile(fileString, filepath, fileName);
    }

    public static List<Quotation> loadQuatations(String filepath, String fileName) {
        List<Quotation> quotationList = new ArrayList<>();
        if(filepath == null || filepath.isBlank() || filepath.isEmpty()) filepath = "./";
        if(fileName.contains(".")) fileName = fileName.split(".")[0];

            JsonObject object = Utility.getJSONData(filepath, fileName);
            if(object == null) {
                System.out.println("Kon de offeres niet laden van het bestand: " + filepath + fileName + ".json");
                return null;
            }


            List<JsonElement> qList = object.get("quotations").getAsJsonArray().asList();

            qList.forEach(jsonElement -> {
                JsonObject obj = jsonElement.getAsJsonObject();
                Klant klant = Utility.getKlantFromJSON(obj.get("klant").getAsJsonObject());
                Boat boat = Utility.getBoatByName(obj.get("boat").getAsString());
                Quotation quotation = new Quotation(boat, klant);

                for (String currentOption : obj.get("options").getAsString().split(",")) {
                    quotation.addOption(Utility.getOptionByName(currentOption));
                }

                if(!quotationList.contains(quotation)) quotationList.add(quotation);
                else System.out.println("Deze offerte is al geladen: " + quotation.getBoat().getName());
            });



        return quotationList;
    }


    // layout https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/Format-double-Java-printf-example#:~:text=Just%20use%20%25.2f%20as%20the,double%20to%20two%20decimal%20places.

    public void printQuotation() {
        int paddingSides = 5;
        int paddingTop = 3;
        int rowLength = 100;

        String sideCharacter = "|";
        String topCharacter = "—";

// start program
        String paddingSidesString = "";

        for (int c = 1; c <= rowLength; c++) {
            System.out.print(topCharacter);
        }

        System.out.print("\n");

        for(int i = 0; i < paddingSides; i++) {
            paddingSidesString += " ";
        }

        for(int i = 0; i < paddingTop; i++) {
            System.out.printf("%-30s %-30s %-30s\n", sideCharacter + "", "", "", sideCharacter);
        }

        System.out.print(sideCharacter + paddingSidesString);
        System.out.printf("%-30s %-30s %-30s%s\n", "SHIPFLEX", "", "OFFERTE", sideCharacter);
        System.out.print(sideCharacter + paddingSidesString);
        System.out.printf("%-30s %-30s %-30s%s\n", "Blauw-roodlaan 286", "", "", sideCharacter);
        System.out.print(sideCharacter + paddingSidesString);
        System.out.printf("%-30s %-30s %-30s%s\n", "2718 SK Zoetermeer ", "", "", sideCharacter);
        System.out.print(sideCharacter + paddingSidesString);
        System.out.printf("%-30s %-30s %-30s%s\n", "088 - 42 42 042", "", "", sideCharacter);
        System.out.print(sideCharacter + paddingSidesString);

        System.out.printf("%-30s %-30s %-30s\n", sideCharacter , " ", " ", sideCharacter);
//hier moeten nog de echte klant naam en het echte factuur adres worden ingevuld
//maar die hebben we nu nog niet
        System.out.print(sideCharacter + paddingSidesString);
        System.out.printf("%-30s %-30s %-30s%s\n", "KLANT", "", "FACTUUR ADRES", sideCharacter);
        System.out.print(sideCharacter + paddingSidesString);
        System.out.printf("%-30s %-30s %-30s%s\n", "Naam", "", klant.getNaam(), sideCharacter);
        System.out.print(sideCharacter + paddingSidesString);
        System.out.printf("%-30s %-30s %-30s%s\n", "Bedrijfsnaam", "", klant.getBedrijfNaam(), sideCharacter);
        System.out.print(sideCharacter + paddingSidesString);
        System.out.printf("%-30s %-30s %-30s%s\n", "Straat", "", klant.getStraat(), sideCharacter);
        System.out.print(sideCharacter + paddingSidesString);
        System.out.printf("%-30s %-30s %-30s%s\n", "postcode, stad", "",klant.getPostcode()+" "+klant.getStad(), sideCharacter);
        System.out.print(sideCharacter + paddingSidesString);
        System.out.printf("%-30s %-30s %-30s%s\n", "email", "", klant.getEmail(), sideCharacter);

        for(int i = 0; i < paddingTop; i++) {
            System.out.printf("%-30s %-30s %-30s\n", sideCharacter + "", "", "", sideCharacter);
        }

        for (int c = 1; c <= rowLength; c++) {
            System.out.print(topCharacter);
        }
        System.out.println("\n" + sideCharacter);

        System.out.println(sideCharacter);
        System.out.print(sideCharacter + paddingSidesString);
        System.out.printf("%-30s %-30s %-30s%s\n", "Boot", boat.getName(), String.format("%.2f", boat.getPrice()), sideCharacter);

        for (int i = 0; i < getOptionList().size(); i++) {
            System.out.print(sideCharacter + paddingSidesString);
            System.out.printf("%-30s %-30s %-30s%s\n", "optie", getOptionList().get(i).getName(), String.format("%.2f", getOptionList().get(i).getPrice()), sideCharacter);
        }
        System.out.print(sideCharacter + paddingSidesString);
        System.out.printf("%-30s %-30s %-30s%s\n", "", "Totaal prijs met Korting:" , "   "+  String.format("%.2f",getTotalPrice() - (getTotalPrice()*(klant.getKlanttype().getKorting()/100))), sideCharacter);

        for (int c = 1; c <= rowLength; c++) {
            System.out.print(topCharacter);
        }

        System.out.println("");


    }

}
