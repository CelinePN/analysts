package modele.parametre;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ParametreWrapper {

    @JsonProperty("documents")
    private List<Parametre> parametres;

    public List<Parametre> getParametres() {
        return parametres;
    }

    public void setParametres(List<Parametre> parametres) {
        this.parametres = parametres;
    }
}
