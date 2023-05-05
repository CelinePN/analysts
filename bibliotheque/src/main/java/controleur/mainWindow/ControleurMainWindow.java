package controleur.mainWindow;

import controleur.menu.ObserverMenu;
import modele.parametre.ParametreType;
import modele.utils.Mode;
import modele.utils.TypeDeDocGrouping;

import java.util.ArrayList;
import java.util.List;

public class ControleurMainWindow {

    private Mode currentMode;
    private ParametreType parametreType;
    private TypeDeDocGrouping typeDeDocGrouping;
    private final List<ObserverMainWindow> observers ;

    private int limite;


    public ControleurMainWindow(Mode mode) {
        this.currentMode = mode;
        this.observers = new ArrayList<>();
    }

    public void registerObserver(ObserverMainWindow observer) {
        this.observers.add(observer);
    }

    public TypeDeDocGrouping getTypeDeDocGrouping() {
        return typeDeDocGrouping;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }
    public void setTypeDeDocGrouping(TypeDeDocGrouping typeDeDocGrouping) {
        this.typeDeDocGrouping = typeDeDocGrouping;
    }
    public ParametreType getParametreType() {
        return parametreType;
    }

    public void setParametreType(ParametreType parametreType) {
        this.parametreType = parametreType;
    }

    public Mode getCurrentMode() {
        return currentMode;
    }

    public void setCurrentMode(Mode currentMode) {
        this.currentMode = currentMode;
    }

    public void valider() {
        if (this.getCurrentMode()!=null) {
            for (ObserverMainWindow observer : observers) {
               // observer.fenetrefermer(this.getCurrentMode());

                observer.mode(this.getCurrentMode());
                observer.typeParametre(this.getParametreType());
                observer.limite(this.getLimite());
                observer.typeDeDocGrouping(this.getTypeDeDocGrouping());
                // if

            }
        }

        else{
            for(ObserverMainWindow observer : observers){
                observer.choisir();
            }
        }
    }


}
