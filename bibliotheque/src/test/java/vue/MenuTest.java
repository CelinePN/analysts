package vue;

import modele.utils.Mode;
import org.junit.jupiter.api.Test;
import vue.frames.MainWindow;
import vue.frames.Menu;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  <h1> Test Menu </h1>
 *
 * <p>
 *     Cette classe permet de tester la classe Menu selon le mode selectionne
 * </p>
 *
 * @author : Alice
 * @version : 2.0
 * @since : 01/04/2023
 */


public class MenuTest {

    public static void main(String[] args) {
        new Menu();
    }

    @Test
    public void testButtonClick() {
        Menu menu = new Menu();
        menu.getBtnDemande().doClick();
        assertEquals(Mode.EMPRUNTS, menu.getControleurMenu().getCurrentMode());
    }

    @Test
    public void testInitView() {
        Menu menu = new Menu();
        assertNotNull(menu.getContentPane());
    }

}
