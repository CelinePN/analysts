package vue.panels.graphs;

import modele.parametre.Parametre;
import modele.utils.Mode;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.ChartPanel;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *
 * Classe pour afficher un graphique de type Camembert
 * a partir de la liste de parametre recuperee de nos donnees
 *
 * @author  Alice Hue
 * @since   30/03/2023
 * @version 1.0
 *
 **/
/**
 *
 * Classe pour afficher un graphique de type Camembert
 * a partir de la liste de parametre recuperee de nos donnees
 *
 * @author  Alice Hue
 * @version 1.0
 * @since   2023-03-30
 *
 **/
public class Camembert extends JPanel {

    public Camembert(List<Parametre> liste, Mode sortBy) {

        String typeParam = liste.get(0).getType_param().getString();
        this.setLayout(new BorderLayout());

        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        String val="";

        for (Parametre param : liste){
            switch(sortBy){
                case EXEMPLAIRES:
                    val="OFFRE";
                    dataset.setValue(param.getNom(), param.getTotalExemplaires());
                    break;

                case EMPRUNTS:
                    val="DEMANDE";
                    dataset.setValue(param.getNom(), param.getTotalPrets());
                    break;
            }
        }

        // Création du graphique en camembert
        JFreeChart chart = ChartFactory.createPieChart(
                "Graphique "+val+" pour "+typeParam, // Titre du graphique
                dataset // Données à afficher
        );

        // Création du panel contenant le graphique
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        this.add(chartPanel, BorderLayout.CENTER);

    }
}
