package controleur;

import controleur.firstscreen.ControleurFirstScreen;
import controleur.mainWindow.ControleurMainWindow;
import controleur.mainWindow.ObserverMainWindow;
import controleur.menu.ControleurMenu;
import dao.Database;
import modele.Cache;
import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.utils.Mode;
import modele.utils.TypeDeDocGrouping;
import modele.utils.TypeGraph;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  <h1> Test MainWindow Controleur </h1>
 *
 * <p>
 *     Cette classe permet de tester la classe MainWindow et les donnees recuperees
 * </p>
 *
 * @author : Celine
 * @version : 1.0
 * @since : 14/05/2023
 */



public class ControleurMainWindowTest {
    /**
     * Verifie que le controleur MainWindow se crée correctement avec un mode
     *
     */
    @Test
    public void testConstructeurControleurMainWindow() {
        ControleurMainWindow mainTest = new ControleurMainWindow(Mode.EXEMPLAIRES);
        assertEquals(Mode.EXEMPLAIRES, mainTest.getCurrentMode());
    }

    /**
     * Verifie que le setter du mode change bien le mode enregistre
     *
     */
    @Test
    public void testControleurMenuSetCurrentMode() {
        ControleurMainWindow mainTest = new ControleurMainWindow(Mode.EMPRUNTS);
        mainTest.setCurrentMode(Mode.EXEMPLAIRES);
        assertEquals(Mode.EXEMPLAIRES, mainTest.getCurrentMode());
    }

    /**
     * Verifie que l'observer se met bien à jour
     *
     */
    /*@Test
    public void testControleurMainSetObserver() {
        ControleurMainWindow mainTest = new ControleurMainWindow(Mode.EXEMPLAIRES);
        mainTest.registerObserver(ObserverMainWindow.choisir());
        assertEquals(Mode.EXEMPLAIRES, mainTest.getCurrentMode());
    }*/

    /**
     * Verifie que le setter du type de doc change bien le type enregistre
     *
     */
    @Test
    public void testControleurMainSetTypeDeDocGrouping() {
        ControleurMainWindow mainTest = new ControleurMainWindow(Mode.EXEMPLAIRES);
        mainTest.setTypeDeDocGrouping(TypeDeDocGrouping.LIVRES);
        assertEquals(TypeDeDocGrouping.LIVRES, mainTest.getTypeDeDocGrouping());
    }

    /**
     * Verifie que le setter de la limite change bien la limite enregistre
     *
     */
    @Test
    public void testControleurMainSetLimite() {
        ControleurMainWindow mainTest = new ControleurMainWindow(Mode.EXEMPLAIRES);
        mainTest.setLimite(7);
        assertEquals(7, mainTest.getLimite());
    }

    /**
     * Verifie que le setter du type de graph change bien le graph enregistre
     *
     */
    @Test
    public void testControleurMainSetTypeGraph() {
        ControleurMainWindow mainTest = new ControleurMainWindow(Mode.EXEMPLAIRES);
        mainTest.setTypeGraph(TypeGraph.HISTOGRAMMES);
        assertEquals(TypeGraph.HISTOGRAMMES, mainTest.getTypeGraph());
    }

    /**
     * Verifie que le setter du parametre type change bien le parametre enregistre
     *
     */
    @Test
    public void testControleurMainSetTypeParametre() {
        ControleurMainWindow mainTest = new ControleurMainWindow(Mode.EXEMPLAIRES);
        mainTest.setParametreType(ParametreType.GENRE);
        assertEquals(ParametreType.GENRE, mainTest.getParametreType());
    }
}