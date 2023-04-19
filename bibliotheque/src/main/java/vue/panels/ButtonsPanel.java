package vue.panels;


import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {
    public ButtonsPanel() {

        // panel utilisé pour la frame menu et la frame mainWindow
        //tous les params possibles
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));


        JLabel label = new JLabel("Choisir entre les différentes possibilités");
       label.setHorizontalAlignment(JLabel.CENTER);
        this.add(label);


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
        JButton valider = new JButton("Valider");
        valider.setSize(100,50);

        this.add(labelDemande);
        this.add(boutonDemande1);
        this.add(boutonDemande2);
        this.add(boutonDemande3);

        this.add(labelOffres);
        this.add(boutonOffre1);
        this.add(boutonOffre2);

        this.add(labelOD);
        this.add(boutonOD1);
        this.add(boutonOD2);
        this.add(valider);

       // this.add(this, BorderLayout.EAST);
    }
    public static void main(String[] args) {
        new ButtonsPanel();
    }
}