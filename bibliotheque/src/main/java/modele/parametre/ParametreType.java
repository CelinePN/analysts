package modele.parametre;

/**
 *  <h1> ParametreType </h1>
 *
 * <p>
 *     Cette enumeration regroupe tous les parametres que l'on pourra rechercher sur l'application
 * </p>
 *
 * @author Marine
 * @version 1.0
 * @since 30/03/2023
 */

public enum ParametreType {
    LANGUE("Langue"),
    AUTEUR("Auteur Nom"),
    TYPE_DE_DOC("Type de document"),
    GENRE("Catégorie statistique 1");

    public String getString() {
        return value;
    }
    private final String value;

    ParametreType(String v) {
        this.value=v;
    }

}
