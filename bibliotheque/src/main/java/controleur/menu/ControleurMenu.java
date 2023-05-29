package controleur.menu;

import modele.utils.Mode;
import java.util.ArrayList;
import java.util.List;

/**
 *  <h1> MainWindow Controleur </h1>
 *
 * <p>
 *      Cette classe gère les intéractions du menu.
 *
 * </p>
 *
 * @author Mathilde
 * @version 1.0
 * @since 09/05/2023
 */


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
