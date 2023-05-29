package vue.frames;

import controleur.firstscreen.ControleurFirstScreen;
import controleur.firstscreen.ObserverFirstScreen;
import net.java.dev.designgridlayout.DesignGridLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


/**
 *  <h1> FirstScreen </h1>
 *
 * <p>
 *
 *  Ecran de lancement et de chargement des donnees de l'application
 *  Permet de remplir les donnees chargees dans un cache pour y avoir acces en tout temps meme sans reseau
 *
 *  L'appel dure environ 1min30.
 *
 *
 * </p>
 *
 * @author Alice (pour la vue)
 * @author Marine (pour les actions)
 * @Version: 2.0
 * @since 10/04/2023


 */

public class FirstScreen extends JFrame implements ObserverFirstScreen {
    private JProgressBar progressBar;
    private final CardLayout cardLayout;
    private final ControleurFirstScreen controleurFirstScreen;


    /**
     * Constructeur : initialisation de la fenetre et lancement du chargement des donnees
     * Creation d'un controleur associe
     */
    public FirstScreen() {
        super("Ecran de chargement"); // Titre de la fenêtre

        this.controleurFirstScreen=new ControleurFirstScreen();
        this.controleurFirstScreen.registerObserver(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer la fenêtre en cliquant sur la croix
        this.setSize(500, 400);
        this.cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        this.initView();
        cardLayout.show(getContentPane(), "main");
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null); // Centrer la fenêtre sur l'ecran
        setVisible(true); // Rendre la fenêtre visible

        this.controleurFirstScreen.loadData();

    }

    /**
     * initialisation des vues de la fenetre
     */
    public void initView(){
        //init main panel
        JPanel panelMain = new JPanel(new BorderLayout(10, 10));
        panelMain.setBackground(Color.WHITE);
        DesignGridLayout layout = new DesignGridLayout(panelMain);
        JLabel loadingLabel = new JLabel("Veuillez patienter, nous chargeons les données pour vous...");
        loadingLabel.setFont(new Font("Roboto", Font.PLAIN, 16)); // changement de la police
        loadingLabel.setForeground(new Color(53, 152, 220)); // changement de la couleur du texte
        layout.row().center().add(loadingLabel);
        layout.row().center().add(new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("53836.gif"))))).withOwnRowWidth();
        this.progressBar = new JProgressBar();
        this.progressBar.setPreferredSize(new Dimension(200, 20));
        this.progressBar.setStringPainted(true); // afficher la chaîne de caractères sous la barre de progression
        layout.row().center().add(progressBar).withOwnRowWidth();

        //ICON
        ClassLoader classLoader2 = getClass().getClassLoader();
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(classLoader2.getResource("livreB.jpg")));
        setIconImage(icon.getImage());


        //init panelError
        JPanel panelError = new JPanel();
        panelError.setBackground(Color.WHITE); //ajout d'une couleur de fond
        panelError.setBorder(BorderFactory.createLineBorder(Color.GRAY)); //ajout d'une bordure
        DesignGridLayout layoutError = new DesignGridLayout(panelError);
        layoutError.row().center().add(new JLabel("Une erreur est survenue, veuillez réessayer")); //modification de la police et de la couleur du texte
        JButton buttonRetry = new JButton("Réessayer");

        buttonRetry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                controleurFirstScreen.retry();
            }
        });
        layoutError.row().center().add(buttonRetry);

        this.add(panelMain, "main");
        this.add(panelError, "error");
    }



    /**
     * fonction permettant de relancer le chargement des donnees du debut si une erreur est survenue
     */


    public JProgressBar getProgressBar() {
        return progressBar;
    }


    public void updateProgressBar(int val){
        this.progressBar.setValue(val);
    }

    @Override
    public void loadingFailed() {
        this.cardLayout.show(this.getContentPane(), "error");
    }

    /**
     * gestion du chargement complet des donnees
     */
    @Override
    public void loadingSuccess() {
        Menu menu = new Menu();
        menu.pack();
        menu.setVisible(true);
        setVisible(false);
        dispose();
    }

    @Override
    public void retry() {
        this.cardLayout.show(this.getContentPane(), "main");
    }

    public ControleurFirstScreen getControleurFirstScreen() {
        return controleurFirstScreen;
    }

    public static void main(String[] args) {
        new FirstScreen();
    }

}
