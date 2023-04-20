package vue.panels.graphs;

import modele.parametre.Parametre;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BarresEmpileesBis extends JPanel{
    public BarresEmpileesBis(List<Parametre> liste){
        // Création des données
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Parametre param : liste){
            dataset.setValue(param.getTotalExemplaires(), "Offre", param.getNom()); //Offre
            dataset.setValue(param.getTotalPrets(), "Demande", param.getNom()); //Demande
        }
        // Exemple :
        // dataset.addValue(40, "Offre", "Roman Policier");
        // dataset.addValue(20, "Demande", "Roman Policier");

        // Création du graphique en barres empilées
        JFreeChart chart = ChartFactory.createStackedBarChart(
                "Mon graphique en barres empilées", // Titre du graphique
                "Catégories", // Titre de l'axe des abscisses
                "Valeurs", // Titre de l'axe des ordonnées
                dataset // Données à afficher
        );

    }
}
