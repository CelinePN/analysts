package modele.utils;

import java.util.List;

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
public enum SortBy {
    EXEMPLAIRES("total_exemplaires"),
    EMPRUNTS("total_prets"),
    COUNT("count"),
    ALPHABETIQUE("_id");

    public String getSortingType() {
        return sortingType;
    }

    private String sortingType;


    SortBy(String a) {
        this.sortingType=a;
    }

}
