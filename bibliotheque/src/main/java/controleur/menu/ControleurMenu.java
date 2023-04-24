package controleur.menu;


import vue.frames.MainWindow;
import vue.frames.Menu;
import modele.utils.Mode;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ControleurMenu {

    private Mode currentMode;

    private final List<ObserverMenu> observers ;


    public ControleurMenu(Mode mode) {
        this.currentMode = mode;
        this.observers = new ArrayList<>();
    }

    public void registerObserver(ObserverMenu observer) {
        this.observers.add(observer);
    }


    public Mode getCurrentMode() {
        return currentMode;
    }

    public void setCurrentMode(Mode currentMode) {
        this.currentMode = currentMode;
    }

    public void selectioner() {
        if (Menu.btnDemande.isSelected()){
            MainWindow mainWindow = new MainWindow(SortBy.EMPRUNTS);
            mainWindow.setVisible(true);
        }
        else if (Menu.btnOffre.isSelected()){
            MainWindow mainWindow = new MainWindow(SortBy.EXEMPLAIRES);
            mainWindow.setVisible(true);
        }
        else if (Menu.btnComparaison.isSelected()){
            MainWindow mainWindow = new MainWindow(SortBy.BOTH);
            mainWindow.setVisible(true);
        }
        else{
            Menu.choisir();
        }
    }
}
