package vue;

import modele.utils.Mode;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import vue.frames.MainWindow;

import static modele.parametre.ParametreType.LANGUE;
import static modele.utils.TypeDeDocGrouping.NO_TYPE;
import static org.junit.jupiter.api.Assertions.*;

/**
 *  <h1> Test MainWindow </h1>
 *
 * <p>
 *     Cette classe permet de tester l'affichage de la classe MainWindow
 * </p>
 *
 * @author : Marine
 * @version : 2.0
 * @since : 01/04/2023
 */

public class MainWindowTest {

    /**
     * Verifie que la fenêtre se lance avec les attributs visibles aux bonnes places selon le mode
     * Verifie que par defaut, la fenêtre lance un graphique par defaut
     */

    @Test
    public void testInitDemande() {
        MainWindow mainWindow = new MainWindow(Mode.EXEMPLAIRES);
        assertTrue(mainWindow.getBtnCamembert().isSelected());
        assertEquals(1, mainWindow.getComboBoxLimite().getSelectedItem());
        assertEquals(LANGUE, mainWindow.getComboBoxParametre().getSelectedItem());
        assertEquals(NO_TYPE, mainWindow.getComboBoxType().getSelectedItem());
    }
    @Test
    public void testInitComparaison() {
        MainWindow mainWindow = new MainWindow(Mode.BOTH);
        assertTrue(mainWindow.getBtnBarresEmpilees().isSelected());
        assertEquals(1, mainWindow.getComboBoxLimite().getSelectedItem());
        assertEquals(LANGUE, mainWindow.getComboBoxParametre().getSelectedItem());
        assertEquals(NO_TYPE, mainWindow.getComboBoxType().getSelectedItem());
    }

}
