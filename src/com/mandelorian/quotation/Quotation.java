package com.mandelorian.quotation;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
        double total = ((boat == null) ? 0 : boat.getPrice());

        for(Option currentOption : optionList) {
            total += currentOption.getPrice();
        }

        return  total;
    }


    public String optionListAsString() {
        String s = "";

        for (Option option : optionList) {
            s += (s == null || s.isBlank() || s.isEmpty()) ? s + option.getName() : s + "," + option.getName();
        }

        return s;
    }

    // STATIC METHODS

    public boolean saveQuoatation(String filepath, String fileName){
        if(filepath == null || filepath.isBlank() || filepath.isEmpty()) filepath = "./";

        File folder = new File(filepath);
        if(!folder.exists()) folder.mkdir();

        String fileString = "{" + "\n"
                + " \"quotations\": "
                + "[" + getJSONString(this,getBoat().getName()) + "\n]"
                + "\n"
                + "}";

        try (FileWriter writer = new FileWriter( filepath + "/" + fileName + ".json")) {
            writer.write(fileString);
            return true;
        } catch (Exception ex) {
            System.out.println("Kon het offerte bestand niet opslaan. door de Error: ");
            ex.printStackTrace();
            return false;
        }
    }

    //// STATIC METHODS ////

    private static String getJSONString(Quotation quotation, String name) {
        String quotationName = name;
        String boat = quotation.getBoat().getName();
        String boatOptions = quotation.optionListAsString();

        return   "  \n  {\n" +
                "    \"name\": \"%name%\",".replace("%name%", quotationName) + "\n" +
                "      \"boat\": \"%boat%\",".replace("%boat%", boat) + "\n" +
                "      \"options\": \"%options%\"".replace("%options%", boatOptions) +
                "\n  }";
    }

    public static boolean saveQuatationListToFile(List<Quotation> quotationList, String filepath, String fileName) {
        if(filepath == null || filepath.isBlank() || filepath.isEmpty()) filepath = "./";
        if(fileName.contains(".")) fileName = fileName.split(".")[0];
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

        if(!new File(filepath).exists()) new File(filepath).mkdir();

        try (FileWriter writer = new FileWriter( filepath + "/" + fileName + ".json")) {
            writer.write(fileString);
            return true;
        } catch (Exception ex) {
            System.out.println("Kon het offerte bestand niet opslaan. door de Error: ");
            ex.printStackTrace();
            return false;
        }
    }

    public static List<Quotation> loadQuatations(String filepath, String fileName) {
        List<Quotation> quotationList = new ArrayList<>();
        if(filepath == null || filepath.isBlank() || filepath.isEmpty()) filepath = "./";
        if(fileName.contains(".")) fileName = fileName.split(".")[0];

        try (Reader reader = new FileReader(filepath + "/" + fileName + ".json")) {
            JsonObject object = new Gson().fromJson(reader, JsonObject.class);
            List<JsonElement> qList = object.get("quotations").getAsJsonArray().asList();

            qList.forEach(jsonElement -> {
                JsonObject obj = jsonElement.getAsJsonObject();
                Boat boat = Utility.getBoatByName(obj.get("boat").getAsString());
                Quotation quotation = new Quotation(boat);

                for (String currentOption : obj.get("options").getAsString().split(",")) {
                    quotation.addOption(Utility.getOptionByName(currentOption));
                }

                quotationList.add(quotation);
            });

        } catch (Exception ex) {
            System.out.println("Kon het offerte bestand niet laden. door de Error: " + ex.getMessage());
        }

        return quotationList;
    }

    public void printQuotation() {
        int paddingSides = 5;
        int paddingTop = 3;
        int rows = 85;

        String sideCharacter = "|";
        String topCharacter = "—";

        // start program
        String paddingSidesString = "";


        for (int c = 1; c <= rows; c++) {
            System.out.print(topCharacter);
        }

        System.out.println("\n" + sideCharacter);


        for(int i = 0; i < paddingTop; i++) {
            System.out.println(sideCharacter);
        }

        for(int i = 0; i < paddingSides; i++) {
            paddingSidesString += " ";
        }


        System.out.print(sideCharacter + paddingSidesString);
        System.out.printf("%-30s %-30s %-30s\n", "SHIPFLEX", "", "OFFERTE");
        System.out.print(sideCharacter + paddingSidesString);
        System.out.printf("%-30s %-30s %-30s\n", "Blauw-roodlaan 286", "", "");
        System.out.print(sideCharacter + paddingSidesString);
        System.out.printf("%-30s %-30s %-30s\n", "2718 SK Zoetermeer ", "", "");
        System.out.print(sideCharacter + paddingSidesString);
        System.out.printf("%-30s %-30s %-30s\n", "088 - 42 42 042", "", "");
        System.out.print(sideCharacter + paddingSidesString);
        System.out.println();
        //hier moeten nog de echte klant naam en het echte factuur adres worden ingevuld
        //maar die hebben we nu nog niet
        System.out.print(sideCharacter + paddingSidesString);
        System.out.printf("%-30s %-30s %-30s\n", "KLANT", "", "FACTUUR ADRES");
        System.out.print(sideCharacter + paddingSidesString);
        System.out.printf("%-30s %-30s %-30s\n", "Naam", "", "Naam");
        System.out.print(sideCharacter + paddingSidesString);
        System.out.printf("%-30s %-30s %-30s\n", "Bedrijfsnaam", "", "Bedrijfsnaam");
        System.out.print(sideCharacter + paddingSidesString);
        System.out.printf("%-30s %-30s %-30s\n", "Straat", "", "Straat");
        System.out.print(sideCharacter + paddingSidesString);
        System.out.printf("%-30s %-30s %-30s\n", "postcode, stad", "", "postcode, stad");
        System.out.print(sideCharacter + paddingSidesString);
        System.out.printf("%-30s %-30s %-30s\n", "email", "", "email");

        for (int i = 0; i < paddingTop; i++) {
            System.out.println(sideCharacter);
        }

        int rows2 = 84;
        for (int c = 1; c <= rows2; c++) {
            System.out.print(topCharacter);
        }
        System.out.println("\n" + sideCharacter);


        System.out.println(sideCharacter);
        System.out.print(sideCharacter + paddingSidesString);
        System.out.printf("%-30s %-30s %-30s\n", "Boot", boat.getName(), boat.getPrice());

        for (int i = 0; i < getOptionList().size(); i++) {
            System.out.print(sideCharacter + paddingSidesString);
            System.out.printf("%-30s %-30s %-30s\n", "optie", getOptionList().get(i).getName(), String.format("%.2f", getOptionList().get(i).getPrice()));
        }
        System.out.printf("%-30s %-30s %-30s\n", sideCharacter, "   Totaal:" , "   "+  String.format("%.2f", getTotalPrice()));

        int rows3 = 84;
        for (int c = 1; c <= rows3; c++) {
            System.out.print(topCharacter);
        }

        for (int c = 1; c <= rows; c++) {
            System.out.print("");
        }


    }

}
