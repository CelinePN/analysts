/*package modele;

import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.utils.SortBy;
import modele.utils.TypeDeDocGrouping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static modele.utils.TypeDeDocGrouping.values;

public class Cache {

    public static List<List<List<Parametre>>> data_tab = new ArrayList<>();


    public static void initCache(){
        for(int i = 0; i < ParametreType.values().length; i++) {
            List<List<Parametre>> innerList = new ArrayList<>();
            for(int j = 0; j < TypeDeDocGrouping.values().length; j++) {
                innerList.add(new ArrayList<>());
            }
            data_tab.add(innerList);
        }
    }
    public static List<Parametre> getList(ParametreType param, TypeDeDocGrouping typeDeDocGrouping, SortBy sortBy, int limit){
        List<Parametre> list= data_tab.get(param.getIndice()).get(typeDeDocGrouping.getIndice());
        List<Parametre> sortedList = sort(sortBy, list);
        return sortedList.subList(0, limit);
    }

    private static List<Parametre> sort(SortBy sort, List<Parametre> listToSort){
        switch(sort){
            case EXEMPLAIRES: listToSort.sort(comparator_exemplaires);
            case EMPRUNTS: listToSort.sort(comparator_emprunts);
            default: listToSort.sort(comparator_exemplaires);
        }
        return listToSort;
    }

    static Comparator<Parametre> comparator_exemplaires = new Comparator<Parametre>() {
        @Override
        public int compare(Parametre p1, Parametre p2) {
            return Integer.compare(p2.getTotalExemplaires(), p1.getTotalExemplaires());
        }
    };
    static Comparator<Parametre> comparator_emprunts = new Comparator<Parametre>() {
        @Override
        public int compare(Parametre p1, Parametre p2) {
            return Integer.compare(p2.getTotalPrets(), p1.getTotalPrets());
        }
    };

}*/
