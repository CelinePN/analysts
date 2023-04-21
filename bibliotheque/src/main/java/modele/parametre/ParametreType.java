package modele.parametre;

/**
 *  <h1> ParamètreType </h1>
 *
 * <p>
 *     Cette énumération regroupe tous les paramètres que l'on pourra rechercher sur l'application
 * </p>
 *
 * @Author: Marine
 * @Version: 1.0
 * @since: 30/03/2023
 */

public enum ParametreType {
    LANGUE("Langue", 0),
    AUTEUR("Auteur Nom", 1),
    TYPE_DE_DOC("Type de document", 2),
    GENRE("Catégorie statistique 1", 3);

    public String getString() {
        return value;
    }

    private String value;

    public int getIndice() {
        return indice;
    }

    private int indice;


    ParametreType(String v, int i) {

        this.value=v;
        this.indice = i;
    }

}
