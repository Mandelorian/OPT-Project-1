package com.mandelorian.menu;

import com.mandelorian.Program;

import java.util.Arrays;
import java.util.List;

public class MainMenu extends Menu {

    public MainMenu(Program program) {
        super(program);
    }

    @Override
    public List<String> getChoices() {
     return Arrays.asList("Nieuwe klanttype toevoegen", "Boot opties", "Offerte opties (maken, laden, bewerken)", "Afsluiten");
    }

    @Override
    public void executeChoice(int choice) {
            switch (choice) {
                case 1:
                    program.addNewKlantType();
                    break;
                case 2:
                    stopMenu();
                    new BoatMenu(program).chooseOption();
                    break;
                case 3:
                    stopMenu();
                    new OptionMenu(program).chooseOption();
                    break;
                case 4:
                   stopMenu();
                    break;
            }
    }


}
