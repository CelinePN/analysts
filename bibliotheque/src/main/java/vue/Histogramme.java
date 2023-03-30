package vue;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class Histogramme extends JPanel {

        private static final long serialVersionUID = 1L;

    public Histogramme() {
            JFreeChart chart = createChart(createDataset());
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(500, 300));
            add(chartPanel);




        }

        private DefaultCategoryDataset createDataset() {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

           /* Random rand = new Random();
            for (int i = 1; i <= 10; i++) {
                dataset.addValue(rand.nextInt(50), "Valeurs", "Catégorie " + i);
            }*/

            return dataset;
        }

        private JFreeChart createChart(DefaultCategoryDataset dataset) {
            JFreeChart chart = ChartFactory.createBarChart(
                    "Histogramme ", // titre du graphique
                    "Catégories", // étiquette de l'axe des abscisses
                    "Valeurs", // étiquette de l'axe des ordonnées
                    dataset, // données
                    PlotOrientation.VERTICAL, // orientation du graphique
                    true, // légende
                    true, // outils
                    false // URL
            );

            chart.setBackgroundPaint(Color.white);

            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            plot.setBackgroundPaint(Color.lightGray);
            plot.setDomainGridlinePaint(Color.white);
            plot.setRangeGridlinePaint(Color.white);

           /* CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
            xAxis.setLowerMargin(0.01);
            xAxis.setUpperMargin(0.01);

            NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
            yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
*/
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setDrawBarOutline(false);

            return chart;
        }

        public static void main(String[] args) {
            JFrame frame = new JFrame("Les Histogrammes");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new Histogramme());
            frame.pack();
            frame.setVisible(true);
        }

    }