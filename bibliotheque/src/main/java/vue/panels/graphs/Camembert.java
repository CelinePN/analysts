package vue.panels.graphs;

import modele.parametre.Parametre;
import modele.utils.SortBy;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.util.List;

public class Camembert extends JPanel {

    /**
     *
     * Classe pour afficher un graphique de type Camembert
     * à partir de la liste de paramètre récupérée de nos données
     *
     * @author  Alice Hué
     * @version 1.0
     * @since   2023-03-30
     *
     **/
    public Camembert(List<Parametre> liste, SortBy sortBy) {

        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        for (Parametre param : liste){
            switch(sortBy){
                case EXEMPLAIRES:
                    dataset.setValue(param.getNom(), param.getTotalExemplaires());
                    break;

                case EMPRUNTS:
                    dataset.setValue(param.getNom(), param.getTotalPrets());
                    break;
            }
        }

        // Création du graphique en camembert
        JFreeChart chart = ChartFactory.createPieChart(
                "Mon graphique en camembert", // Titre du graphique
                dataset // Données à afficher
        );

    }
}
