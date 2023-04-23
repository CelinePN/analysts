package vue.panels.graphs;

import modele.parametre.Parametre;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BarresEmpilees extends JPanel{

    public BarresEmpilees(){
        // Création des données
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Création du graphique en barres empilées
        JFreeChart chart = ChartFactory.createStackedBarChart(
                "Graphique par défaut ", // Titre du graphique
                "Catégories", // Titre de l'axe des abscisses
                "Valeurs", // Titre de l'axe des ordonnées
                dataset // Données à afficher
        );

    }

    public BarresEmpilees(List<Parametre> liste){
        this.setLayout(new BorderLayout());
        String type = liste.get(0).getType_param().getString();
        // Création des données
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Parametre param : liste){
            dataset.setValue(param.getTotalExemplaires(), "Offre", param.getNom()); //Offre
            dataset.setValue(param.getTotalPrets(), "Demande", param.getNom()); //Demande
        }


        // Création du graphique en barres empilées
        JFreeChart chart = ChartFactory.createStackedBarChart(
                "Comparaison offre et demande de "+type, // Titre du graphique
                "Catégories", // Titre de l'axe des abscisses
                "Valeurs", // Titre de l'axe des ordonnées
                dataset // Données à afficher
        );

        // Création du panel contenant le graphique
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

        this.add(chartPanel, BorderLayout.CENTER);

    }
}
