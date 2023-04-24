package vue;

import dao.Database;
import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.utils.Mode;
import modele.utils.TypeDeDocGrouping;
import vue.panels.graphs.Camembert;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

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

public class CamembertTest {

    /**
     * Test d'affichage visuellement que la fenêtre se lance correctement avec graphique par défaut
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        Thread thread = new Thread() {
            List<Parametre> liste;
            public void run() {
                try {
                    liste = Database.getParamByTypeDeDoc(ParametreType.AUTEUR, TypeDeDocGrouping.FILMS, Mode.EXEMPLAIRES);
                }
                catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer la fenêtre en cliquant sur la croix
                frame.setSize(550, 350); // Taille de la fenêtre
                frame.setLocationRelativeTo(null);

                frame.getContentPane().add(new Camembert(Objects.requireNonNull(liste.subList(0,10)), Mode.EXEMPLAIRES));
                frame.setVisible(true);

            }
        };
        thread.start();

    }
}
