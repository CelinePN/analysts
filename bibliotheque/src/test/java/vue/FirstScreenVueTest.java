package vue;

import dao.DatabaseEco;
import modele.DoubleKeyCacheEco;
import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.utils.SortBy;
import modele.utils.TypeDeDocGrouping;
import org.junit.jupiter.api.Test;
import vue.frames.FirstScreenEco;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

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



public class FirstScreenVueTest {
    private final FirstScreenEco firstScreenEco= new FirstScreenEco();

    @Test
    public void testInitView() {
        firstScreenEco.initView();
        assertNotNull(firstScreenEco.getContentPane());
        assertNotNull(firstScreenEco.getPanelError());
        assertNotNull(firstScreenEco.getPanelError());
    }

    @Test
    public void testRetry() {
        assertFalse(firstScreenEco.getPanelError().isVisible());
        assertTrue(firstScreenEco.getProgressBar().isVisible());
        firstScreenEco.retry();
        assertFalse(firstScreenEco.getPanelError().isVisible());
        assertTrue(firstScreenEco.getProgressBar().isVisible());
    }

    @Test
    public void testProgressBar(){
        firstScreenEco.getControleurFirstScreen().loadData();

        //attends que toutes les données soient chargées avant de tester le chargement du cache
        while (!firstScreenEco.getControleurFirstScreen().isDataLoaded()) {
            try {
                Thread.sleep(100); // wait for 100 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
         assertEquals(100, firstScreenEco.getProgressBar().getValue());
         assertFalse(firstScreenEco.getProgressBar().isVisible());
    }

    /**
     * couper le wifi pour faire ce test
     */
    @Test
    public void testErrorLoading(){
        firstScreenEco.getControleurFirstScreen().loadData();

        //attends que toutes les données soient chargées avant de tester le chargement du cache
        while (firstScreenEco.getControleurFirstScreen().getThread().isAlive()) {
            try {
                Thread.sleep(100); // wait for 100 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        assertTrue(firstScreenEco.getPanelError().isVisible());
        assertFalse(firstScreenEco.getProgressBar().isVisible());
    }
}
