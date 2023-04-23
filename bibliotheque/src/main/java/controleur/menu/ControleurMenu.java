package controleur.menu;

import modele.utils.Mode;

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

}
