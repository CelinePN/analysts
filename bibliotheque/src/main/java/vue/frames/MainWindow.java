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

 * @author Alice (pour la vue)
 * @author Mathilde (pour les actions)
 * @version 3.0
 * @since 29/05/2023
 */

public class MainWindow extends JFrame implements ObserverMainWindow {
    private final JPanel panelRight;
    private JRadioButton btnCamembert, btnHistogramme, btnBarresEmpilees;
    private final JComboBox<String> comboBoxLimite;
    private final JComboBox<ParametreType> comboBoxParametre;
    private final JComboBox<TypeDeDocGrouping> comboBoxType;
    private final JButton btnValider;
    private final JButton btnRetour;

    private final ControleurMainWindow controleurMainWindow;

    /**
     * Constructeur de la fenetre MainWindow avec un panel bouton et un panel graphique
     * @author Alice
     * @param mode : le mode qu'on a choisi (demande ou offre ou comparaison)
     */
    public MainWindow(Mode mode) {
        super("Les bibliothèques de PARIS"); // Titre de la fenêtre

        //Initialisation du controleur pour la fenetre principale
        this.controleurMainWindow = new ControleurMainWindow(mode);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.controleurMainWindow.registerObserver(this);

        //Création et affichage de l'ICON
        ClassLoader classLoader2 = getClass().getClassLoader();
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(classLoader2.getResource("livreB.jpg")));
        setIconImage(icon.getImage());

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

        btnValider = new JButton("Valider");
        btnValider.setBackground(new Color(53, 152, 220));
        btnValider.setForeground(Color.WHITE);
        Font btnFont = new Font("Roboto", Font.BOLD, 14); // Utilisation de la police Roboto
        btnValider.setFont(btnFont);
        btnRetour = new JButton("Retour Menu");
        btnRetour.setBackground(new Color(53, 152, 220));
        btnRetour.setForeground(Color.WHITE);
        btnRetour.setFont(btnFont);


        // Label en haut
        JLabel labelTop = new JLabel("Les documents des bibliothèques de Paris");
        int topLabelVerticalPadding = 10;
        labelTop.setBorder(BorderFactory.createEmptyBorder(topLabelVerticalPadding, 20, topLabelVerticalPadding, 20));
        labelTop.setHorizontalAlignment(JLabel.CENTER);
        Font titleFont = new Font("Roboto", Font.BOLD, 20); // Utilisation de la police Roboto
        labelTop.setFont(titleFont);

        Font labelFont = new Font("Roboto", Font.BOLD, 13); // Utilisation de la police Roboto
        JLabel labelParametre = new JLabel("Type de document");
        labelParametre.setFont(labelFont);

        JLabel labelType = new JLabel("Catégorie");
        labelType.setFont(labelFont);

        JLabel labelLimite = new JLabel("Nombre max.");
        labelLimite.setFont(labelFont);


        // Boutons déroulants en haut
        comboBoxType = new JComboBox<>(TypeDeDocGrouping.values());
        comboBoxParametre = new JComboBox<>(ParametreType.values());
        String[] limit = {"1", "5", "10", "20"};
        comboBoxLimite = new JComboBox<>(limit);

        // Label à gauche
        JLabel labelTypeGraph = new JLabel("Type de graphique");
        Font titleFont1 = new Font("Roboto", Font.BOLD, 16); // Utilisation de la police Roboto
        labelTypeGraph.setFont(titleFont1);

        // Ajout de l'image label au panel2
        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(classLoader.getResource("enfant.png")));
        Image image = imageIcon.getImage();
        // Redimensionner l'image en utilisant getScaledInstance()
        int newWidth = 90; // Nouvelle largeur souhaitée
        int newHeight = 90; // Nouvelle hauteur souhaitée
        Image resizedImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Créer un nouvel ImageIcon à partir de l'image redimensionnée
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel jLabelImage = new JLabel(resizedIcon);

        // Panel à gauche
        JPanel panelLeft = new JPanel(new GridLayout(5, 1));
        panelLeft.setBorder(BorderFactory.createEmptyBorder(25, 20, 25, 10));
        panelLeft.add(labelTypeGraph);

        if (controleurMainWindow.getCurrentMode() == BOTH) {
            panelLeft.add(btnBarresEmpilees);
        } else {
            panelLeft.add(btnCamembert);
            panelLeft.add(btnHistogramme);
        }

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(btnValider);
        buttonsPanel.add(btnRetour);
        panelLeft.add(buttonsPanel);

        // Ajoutez l'imageLabel en bas du panelLeft
        panelLeft.add(jLabelImage);

        // Panel à droite
        panelRight = new JPanel(new BorderLayout());
        panelRight.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Ajouter les panels au layout avec la méthode size()
        JPanel contentPanel = new JPanel();
        DesignGridLayout layout = new DesignGridLayout(contentPanel);
        layout.row().center().add(labelTop); // Nouvelle ligne pour le label en haut
        layout.row().grid().add(labelParametre).add(labelType).add(labelLimite);
        layout.row().grid().add(comboBoxType).add(comboBoxParametre).add(comboBoxLimite); // Boutons déroulants en haut sur la même ligne
        layout.row().grid(2).add(new JScrollPane(panelLeft)).grid(8).add(new JScrollPane(panelRight)); // Panels à gauche et à droite sur la même ligne avec taille spécifiée

        initButtons();
        gestionEvents();

        add(contentPanel, BorderLayout.CENTER);
        try{
            controleurMainWindow.valider();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

        pack();
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
    /**
     * Gestion retour au menu
     * @author Mathilde
     */
    private void retourMenu(){
        Menu menu = new Menu();
        menu.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }

    /**
     * Initialisation des boutons avec les valeurs des boutons par defaut
     * @author Mathilde
     */
    void initButtons(){
        comboBoxType.setSelectedIndex(0);
        comboBoxParametre.setSelectedIndex(0);
        comboBoxLimite.setSelectedIndex(0);
        controleurMainWindow.setTypeDeDocGrouping((TypeDeDocGrouping) comboBoxType.getSelectedItem());
        controleurMainWindow.setParametreType((ParametreType) comboBoxParametre.getSelectedItem());
        controleurMainWindow.setLimite(1);
    }

    /**
     * Gestion des evenements dans le controleur sur appui d'un bouton
     * @author Mathilde
     */
    void gestionEvents(){
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


        comboBoxType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TypeDeDocGrouping selectedOption = (TypeDeDocGrouping) comboBoxType.getSelectedItem();

                controleurMainWindow.setTypeDeDocGrouping(selectedOption);
            }
        });


        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    controleurMainWindow.valider();
                }
                catch(Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }
        });
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retourMenu();
            }
        });
    }

    /**
     * Methode permettant d'afficher des Messages Box si rien n'est selectionne
     * @author Mathilde
     */
    @Override
    public void choisir() {
        JOptionPane.showMessageDialog(null, "Choisir les différents paramètres");
    }

    /**
     * Methode pour mettre a jour le panel avec le graphique selectionne
     * @author Mathilde
     * @param liste : liste de parametre a afficher sur le graphique
     * @param typeGraph : type de graphique choisi (offre, demande ou comparaison)
     * @param currentmode : mode choisi (offre, demande ou comparaison)
     */
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

    /**
     * Methode pour mettre a jour le panel avec le graphique barres empilees
     * @author Mathilde
     */
    @Override
    public void updateGraphBarre(List<Parametre> listeexemplaire, List<Parametre> listeemprunt) {
        if(panelRight.getComponentCount()!=0){
            panelRight.removeAll();
        }
        BarresEmpilees jPanel3 = new BarresEmpilees(listeexemplaire,listeemprunt);
        panelRight.add(jPanel3, BorderLayout.CENTER);
        panelRight.revalidate();
    }

    public JRadioButton getBtnCamembert() {
        return btnCamembert;
    }

    public JRadioButton getBtnHistogramme() {
        return btnHistogramme;
    }

    public JRadioButton getBtnBarresEmpilees() {
        return btnBarresEmpilees;
    }

    public JComboBox<String> getComboBoxLimite() {
        return comboBoxLimite;
    }

    public JComboBox<ParametreType> getComboBoxParametre() {
        return comboBoxParametre;
    }

    public JComboBox<TypeDeDocGrouping> getComboBoxType() {
        return comboBoxType;
    }
}