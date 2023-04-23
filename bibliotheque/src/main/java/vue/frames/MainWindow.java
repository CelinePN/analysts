package vue.frames;

import vue.panels.ButtonsPanel;
import vue.panels.graphs.Histogramme;
import net.java.dev.designgridlayout.DesignGridLayout;
import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        super("Les bibliothèques de PARIS"); // Titre de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer la fenêtre en cliquant sur la croix
        setSize(550, 350); // Taille de la fenêtre
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran

        ButtonsPanel buttonPanel = new ButtonsPanel(); // création du panel boutons

        //mettre la photo dans menu

        ClassLoader classLoader2 = getClass().getClassLoader();
        ImageIcon icon = new ImageIcon(classLoader2.getResource("livreB.jpg"));
        setIconImage(icon.getImage());

        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon imageIcon = new ImageIcon(classLoader.getResource("bibliothequeParis.jpg"));
        JLabel jLabel = new JLabel(imageIcon);

        //création d'un panel histogramme par défaut

        Histogramme jPanel = new Histogramme();

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.WEST);
        getContentPane().add(jPanel);


        setVisible(true); // Rendre la fenêtre visible


    }
}
