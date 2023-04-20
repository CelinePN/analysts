package controleur;

import controleur.firstscreen.ControleurFirstScreen;
import dao.DatabaseEco;
import modele.DoubleKeyCacheEco;
import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.utils.SortBy;
import modele.utils.TypeDeDocGrouping;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  <h1> Test FirstScreen Controleur </h1>
 *
 * <p>
 *     Cette classe permet de tester la classe FirstScreen et les données récupérées
 * </p>
 *
 * @Author: Marine
 * @Version: 2.0
 * @since: 01/04/2023
 */



public class FirstScreenControleurTest {
    private final ControleurFirstScreen controleurFirstScreen = new ControleurFirstScreen();

    public static Map<ParametreType, Map<TypeDeDocGrouping, List<Parametre>>> cacheMapExemplairesTest = new HashMap<>();
    public static Map<ParametreType, Map<TypeDeDocGrouping, List<Parametre>>> cacheMapEmpruntsTest = new HashMap<>();
    public FirstScreenControleurTest() {
    }

    //à refaire, ça sert à rien de tester la même chose que y'a dans le code
    //plutôt tester des exemples et des cas  complexes
    //tester le coverage, les exceptions...
    @Before
    public void creationTestData() {
        //on suggère que la database est le cache ont déjà été testés
        for(ParametreType param: ParametreType.values()){
            for(TypeDeDocGrouping typeDeDoc: TypeDeDocGrouping.values()){
                try {
                    List<Parametre> liste1 = DatabaseEco.getParamByTypeDeDoc(param,typeDeDoc,SortBy.EXEMPLAIRES).subList(0,10);
                    List<Parametre> liste2 = DatabaseEco.getParamByTypeDeDoc(param,typeDeDoc,SortBy.EMPRUNTS).subList(0,10);
                    DoubleKeyCacheEco.put(param, typeDeDoc,liste1,liste2);

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        cacheMapExemplairesTest = DoubleKeyCacheEco.cacheMapExemplaires;
        cacheMapEmpruntsTest = DoubleKeyCacheEco.cacheMapEmprunts;
    }

    /**
     * Vérifie que le cache se remplit correctement en appelant loadData() si on a du réseau
     *
     * @throws IOException
     */
    @Test
    public void testCacheIsCreatedAfterLoading() {

        controleurFirstScreen.loadData();

        //attends que toutes les données soient chargées avant de tester le chargement du cache
        while (!controleurFirstScreen.isDataLoaded()) {
            try {
                Thread.sleep(100); // wait for 100 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        assertNotNull(DoubleKeyCacheEco.cacheMapExemplaires);
        assertNotNull(DoubleKeyCacheEco.cacheMapExemplaires);
        assertFalse(DoubleKeyCacheEco.cacheMapExemplaires.isEmpty());
        assertFalse(DoubleKeyCacheEco.cacheMapEmprunts.isEmpty());

    }

    @Test
    public void testCacheIsCorrectlyCompleted() {
        controleurFirstScreen.loadData();

        //attends que toutes les données soient chargées avant de tester le chargement du cache
        while (!controleurFirstScreen.isDataLoaded()) {
            try {
                Thread.sleep(100); // wait for 100 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        assertEquals(cacheMapExemplairesTest, DoubleKeyCacheEco.cacheMapExemplaires) ;
        assertEquals(cacheMapEmpruntsTest, DoubleKeyCacheEco.cacheMapEmprunts) ;

    }
}
