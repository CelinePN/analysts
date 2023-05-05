package controleur.mainWindow;

import modele.parametre.ParametreType;
import modele.utils.Mode;
import modele.utils.TypeDeDocGrouping;

public interface ObserverMainWindow {


    void choisir();

    void fenetrefermer(Mode mode);

    void mode(Mode currentMode);

    void typeParametre(ParametreType parametreType);

    void limite(int limite);

    void typeDeDocGrouping(TypeDeDocGrouping typeDeDocGrouping);
}
