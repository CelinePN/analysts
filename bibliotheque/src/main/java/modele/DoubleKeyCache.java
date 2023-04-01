package modele;

import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.utils.SortBy;
import modele.utils.TypeDeDocGrouping;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoubleKeyCache {
    private static Map<ParametreType, Map<TypeDeDocGrouping, List<Parametre>>> cacheMap;

    public DoubleKeyCache() {
        cacheMap = new HashMap<>();
    }

    public void put(ParametreType key1, TypeDeDocGrouping key2, List<Parametre> value) {
        Map<TypeDeDocGrouping, List<Parametre>> innerMap = cacheMap.computeIfAbsent(key1, k -> new HashMap<>());
        innerMap.put(key2, value);
    }

    public List<Parametre> get(ParametreType key1, TypeDeDocGrouping key2, SortBy sortBy, int limit) {
        Map<TypeDeDocGrouping, List<Parametre>> innerMap = cacheMap.get(key1);
        if (innerMap != null) {
            List<Parametre> list = innerMap.get(key2);
            List<Parametre> sortedList = sort(sortBy, list);
            return sortedList.subList(0, limit);
        }
        return null;
    }

    private List<Parametre> sort(SortBy sort, List<Parametre> listToSort){
        switch(sort){
            case EXEMPLAIRES: listToSort.sort(comparator_exemplaires);
            case EMPRUNTS: listToSort.sort(comparator_emprunts);
            default: listToSort.sort(comparator_exemplaires);
        }
        return listToSort;
    }

    Comparator<Parametre> comparator_exemplaires = new Comparator<Parametre>() {
        @Override
        public int compare(Parametre p1, Parametre p2) {
            return Integer.compare(p2.getTotalExemplaires(), p1.getTotalExemplaires());
        }
    };
    Comparator<Parametre> comparator_emprunts = new Comparator<Parametre>() {
        @Override
        public int compare(Parametre p1, Parametre p2) {
            return Integer.compare(p2.getTotalPrets(), p1.getTotalPrets());
        }
    };

    public boolean containsKeys(ParametreType key1, TypeDeDocGrouping key2) {
        Map<TypeDeDocGrouping, List<Parametre>> innerMap = cacheMap.get(key1);
        if (innerMap != null) {
            return innerMap.containsKey(key2);
        }
        return false;
    }

    public void remove(ParametreType key1, TypeDeDocGrouping key2) {
        Map<TypeDeDocGrouping, List<Parametre>> innerMap = cacheMap.get(key1);
        if (innerMap != null) {
            innerMap.remove(key2);
            if (innerMap.isEmpty()) {
                cacheMap.remove(key1);
            }
        }
    }

    public void clear() {
        cacheMap.clear();
    }
}
