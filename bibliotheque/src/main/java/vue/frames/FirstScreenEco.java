package vue.frames;

import dao.DatabaseEco;
import modele.DoubleKeyCacheEco;
import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.utils.SortBy;
import modele.utils.TypeDeDocGrouping;
import vue.panels.graphs.Histogramme;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Ecran de lancement de l'application
 * Permet de charger les données dans un cache pour y avoir accès en tout temps une fois le réseau coupé
 */
public class FirstScreenEco extends JFrame {
    private final JPanel panelError;
    private final JProgressBar progressBar;


    public FirstScreenEco() {
        super("Ecran de chargement"); // Titre de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer la fenêtre en cliquant sur la croix
        setSize(550, 350); // Taille de la fenêtre
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        //init panelMain
        JPanel panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.PAGE_AXIS));
        panelMain.setVisible(true);

        //init panelMain components
        JLabel labelTitle = new JLabel("Veuillez patienter un instant, nous chargeons les données pour vous");
        progressBar = new JProgressBar();
        panelMain.add(labelTitle);
        panelMain.add(progressBar);

        //init panelError
        panelError = new JPanel();
        panelError.setVisible(false);
        panelError.setLayout(new BoxLayout(panelError, BoxLayout.PAGE_AXIS));

        //init panelError components
        JLabel labelError = new JLabel("Une erreur est survenue, veuillez réessayer");
        JButton buttonRetry = new JButton("Réessayer");
        panelError.add(labelError);
        panelError.add(buttonRetry);

        getContentPane().add(panelMain);
        getContentPane().add(panelError);
        setVisible(true); // Rendre la fenêtre visible
        loadData();

    }

    private void retry(){
        panelError.setVisible(false);
        progressBar.setVisible(true);
        loadData();
    }
    private void loadData() {
        DoubleKeyCacheEco.cacheMapExemplaires.clear();
        DoubleKeyCacheEco.cacheMapEmprunts.clear();
        Thread thread = new Thread() {
            public void run() {
                // Perform data loading here
                int totalTasks = ParametreType.values().length * (TypeDeDocGrouping.values().length*2);
                int completedTasks=0;

                for (ParametreType parametreType : ParametreType.values()) {
                    for(TypeDeDocGrouping typeDeDocGrouping: TypeDeDocGrouping.values())
                    {
                        List<Parametre> listeExemplaires = new ArrayList<>();
                        List<Parametre> listeEmprunts = new ArrayList<>();
                        try {
                            listeExemplaires = DatabaseEco.getParamByTypeDeDoc(parametreType, typeDeDocGrouping, SortBy.EXEMPLAIRES);
                            completedTasks++;
                            progressBar.setValue((int) (((float) completedTasks / totalTasks) * 100));
                        }  catch (IOException e) {
                            //throw new RuntimeException(e);
                            progressBar.setVisible(false);
                            panelError.setVisible(true);
                            interrupt();
                        }
                        try {
                            listeEmprunts = DatabaseEco.getParamByTypeDeDoc(parametreType, typeDeDocGrouping, SortBy.EMPRUNTS);
                            completedTasks++;
                            progressBar.setValue((int) (((float) completedTasks / totalTasks) * 100));
                        }  catch (IOException e) {
                            //throw new RuntimeException(e);
                            progressBar.setVisible(false);
                            panelError.setVisible(true);
                            interrupt();

                        }
                        if(!listeExemplaires.isEmpty() && !listeEmprunts.isEmpty()){
                            DoubleKeyCacheEco.put(parametreType, typeDeDocGrouping, listeExemplaires, listeEmprunts);
                        }

                    }
                }
                progressBar.setVisible(false);

                /*liste = DoubleKeyCacheEco.get(ParametreType.AUTEUR, TypeDeDocGrouping.FILMS, SortBy.EMPRUNTS, 10);

                if(!liste.isEmpty()){
                    Histogramme myChart = new Histogramme(liste, SortBy.EMPRUNTS);
                    myChart.pack();
                    myChart.setVisible(true);
                    setVisible(false);
                    dispose();
                }
                else{
                    panelError.setVisible(true);
                }*/

                MainWindow mainWindow = new MainWindow();
                mainWindow.pack();
                mainWindow.setVisible(true);
                setVisible(false);
                dispose();
            }
        };
        thread.start();
    }

    public static void main(String[] args) {
        new FirstScreenEco();
    }


}
