package vue;

import dao.Database;
import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.utils.Mode;
import modele.utils.TypeDeDocGrouping;
import vue.panels.graphs.BarresEmpilees;
import vue.panels.graphs.Histogramme;

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
 * @author : Alice
 * @version : 2.0
 * @since : 01/04/2023
 */


public class BarresEmpileesTest {

    /**
     * Test d'affichage visuellement que la fenêtre se lance correctement avec graphique par defaut
     */
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    public static void test1(){
        Thread thread = new Thread() {
            List<Parametre> listeExemplaires;
            List<Parametre> listeEmprunts;
            public void run() {
                try {
                    listeExemplaires = Database.getParamByTypeDeDoc(ParametreType.LANGUE, TypeDeDocGrouping.LIVRES, Mode.EXEMPLAIRES);
                    listeEmprunts = Database.getParamByTypeDeDoc(ParametreType.LANGUE, TypeDeDocGrouping.LIVRES, Mode.EMPRUNTS);

                }
                catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer la fenêtre en cliquant sur la croix
                frame.setSize(550, 350); // Taille de la fenêtre
                frame.setLocationRelativeTo(null);

                frame.getContentPane().add(new BarresEmpilees(Objects.requireNonNull(listeExemplaires.subList(0,5)),Objects.requireNonNull(listeEmprunts.subList(0,5)) ));
                frame.setVisible(true);

            }
        };
        thread.start();
    }

    public static void test2(){
        Thread thread = new Thread() {
            List<Parametre> listeExemplaires;
            List<Parametre> listeEmprunts;
            public void run() {
                try {
                    listeExemplaires = Database.getParamByTypeDeDoc(ParametreType.AUTEUR, TypeDeDocGrouping.JEUX, Mode.EXEMPLAIRES);
                    listeEmprunts = Database.getParamByTypeDeDoc(ParametreType.AUTEUR, TypeDeDocGrouping.JEUX, Mode.EMPRUNTS);
                }
                catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer la fenêtre en cliquant sur la croix
                frame.setSize(550, 350); // Taille de la fenêtre
                frame.setLocationRelativeTo(null);

                frame.getContentPane().add(new BarresEmpilees(Objects.requireNonNull(listeExemplaires.subList(0,10)), Objects.requireNonNull(listeEmprunts.subList(0,10))));
                frame.setVisible(true);

            }
        };
        thread.start();
    }

    public static void test3(){
        Thread thread = new Thread() {
            List<Parametre> listeExemplaires;
            List<Parametre> listeEmprunts;
            public void run() {
                try {
                    listeExemplaires = Database.getParamByTypeDeDoc(ParametreType.GENRE, TypeDeDocGrouping.BD, Mode.EXEMPLAIRES);
                    listeEmprunts = Database.getParamByTypeDeDoc(ParametreType.GENRE, TypeDeDocGrouping.BD, Mode.EMPRUNTS);
                }
                catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer la fenêtre en cliquant sur la croix
                frame.setSize(550, 350); // Taille de la fenêtre
                frame.setLocationRelativeTo(null);

                frame.getContentPane().add(new BarresEmpilees(Objects.requireNonNull(listeExemplaires.subList(0,20)), Objects.requireNonNull(listeEmprunts.subList(0,20))));
                frame.setVisible(true);

            }
        };
        thread.start();
    }
}
