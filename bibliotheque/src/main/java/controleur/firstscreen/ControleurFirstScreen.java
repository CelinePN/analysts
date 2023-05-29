package controleur.firstscreen;

import dao.Database;
import modele.Cache;
import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.utils.Mode;
import modele.utils.TypeDeDocGrouping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *  <h1> FirstScreen Controleur </h1>
 *
 * <p>
 *      Cette classe est le controleur qui gere l'ecran de chargement des donnees
 * </p>
 *
 * @author Marine
 * @version 3.0
 * @since 15/05/2023
 */

public class ControleurFirstScreen {

    private boolean isDataLoaded;

    private final List<ObserverFirstScreen> observers ;
    private List<Parametre> listeExemplaires = new ArrayList<>();
    private List<Parametre> listeEmprunts = new ArrayList<>();
    Thread thread;

    /**
     * Constructeur: initialise le chargement a false et initiallise les observers
     */

    public ControleurFirstScreen() {
        this.isDataLoaded = false;
        this.observers = new ArrayList<>();
    }

    public void registerObserver(ObserverFirstScreen observer) {
        this.observers.add(observer);
    }


    /**
     * Chargement des données:
     * Création d'un thread dans lequel on parcourt les caches, on fait une requête et on remplit le cache avec la liste chargée
     * Gestion de l'exception: si une erreur est survenue dans la requête, on affiche un message d'erreur et une possibilité de réeessayer
     * si aucune exception n'est levée, le thread se finit par le lancement du menu
     *
     * Avant de faire une requete pour remplir le cache, on verifie que le cache n'est pas deja rempli, notammenet si une coupure reseau a arrete le chargement en cours
     * Si le cache est deja rempli pour la valeur souhaite, on ne fait pas d'appel, sinon on remplit avec un appel en base
     */

    public void loadData() {
        thread = new Thread() {
            public void run() {
                // Perform data loading here
                int totalTasks = ParametreType.values().length * (TypeDeDocGrouping.values().length * 2);
                int completedTasks = 0;

                for (ParametreType parametreType : ParametreType.values()) {
                    for (TypeDeDocGrouping typeDeDocGrouping : TypeDeDocGrouping.values()) {

                        try {
                            listeExemplaires=Cache.get(parametreType, typeDeDocGrouping, Mode.EXEMPLAIRES, Integer.MAX_VALUE);
                            if(listeExemplaires.isEmpty()){
                                listeExemplaires = Database.getParamByTypeDeDoc(parametreType, typeDeDocGrouping, Mode.EXEMPLAIRES);
                            }
                            completedTasks++;
                            for(ObserverFirstScreen observer : observers){
                                observer.updateProgressBar((int) (((float) completedTasks / totalTasks) * 100));
                            }
                            listeEmprunts=Cache.get(parametreType, typeDeDocGrouping, Mode.EMPRUNTS, Integer.MAX_VALUE);
                            if(listeEmprunts.isEmpty()){
                                listeEmprunts = Database.getParamByTypeDeDoc(parametreType, typeDeDocGrouping, Mode.EMPRUNTS);
                            }
                            completedTasks++;
                            for(ObserverFirstScreen observer : observers){
                                observer.updateProgressBar((int) (((float) completedTasks / totalTasks) * 100));
                            }
                        }
                        catch (IOException e) {
                            System.out.println(e.getMessage());
                            for(ObserverFirstScreen observer : observers){
                                observer.loadingFailed();
                            }
                            return;
                        }

                        Cache.put(parametreType, typeDeDocGrouping, listeExemplaires, listeEmprunts);

                    }
                }
                setDataLoaded(true);
                for(ObserverFirstScreen observer : observers){
                    observer.loadingSuccess();
                }

            }
        };
        thread.start();
    }

    public boolean isDataLoaded() {
        return isDataLoaded;
    }
    public void setDataLoaded(boolean dataLoaded) {
        isDataLoaded = dataLoaded;
    }

    /**
     * Retente le chargement des donnees
     */

    public void retry(){
        for(ObserverFirstScreen observer : observers){
            observer.retry();
        }
        loadData();
    }

}
