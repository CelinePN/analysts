package vue;

import modele.utils.Mode;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import vue.frames.MainWindow;

/**
 *  <h1> Test FirstScreen </h1>
 *
 * <p>
 *     Cette classe permet de tester la classe FirstScreen et les données récupérées
 * </p>
 *
 * @Author: Marine
 * @Version: 2.0
 * @since: 01/04/2023
 */

//tester de lancer MainWindow

public class MainWindowTest {
    private MainWindow mainWindow;

    /**
     * Test d'affichage visuellement que la fenêtre se lance correctement avec graphique par défaut
     */
    public static void main(String[] args) {
        new MainWindow(Mode.EXEMPLAIRES);
    }

    /**
     * Vérifie que la fenêtre se lance avec les attributs visibles aux bonnes places
     * Vérifie que par défaut, la fenêtre lance un graphique par défaut
     */
    @Before
    public void initTest(){
        this.mainWindow = new MainWindow(Mode.EXEMPLAIRES);
    }
    @Test
    public void testInitView() throws InterruptedException {
        /*firstScreenEco.initView();
        assertNotNull(firstScreenEco.getContentPane());
        assertNotNull(firstScreenEco.getPanelError());
        assertNotNull(firstScreenEco.getPanelError());*/
    }

    /**
     * Vérifie que le graphique s'affiche correctement avec les bonnes données
     */
    @Test
    public void testCamembert() {
        /*firstScreenEco.initView();
        assertNotNull(firstScreenEco.getContentPane());
        assertNotNull(firstScreenEco.getPanelError());
        assertNotNull(firstScreenEco.getPanelError());*/
    }

    /**
     * couper le wifi pour faire ce test
     */
    @Test
    public void testErrorLoading(){

    }
}
