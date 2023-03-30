package vue;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class Graphique extends JFrame {

    public Graphique() {
        // Création des données
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Partie 1", 40);
        dataset.setValue("Partie 2", 20);
        dataset.setValue("Partie 3", 30);
        dataset.setValue("Partie 4", 10);

        // Création du graphique en camembert
        JFreeChart chart = ChartFactory.createPieChart(
                "Les langues les plus proposées", // Titre du graphique
                dataset // Données à afficher
        );

        // Création du panel contenant le graphique
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

        // Ajout du panel à la fenêtre
        setContentPane(chartPanel);
    }

    public static void main(String[] args) {
        Graphique myChart = new Graphique();
        myChart.pack();
        myChart.setVisible(true);
    }
}