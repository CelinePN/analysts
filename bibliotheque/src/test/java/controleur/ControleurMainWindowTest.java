package controleur;

import controleur.firstscreen.ControleurFirstScreen;
import controleur.mainWindow.ControleurMainWindow;
import controleur.menu.ControleurMenu;
import dao.Database;
import modele.Cache;
import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.utils.Mode;
import modele.utils.TypeDeDocGrouping;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  <h1> Test FirstScreen Controleur </h1>
 *
 * <p>
 *     Cette classe permet de tester la classe FirstScreen et les donnees recuperees
 * </p>
 *
 * @author : Celine
 * @version : 1.0
 * @since : 14/05/2023
 */



public class ControleurMainWindowTest {
    /**
     * Verifie que le controleur menu se creer correctement avec un mode
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
        ControleurMenu menuTest = new ControleurMenu();
        menuTest.setCurrentMode(Mode.EXEMPLAIRES);
        assertEquals(Mode.EXEMPLAIRES, menuTest.getCurrentMode());
    }

}