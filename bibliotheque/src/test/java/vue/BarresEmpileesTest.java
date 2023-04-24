package vue;

import dao.Database;
import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.utils.Mode;
import modele.utils.TypeDeDocGrouping;
import vue.panels.graphs.BarresEmpilees;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 *  <h1> Test Barres Empilees </h1>
 *
 * <p>
 *     Cette classe permet de tester la classe BarresEmpilees
 * </p>
 *
 * @Author: Alice
 * @Version: 2.0
 * @since: 01/04/2023
 */


public class BarresEmpileesTest {

    /**
     * Test d'affichage visuellement que la fenêtre se lance correctement avec graphique par défaut
     */
    public static void main(String[] args) {
            Thread thread = new Thread() {
                List<Parametre> listeExemplaires;
                List<Parametre> listeEmprunts;

                public void run() {
                    try {
                        listeExemplaires = Database.getParamByTypeDeDoc(ParametreType.AUTEUR, TypeDeDocGrouping.BD, Mode.EXEMPLAIRES);
                        listeEmprunts = Database.getParamByTypeDeDoc(ParametreType.AUTEUR, TypeDeDocGrouping.BD, Mode.EMPRUNTS);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }

                    JFrame frame = new JFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer la fenêtre en cliquant sur la croix
                    frame.setSize(550, 350); // Taille de la fenêtre
                    frame.setLocationRelativeTo(null);

                    frame.getContentPane().add(new BarresEmpilees(Objects.requireNonNull(listeExemplaires.subList(0, 10)), Objects.requireNonNull(listeEmprunts.subList(0, 10))));
                    frame.setVisible(true);

                }
            };
            thread.start();

    }

}
