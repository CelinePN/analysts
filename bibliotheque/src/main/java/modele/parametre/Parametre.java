package modele.parametre;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Parametre {

    private TypesParametre type_param;

    @JsonProperty("_id")
    private String nom;

    private int count;

    @JsonProperty("total_prets")
    private int totalPrets;

    @JsonProperty("total_exemplaires")
    private int totalExemplaires;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypesParametre getType_param() {
        return type_param;
    }

    public void setType_param(TypesParametre type_param) {
        this.type_param = type_param;
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
