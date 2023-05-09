package vue.frames;

import controleur.mainWindow.ControleurMainWindow;
import controleur.mainWindow.ObserverMainWindow;

import modele.parametre.Parametre;
import modele.parametre.ParametreType;

import modele.utils.Mode;
import modele.utils.TypeDeDocGrouping;
import modele.utils.TypeGraph;
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
import java.util.Objects;

import static modele.utils.Mode.BOTH;

public class MainWindow extends JFrame implements ObserverMainWindow {
    private JPanel panelLeft, panelRight;
    private JRadioButton btnCamembert, btnHistogramme, btnBarresEmpilees;
    private ButtonGroup buttonGroup;
    private JButton btnValider;
    private JButton btnRetour;
    private JLabel labelTop;
    private final ControleurMainWindow controleurMainWindow;

    //essayer de séparer le constructeurs en fonctions expliquées
    public MainWindow(Mode mode) {
        super("Les bibliothèques de PARIS"); // Titre de la fenêtre
        //ICON
        ClassLoader classLoader2 = getClass().getClassLoader();
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(classLoader2.getResource("livreB.jpg")));
        setIconImage(icon.getImage());

        this.controleurMainWindow = new ControleurMainWindow(mode);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.controleurMainWindow.registerObserver(this);

        // Label en haut
        labelTop = new JLabel("Les documents des bibliothèque de Paris");
        labelTop.setHorizontalAlignment(JLabel.CENTER);

        // Boutons à gauche

        buttonGroup = new ButtonGroup();
        if(controleurMainWindow.getCurrentMode()==BOTH){
            btnBarresEmpilees = new JRadioButton("Barres Empilées");
            buttonGroup.add(btnBarresEmpilees);

        }
        else{
            btnCamembert = new JRadioButton("Camembert");
            btnHistogramme = new JRadioButton("Histogramme");
            buttonGroup.add(btnCamembert);
            buttonGroup.add(btnHistogramme);
        }


        btnValider = new JButton("Valider");
        btnRetour = new JButton("Retour Menu");


        // Label en haut
        JLabel labelParametre = new JLabel("Type de document");
        JLabel labelType = new JLabel("Catégorie");
        JLabel labelLimite = new JLabel("Nombre max.");

        // Boutons déroulants en haut
        JComboBox<TypeDeDocGrouping> comboBoxPartType = new JComboBox<>(TypeDeDocGrouping.values());
        comboBoxPartType.setSelectedIndex(0);
        JComboBox<ParametreType> comboBoxParametre = new JComboBox<>(ParametreType.values());
        comboBoxParametre.setSelectedIndex(0);
        String[] limit = {"1", "5", "10", "20"};
        JComboBox<String> comboBoxLimite = new JComboBox<>(limit);
        comboBoxLimite.setSelectedIndex(0);
        controleurMainWindow.setTypeDeDocGrouping((TypeDeDocGrouping) comboBoxPartType.getSelectedItem());
        controleurMainWindow.setParametreType((ParametreType) comboBoxParametre.getSelectedItem());
        controleurMainWindow.setLimite(1);
        controleurMainWindow.setTypeGraph(TypeGraph.CAMEMBERTS);
        btnCamembert.setSelected(true);



        if(controleurMainWindow.getCurrentMode()==BOTH) {

            btnBarresEmpilees.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleurMainWindow.setTypeGraph(TypeGraph.BARRES_EMPILEES);

                }
            });
        }
        else{
            btnCamembert.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleurMainWindow.setTypeGraph(TypeGraph.CAMEMBERTS);

                }
            });

            btnHistogramme.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleurMainWindow.setTypeGraph(TypeGraph.HISTOGRAMMES);
                }
            });
        }

        comboBoxLimite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedOption = Integer.parseInt( (String)comboBoxLimite.getSelectedItem());

                controleurMainWindow.setLimite(selectedOption);
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
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retourMenu();
            }
        });

        // Label à gauche
        JLabel labelTypeGraph = new JLabel("Type de graphique");

        // Panel à gauche
        panelLeft = new JPanel(new GridLayout(5, 1));
        panelLeft.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panelLeft.add(labelTypeGraph);
        if(controleurMainWindow.getCurrentMode()==BOTH) {
            panelLeft.add(btnBarresEmpilees);
        }
        else{
            panelLeft.add(btnCamembert);
            panelLeft.add(btnHistogramme);
        }

        panelLeft.add(btnValider);
        panelLeft.add(btnRetour);

        // Panel à droite
        panelRight = new JPanel();
        panelRight.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        Histogramme jPanel = new Histogramme();
        panelRight.add(jPanel);

        // Définir la taille préférée du panel gauche
        //panelLeft.setPreferredSize(new Dimension(200, 300));

        // Définir la taille préférée du panel droit
        //panelRight.setPreferredSize(new Dimension(500, 300));

        // Ajouter les panels au layout avec la méthode size()
        JPanel contentPanel = new JPanel();
        DesignGridLayout layout = new DesignGridLayout(contentPanel);
        layout.row().center().add(labelTop); // Nouvelle ligne pour le label en haut
        layout.row().grid().add(labelParametre).add(labelType).add(labelLimite);
        layout.row().grid().add(comboBoxPartType).add(comboBoxParametre).add(comboBoxLimite); // Boutons déroulants en haut sur la même ligne
        layout.row().grid(3).add(new JScrollPane(panelLeft)).grid(7).add(new JScrollPane(panelRight)); // Panels à gauche et à droite sur la même ligne avec taille spécifiée


        add(contentPanel, BorderLayout.CENTER);
        controleurMainWindow.valider();
        pack();
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        setPreferredSize(new Dimension(1200, 250));
        setVisible(true);
    }

    private void retourMenu(){
        Menu menu = new Menu();
        menu.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }


    public ControleurMainWindow getControleurMainWindow() {
        return controleurMainWindow;
    }


    @Override
    public void choisir() {
        JOptionPane.showMessageDialog(null, "Choisir les différents paramètres");
    }

    @Override
    public void updateGraphPanel(List<Parametre> liste, TypeGraph typeGraph, Mode currentmode) {
        panelRight.removeAll();

        switch (typeGraph) {
            case HISTOGRAMMES:
                Histogramme jPanel = new Histogramme(liste, currentmode);
                panelRight.add(jPanel);
                break;

            case CAMEMBERTS:
                Camembert jPanel2 = new Camembert(liste, currentmode);
                panelRight.add(jPanel2);
                break;
        }
        panelRight.revalidate();

    }

    @Override
    public void updateGraphBarre(List<Parametre> listeexemplaire, List<Parametre> listeemprunt) {
        panelRight.removeAll();
        BarresEmpilees jPanel3 = new BarresEmpilees(listeexemplaire,listeemprunt);
        panelRight.add(jPanel3);
        panelRight.revalidate();

    }

}