package controleur.firstscreen;

/**
 *  <h1> FirstScreen Observer </h1>
 *
 * <p>
 *      Cette interface permet de mettre a jour la vue de la FirstScreen grace au controleur de FirstScreen.
 * </p>
 *
 * @author Marine
 * @version 1.0
 * @since 09/05/2023
 */


public interface ObserverFirstScreen {
    public void updateProgressBar(int progression);
    public void loadingFailed();
    public void loadingSuccess();
    public void retry();

}
