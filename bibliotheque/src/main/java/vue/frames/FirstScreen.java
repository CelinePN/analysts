package vue.frames;


/**
 * Ecran de lancement de l'application
 * Permet de charger les données dans un cache pour y avoir accès en tout temps une fois le réseau coupé
 */

/***
public class FirstScreen extends JFrame {
    private JLabel label;
    private JProgressBar progressBar;

    public FirstScreen() {
        super("Ecran de chargement"); // Titre de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer la fenêtre en cliquant sur la croix
        setSize(550, 350); // Taille de la fenêtre
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        label = new JLabel("Veuillez patienter un instant, nous chargeons les données pour vous");
        progressBar = new JProgressBar();

        panel.add(label);
        panel.add(progressBar);
        getContentPane().add(panel);
        setVisible(true); // Rendre la fenêtre visible
        loadData();

    }
    public void loadData() {
        Database db = new Database();
        Thread thread = new Thread() {
            public void run() {
                // Perform data loading here
                Cache.initCache();
                int i=0;
                int totalIterations = ParametreType.values().length * TypeDeDocGrouping.values().length;
                for (ParametreType parametreType : ParametreType.values()) {
                    int j=0;
                    for(TypeDeDocGrouping typeDeDocGrouping: TypeDeDocGrouping.values())
                    {
                        try {
                            //Thread.sleep(100);
                            //List<Parametre> listeLanguages= db.getParamByTypeDeDoc(parametreType, typeDeDocGrouping);
                            Cache.data_tab.get(i).set(j, listeLanguages);
                            j++;
                        }  catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        int progress = (i * TypeDeDocGrouping.values().length) + j;
                        int percentage = (int) Math.round((progress * 100.0) / totalIterations);
                        progressBar.setValue(percentage);

                    }
                    i++;
                }
                // Done loading data, hide progress bar
                progressBar.setVisible(false);

                //lance la fenêtre menu (ou main)
                /*List<Parametre> list = Cache.getList(ParametreType.AUTEUR, TypeDeDocGrouping.FILMS, SortBy.EXEMPLAIRES, 5);
                for( Parametre el : list)
                {
                    System.out.println(el.getTotalExemplaires());
                    System.out.println(el.getTotalPrets());
                    System.out.println(el.getNom());

                }*/
 /*           }
        };
        thread.start();
        //return cache;
    }

    public static void main(String[] args) {
        new FirstScreen();
    }


}*/
