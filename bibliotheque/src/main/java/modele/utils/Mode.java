package modele.utils;

/**
 *  <h1> SortBy </h1>
 *
 * <p>
 *     Enumération comportant les différentes manières dont on peut trier les données appelées.
 *     Simplifie la requête en base
 * </p>
 *
 * @Author: Marine
 * @Version: 1.0
 * @since: 30/03/2023
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
