package vue;
import javax.swing.*;
import java.awt.*;

public class MaFenetre extends JFrame {
    public MaFenetre() {
        super("Les bibliothèques de PARIS"); // Titre de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer la fenêtre en cliquant sur la croix
        //setSize(500, 500); // Taille de la fenêtre
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        JPanel panel = new JPanel();
        setLayout(new FlowLayout(FlowLayout.CENTER));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Choisir entre les différentes possibilités");
        JLabel labelDemande = new JLabel("Les demandes");
        JLabel labelOffres = new JLabel("Les offres");


        /*JButton bouton1 = new JButton("Offre ");
        JButton bouton2 = new JButton("Demande");
        JButton bouton3 = new JButton("Comparaison");*/

        //boutons des demandes
        JRadioButton boutonDemande1 = new JRadioButton("Genres les plus empruntés");
        JRadioButton boutonDemande2 = new JRadioButton("Auteurs les plus empruntés");
        JRadioButton boutonDemande3 = new JRadioButton("Nombre d'emprunts des types de documents");

        //boutons des offres
        JRadioButton boutonOffre1 = new JRadioButton("Types de documents possédés");
        JRadioButton boutonOffre2 = new JRadioButton("Langues les plus proposées");

        //bouton de validation
        JButton valider = new JButton("Valider");

        //groupement des boutons de demandes
        ButtonGroup bg = new ButtonGroup();
        bg.add(boutonDemande1);
        bg.add(boutonDemande2);
        bg.add(boutonDemande3);

        //groupement des boutons d'offres
        bg.add(boutonOffre1);
        bg.add(boutonOffre2);

        //affichage
        panel.add(label);
        panel.add(labelDemande);
        panel.add(boutonDemande1);
        panel.add(boutonDemande2);
        panel.add(boutonDemande3);

        panel.add(labelOffres);
        panel.add(boutonOffre1);
        panel.add(boutonOffre2);


        getContentPane().add(panel);



        setVisible(true); // Rendre la fenêtre visible


    }

    public static void main(String[] args) {
        new MaFenetre();
    }
}
