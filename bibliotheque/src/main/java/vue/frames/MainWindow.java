package vue.frames;

import vue.panels.ButtonsPanel;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        super("Les bibliothèques de PARIS"); // Titre de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer la fenêtre en cliquant sur la croix
        setSize(550, 350); // Taille de la fenêtre
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        ButtonsPanel buttonPanel = new ButtonsPanel();


        JPanel mainPanel = new JPanel();
        JLabel labelImage = new JLabel(new ImageIcon("C:/Users/mathi/Documents/Cours/E4/bibliothequeParis.jpg"));
        mainPanel.add(labelImage);

        //  JPanel panelButtons; //le même que dans menu, récup les boutons d'alice dnas une classe unique extends Jpanel)
      //  JPanel panelGraph; // récup les graph d'alice, chaque graph extends JPanel
        // if ou switch
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.WEST);
        getContentPane().add(mainPanel, BorderLayout.EAST);


        setVisible(true); // Rendre la fenêtre visible


    }
    public static void main(String[] args) {
        new MainWindow();
    }
}
