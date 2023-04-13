package com.mandelorian.library;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mandelorian.klant.Klant;
import com.mandelorian.klant.KlantType;
import com.mandelorian.product.Boat;
import com.mandelorian.product.Option;
import com.mandelorian.product.ProductList;
import com.mandelorian.quotation.Quotation;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.List;

public class Utility {

    public static Boat getBoatByName(String name) {
        Boat boat = null;

        for(Boat cb : ProductList.getBoatList()) {
            if(!cb.getName().equalsIgnoreCase(name)) continue;
            boat = cb;
            break;
        }

        return boat;
    }

    public static Option getOptionByName(String name) {
        Option option = null;

        for(Option co : ProductList.getOptionList()) {
            if(!co.getName().equalsIgnoreCase(name)) continue;
            option = co;
            break;
        }

        return option;
    }

    public static Categorie getCategorieByName(String name) {
        Categorie categorie = null;

        for(Categorie cc : Categorie.getCategorieList()) {
            if(!cc.getName().equalsIgnoreCase(name)) continue;
            categorie = cc;
            break;
        }

        return categorie;
    }

    public static Klant getKlantFromJSON(JsonObject element) {
        String name = element.get("name").getAsString();
        String companyName = element.get("companyName").getAsString();
        String city = element.get("city").getAsString();
        KlantType klantType = KlantType.getByName(element.get("klantType").getAsString());
        String postcode = element.get("postcode").getAsString();
        String email = element.get("email").getAsString();
        String street = element.get("street").getAsString();

        return new Klant(companyName, email, city, klantType, postcode, name, street);
    }

    public static void printChoices(String listName, List<?> objectList) {
        System.out.println();
        System.out.println("———————————————————————————————————————————————————");
        System.out.println("                 " + listName + "                  ");
        System.out.println("———————————————————————————————————————————————————");


        for (int i = 0; i < objectList.size(); i++) {
            System.out.println((i + 1) + ". " + objectList.get(i).toString());
        }

        System.out.println("———————————————————————————————————————————————————");
    }

    public static void clearScreen() {
        for (int i = 0; i < 200; i++) {System.out.println();}
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static boolean saveJSONFile(String fileString, String filepath, String fileName) {
        if(filepath == null || filepath.isBlank() || filepath.isEmpty()) filepath = "./";
        if(fileName.contains(".")) fileName = fileName.split(".")[0];

        String[] folders = filepath.split("/");
        if(folders.length > 1) {
            String done = "";
            for(String cf : folders) {
                done += cf + "/";
                if(cf.equalsIgnoreCase(".")) continue;
                if(new File(done).exists()) continue;
                new File(done).mkdir();
            }
        } else {
            File folder = new File(filepath);
            if(!folder.exists()) folder.mkdir();
        }


        try (FileWriter writer = new FileWriter( filepath + "/" + fileName + ".json")) {
            writer.write(fileString);
            writer.close();
            return true;
        } catch (Exception ex) {
            System.out.println("Kon het bestand " + fileName + " niet opslaan.");
            ex.printStackTrace();
            return false;
        }
    }

    public static JsonObject getJSONData(String filepath, String fileName) {
        if(filepath == null || filepath.isBlank() || filepath.isEmpty()) filepath = "./";
        if(fileName.contains(".")) fileName = fileName.split(".")[0];

        try (Reader reader = new FileReader(filepath + "/" + fileName + ".json")) {
            JsonObject jsonObject = new Gson().fromJson(reader, JsonObject.class);
            reader.close();
            return jsonObject;
        } catch (Exception ex) {
            System.out.println("Kon het bestand: " + fileName + " niet laden.");
            ex.printStackTrace();
            return null;
        }
    }

}
