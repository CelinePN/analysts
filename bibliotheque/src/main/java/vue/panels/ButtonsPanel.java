package vue.panels;


import controleur.firstscreen.ControleurFirstScreen;
import controleur.mainWindow.ControleurMainWindow;
import modele.parametre.ParametreType;
import modele.utils.SortBy;
import modele.utils.TypeDeDocGrouping;

import javax.swing.*;

import static modele.utils.SortBy.BOTH;

public class ButtonsPanel extends JPanel {

    private final ControleurMainWindow controleur;

    public ButtonsPanel(ControleurMainWindow controleurMainWindow) {
        this.controleur= controleurMainWindow;

        // panel utilisé pour la frame menu et la frame mainWindow
        //tous les params possibles
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Choisir entre les différentes possibilités");
        label.setHorizontalAlignment(JLabel.CENTER);
        this.add(label);


        JLabel labelParametre = new JLabel("Paramètre");
        JLabel labelType = new JLabel("Par type");
        JLabel labelLimite = new JLabel("Limite");
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

        JPanel bottomButtons = new JPanel();
        JButton validerButton = new JButton("Valider");
        validerButton.setSize(100,50);
        JButton menuButton = new JButton("Menu");
        menuButton.setSize(100,50);

        this.add(labelParametre);
        this.add(comboBox);

        this.add(labelType);
        this.add(comboBox2);

        this.add(labelLimite);
        this.add(comboBox3);

        this.add(labelTypeGraph);

        if(this.controleur.getCurrentMode()==BOTH){
            JRadioButton barreEmpilees = new JRadioButton("BarresEmpilees");
            this.add(barreEmpilees);
        }
        else{
            JRadioButton camembert = new JRadioButton("Camembert");
            JRadioButton histogramme = new JRadioButton("Histogramme");
            this.add(camembert);
            this.add(histogramme);
        }

        this.add(validerButton);
        this.add(menuButton);

       // this.add(this, BorderLayout.EAST);
    }

    public void BoutonGraphique() {

    }

    public void BoutonCaracteristique() {

    }
}