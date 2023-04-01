package modele.utils;

import java.util.Arrays;
import java.util.List;

/**
 *  <h1> TypeDeDocGrouping </h1>
 *
 * <p>
 *     Cette énumération regroupe des types de document (trop) spécifiques dans des catégories plus larges.
 *     Permet de choisir sur l'interface un type de document sans avoir une liste trop longue à dérouler
 * </p>
 *
 * @Author: Marine
 * @Version: 1.0
 * @since: 30/03/2023
 */
public enum TypeDeDocGrouping {

    NO_TYPE(0, Arrays.asList()),
    FILMS(1, Arrays.asList("Adaptation en Langue des Signes Française", "Cassette vidéo VHS > 12 ans", "Cassette vidéo VHS tous publics", "DVD jeunesse", "DVD nouveautés tous publics", "DVD- vidéo > 12 ans", "DVD- vidéo > 18 ans", "DVD-vidéo > 16 ans", "DVD-vidéo tous publics")),
    BD(2, Arrays.asList("BD adulte non réservable", "BD jeunesse non réservable", "Bande dessinée jeunesse", "Bande dessinée jeunesse >12 ans", "Bande dessinée pour adulte")),
    MUSIQUE(3, Arrays.asList("CD Rom revue", "Disque compact", "Enregistrement musical pour la jeunesse", "Instrument de musique", "Nouveauté disque compact", "Partition", "Vinyle")),
    JEUX(4, Arrays.asList("Jeux Vidéos tous publics", "Jeux de société", "Jeux vidéos > 18 ans")),
    LIVRES(5, Arrays.asList("Livre de Fonds spécialisés","Livre de section jeunesse > 12 ans","Livre en gros caractères", "Livre en langue étrangère", "Livre jeunesse", "Livre pour adulte", "Livre sonore jeunesse", "Livre sonore pour adulte", "Livres et périodiques DAISY", "Méthode de langue", "Méthode musicale")),
    REVUES(6, Arrays.asList("Revue de Fonds spécialisés", "Revue jeunesse", "Revue pour ado", "Revue pour adulte")),
    AUTRES(7, Arrays.asList("Carte ou plan", "Diapositives jeunesse", "Documents numériques et multimédia adulte", "Documents numériques et multimédia jeunesse", "Dossier", "Image, fiche cuisine …", "Non empruntable", "Nouveauté", "Nouveauté jeunesse", "Usuels")),
    ;

    public List<String> getListTypes() {
        return listTypes;
    }

    private List<String> listTypes;

    public int getIndice() {
        return indice;
    }

    private int indice;


    TypeDeDocGrouping(int i, List<String> a) {

        this.listTypes=a;
        this.indice = i;
    }

    /** @return String: Cette méthode retourne une chaîne de caractères regroupant tous
     * les types englobés dans un TypeDeDocGrouping. Cette chaîne servira au body de la
     * requête en MongoDB. */

    public String enumToString(){
        String string="";
        for(int i=0; i<this.listTypes.size();i++){
            string+="{\"Type de document\": \""+this.listTypes.get(i)+"\"}";
            if(i!=this.listTypes.size()-1){
                string+=",";
            }
        }
        return string;
    }

}
