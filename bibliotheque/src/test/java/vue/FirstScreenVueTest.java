package vue;

import org.junit.jupiter.api.Test;
import vue.frames.FirstScreen;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  <h1> Test FirstScreen </h1>
 *
 * <p>
 *     Cette classe permet de tester la classe FirstScreen et les donnees recuperees
 * </p>
 *
 * @author : Marine
 * @version : 2.0
 * @since : 01/04/2023
 */



public class FirstScreenVueTest {
    private final FirstScreen firstScreen= new FirstScreen();

    @Test
    public void testInitView() {
        firstScreen.initView();
        assertNotNull(firstScreen.getContentPane());
    }

    @Test
    public void testRetry() {
        assertTrue(firstScreen.getProgressBar().isVisible());
        firstScreen.retry();
        assertTrue(firstScreen.getProgressBar().isVisible());
    }

    @Test
    public void testProgressBar(){
        firstScreen.getControleurFirstScreen().loadData();

        //attends que toutes les donnees soient chargees avant de tester le chargement du cache
        while (!firstScreen.getControleurFirstScreen().isDataLoaded()) {
            try {
                Thread.sleep(100); // wait for 100 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
         assertEquals(100, firstScreen.getProgressBar().getValue());
    }

    /**
     * couper le wifi pour faire ce test
     */
    @Test
    public void testErrorLoading(){
        firstScreen.getControleurFirstScreen().loadData();

        //attends que toutes les donnees soient chargees avant de tester le chargement du cache
        while (firstScreen.getControleurFirstScreen().getThread().isAlive()) {
            try {
                Thread.sleep(100); // wait for 100 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        assertTrue(firstScreen.getProgressBar().isVisible());
    }
}
