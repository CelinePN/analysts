package vue.panels.graphs;

import modele.parametre.Parametre;
import modele.utils.SortBy;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Histogramme extends JPanel {

    //ajouter un histogramme par défaut (voir classe histogramme)

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

        this.add(chartPanel, BorderLayout.CENTER);

    }
    public Histogramme(List<Parametre> liste, SortBy sortBy) {
        this.setLayout(new BorderLayout());
        // Création des données
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Parametre param : liste){
            switch(sortBy){
                case EXEMPLAIRES:
                    dataset.setValue(param.getTotalExemplaires(), "Nombre d'exemplaires",param.getNom());
                    break;

                case EMPRUNTS:
                    dataset.setValue(param.getTotalPrets(), "Nombre d'emprunts",param.getNom());
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
        chartPanel.setPreferredSize(new Dimension(500, 270));

        this.add(chartPanel, BorderLayout.CENTER);

    }


}
