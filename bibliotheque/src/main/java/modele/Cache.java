package modele;

import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.utils.SortBy;
import modele.utils.TypeDeDocGrouping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache {

    /**
     * @Author: Marine
     * @since: 10/04/2023
     * @Version: 1.0
     * Le cache permet de stocker nos données côté applicatif pour les charger une fois au démarrage
     * et ne pas avoir à les re-télécharger à chaque appel.
     * Sachant que l'appel aux données est limité à 100, pour ne pas charger des données inutiles,
     * on charge 2 listes:
     *  - 1 avec les 100premiers documents triés par exemplaires
     *  - 1 avec les 100premiers documents triés par emprunts
     *  Car sinon selon le tri, les 100premières valeurs ne seront pas les mêmes
     *  On les stocke donc dans 2 maps de cache différentes
     */
    public static Map<ParametreType, Map<TypeDeDocGrouping, List<Parametre>>> cacheMapExemplaires = new HashMap<>();
    public static Map<ParametreType, Map<TypeDeDocGrouping, List<Parametre>>> cacheMapEmprunts = new HashMap<>();

    /**
     * Remplit le cache
     * @param key1 : type de paramètre
     * @param key2  :  type de document
     * @param valueExemplaires : liste triée par exemplaires
     * @param valueEmprunts : liste triée par emprunts
     *
     * Ajoute à la map cache une key1 dont la valeur est une nouvelle map, qui est elle-même ensuite remplie par key2 et sa valeur: la liste.
     */
    public static void put(ParametreType key1, TypeDeDocGrouping key2, List<Parametre> valueExemplaires, List<Parametre> valueEmprunts ) {
        Map<TypeDeDocGrouping, List<Parametre>> innerMapExemplaires;
        Map<TypeDeDocGrouping, List<Parametre>> innerMapEmprunts;

        innerMapExemplaires = cacheMapExemplaires.computeIfAbsent(key1, k -> new HashMap<>());
        innerMapEmprunts = cacheMapEmprunts.computeIfAbsent(key1, k -> new HashMap<>());

        innerMapExemplaires.put(key2, valueExemplaires);
        innerMapEmprunts.put(key2, valueEmprunts);
    }

    public static List<Parametre> get(ParametreType key1, TypeDeDocGrouping key2, SortBy sortBy, int limit) {
        Map<TypeDeDocGrouping, List<Parametre>> innerMap = null;
        switch(sortBy){
            case EXEMPLAIRES: {
                innerMap = cacheMapExemplaires.get(key1);
                break;
            }
            case EMPRUNTS: {
                innerMap = cacheMapEmprunts.get(key1);
                break;
            }
        }
        if (innerMap != null) {
            List<Parametre> list = innerMap.get(key2);
            return list.subList(0, limit);
        }
        return new ArrayList<>();
    }

    public static void clearCache(){
        Cache.cacheMapExemplaires.clear();
        Cache.cacheMapEmprunts.clear();
    }
}
