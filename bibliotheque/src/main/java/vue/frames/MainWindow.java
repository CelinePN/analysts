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

/**
 *  <h1> MainWindow Vue </h1>
 *
 * <p>
 *      Cette classe permet l'affichage de la fenetre principale (mainwindow) avec les graphiques.
 *      A l'aide des boutons, elle gere les interactions avec l'utilisateur.
 * </p>
 *
 * @author Alice
 * @author Mathilde
 * @version 1.0
 * @since 09/05/2023
 */

public class MainWindow extends JFrame implements ObserverMainWindow {
    private final JPanel panelRight;
    private JRadioButton btnCamembert, btnHistogramme, btnBarresEmpilees;
    private final ControleurMainWindow controleurMainWindow;

   //Constructeur de la MainWindow
    public MainWindow(Mode mode) {
        super("Les bibliothèques de PARIS"); // Titre de la fenêtre
        //Création et affichage de l'ICON
        ClassLoader classLoader2 = getClass().getClassLoader();
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(classLoader2.getResource("livreB.jpg")));
        setIconImage(icon.getImage());

        //Initialisation du controleur pour la fenètre principale
        this.controleurMainWindow = new ControleurMainWindow(mode);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.controleurMainWindow.registerObserver(this);

        // Label en haut
        JLabel labelTop = new JLabel("Les documents des bibliothèques de Paris");
        labelTop.setHorizontalAlignment(JLabel.CENTER);

        // Boutons à gauche
        ButtonGroup buttonGroup = new ButtonGroup();
        if(controleurMainWindow.getCurrentMode()==BOTH){
            btnBarresEmpilees = new JRadioButton("Barres Empilées");
            btnBarresEmpilees.setSelected(true);
            controleurMainWindow.setTypeGraph(TypeGraph.BARRES_EMPILEES);
            buttonGroup.add(btnBarresEmpilees);
        }
        else{
            btnCamembert = new JRadioButton("Camembert");
            btnCamembert.setSelected(true);
            controleurMainWindow.setTypeGraph(TypeGraph.CAMEMBERTS);
            btnHistogramme = new JRadioButton("Histogramme");
            buttonGroup.add(btnCamembert);
            buttonGroup.add(btnHistogramme);
        }


        JButton btnValider = new JButton("Valider");
        JButton btnRetour = new JButton("Retour Menu");


        // Label en haut
        JLabel labelParametre = new JLabel("Type de document");
        JLabel labelType = new JLabel("Catégorie");
        JLabel labelLimite = new JLabel("Nombre max.");

        // Boutons déroulants en haut
        JComboBox<TypeDeDocGrouping> comboBoxPartType = new JComboBox<>(TypeDeDocGrouping.values());
        JComboBox<ParametreType> comboBoxParametre = new JComboBox<>(ParametreType.values());
        String[] limit = {"1", "5", "10", "20"};
        JComboBox<String> comboBoxLimite = new JComboBox<>(limit);

        //initialisation des boutons selectionnes dans le controleur
        comboBoxPartType.setSelectedIndex(0);
        comboBoxParametre.setSelectedIndex(0);
        comboBoxLimite.setSelectedIndex(0);

        controleurMainWindow.setTypeDeDocGrouping((TypeDeDocGrouping) comboBoxPartType.getSelectedItem());
        controleurMainWindow.setParametreType((ParametreType) comboBoxParametre.getSelectedItem());
        controleurMainWindow.setLimite(1);

        //Gestion des événements des composants
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
                int selectedOption = Integer.parseInt( (String) Objects.requireNonNull(comboBoxLimite.getSelectedItem()));

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
        // Déclaration des composants de la fenêtre
        JPanel panelLeft = new JPanel(new GridLayout(5, 1));
        panelLeft.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
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
        panelRight = new JPanel(new BorderLayout());
        panelRight.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Ajouter les panels au layout avec la méthode size()
        JPanel contentPanel = new JPanel();
        DesignGridLayout layout = new DesignGridLayout(contentPanel);
        layout.row().center().add(labelTop); // Nouvelle ligne pour le label en haut
        layout.row().grid().add(labelParametre).add(labelType).add(labelLimite);
        layout.row().grid().add(comboBoxPartType).add(comboBoxParametre).add(comboBoxLimite); // Boutons déroulants en haut sur la même ligne
        layout.row().grid(2).add(new JScrollPane(panelLeft)).grid(8).add(new JScrollPane(panelRight)); // Panels à gauche et à droite sur la même ligne avec taille spécifiée

        add(contentPanel, BorderLayout.CENTER);
        controleurMainWindow.valider();
        pack();
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setPreferredSize(new Dimension(1200, 250));
        setVisible(true);
    }
    // Mathode pour revenir au menu principal
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

    //Méthode pour afficher le graphique choisi par l'utilisateur (Histogramme ou Camembert) si celui-ci a précédement appuyé sur Offre ou Demande
    @Override
    public void updateGraphPanel(List<Parametre> liste, TypeGraph typeGraph, Mode currentmode) {
        if(panelRight.getComponentCount()!=0){
            panelRight.removeAll();
        }

        switch (typeGraph) {
            case HISTOGRAMMES:
                Histogramme jPanel = new Histogramme(liste, currentmode);
                panelRight.add(jPanel, BorderLayout.CENTER);
                break;

            case CAMEMBERTS:
                Camembert jPanel2 = new Camembert(liste, currentmode);
                panelRight.add(jPanel2, BorderLayout.CENTER);
                break;
        }
        panelRight.revalidate();

    }

    //Méthode pour afficher le graphique barre empillé si l'utilisateur a appuyé sur le bouton Comparaison Offre/Demande
    @Override
    public void updateGraphBarre(List<Parametre> listeexemplaire, List<Parametre> listeemprunt) {
        if(panelRight.getComponentCount()!=0){
            panelRight.removeAll();
        }
        BarresEmpilees jPanel3 = new BarresEmpilees(listeexemplaire,listeemprunt);
        panelRight.add(jPanel3, BorderLayout.CENTER);
        panelRight.revalidate();

    }


}