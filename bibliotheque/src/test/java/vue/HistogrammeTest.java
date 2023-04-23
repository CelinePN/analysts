package vue;

import dao.Database;
import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.utils.SortBy;
import modele.utils.TypeDeDocGrouping;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import vue.frames.MainWindow;
import vue.panels.graphs.Camembert;
import vue.panels.graphs.Histogramme;

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

public class HistogrammeTest {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            List<Parametre> liste;
            public void run() {
                try {
                    liste = Database.getParamByTypeDeDoc(ParametreType.LANGUE, TypeDeDocGrouping.LIVRES, SortBy.EMPRUNTS);
                }
                catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer la fenêtre en cliquant sur la croix
                frame.setSize(550, 350); // Taille de la fenêtre
                frame.setLocationRelativeTo(null);

                frame.getContentPane().add(new Histogramme(Objects.requireNonNull(liste.subList(0,20)), SortBy.EMPRUNTS));
                frame.setVisible(true);

            }
        };
        thread.start();
    }
}
