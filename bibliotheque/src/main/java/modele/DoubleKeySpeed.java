package modele;

import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.utils.SortBy;
import modele.utils.TypeDeDocGrouping;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoubleKeySpeed {

    /**
     * Le cache permet de stocker nos données côté applicatif pour les charger une fois au démarrage
     * et ne pas avoir à les re-télécharger à chaque appel.
     * Sachant que l'appel aux données est limité à 100, pour ne pas charger des données inutiles,
     * on charge 2 listes:
     *  - 1 avec les 100premiers documents triés par exemplaires
     *  - 1 avec les 100premiers documents triés par emprunts
     *  On les stocke donc dans 2 maps de cache différentes
     */
    public static Map<ParametreType, Map<TypeDeDocGrouping, List<Parametre>>> cacheMap = new HashMap<>();

    /**
     * Remplit le cache
     * @param key1 : type de paramètre
     * @param key2  :  type de document
     * @param value : liste complete
     */
    public static void put(ParametreType key1, TypeDeDocGrouping key2, List<Parametre> value ) {
        Map<TypeDeDocGrouping, List<Parametre>> innerMap;
        innerMap = cacheMap.computeIfAbsent(key1, k -> new HashMap<>());
        innerMap.put(key2, value);
    }

    public static List<Parametre> get(ParametreType key1, TypeDeDocGrouping key2, SortBy sortBy, int limit) {
        Map<TypeDeDocGrouping, List<Parametre>> innerMap;
        innerMap = cacheMap.get(key1);

        if (innerMap != null) {
            List<Parametre> list = innerMap.get(key2);
            List<Parametre> sortedList = sort(sortBy, list);
            return sortedList.subList(0, limit);
        }
        return null;
    }

    private static List<Parametre> sort(SortBy sort, List<Parametre> listToSort){
        switch(sort){
            case EXEMPLAIRES: listToSort.sort(comparator_exemplaires);
            case EMPRUNTS: listToSort.sort(comparator_emprunts);
            default: listToSort.sort(comparator_exemplaires);
        }
        return listToSort;
    }

    static Comparator<Parametre> comparator_exemplaires = new Comparator<Parametre>() {
        @Override
        public int compare(Parametre p1, Parametre p2) {
            return Integer.compare(p2.getTotalExemplaires(), p1.getTotalExemplaires());
        }
    };
    static Comparator<Parametre> comparator_emprunts = new Comparator<Parametre>() {
        @Override
        public int compare(Parametre p1, Parametre p2) {
            return Integer.compare(p2.getTotalPrets(), p1.getTotalPrets());
        }
    };
}
