/**
 * Menu extends JFrame
 * Dans le menu, un layout
 * Sur le layout 2 JPanel
 * 1 JPanel: add tous les boutons
 * 1 JPanel: 1 photo
 * et 1 bouton valider
 */
/*package vue.frames;
import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    public Menu() {
        super("Les bibliothèques de PARIS"); // Titre de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer la fenêtre en cliquant sur la croix
        setSize(550, 350); // Taille de la fenêtre
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());



        JLabel label = new JLabel("Choisir entre les différentes possibilités");
        label.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(label, BorderLayout.NORTH);



        JLabel labelDemande = new JLabel("Les demandes");
        JLabel labelOffres = new JLabel("Les offres");
        JLabel labelOD = new JLabel("Les liens entre offres et demandes");
        //boutons des demandes
        JRadioButton boutonDemande1 = new JRadioButton("Genres les plus empruntés");
        JRadioButton boutonDemande2 = new JRadioButton("Auteurs les plus empruntés");
        JRadioButton boutonDemande3 = new JRadioButton("Nombre d'emprunts des types de documents");

        //boutons des offres
        JRadioButton boutonOffre1 = new JRadioButton("Types de documents possédés");
        JRadioButton boutonOffre2 = new JRadioButton("Langues les plus proposées");

        //boutons des offres et demandes
        JRadioButton boutonOD1 = new JRadioButton("Offres et demandes par genre et type de documents");
        JRadioButton boutonOD2 = new JRadioButton("Types de documents les plus rentables");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.add(labelDemande);
        buttonPanel.add(boutonDemande1);
        buttonPanel.add(boutonDemande2);
        buttonPanel.add(boutonDemande3);

        buttonPanel.add(labelOffres);
        buttonPanel.add(boutonOffre1);
        buttonPanel.add(boutonOffre2);

        buttonPanel.add(labelOD);
        buttonPanel.add(boutonOD1);
        buttonPanel.add(boutonOD2);

        mainPanel.add(buttonPanel, BorderLayout.EAST);



        JLabel labelImage = new JLabel(new ImageIcon("C:/Users/mathi/Documents/Cours/E4/bibliothequeParis.jpg"));
        mainPanel.add(labelImage, BorderLayout.WEST);



        //bouton de validation
        JButton valider = new JButton("Valider");
        valider.setSize(100,50);


        //affichage
        mainPanel.add(valider, BorderLayout.SOUTH);

        //panel.add(label);


       // panel.add(valider);

        getContentPane().add(mainPanel);



        setVisible(true); // Rendre la fenêtre visible


    }

    public static void main(String[] args) {
        new Menu();
    }
}
*/

