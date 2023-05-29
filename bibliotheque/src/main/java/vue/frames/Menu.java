package vue.frames;

import javax.swing.*;
import controleur.menu.ControleurMenu;
import controleur.menu.ObserverMenu;
import modele.utils.Mode;
import net.java.dev.designgridlayout.DesignGridLayout;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;


/**
 *  <h1> Menu Vue </h1>
 *
 * <p>
 *      Cette classe permet l'affichage de la fenêtre du menu.
 *
 * </p>
 *

 * @author Alice (pour la vue)
 * @author Mathilde (pour les actions)
 * @version 3.0
 * @since 29/05/2023
 */

public class Menu extends JFrame implements ObserverMenu {
    private final JButton btnValider;
    private final JRadioButton btnOffre, btnDemande, btnComparaison;
    private final ControleurMenu controleurMenu;


    /**
     * Creation du constructeur du menu avec l'initialisation des vues
     * @author Alice
     */
    public Menu() {
        super("Menu");
        this.controleurMenu = new ControleurMenu();
        this.controleurMenu.registerObserver(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Personnalisation des couleurs du look and feel Nimbus
        UIManager.put("nimbusBase", new Color(240, 240, 240)); // Couleur de base
        UIManager.put("nimbusLightBackground", new Color(255, 255, 255)); // Fond clair
        UIManager.put("control", new Color(225, 225, 225)); // Couleur des contrôles

        // Utilisation du look and feel Nimbus
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Titre au centre en haut
        JLabel labelTitre = new JLabel("Bienvenue dans le menu des bibliothèques de Paris");
        Font titleFont = new Font("Roboto", Font.BOLD, 24); // Utilisation de la police Roboto
        labelTitre.setFont(titleFont);
        labelTitre.setForeground(new Color(53, 152, 220));
        labelTitre.setHorizontalAlignment(JLabel.CENTER);
        add(labelTitre, BorderLayout.NORTH);

        // Initialisation des boutons radio avec une couleur de fond personnalisée
        btnDemande = new JRadioButton("Demande");
        btnOffre = new JRadioButton("Offre");
        btnComparaison = new JRadioButton("Comparaison Offre/Demande");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(btnDemande);
        buttonGroup.add(btnOffre);
        buttonGroup.add(btnComparaison);
        btnDemande.setBackground(new Color(53, 152, 220));
        btnOffre.setBackground(new Color(53, 152, 220));
        btnComparaison.setBackground(new Color(53, 152, 220));


        // Initialisation du bouton "Valider" avec une couleur de fond et une police personnalisées
        btnValider = new JButton("Valider");
        btnValider.setBackground(new Color(53, 152, 220));
        btnValider.setForeground(Color.WHITE);
        Font btnFont = new Font("Roboto", Font.BOLD, 14); // Utilisation de la police Roboto
        btnValider.setFont(btnFont);

        // Ajout des boutons radio et du bouton "Valider" au panel1
        JLabel labelExplication = new JLabel("Que voulez-vous regarder?");
        Font labelFont = new Font("Roboto", Font.BOLD, 15); // Création d'une nouvelle police avec une taille de 24 points
        labelExplication.setFont(labelFont); // Appliquer la nouvelle police à la JLabel
        labelExplication.setPreferredSize(new Dimension(400, 50)); // Définir une taille personnalisée pour la JLabel (largeur de 400 pixels, hauteur de 50 pixels)

        JPanel panel1 = new JPanel(new GridLayout(5, 1)); // Augmentation du nombre de lignes pour inclure le label
        panel1.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panel1.add(labelExplication); // Ajout du label
        panel1.add(btnDemande);
        panel1.add(btnOffre);
        panel1.add(btnComparaison);
        panel1.add(btnValider);

        //Ajout de l'icone
        ClassLoader classLoader2 = getClass().getClassLoader();
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(classLoader2.getResource("livreB.jpg")));
        setIconImage(icon.getImage());

        // Ajout de l'image label au panel2
        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(classLoader.getResource("menu.JPG")));
        Image image = imageIcon.getImage();

        // Redimensionner l'image en utilisant getScaledInstance()
        int newWidth = 350; // Nouvelle largeur souhaitée
        int newHeight = 350; // Nouvelle hauteur souhaitée
        Image resizedImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Créer un nouvel ImageIcon à partir de l'image redimensionnée
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel jLabelImage = new JLabel(resizedIcon);


        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setVisible(true);
        panel2.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panel2.add(jLabelImage);

        gestionEvents();

        // Ajout des panels au frame avec DesignGridLayout
        JPanel contentPanel = new JPanel();
        DesignGridLayout layout = new DesignGridLayout(contentPanel);
        layout.row().center().add(labelTitre);
        layout.row().grid().add(new JScrollPane(panel1)).add(new JScrollPane(panel2));
        add(contentPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        setVisible(true);

    }

    /**
     * Gestion des clics sur les boutons dans le controleur
     * @author Mathilde
     */
    void gestionEvents(){
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                controleurMenu.selectioner();
            }
        });

        btnDemande.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                controleurMenu.setCurrentMode(Mode.EMPRUNTS);
            }
        });

        btnOffre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                controleurMenu.setCurrentMode(Mode.EXEMPLAIRES);
            }
        });

        btnComparaison.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                controleurMenu.setCurrentMode(Mode.BOTH);
            }
        });
    }

    public static void main(String[] args) {
        new Menu();
    }

    /**
     * Methode affichant un message (pop up) lorsque l'utilisateur n'a pas choisi sa categorie
     * @author Mathilde
     * */
    @Override
    public void choisir() {
        JOptionPane.showMessageDialog(null, "Choisir une catégorie");
    }


    /**
     * Methode permettant de fermer le fenetre du menu
     * @author Mathilde
     * @param mode : mode choisi (offre demande ou comparaison)
     */
    @Override
    public void fenetrefermer(Mode mode) {
        MainWindow mainWindow = new MainWindow(mode);
        mainWindow.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }


}