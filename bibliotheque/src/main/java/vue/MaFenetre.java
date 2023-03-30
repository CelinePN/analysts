package vue;
import javax.swing.*;
import java.awt.*;

public class MaFenetre extends JFrame {
    public MaFenetre() {
        super("Les bibliothèques de PARIS"); // Titre de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer la fenêtre en cliquant sur la croix
        setSize(500, 500); // Taille de la fenêtre
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        JPanel panel = new JPanel();
        setLayout(new FlowLayout(FlowLayout.CENTER));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Choisir entre les différentes possibilités");
        panel.add(label);

        JButton bouton1 = new JButton("Offre ");
        JButton bouton2 = new JButton("Demande");
        JButton bouton3 = new JButton("Comparaison");
        panel.add(bouton1);
        panel.add(bouton2);
        panel.add(bouton3);
        getContentPane().add(panel);



        setVisible(true); // Rendre la fenêtre visible


    }

    public static void main(String[] args) {
        new MaFenetre();
    }
}
