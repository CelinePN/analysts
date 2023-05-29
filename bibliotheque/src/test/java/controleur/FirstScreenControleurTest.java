package controleur;

import controleur.firstscreen.ControleurFirstScreen;
import dao.Database;
import modele.Cache;
import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.utils.Mode;
import modele.utils.TypeDeDocGrouping;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static modele.utils.TypeDeDocGrouping.FILMS;
import static modele.utils.TypeDeDocGrouping.NO_TYPE;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  <h1> Test FirstScreen Controleur </h1>
 *
 * <p>
 *     Cette classe permet de tester la classe FirstScreen et les donnees recuperees
 * </p>
 *
 * @author : Marine
 * @version : 2.0
 * @since : 01/04/2023
 */



public class FirstScreenControleurTest {
    private final ControleurFirstScreen controleurFirstScreen = new ControleurFirstScreen();

    Map<ParametreType, Map<TypeDeDocGrouping, List<Parametre>>> cacheTestExemplaires = new HashMap<>();
    Map<ParametreType, Map<TypeDeDocGrouping, List<Parametre>>> cacheTestEmprunts = new HashMap<>();


    /**
     * Verifie que le cache se remplit correctement en appelant loadData() si on a du reseau
     * @throws IOException
     */
    @Test
    public void testCacheIsCreatedAfterLoading() {
        controleurFirstScreen.loadData();

        //attends que toutes les donnees soient chargees avant de tester le chargement du cache
        while (!controleurFirstScreen.isDataLoaded()) {
            try {
                Thread.sleep(100); // wait for 100 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        assertNotNull(Cache.cacheMapExemplaires);
        assertNotNull(Cache.cacheMapEmprunts);
        assertFalse(Cache.cacheMapExemplaires.isEmpty());
        assertFalse(Cache.cacheMapEmprunts.isEmpty());
        assertTrue(controleurFirstScreen.isDataLoaded());

    }

    /**
     *  verifie que le cache se remplit correctement s'il etait deja rempli
     */
    @Test
    public void testCacheIsCorrectlyImplementedIfNotEmpty() {
        creationTestData();
        remplitCache();
        assertFalse(Cache.cacheMapExemplaires.isEmpty());

        controleurFirstScreen.loadData();


        //attends que toutes les donnees soient chargees avant de tester le chargement du cache
        while (!controleurFirstScreen.isDataLoaded()) {
            try {
                Thread.sleep(100); // wait for 100 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        assertEquals(cacheTestEmprunts.get(ParametreType.LANGUE).get(NO_TYPE), Cache.get(ParametreType.LANGUE, NO_TYPE, Mode.EMPRUNTS, Integer.MAX_VALUE));
        assertEquals(cacheTestExemplaires.get(ParametreType.AUTEUR).get(TypeDeDocGrouping.FILMS), Cache.get(ParametreType.AUTEUR, FILMS, Mode.EXEMPLAIRES, Integer.MAX_VALUE));
    }

    void remplitCache(){
        controleurFirstScreen.loadData();

        //attends que toutes les donnees soient chargees avant de tester le chargement du cache
        while (!controleurFirstScreen.isDataLoaded()) {
            try {
                Thread.sleep(100); // wait for 100 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void creationTestData() {
        for(ParametreType param: ParametreType.values()){
            for(TypeDeDocGrouping typeDeDoc: TypeDeDocGrouping.values()){
                try {
                    List<Parametre> liste1 = Database.getParamByTypeDeDoc(param,typeDeDoc, Mode.EXEMPLAIRES);
                    List<Parametre> liste2 = Database.getParamByTypeDeDoc(param,typeDeDoc, Mode.EMPRUNTS);

                    Map<TypeDeDocGrouping, List<Parametre>> innerMapExemplaires;
                    Map<TypeDeDocGrouping, List<Parametre>> innerMapEmprunts;

                    innerMapExemplaires = cacheTestExemplaires.computeIfAbsent(param, k -> new HashMap<>());
                    innerMapEmprunts = cacheTestEmprunts.computeIfAbsent(param, k -> new HashMap<>());

                    innerMapExemplaires.put(typeDeDoc, liste1);
                    innerMapEmprunts.put(typeDeDoc, liste2);

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
