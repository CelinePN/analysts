package vue.frames;
import javax.swing.*;

public class Menu extends JFrame {
    public Menu() {
        super("Les bibliothèques de PARIS"); // Titre de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer la fenêtre en cliquant sur la croix
        setSize(550, 350); // Taille de la fenêtre
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        JPanel panel = new JPanel();

        /*JComponent component1 = new JPanel();
        //component1.setSize(450, 25);
        component1.setAlignmentX(LEFT_ALIGNMENT);
        JComponent component2 = new JPanel();
        component2.setSize(450, 300);
        component2.setAlignmentX(CENTER_ALIGNMENT);
        JComponent component3 = new JPanel();
        component3.setSize(450, 25);
        component3.setAlignmentX(RIGHT_ALIGNMENT);

        panel.add(component1);
        panel.add(component2);
        panel.add(component3);*/
        //setLayout(new FlowLayout(FlowLayout.CENTER));

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JLabel label = new JLabel("Choisir entre les différentes possibilités");
        label.setAlignmentX(RIGHT_ALIGNMENT);
        JLabel labelDemande = new JLabel("Les demandes");
        JLabel labelOffres = new JLabel("Les offres");
        JLabel labelOD = new JLabel("Les liens entre offres et demandes");


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

        //boutons des offres et demandes
        JRadioButton boutonOD1 = new JRadioButton("Offres et demandes par genre et type de documents");
        JRadioButton boutonOD2 = new JRadioButton("Types de documents les plus rentables");

        //bouton de validation
        JButton valider = new JButton("Valider");
        valider.setAlignmentX(panel.RIGHT_ALIGNMENT);

        //groupement des boutons de demandes
        ButtonGroup bg = new ButtonGroup();
        bg.add(boutonDemande1);
        bg.add(boutonDemande2);
        bg.add(boutonDemande3);

        //groupement des boutons d'offres
        bg.add(boutonOffre1);
        bg.add(boutonOffre2);

        //groupement des boutons d'offres
        bg.add(boutonOD1);
        bg.add(boutonOD2);

        //affichage
        panel.add(label);
        panel.add(labelDemande);
        panel.add(boutonDemande1);
        panel.add(boutonDemande2);
        panel.add(boutonDemande3);

        panel.add(labelOffres);
        panel.add(boutonOffre1);
        panel.add(boutonOffre2);

        panel.add(labelOD);
        panel.add(boutonOD1);
        panel.add(boutonOD2);

        panel.add(valider);

        getContentPane().add(panel);



        setVisible(true); // Rendre la fenêtre visible


    }

    public static void main(String[] args) {
        new Menu();
    }
}
