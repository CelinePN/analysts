package modele.parametre;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *  <h1> Paramètre </h1>
 *
 * <p>
 *     C'est un modèle représentant l'élément (paramètre) qui sera analysé dans un graphique.
 *     Un paramètre est qualifié par son type ParametreType, attribué à l'issue de sa sérialization
 * </p>
 *
 * @Author: Marine
 * @Version: 1.0
 * @since: 30/03/2023
 */
public class Parametre {

    /** Le type du paramètre (LANGUE, GENRE, AUTEUR...) */
    private ParametreType type;

    /** Le nom de ce paramètre ("Jules Verne" pour un type AUTEUR, "anglais" pour un type LANGUE etc.) */
    @JsonProperty("_id")
    private String nom;

    /** Le nombre d'occurrence de ce paramètre dans la base
     * Donc, le nombre total de documents qui portent le nom du paramètre */
    private int count;

    /** La somme calculée du nombre de prêts total pour ce paramètre (tout documents confondus) */
    @JsonProperty("total_prets")
    private int totalPrets;

    /** La somme calculée du nombre d'exemplaires total pour ce paramètre (tout documents confondus) */
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
