package vue.frames;

import controleur.mainWindow.ControleurMainWindow;
import modele.parametre.ParametreType;
import modele.utils.Mode;
import modele.utils.TypeDeDocGrouping;
import vue.panels.graphs.Histogramme;
import javax.swing.*;
import net.java.dev.designgridlayout.DesignGridLayout;
import vue.panels.ButtonsPanel;
import vue.panels.graphs.Histogramme;

import java.awt.*;

public class MainWindow extends JFrame {
    private JPanel panelLeft, panelRight;
    private JComboBox<String> comboBoxParametre, comboBoxPartType, comboBoxLimite;
    private JRadioButton btnCamembert, btnHistogramme, btnBarresEmpilees;
    private ButtonGroup buttonGroup;
    private JButton btnValider;
    private JLabel labelTop;
    private final ControleurMainWindow controleurMainWindow;

    public MainWindow(Mode mode) {
        super("Les bibliothèques de PARIS"); // Titre de la fenêtre
        this.controleurMainWindow= new ControleurMainWindow(mode);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Label en haut
        labelTop = new JLabel("Les documents des bibliothèque de Paris");
        labelTop.setHorizontalAlignment(JLabel.CENTER);

        // Boutons à gauche
        btnCamembert = new JRadioButton("Camembert");
        btnHistogramme = new JRadioButton("Histogramme");
        btnBarresEmpilees = new JRadioButton("Barres Empilées");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(btnCamembert);
        buttonGroup.add(btnHistogramme);
        buttonGroup.add(btnBarresEmpilees);
        btnValider = new JButton("Valider");

        // Label en haut
        JLabel labelParametre = new JLabel("Type de document");
        JLabel labelType = new JLabel("Catégorie");
        JLabel labelLimite = new JLabel("Nombre max.");

        // Boutons déroulants en haut
        JComboBox<TypeDeDocGrouping> comboBox = new JComboBox<>(TypeDeDocGrouping.values());
        comboBox.setSelectedIndex(0);
        JComboBox<ParametreType> comboBox2 = new JComboBox<>(ParametreType.values());
        comboBox2.setSelectedIndex(0);
        String[] limit = {"1", "5", "10", "20"};
        JComboBox<String> comboBox3 = new JComboBox<>(limit);
        comboBox3.setSelectedIndex(0);

        // Label à gauche
        JLabel labelTypeGraph = new JLabel("Type de graphique");

        // Panel à gauche
        panelLeft = new JPanel(new GridLayout(5, 1));
        panelLeft.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panelLeft.add(labelTypeGraph);
        panelLeft.add(btnCamembert);
        panelLeft.add(btnHistogramme);
        panelLeft.add(btnBarresEmpilees);
        panelLeft.add(btnValider);

        // Panel à droite
        panelRight = new JPanel();
        panelRight.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        Histogramme jPanel = new Histogramme();
        panelRight.add(jPanel);

        // Définir la taille préférée du panel gauche
        panelLeft.setPreferredSize(new Dimension(200, 300));

        // Définir la taille préférée du panel droit
        panelRight.setPreferredSize(new Dimension(500, 300));

        // Ajouter les panels au layout avec la méthode size()
        JPanel contentPanel = new JPanel();
        DesignGridLayout layout = new DesignGridLayout(contentPanel);
        layout.row().center().add(labelTop); // Nouvelle ligne pour le label en haut
        layout.row().grid().add(labelType).add(labelParametre).add(labelLimite);
        layout.row().grid().add(comboBox).add(comboBox2).add(comboBox3); // Boutons déroulants en haut sur la même ligne
        layout.row().grid(3).add(new JScrollPane(panelLeft)).grid(7).add(new JScrollPane(panelRight)); // Panels à gauche et à droite sur la même ligne avec taille spécifiée



        add(contentPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }


}
