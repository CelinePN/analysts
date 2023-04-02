package vue.panels.graphs;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

import dao.Database;
//import modele.TypeDeDocEnum;
import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.utils.TypeDeDocGrouping;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarresEmpilees extends JFrame {

    /**
     *
     * Classe pour afficher un graphique de type Barres Empilees
     * à partir de la liste de paramètre récupérée de nos données
     *
     * @author  Alice Hué
     * @version 1.0
     * @since   2023-03-30
     *
     **/
    public BarresEmpilees(List<Parametre> liste){
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
        mainPanel.add(selectionPanel, BorderLayout.WEST);
        mainPanel.add(chartPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Ajout du panel principal à la fenêtre
        setContentPane(mainPanel);
    }

    public static void main(String[] args) throws IOException {
        Database db = new Database();
        List<Parametre> listeCategorie = db.getParamByTypeDeDoc(ParametreType.GENRE, TypeDeDocGrouping.LIVRES);
        BarresEmpilees myChart = new BarresEmpilees(listeCategorie);
        myChart.pack();
        myChart.setVisible(true);
    }
}