package com.mandelorian;

import com.mandelorian.klant.Klant;
import com.mandelorian.klant.KlantType;
import com.mandelorian.library.Categorie;
import com.mandelorian.menu.MainMenu;
import com.mandelorian.product.Boat;
import com.mandelorian.library.Utility;
import com.mandelorian.product.Option;
import com.mandelorian.product.ProductList;
import com.mandelorian.quotation.Quotation;

import javax.sound.midi.Soundbank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.*;


public class Main {


    public static void main(String[] args) {
        Categorie.setDefaultCategories();
        ProductList.setDefaultBoatList();
        ProductList.setDefaultOptionList();
        KlantType.setDefaultKlantType();

       Program program = new Program();
       program.start();
    }

}


