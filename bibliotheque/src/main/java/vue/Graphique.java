package vue;

import javax.swing.*;
import java.awt.*;
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
                "Mon graphique en camembert", // Titre du graphique
                dataset // Données à afficher
        );

        // Création du panel contenant le graphique
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

        // Création des boutons
        JButton buttonLeft = new JButton("Bouton gauche");
        JButton buttonRight = new JButton("Bouton droit");

        // Création de la liste déroulante
        String[] options = {"Option 1", "Option 2", "Option 3", "Option 4"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setSelectedIndex(0); // Option sélectionnée par défaut

        // Création du panel contenant la liste déroulante
        JPanel selectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        selectionPanel.add(new JLabel("Sélection : "));
        selectionPanel.add(comboBox);

        // Création du panel contenant les boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        buttonPanel.add(buttonLeft);
        buttonPanel.add(buttonRight);

        // Création du panel principal contenant le graphique, la liste déroulante et les boutons
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(selectionPanel, BorderLayout.CENTER);
        mainPanel.add(chartPanel, BorderLayout.WEST);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Ajout du panel principal à la fenêtre
        setContentPane(mainPanel);
    }

    public static void main(String[] args) {
        Graphique myChart = new Graphique();
        myChart.pack();
        myChart.setVisible(true);
    }
}