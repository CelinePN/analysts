package vue.frames;

import dao.DatabaseSpeed;
import modele.DoubleKeySpeed;
import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.utils.SortBy;
import modele.utils.TypeDeDocGrouping;
import vue.panels.graphs.Histogramme;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

/**
 * Ecran de lancement de l'application
 * Permet de charger les données dans un cache pour y avoir accès en tout temps une fois le réseau coupé
 */
public class FirstScreenSpeed extends JFrame {
    private final JProgressBar progressBar;

    public FirstScreenSpeed() {
        super("Ecran de chargement"); // Titre de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer la fenêtre en cliquant sur la croix
        setSize(550, 350); // Taille de la fenêtre
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JLabel label = new JLabel("Veuillez patienter un instant, nous chargeons les données pour vous");
        progressBar = new JProgressBar();

        panel.add(label);
        panel.add(progressBar);
        getContentPane().add(panel);
        setVisible(true); // Rendre la fenêtre visible
        loadData();

    }
    public void loadData() {
        DatabaseSpeed db = new DatabaseSpeed();
        Thread thread = new Thread() {
            public void run() {
                // Perform data loading here
                int totalTasks = ParametreType.values().length * (TypeDeDocGrouping.values().length*2);
                int completedTasks=0;

                for (ParametreType parametreType : ParametreType.values()) {
                    for(TypeDeDocGrouping typeDeDocGrouping: TypeDeDocGrouping.values())
                    {
                        List<Parametre> liste;
                        try {
                            liste= db.getParamByTypeDeDoc(parametreType, typeDeDocGrouping);
                            DoubleKeySpeed.put(parametreType, typeDeDocGrouping, liste);
                            completedTasks++;

                        }  catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                progressBar.setValue((int) (((float) completedTasks / totalTasks) * 100));
                progressBar.setVisible(false);

                List<Parametre> liste= DoubleKeySpeed.get(ParametreType.AUTEUR, TypeDeDocGrouping.FILMS, SortBy.EMPRUNTS, 10);

                Histogramme myChart = new Histogramme(liste);

                myChart.pack();
                myChart.setVisible(true);
                setVisible(false);
                dispose();

            }
        };
        thread.start();
    }

    public static void main(String[] args) {
        new FirstScreenSpeed();
    }


}
