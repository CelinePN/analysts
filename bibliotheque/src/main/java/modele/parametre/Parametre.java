package modele.parametre;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *  <h1> Parametre </h1>
 *
 * <p>
 *     C'est un modele representant l'element (parametre) qui sera analyse dans un graphique.
 *     Un parametre est qualifie par son type ParametreType, attribue a l'issue de sa serialization
 * </p>
 *
 * @author Marine
 * @version 1.0
 * @since 30/03/2023
 */
public class Parametre {

    /** Le type du parametre (LANGUE, GENRE, AUTEUR...) */
    private ParametreType type;

    /** Le nom de ce parametre ("Jules Verne" pour un type AUTEUR, "anglais" pour un type LANGUE etc.) */
    @JsonProperty("_id")
    private String nom;

    /** Le nombre d'occurrences de ce parametre dans la base
     * Donc, le nombre total de documents qui portent le nom du parametre */
    private int count;

    /** La somme calculee du nombre de prets total pour ce parametre (tout documents confondus) */
    @JsonProperty("total_prets")
    private int totalPrets;

    /** La somme calculee du nombre d'exemplaires total pour ce parametre (tout documents confondus) */
    @JsonProperty("total_exemplaires")
    private int totalExemplaires;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ParametreType getType_param() {
        return type;
    }

    public void setType_param(ParametreType type_param) {
        this.type = type_param;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalPrets() {
        return totalPrets;
    }

    public void setTotalPrets(int totalPrets) {
        this.totalPrets = totalPrets;
    }

    public int getTotalExemplaires() {
        return totalExemplaires;
    }

    public void setTotalExemplaires(int totalExemplaires) {
        this.totalExemplaires = totalExemplaires;
    }

}
