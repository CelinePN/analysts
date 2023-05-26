package modele.utils;

/**
 *  <h1> Mode </h1>
 *
 * <p>
 *     Enumeration comportant les differentes manieres dont on peut trier les donnees appelees.
 *     Simplifie la requete en base
 * </p>
 *
 * @author Marine
 * @version 1.0
 * @since 30/03/2023
 */
public enum Mode {
    EXEMPLAIRES("total_exemplaires"),
    EMPRUNTS("total_prets"),
    BOTH("");


    public String getSortingString() {
        return sortingType;
    }

    private final String sortingType;


    Mode(String a) {
        this.sortingType=a;
    }

}
