package controleur.menu;
import modele.utils.Mode;

/**
 *  <h1> Menu Observer </h1>
 *
 * <p>
 *      Cette interface permet de mettre a jour la vue de la Menu grace au controleur de Menu.
 * </p>
 *
 * @author Mathilde
 * @version 1.0
 * @since 13/05/2023
 */

public interface ObserverMenu {

    void choisir();
    void fenetrefermer(Mode mode);
}
