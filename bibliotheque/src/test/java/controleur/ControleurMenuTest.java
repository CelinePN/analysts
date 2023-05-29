package controleur;

import controleur.firstscreen.ControleurFirstScreen;
import controleur.menu.ControleurMenu;
import controleur.menu.ObserverMenu;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  <h1> Test du Controleur Menu </h1>
 *
 * <p>
 *     Cette classe permet de tester la classe ControleurMenu
 * </p>
 *
 * @author : Celine
 * @version : 1.0
 * @since : 13/05/2023
 */



public class ControleurMenuTest {

    /**
     * Verifie que le controleur menu se creer correctement
     *
     */
    @Test
    public void testConstructeurControleurMenu() {
        ControleurMenu menuTest = new ControleurMenu();
        assertEquals(null, menuTest.getCurrentMode());
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

    @Test
    public void testControleurMenuSetCurrentMode2() {
        ControleurMenu menuTest = new ControleurMenu();
        menuTest.setCurrentMode(Mode.EXEMPLAIRES);
        menuTest.setCurrentMode(Mode.EMPRUNTS);
        assertEquals(Mode.EMPRUNTS, menuTest.getCurrentMode());
    }

    @Test
    public void testControleurMenuSetCurrentMode3() {
        ControleurMenu menuTest = new ControleurMenu();
        menuTest.setCurrentMode(Mode.EXEMPLAIRES);
        menuTest.setCurrentMode(Mode.EMPRUNTS);
        menuTest.setCurrentMode(Mode.BOTH);
        assertEquals(Mode.BOTH, menuTest.getCurrentMode());
    }

}
