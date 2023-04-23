
package vue.frames;

/**
 * Menu extends JFrame
 * Dans le menu, un layout
 * Sur le layout 2 JPanel
 * 1 JPanel: add tous les boutons
 * 1 JPanel: 1 photo
 * et 1 bouton valider
 */

import javax.swing.*;
import net.java.dev.designgridlayout.DesignGridLayout;

import java.awt.*;
import java.util.Objects;

public class Menu extends JFrame {
    private JPanel panel1, panel2;
    private JRadioButton btnDemande, btnOffre, btnComparaison;
    private ButtonGroup buttonGroup;
    private JButton btnValider;


    public Menu() {
        super("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Titre au centre en haut
        JLabel labelTitre = new JLabel("Bienvenue dans le menu des bibliothèques de Paris");
        Font titleFont = new Font("Georgia", Font.BOLD, 20);
        labelTitre.setFont(titleFont);
        labelTitre.setForeground(Color.BLUE);
        labelTitre.setHorizontalAlignment(JLabel.CENTER);
        add(labelTitre, BorderLayout.NORTH);

        // Initialisation des boutons radio
        btnDemande = new JRadioButton("Offre");
        btnOffre = new JRadioButton("Demande");
        btnComparaison = new JRadioButton("Comparaison Offre/Demande");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(btnDemande);
        buttonGroup.add(btnOffre);
        buttonGroup.add(btnComparaison);

        // Initialisation du bouton "Valider"
        btnValider = new JButton("Valider");
        btnValider.setBackground(Color.BLUE);
        btnValider.setForeground(Color.WHITE);

        btnValider.addActionListener(e -> {
            if (btnDemande.isSelected()) {
                System.out.println("Offre est sélectionné");
            } else if (btnOffre.isSelected()) {
                System.out.println("Demande est sélectionné");
            } else if (btnComparaison.isSelected()) {
                System.out.println("Comparaison Offre/Demande est sélectionné");
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une option");
            }
        });

        // Ajout des boutons radio et du bouton "Valider" au panel1
        JLabel labelExplication = new JLabel("Que voulez-vous regarder?");
        panel1 = new JPanel(new GridLayout(5, 1)); // Augmentation du nombre de lignes pour inclure le label
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
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(classLoader.getResource("bibliotheque-2.jpg")));
        JLabel jLabel = new JLabel(imageIcon);
        panel2 = new JPanel(new BorderLayout());
        panel2.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panel2.add(jLabel, BorderLayout.CENTER);

        // Ajout des panels au frame avec DesignGridLayout
        JPanel contentPanel = new JPanel();
        DesignGridLayout layout = new DesignGridLayout(contentPanel);
        layout.row().center().add(labelTitre);
        layout.row().grid().add(new JScrollPane(panel1)).add(new JScrollPane(panel2));
        add(contentPanel, BorderLayout.CENTER);

        // Ajout d'une marge autour de la fenêtre pour centrer
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        int horizontalInsets = insets.left + insets.right;
        int verticalInsets = insets.top + insets.bottom;
        int windowWidth = imageIcon.getIconWidth() + horizontalInsets;
        int windowHeight = imageIcon.getIconHeight() + labelTitre.getPreferredSize().height + panel1.getPreferredSize().height + verticalInsets;
        setPreferredSize(new Dimension(windowWidth+700, windowHeight+150));
        setLocationRelativeTo(null);

        pack();
        setVisible(true);
        //Bloquer taille de la fenetre
        //setResizable(false);

    }


    public static void main(String[] args) {
        new Menu();
    }
}