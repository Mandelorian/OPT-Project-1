package com.mandelorian.library;

import com.google.gson.JsonObject;
import com.mandelorian.product.Boat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Categorie {

    private static List<Categorie> categorieList = new ArrayList<>();
    public static List<Categorie> getCategorieList() {return categorieList;}
    public static void setDefaultCategories() {

        // categorieList.add(new Categorie("Example"));
        if(new File("./saved/categories.json") != null) {
            JsonObject object = Utility.getJSONData("./saved/", "categories");
            if (object == null) return;
            object.get("categorie-list").getAsJsonArray().forEach(name -> {
                categorieList.add(new Categorie(name.getAsString()));
            });
        }

        if(!categorieList.isEmpty()) return;
        categorieList.add(new Categorie("Grote boten"));
        categorieList.add(new Categorie("Middelgrote boten"));
        categorieList.add(new Categorie("Kleine boten"));
    }


    private String name;

    public Categorie(String name) {
        this.name = name;
    }

    public String getName() {return this.name;}

    public void setName(String name) {this.name = name;}

    public static Categorie getCategoryByName(String categoryName) {
        for (Categorie category : categorieList) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        return null;

    }

    public static String getCatoriesJSONString() {
        String fileString = "";

        for (Categorie categorie : categorieList) {
            fileString += (fileString.equalsIgnoreCase("")) ? "\"categorie\"".replace("categorie", categorie.getName()) : "," + "\"categorie\"".replace("categorie", categorie.getName());
        }

        return  "{" + "\n"
                + " \"categorie-list\": "
                + "[" + fileString + "]"
                + "\n"
                + "}";
    }
}
