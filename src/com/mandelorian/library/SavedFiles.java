package com.mandelorian.library;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mandelorian.quotation.Quotation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SavedFiles {

    public static boolean saveQuatationListToFile(List<Quotation> quotationList, String filepath, String fileName) {
        if(filepath == null) return false;
        JsonObject file = new JsonObject();

        for(int i = 0; i < quotationList.size(); i++) {
            Quotation quotation = quotationList.get(i);
            String quatationString = new Gson().toJson(quotation);
            file.addProperty("quatation-" + i, quatationString);
        }

        String jsonString = new Gson().toJson(file);
        try (FileWriter writer = new FileWriter( fileName + ".json")) {
            writer.write(jsonString);
            return true;
       } catch (Exception ex) {
            System.out.println("Kon het offerte bestand niet opslaan. door de Error: " + ex.getMessage());
            return false;
        }
    }

    public static List<Quotation> loadQuatations(String fileName) {
        List<Quotation> quotationList = new ArrayList<>();

        try (Reader reader = new FileReader(fileName + ".json")) {
            JsonObject object = new Gson().fromJson(reader, JsonObject.class);

            object.entrySet().forEach(stringJsonElementEntry -> {
                String quatationName = stringJsonElementEntry.getKey();
                JsonElement quotationString = stringJsonElementEntry.getValue();
                Quotation quotation = new Gson().fromJson(quotationString, Quotation.class);
//                quotationList.add(quotation);
            });


        } catch (Exception ex) {
            System.out.println("Kon het offerte bestand niet laden. door de Error: " + ex.getMessage());
        }

        return quotationList;
    }
}
