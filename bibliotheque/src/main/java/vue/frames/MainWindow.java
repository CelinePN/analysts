package vue.frames;

import vue.panels.ButtonsPanel;
import vue.panels.graphs.HistogrammeBis;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.net.URL;

public class MainWindow extends JFrame {
    public MainWindow() {
        super("Les bibliothèques de PARIS"); // Titre de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer la fenêtre en cliquant sur la croix
        setSize(550, 350); // Taille de la fenêtre
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        ButtonsPanel buttonPanel = new ButtonsPanel();
     //   HistogrammeBis histogrammeBis = new HistogrammeBis();

        ClassLoader classLoader2 = getClass().getClassLoader();

        ImageIcon icon = new ImageIcon(classLoader2.getResource("livreB.jpg"));
        setIconImage(icon.getImage());



        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon imageIcon = new ImageIcon(classLoader.getResource("bibliothequeParis.jpg"));
        JLabel jLabel = new JLabel(imageIcon);
        JPanel jPanel = new JPanel();
        jPanel.add(jLabel);
        //  JPanel panelButtons; //le même que dans menu, récup les boutons d'alice dnas une classe unique extends Jpanel)
      //  JPanel panelGraph; // récup les graph d'alice, chaque graph extends JPanel
        // if ou switch
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.WEST);
        getContentPane().add(jPanel);


        setVisible(true); // Rendre la fenêtre visible


    }
    public static void main(String[] args) {

        new MainWindow();
    }
}
