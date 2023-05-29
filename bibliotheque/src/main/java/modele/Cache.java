package modele;

import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.utils.Mode;
import modele.utils.TypeDeDocGrouping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  <h1> Cache </h1>
 *
 *  <p>
 *  Le cache permet de stocker nos donnees dans l'application pour les charger une fois au demarrage
 *  et ne pas avoir a les re-telecharger a chaque appel.
 *  Sachant que l'appel aux donnees est limite a 100 pour ne pas charger des donnees inutiles,
 *  on charge 2 listes:
 *    - 1 avec les 100premiers documents tries par exemplaires
 *    - 1 avec les 100premiers documents tries par emprunts
 *    Car sinon selon le tri, les 100premieres valeurs ne seront pas les mêmes
 *   On les stocke donc dans 2 maps de cache differentes
 *  </p>
 *
 *  @author Marine
 *  @author Celine
 *  @since 10/04/2023
 *  @version 1.0
 */
public class Cache {

    /***
     * Une liste d'exemplaires ou d'emprunts est un dictionnaire en deux dimensions, avec deux cles une de type parametre et une de type typededoc
     */
    public static Map<ParametreType, Map<TypeDeDocGrouping, List<Parametre>>> cacheMapExemplaires = new HashMap<>();
    public static Map<ParametreType, Map<TypeDeDocGrouping, List<Parametre>>> cacheMapEmprunts = new HashMap<>();

    /**
     * Remplit le cache
     * @param key1 : type de parametre
     * @param key2  :  type de document
     * @param valueExemplaires : liste triee par exemplaires
     * @param valueEmprunts : liste triee par emprunts
     *
     * Ajoute a la map cache une key1 dont la valeur est une nouvelle map, qui est elle-même ensuite remplie par key2 et sa valeur: la liste.
     */
    public static void put(ParametreType key1, TypeDeDocGrouping key2, List<Parametre> valueExemplaires, List<Parametre> valueEmprunts ) {
        Map<TypeDeDocGrouping, List<Parametre>> innerMapExemplaires;
        Map<TypeDeDocGrouping, List<Parametre>> innerMapEmprunts;

        innerMapExemplaires = cacheMapExemplaires.computeIfAbsent(key1, k -> new HashMap<>());
        innerMapEmprunts = cacheMapEmprunts.computeIfAbsent(key1, k -> new HashMap<>());

        innerMapExemplaires.put(key2, valueExemplaires);
        innerMapEmprunts.put(key2, valueEmprunts);
    }

    /**
     * @return une liste de parametres, liste vide si aucune liste n'est trouvee pour les cles choisies
     * @param key1: le type de parametre
     * @param key2: le type de document
     * @param limit: le nombre de parametres que l'on veut recuperer
     * @param sortBy: selon si on veut recuperer le cache de l'offre (exemplaires) ou de la demande (emprunts)
     * Recupere une liste dans le cache selon ses parametres
     *
     */
    public static List<Parametre> get(ParametreType key1, TypeDeDocGrouping key2, Mode sortBy, int limit) {
        if(key1 == null || key2 == null || sortBy == null){
            throw new IllegalArgumentException("Erreur: Les paramètres ne peuvent pas être null");
        }

        Map<TypeDeDocGrouping, List<Parametre>> innerMap = null;
        switch (sortBy) {
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
            if(list!=null){
                if(limit>list.size()){
                    return list;
                }
                else{
                    return list.subList(0, limit);
                }
            }
        }
        return new ArrayList<>();
    }

    /**
     * Vide entierement le cache
     */
    public static void clearCache(){
        Cache.cacheMapExemplaires.clear();
        Cache.cacheMapEmprunts.clear();
    }
}
