package controleur.mainWindow;

import dao.Database;
import modele.Cache;
import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.utils.SortBy;
import modele.utils.TypeDeDocGrouping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControleurMainWindow {

    private SortBy currentMode;
    private final List<ObserverMainWindow> observers ;


    public ControleurMainWindow(SortBy mode) {
        this.currentMode = mode;
        this.observers = new ArrayList<>();
    }

    public void registerObserver(ObserverMainWindow observer) {
        this.observers.add(observer);
    }







    public SortBy getCurrentMode() {
        return currentMode;
    }

    public void setCurrentMode(SortBy currentMode) {
        this.currentMode = currentMode;
    }

}
