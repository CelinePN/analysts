package vue.panels.graphs;

import modele.parametre.Parametre;
import modele.utils.SortBy;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CamembertBis extends JPanel {
    public CamembertBis(List<Parametre> liste, SortBy sortBy) {

        // Création des données
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Parametre param : liste){
            dataset.setValue(param.getNom(), param.getTotalExemplaires()); //db.getLanguages()
        }

        // Création du graphique en camembert
        JFreeChart chart = ChartFactory.createPieChart(
                "Mon graphique en camembert", // Titre du graphique
                dataset // Données à afficher
        );

    }
}
