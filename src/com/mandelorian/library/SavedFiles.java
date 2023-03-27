package com.mandelorian.library;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mandelorian.product.Boat;
import com.mandelorian.product.Option;
import com.mandelorian.quotation.Quotation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SavedFiles {

    public static String getJSONString(Quotation quotation, String name) {
        String quotationName = name;
        String boat = quotation.getBoat().getName();
        String boatOptions = quotation.optionListAsString();

        return   "  \n  {\n" +
                "    \"name\": \"%name%\",".replace("%name%", quotationName) + "\n" +
                "      \"boat\": \"%boat%\",".replace("%boat%", boat) + "\n" +
                "      \"options\": \"%options%\"".replace("%options%", boatOptions) +
                "\n  }";
    }



    public static boolean saveQuoatation(Quotation quotation, String filepath, String fileName){
        if(filepath == null || filepath.isBlank() || filepath.isEmpty()) filepath = "./";

        File folder = new File(filepath);
        if(!folder.exists()) folder.mkdir();
        String fileString = "{" + "\n"
                + " \"quotations\": "
                + "[" + getJSONString(quotation, quotation.getBoat().getName()) + "\n]"
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
}
