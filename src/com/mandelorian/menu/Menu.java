package com.mandelorian.menu;


import com.mandelorian.Program;

import java.util.*;

public abstract class Menu {

    private List<String> choices;
    public Program program;
    private int choice = 0;

    public Menu(Program program) {
        this.choices = this.getChoices();
        this.program = program;
    }

    public void stopMenu() {
        this.choice = 100;
    }

    private void displayMenu() {
        System.out.println("Menu");
        System.out.println();
        for (int i = 0; i < choices.size(); i++) {
            System.out.println((i + 1) + ". " + choices.get(i));
        }
        System.out.println();
    }

    public void chooseOption() {
        // 100 is to shutdown the program

        do {
        displayMenu();
        System.out.println("Wat wilt u doen? Kies een nummer.");

        if(choices == null || choices.isEmpty()) {
            System.out.println("Het geladen menu is leeg.");
            stopMenu();
            program.stop();
            return;
        }

        Scanner input = new Scanner(System.in);
        try {
            choice = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println();
            System.out.println("U heeft geen geldig nummer ingevoerd.");
            System.out.println();
            chooseOption();
        }

        if(choice > choices.size() || choice < 0) {
            System.out.println();
            System.out.println("Deze optie bestaat niet kies er nog een");
            System.out.println();
            chooseOption();
        }

        this.executeChoice(choice);
        } while (choice != 100);

        program.stop();
    }




    // methods to change the menu
    public abstract void executeChoice(int choice);
    public abstract List<String> getChoices();
}
