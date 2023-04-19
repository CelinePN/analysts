package vue.panels;


import modele.parametre.ParametreType;
import modele.utils.SortBy;
import modele.utils.TypeDeDocGrouping;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {
    public ButtonsPanel() {

        // panel utilisé pour la frame menu et la frame mainWindow
        //tous les params possibles
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        JLabel label = new JLabel("Choisir entre les différentes possibilités");
       label.setHorizontalAlignment(JLabel.CENTER);
        this.add(label);


        JLabel labelParametre = new JLabel("Paramètres");
        JLabel labelType = new JLabel("Par type");
        JLabel labelLimite = new JLabel("Limite");
        JLabel labelShortBy = new JLabel("ShortBy");
        JLabel labelTypeGraph = new JLabel("Type de graphique ");

        //Menu déroulant pour Parametre
       // String[] param = {"Auteur", "Langue", "Type de document", "Genre"};
        JComboBox<ParametreType> comboBox = new JComboBox<>(ParametreType.values());
        //comboBox.setPreferredSize(new Dimension(200, 300));
        comboBox.setSelectedIndex(0); // Option sélectionnée par défaut

      // String[] type = {"Films", "BD", "Musiques", "Jeux","Livres","Revues","Autres"};
       JComboBox<TypeDeDocGrouping> comboBox2 = new JComboBox<>(TypeDeDocGrouping.values());
       comboBox2.setSelectedIndex(0); // Option sélectionnée par défaut

        String[] limit = {"1", "5", "10", "20"};
        JComboBox<String> comboBox3 = new JComboBox<>(limit);
        comboBox3.setSelectedIndex(0); // Option sélectionnée par défaut

        //String[] shortby = {"Total des Exemplaires", "Total des Prêts"};
        JComboBox<SortBy> comboBox4 = new JComboBox<>(SortBy.values());
        comboBox4.setSelectedIndex(0); // Option sélectionnée par défaut

       JRadioButton BarreEmpilees = new JRadioButton("BarresEmpilees");
       JRadioButton Camembert = new JRadioButton("Camembert");
       JRadioButton Histogramme = new JRadioButton("Histogramme");
        // Création du panel contenant la liste déroulante
       /* JPanel selectionPanelParam = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
      */

        JButton valider = new JButton("Valider");
        valider.setSize(100,50);


        this.add(labelParametre);
        this.add(comboBox);

        this.add(labelType);
        this.add(comboBox2);

        this.add(labelLimite);
        this.add(comboBox3);

        this.add(labelShortBy);
        this.add(comboBox4);

        this.add(labelTypeGraph);
        this.add(BarreEmpilees);
       this.add(Camembert);
       this.add(Histogramme);


     this.add(valider);

       // this.add(this, BorderLayout.EAST);
    }
    public static void main(String[] args) {
        new ButtonsPanel();
    }
}