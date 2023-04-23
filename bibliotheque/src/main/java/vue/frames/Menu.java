
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

public class Menu extends JFrame {
    private JPanel panel1, panel2;
    private JButton btnDemande, btnOffre, btnComparaison;

    public Menu() {
        super("Mon Frame avec des panels");

        // Initialisation des panels
        panel1 = new JPanel();
        panel2 = new JPanel();

        // Initialisation des boutons
        btnDemande = new JButton("Demande");
        btnOffre = new JButton("Offre");
        btnComparaison = new JButton("Comparaison Offre/demande");

        // Ajout des boutons au premier panel
        panel1.setLayout(new GridLayout(3, 1));
        panel1.add(btnDemande);
        panel1.add(btnOffre);
        panel1.add(btnComparaison);

        // Création de l'image label
        ClassLoader classLoader2 = getClass().getClassLoader();

        ImageIcon icon = new ImageIcon(classLoader2.getResource("livreB.jpg"));
        setIconImage(icon.getImage());

        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon imageIcon = new ImageIcon(classLoader.getResource("bibliothequeParis.jpg"));
        JLabel jLabel = new JLabel(imageIcon);


        // Ajout de l'image label au deuxième panel
        panel2.setLayout(new GridLayout(1, 1));
        panel2.add(jLabel);

        // Ajout des panels au frame
        DesignGridLayout layout = new DesignGridLayout(getContentPane());
        layout.row().grid().add(panel1).add(panel2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Menu();
    }
}