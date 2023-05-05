package vue.frames;

import controleur.mainWindow.ControleurMainWindow;
import controleur.mainWindow.ObserverMainWindow;

import modele.parametre.Parametre;
import modele.parametre.ParametreType;

import modele.utils.Mode;
import modele.utils.TypeDeDocGrouping;
import vue.panels.graphs.BarresEmpilees;
import vue.panels.graphs.Camembert;
import vue.panels.graphs.Histogramme;
import javax.swing.*;
import net.java.dev.designgridlayout.DesignGridLayout;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame implements ObserverMainWindow {
    private JPanel panelLeft, panelRight;
    private JComboBox<String> comboBoxParametre = new JComboBox<String>();
    private JComboBox<String> comboBoxPartType = new JComboBox<String>();
    private JComboBox<String> comboBoxLimite = new JComboBox<String>();


    private JRadioButton btnCamembert, btnHistogramme, btnBarresEmpilees;
    private ButtonGroup buttonGroup;
    private JButton btnValider;
    private JLabel labelTop;
    private final ControleurMainWindow controleurMainWindow;
    private Mode mode;

    private List<Parametre> type;
    private List<ObserverMainWindow> observers = new ArrayList<>();

    public MainWindow(Mode mode) {
        super("Les bibliothèques de PARIS"); // Titre de la fenêtre
        this.controleurMainWindow = new ControleurMainWindow(mode);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.controleurMainWindow.registerObserver(this);

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


        comboBoxLimite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedOption = (int) comboBoxLimite.getSelectedItem();

                controleurMainWindow.setLimite(selectedOption);
            }
        });

        btnCamembert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Camembert(type, mode);
                // controleurMainWindow.setCurrentMode(Camembert);
            }
        });

        btnBarresEmpilees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BarresEmpilees(type, type);
                // controleurMainWindow.setCurrentMode(Mode.EMPRUNTS);
            }
        });

        btnHistogramme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Histogramme(type, mode);
                //    controleurMainWindow.setCurrentMode(Mode.EMPRUNTS);
            }
        });

        comboBoxParametre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ParametreType selectedOption = (ParametreType) comboBoxParametre.getSelectedItem();
                controleurMainWindow.setParametreType(selectedOption);
            }
        });


        comboBoxPartType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TypeDeDocGrouping selectedOption = (TypeDeDocGrouping) comboBoxPartType.getSelectedItem();

                controleurMainWindow.setTypeDeDocGrouping(selectedOption);
            }
        });


        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                controleurMainWindow.valider();
            }
        });


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
        layout.row().grid().add(labelParametre).add(labelType).add(labelLimite);
        layout.row().grid().add(comboBox).add(comboBox2).add(comboBox3); // Boutons déroulants en haut sur la même ligne
        layout.row().grid(3).add(new JScrollPane(panelLeft)).grid(7).add(new JScrollPane(panelRight)); // Panels à gauche et à droite sur la même ligne avec taille spécifiée


        add(contentPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }


    public ControleurMainWindow getControleurMainWindow() {
        return controleurMainWindow;
    }

    public static void main(String[] args) {
        // new MainWindow(Mode mode);
    }

    @Override
    public void choisir() {
        JOptionPane.showMessageDialog(null, "Choisir les différents paramètres");

    }

    @Override
    public void fenetrefermer(Mode mode) {
        MainWindow mainWindow = new MainWindow(mode);
        mainWindow.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }

    @Override
    public void mode(Mode currentMode) {
        MainWindow mainWindow = new MainWindow(currentMode);


    }

    @Override
    public void typeParametre(ParametreType parametreType) {

    }

    @Override
    public void limite(int limite) {
        switch (limite) {
            case 1:

                break;

            default:

        }
    }
//si c 'est un histogramme recupérer la limite
    @Override
    public void typeDeDocGrouping(TypeDeDocGrouping typeDeDocGrouping) {

    }

}