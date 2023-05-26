package modele.parametre;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 *  <h1> ParametreWrapper </h1>
 *
 * <p>
 *     C'est un objet modele qui comporte la structure de nos donnees telles que recuperees.
 *     C'est à dire un objet qui contient une liste de parametres
 * </p>
 *
 * @author Marine
 * @version 1.0
 * @since 30/03/2023
 */
public class ParametreWrapper {

    /**
     * Variable recuperant la liste des parametres
     */
    @JsonProperty("documents")
    private List<Parametre> parametres;

    public List<Parametre> getParametres() {
        return parametres;
    }

}
