package controleur.menu;

import modele.utils.SortBy;

import java.util.ArrayList;
import java.util.List;

public class ControleurMenu {

    private SortBy currentMode;

    private final List<ObserverMenu> observers ;


    public ControleurMenu(SortBy mode) {
        this.currentMode = mode;
        this.observers = new ArrayList<>();
    }

    public void registerObserver(ObserverMenu observer) {
        this.observers.add(observer);
    }


    public SortBy getCurrentMode() {
        return currentMode;
    }

    public void setCurrentMode(SortBy currentMode) {
        this.currentMode = currentMode;
    }

}
