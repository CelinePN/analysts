package controleur.mainWindow;

import modele.utils.Mode;

import java.util.ArrayList;
import java.util.List;

public class ControleurMainWindow {

    private Mode currentMode;

    private final List<ObserverMainWindow> observers ;


    public ControleurMainWindow(Mode mode) {
        this.currentMode = mode;
        this.observers = new ArrayList<>();
    }

    public void registerObserver(ObserverMainWindow observer) {
        this.observers.add(observer);
    }







    public Mode getCurrentMode() {
        return currentMode;
    }

    public void setCurrentMode(Mode currentMode) {
        this.currentMode = currentMode;
    }

}
