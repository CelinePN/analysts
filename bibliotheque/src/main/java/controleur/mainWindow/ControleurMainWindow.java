package controleur.mainWindow;

import controleur.menu.ObserverMenu;
import modele.Cache;
import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.utils.Mode;
import modele.utils.TypeDeDocGrouping;
import modele.utils.TypeGraph;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  <h1> MainWindow Controleur </h1>
 *
 * <p>
 *      Cette classe est reponsable de la gestion des intéractions et du
 *      contrôle des données pour la mainwindow.
 * </p>
 *
 * @author Mathilde
 * @version 1.0
 * @since 09/05/2023
 */


public class ControleurMainWindow {

    private Mode currentMode;
    private ParametreType parametreType;
    private TypeDeDocGrouping typeDeDocGrouping;
    private final List<ObserverMainWindow> observers;
    private TypeGraph typeGraph;
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

    public TypeGraph getTypeGraph() {
        return typeGraph;
    }

    public void setTypeGraph(TypeGraph typeGraph) {
        this.typeGraph = typeGraph;
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

    /**
     * Gestion de la validation des parametres. En fonction de tous les boutons selectionnes, met a jour la vue en consequence
     */
    public void valider() throws Exception {
        if (this.getParametreType() != null && this.getLimite() != 0 && this.getTypeDeDocGrouping() != null && this.getTypeGraph() != null) {
            if (this.getCurrentMode() == Mode.BOTH) {
                List<Parametre> listeemprunt = Cache.get(this.getParametreType(), this.getTypeDeDocGrouping(), Mode.EMPRUNTS, this.getLimite());
                List<Parametre> listeexemplaire = Cache.get(this.getParametreType(), this.getTypeDeDocGrouping(), Mode.EXEMPLAIRES, this.getLimite());
                if (!listeexemplaire.isEmpty() && !listeemprunt.isEmpty()) {
                    for (ObserverMainWindow observer : observers) {
                        observer.updateGraphBarre(listeexemplaire, listeemprunt);
                    }
                }
            } else {
                List<Parametre> liste = Cache.get(this.getParametreType(), this.getTypeDeDocGrouping(), this.getCurrentMode(), this.getLimite());
                if (!liste.isEmpty()) {
                    for (ObserverMainWindow observer : observers) {
                        observer.updateGraphPanel(liste, this.getTypeGraph(), this.getCurrentMode());
                    }
                }
            }
        }
        else{
            throw new Exception("Les parametres ne sont pas initialisés");
        }
    }
}



