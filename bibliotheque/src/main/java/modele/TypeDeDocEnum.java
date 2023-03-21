package modele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TypeDeDocEnum {

    FILMS(Arrays.asList("Adaptation en Langue des Signes Française", "Cassette vidéo VHS > 12 ans", "Cassette vidéo VHS tous publics", "DVD jeunesse", "DVD nouveautés tous publics", "DVD- vidéo > 12 ans", "DVD- vidéo > 18 ans", "DVD-vidéo > 16 ans", "DVD-vidéo tous publics")),
    BD(Arrays.asList("BD adulte non réservable", "BD jeunesse non réservable", "Bande dessinée jeunesse", "Bande dessinée jeunesse >12 ans", "Bande dessinée pour adulte")),
    MUSIQUE(Arrays.asList("CD Rom revue", "Disque compact", "Enregistrement musical pour la jeunesse", "Instrument de musique", "Nouveauté disque compact", "Partition", "Vinyle")),
    JEUX(Arrays.asList("Jeux Vidéos tous publics", "Jeux de société", "Jeux vidéos > 18 ans")),
    LIVRES(Arrays.asList("Livre de Fonds spécialisés","Livre de section jeunesse > 12 ans","Livre en gros caractères", "Livre en langue étrangère", "Livre jeunesse", "Livre pour adulte", "Livre sonore jeunesse", "Livre sonore pour adulte", "Livres et périodiques DAISY", "Méthode de langue", "Méthode musicale")),
    REVUES(Arrays.asList("Revue de Fonds spécialisés", "Revue jeunesse", "Revue pour ado", "Revue pour adulte")),
    AUTRES(Arrays.asList("Carte ou plan", "Diapositives jeunesse", "Documents numériques et multimédia adulte", "Documents numériques et multimédia jeunesse", "Dossier", "Image, fiche cuisine …", "Non empruntable", "Nouveauté", "Nouveauté jeunesse", "Usuels")),
    ;
    private List<String> type;


    TypeDeDocEnum(List<String> a) {
        this.type=a;
    }

    public String enumToString(){
        String string="";
        for(int i=0; i<this.type.size();i++){
            string+="{Type de document: "+this.type.get(i)+"}";
            if(i!=this.type.size()-1){
                string+=",";
            }
        }
        return string;
    }

}
