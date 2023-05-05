package controleur.mainWindow;

import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.utils.Mode;
import modele.utils.TypeDeDocGrouping;
import modele.utils.TypeGraph;

import java.util.List;

public interface ObserverMainWindow {


    void choisir();

    void updateGraphPanel(List<Parametre> liste, TypeGraph typeGraph, Mode currentMode);

    void updateGraphBarre(List<Parametre> listeexemplaire, List<Parametre> listeemprunt);
}
