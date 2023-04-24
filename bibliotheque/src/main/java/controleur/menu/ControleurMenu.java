package controleur.menu;


import controleur.firstscreen.ObserverFirstScreen;
import vue.frames.MainWindow;
import vue.frames.Menu;
import modele.utils.Mode;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ControleurMenu {

    private Mode currentMode;

    private final List<ObserverMenu> observers ;


    public ControleurMenu() {
        this.currentMode = null;
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
        if (this.getCurrentMode()!=null) {
            for (ObserverMenu observer : observers) {
                observer.fenetrefermer(this.getCurrentMode());
            }
        }

        else{
             for(ObserverMenu observer : observers){
                 observer.choisir();
             }
        }
    }
}
