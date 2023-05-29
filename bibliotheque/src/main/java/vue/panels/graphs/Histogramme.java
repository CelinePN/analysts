package vue.panels.graphs;

import modele.parametre.Parametre;
import modele.utils.Mode;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;

    /**
     *
     * Classe pour afficher un graphique de type Histogramme
     * à partir de la liste de paramètre récupérée de nos données
     *
     * @author  Alice Hué
     * @version 1.0
     * @since   2023-03-30
     *
     **/

public class Histogramme extends JPanel {

    public Histogramme(List<Parametre> liste, Mode mode) {
        String typeParam = liste.get(0).getType_param().getString();
        this.setLayout(new BorderLayout());
        // Création des données
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String val="";
        for (Parametre param : liste){
            switch(mode){
                case EXEMPLAIRES:
                    val="OFFRE";
                    dataset.setValue(param.getTotalExemplaires(), "Nombre d'exemplaires",param.getNom());
                    break;

                case EMPRUNTS:
                    val="DEMANDE";
                    dataset.setValue(param.getTotalPrets(), "Nombre d'emprunts",param.getNom());
                    break;
            }
        }

        // Création du graphique en barres
        JFreeChart chart = ChartFactory.createBarChart(
                "Graphique "+val+" pour "+typeParam, // Titre du graphique
                typeParam, // Titre de l'axe des abscisses
                mode.getSortingString(), // Titre de l'axe des ordonnées
                dataset // Données à afficher
        );

        // Personnalisation du graphique
        BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
        renderer.setSeriesPaint(0, Color.BLUE); // Changer la couleur des barres
        renderer.setSeriesItemLabelGenerator(0,new StandardCategoryItemLabelGenerator()); // Ajouter des labels pour les barres
        renderer.setSeriesItemLabelsVisible(0,true);
        renderer.setSeriesItemLabelFont(0,new Font("SansSerif", Font.BOLD, 12));
        renderer.setSeriesItemLabelPaint(0,Color.BLACK);

        CategoryAxis categoryAxis = chart.getCategoryPlot().getDomainAxis();
        categoryAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        categoryAxis.setTickLabelsVisible(true);
        categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90); // Changer l'orientation de l'axe des abscisses

        // Création du panel contenant le graphique
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));

        this.add(chartPanel, BorderLayout.CENTER);

    }
}
