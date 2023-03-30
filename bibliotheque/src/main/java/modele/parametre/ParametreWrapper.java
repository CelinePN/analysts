package modele.parametre;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 *  <h1> ParamètreWrapper </h1>
 *
 * <p>
 *     C'est un objet modèle qui comporte la structure de nos données telles que récupérées.
 *     C'est à dire un objet qui contient une liste de paramètres
 * </p>
 *
 * @Author: Marine
 * @Version: 1.0
 * @since: 30/03/2023
 */
public class ParametreWrapper {

    @JsonProperty("documents")
    private List<Parametre> parametres;

    public List<Parametre> getParametres() {
        return parametres;
    }

}
