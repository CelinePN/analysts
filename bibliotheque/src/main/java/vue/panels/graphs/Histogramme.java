package vue.panels.graphs;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import modele.parametre.Parametre;

import modele.utils.SortBy;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

//import static modele.TypeDeDocEnum.FILMS;

public class Histogramme extends JPanel {

    /**
     *
     * Classe pour afficher un graphique de type Histogramme
     * à partir de la liste de paramètre récupérée de nos données
     *
     * @authors  Alice Hué & Mathilde Paquelier
     * @version 1.0
     * @since   2023-03-30
     * @param
     **/

    /**
     *
     * Un constructeur par défaut Histogramme qui prend aucun param à afficher au lancement
     * @return
     */

    public Histogramme() {
        // Création des données
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Création du graphique en barres
        JFreeChart chart = ChartFactory.createBarChart(
                "Graphique par défaut", // Titre du graphique
                "Catégories", // Titre de l'axe des abscisses
                "Valeurs", // Titre de l'axe des ordonnées
                dataset // Données à afficher
        );

        // Création du panel contenant le graphique
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));


        //-----------------DESIGNGRID LAYOUT-------------

        // Création du panel principal contenant le graphique, la liste déroulante et les boutons
        //JPanel mainPanel = new JPanel(new BorderLayout());
        this.add(chartPanel, BorderLayout.CENTER);

        // Ajout du panel principal à la fenêtre
        //setContentPane(mainPanel);
    }

   /* public Histogramme(List<Parametre> liste, SortBy sortBy) {
        // Création des données
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Parametre param : liste){
            switch(sortBy){
                case EXEMPLAIRES:
                    dataset.setValue(param.getTotalExemplaires(), "Nombre d'exemplaire",param.getNom());
                    break;

                case EMPRUNTS:
                    dataset.setValue(param.getTotalPrets(), "Nombre d'emprunt",param.getNom());
                    break;

                default:
                    dataset.setValue(param.getCount(), "Default",param.getNom());
                    break;
            }
        }

        // Création du graphique en barres
        JFreeChart chart = ChartFactory.createBarChart(
                "Mon graphique en barres", // Titre du graphique
                "Catégories", // Titre de l'axe des abscisses
                "Valeurs", // Titre de l'axe des ordonnées
                dataset // Données à afficher
        );

        // Création du panel contenant le graphique
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

        /**
         * Mettre tous les boutons dans MainWindow, Histogramme ne fait qu'un graphique
         */
    /*

        // Création des boutons
        JButton buttonLeft = new JButton("Retour Menu");
        JButton buttonRight = new JButton("Fermer");

        // Création de la liste déroulante
        String[] options = {"Option 1", "Option 2", "Option 3", "Option 4"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setSelectedIndex(0); // Option sélectionnée par défaut

        // Création du panel contenant la liste déroulante
        JPanel selectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        selectionPanel.add(new JLabel("Sélection : "));
        selectionPanel.add(comboBox);

        // Création du panel contenant les boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        buttonPanel.add(buttonLeft);
        buttonPanel.add(buttonRight);

        //-----------------DESIGNGRID LAYOUT-------------

        // Création du panel principal contenant le graphique, la liste déroulante et les boutons
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(selectionPanel, BorderLayout.WEST);
        mainPanel.add(chartPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Ajout du panel principal à la fenêtre
        setContentPane(mainPanel);
    }*/

}