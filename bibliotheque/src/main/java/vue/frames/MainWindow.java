package vue.frames;

import controleur.firstscreen.ControleurFirstScreen;
import controleur.mainWindow.ControleurMainWindow;
import modele.utils.SortBy;
import vue.panels.ButtonsPanel;
import vue.panels.graphs.Histogramme;

import javax.swing.*;
import java.awt.*;


public class MainWindow extends JFrame {
    private final ControleurMainWindow controleurMainWindow;

    public MainWindow(SortBy mode) {
        super("Les bibliothèques de PARIS"); // Titre de la fenêtre
        this.controleurMainWindow= new ControleurMainWindow(mode);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer la fenêtre en cliquant sur la croix
        setSize(550, 350); // Taille de la fenêtre
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran

        ButtonsPanel buttonPanel = new ButtonsPanel(controleurMainWindow); // création du panel boutons


        //création d'un panel histogramme par défaut
        Histogramme jPanel = new Histogramme();


        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.WEST);
        getContentPane().add(jPanel);


        setVisible(true); // Rendre la fenêtre visible


    }
}
