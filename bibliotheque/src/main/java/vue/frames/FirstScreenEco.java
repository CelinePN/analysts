package vue.frames;

import controleur.firstscreen.ControleurFirstScreen;
import controleur.firstscreen.ObserverFirstScreen;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *  <h1> FirstScreenEco </h1>
 *
 * <p>
 *  Ecran de lancement et de chargement des donneés de l'application
 *  Permet de remplir les données chargées dans un cache pour y avoir accès en tout temps une fois le réseau coupé
 *
 *  Contrairement à la classe FirstScreenSpeed, dans cette classe, la requête vers la BDD est plus économique
 *  et écologique puisqu'elle pré-traite les données en amont dans la reqûete. Ce pré-traitement est chronophage
 *  et l'appel dure environ 2minutes.
 *
 *
 * </p>
 *
 * @Author: Marine
 * @Version: 1.0
 * @since: 10/04/2023
 */

public class FirstScreenEco extends JFrame implements ObserverFirstScreen {
    private JPanel panelError;
    private JProgressBar progressBar;
    private final ControleurFirstScreen controleurFirstScreen;


    /**
     * Constructeur : initialisation de la fenêtre et lancement du chargement des données
     */
    public FirstScreenEco() {
        super("Ecran de chargement"); // Titre de la fenêtre
        this.controleurFirstScreen=new ControleurFirstScreen();
        this.controleurFirstScreen.registerObserver(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer la fenêtre en cliquant sur la croix
        this.setSize(550, 350); // Taille de la fenêtre
        this.setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.initView();

        setVisible(true); // Rendre la fenêtre visible

        this.controleurFirstScreen.loadData();

    }

    /**
     * initialisaiton des vues de la fenêtre
     */
    public void initView(){
        //init panelMain
        JPanel panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.PAGE_AXIS));
        panelMain.setVisible(true);

        //init panelMain components
        JLabel labelTitle = new JLabel("Veuillez patienter un instant, nous chargeons les données pour vous");
        this.progressBar = new JProgressBar();
        panelMain.add(labelTitle);
        panelMain.add(progressBar);

        //init panelError
        panelError = new JPanel();
        panelError.setVisible(false);
        panelError.setLayout(new BoxLayout(panelError, BoxLayout.PAGE_AXIS));

        //init panelError components
        JLabel labelError = new JLabel("Une erreur est survenue, veuillez réessayer");
        JButton buttonRetry = new JButton("Réessayer");
        buttonRetry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleurFirstScreen.retry();
            }
        });
        panelError.add(labelError);
        panelError.add(buttonRetry);

        getContentPane().add(panelMain);
        getContentPane().add(panelError);
    }

    /**
     * fonction permettant de relancer le chargement des données du début si une erreur est survenue
     */



    public JPanel getPanelError() {
        return panelError;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }


    public void updateProgressBar(int val){
        this.progressBar.setValue(val);
    }

    @Override
    public void loadingFailed() {
        progressBar.setVisible(false);
        panelError.setVisible(true);
    }

    @Override
    public void loadingSuccess() {
        progressBar.setVisible(false);

        MainWindow mainWindow = new MainWindow();
        mainWindow.pack();
        mainWindow.setVisible(true);
        setVisible(false);
        dispose();
    }

    @Override
    public void retry() {
        panelError.setVisible(false);
        progressBar.setVisible(true);
    }

    public ControleurFirstScreen getControleurFirstScreen() {
        return controleurFirstScreen;
    }
}
