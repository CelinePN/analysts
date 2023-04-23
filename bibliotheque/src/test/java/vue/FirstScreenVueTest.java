package vue;

import org.junit.jupiter.api.Test;
import vue.frames.FirstScreen;

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
    private final FirstScreen firstScreenEco= new FirstScreen();

    @Test
    public void testInitView() {
        firstScreenEco.initView();
        assertNotNull(firstScreenEco.getContentPane());

    }

    @Test
    public void testRetry() {
        assertTrue(firstScreenEco.getProgressBar().isVisible());
        firstScreenEco.retry();
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
        assertFalse(firstScreenEco.getProgressBar().isVisible());
    }
}
