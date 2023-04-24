package controleur.menu;

import modele.utils.Mode;

public interface ObserverMenu {

    public void selectioner();

    public void choisir();
    public void fenetrefermer(Mode mode);
}
