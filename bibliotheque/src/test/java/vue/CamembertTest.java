package vue;

import dao.Database;
import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.utils.Mode;
import modele.utils.TypeDeDocGrouping;
import vue.panels.graphs.Camembert;
import vue.panels.graphs.Histogramme;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 *  <h1> Test Camembert </h1>
 *
 * <p>
 *     Cette classe permet de tester fonctionnellement la classe Camembert
 * </p>
 *
 * @author : Alice
 * @version : 2.0
 * @since : 01/04/2023
 */

public class CamembertTest {

    /**
     * Test d'affichage visuellement que la fenêtre se lance correctement avec le graphique demande
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        test1();
        test2();
        test3();
    }

    public static void test1(){
        Thread thread = new Thread() {
            List<Parametre> liste;
            public void run() {
                try {
                    liste = Database.getParamByTypeDeDoc(ParametreType.LANGUE, TypeDeDocGrouping.LIVRES, Mode.EMPRUNTS);
                }
                catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer la fenêtre en cliquant sur la croix
                frame.setSize(550, 350); // Taille de la fenêtre
                frame.setLocationRelativeTo(null);

                frame.getContentPane().add(new Camembert(Objects.requireNonNull(liste.subList(0,5)), Mode.EMPRUNTS));
                frame.setVisible(true);

            }
        };
        thread.start();
    }

    public static void test2(){
        Thread thread = new Thread() {
            List<Parametre> liste;
            public void run() {
                try {
                    liste = Database.getParamByTypeDeDoc(ParametreType.AUTEUR, TypeDeDocGrouping.JEUX, Mode.EXEMPLAIRES);
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

    public static void test3(){
        Thread thread = new Thread() {
            List<Parametre> liste;
            public void run() {
                try {
                    liste = Database.getParamByTypeDeDoc(ParametreType.GENRE, TypeDeDocGrouping.BD, Mode.EMPRUNTS);
                }
                catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer la fenêtre en cliquant sur la croix
                frame.setSize(550, 350); // Taille de la fenêtre
                frame.setLocationRelativeTo(null);

                frame.getContentPane().add(new Camembert(Objects.requireNonNull(liste.subList(0,20)), Mode.EMPRUNTS));
                frame.setVisible(true);

            }
        };
        thread.start();
    }
}
