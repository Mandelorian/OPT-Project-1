package com.mandelorian.library;

import java.util.ArrayList;
import java.util.List;

public class Categorie {

    private static List<Categorie> categorieList = new ArrayList<>();
    public static List<Categorie> getCategorieList() {return categorieList;}
    public static void setDefaultCategories() {
        // categorieList.add(new Categorie("Example"));
        categorieList.add(new Categorie("Grote boten"));
        categorieList.add(new Categorie("Middelgrote boten"));
        categorieList.add(new Categorie("Kleine boten"));
    }


    private String name;

    public Categorie(String name) {
        this.name = name;
    }

    public static Categorie getCategoryByName(String categoryName) {
        for (Categorie category : categorieList) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        return null;

    }

    public String getName() {return this.name;}

    public void setName(String name) {this.name = name;}

}
