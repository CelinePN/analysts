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

public class ControleurFirstScreen {

    private boolean isDataLoaded;

    private final List<ObserverFirstScreen> observers ;
    private List<Parametre> listeExemplaires = new ArrayList<>();
    private List<Parametre> listeEmprunts = new ArrayList<>();
    Thread thread;

    public Thread getThread() {
        return thread;
    }


    public ControleurFirstScreen() {
        this.isDataLoaded = false;
        this.observers = new ArrayList<>();
    }

    public void registerObserver(ObserverFirstScreen observer) {
        this.observers.add(observer);
    }


    /**
     * Chargement des données:
     * On commence par vider le cache au cas où il ait commencé à être rempli et interrompu
     * Création d'un thread dans lequel on parcourt les caches, on fait une requête et on remplit le cache avec la liste chargée
     * Gestion de l'exception: si une erreur est survenue dans la requête, on affiche un message d'erreur et une possibilité de réeessayer
     * si aucune exception n'est levée, le thread se finit par le lancement du menu
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
                            listeEmprunts=Cache.get(parametreType, typeDeDocGrouping, Mode.EXEMPLAIRES, Integer.MAX_VALUE);
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

    public void retry(){
        for(ObserverFirstScreen observer : observers){
            observer.retry();
        }
        loadData();
    }

}
