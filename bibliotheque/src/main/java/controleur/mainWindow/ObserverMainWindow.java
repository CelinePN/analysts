package controleur.mainWindow;
import modele.parametre.Parametre;
import modele.utils.Mode;
import modele.utils.TypeGraph;
import java.util.List;

/**
 *  <h1> MainWindow Observer </h1>
 *
 * <p>
 *      Cette interface permet de mettre a jour la vue de la MainWindow grace au controleur de MainWindow.
 * </p>
 *
 * @author Mathilde
 * @version 1.0
 * @since 13/05/2023
 */

public interface ObserverMainWindow {

    void choisir();

    void updateGraphPanel(List<Parametre> liste, TypeGraph typeGraph, Mode currentMode);

    void updateGraphBarre(List<Parametre> listeexemplaire, List<Parametre> listeemprunt);
}
