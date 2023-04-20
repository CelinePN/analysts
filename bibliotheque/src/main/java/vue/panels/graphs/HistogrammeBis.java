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

public class HistogrammeBis extends JPanel {
    public HistogrammeBis(List<Parametre> liste, SortBy sortBy) {
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

    }


}
