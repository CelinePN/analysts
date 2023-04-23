package modele;

import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.utils.SortBy;
import modele.utils.TypeDeDocGrouping;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static modele.utils.TypeDeDocGrouping.NO_TYPE;
import static org.junit.jupiter.api.Assertions.*;

/**
 *  <h1> Test Cache </h1>
 *
 * <p>
 *     Cette classe permet de tester la classe Cache et les données récupérées
 * </p>
 *
 * @Author: Céline
 * @Version: 1.0
 * @since: 15/04/2023
 */

public class CacheTest {

    /*private final Map<ParametreType, Map<TypeDeDocGrouping, List<Parametre>>> listesExemplairesTest = new HashMap<>();
    private final Map<ParametreType, Map<TypeDeDocGrouping, List<Parametre>>> listesEmpruntsTest = new HashMap<>();
*/

    @Test
    public void testGetLanguages() {
        assertDoesNotThrow(() -> Cache.get(ParametreType.LANGUE, TypeDeDocGrouping.NO_TYPE, SortBy.EMPRUNTS,5));
    }

    /*
    @Test
    public void testGetLanguagesNombre() throws IOException {
        Database db = new Database();
        //compter pour français?
        //assertEquals(14, db.getParamByTypeDeDoc(ParametreType.LANGUE, NO_TYPE).get(0).getTotalExemplaires());
        //assertEquals(14, db.getParamByTypeDeDoc(ParametreType.LANGUE, NO_TYPE).get(0).getTotalPrets());
        assertEquals(67, db.getParamByTypeDeDoc(ParametreType.LANGUE, TypeDeDocGrouping.NO_TYPE).get(0).getCount());
    }
*/
    @Test
    public void testGetAuteurNombre() throws IOException {
   //     assertEquals(10, Cache.put(ParametreType.AUTEUR, TypeDeDocGrouping.LIVRES, , 3));
    }
/*
    @Test
    public void testGetTypeNombre() throws IOException {
        Database db = new Database();
        assertEquals(45, db.getParamByTypeDeDoc(ParametreType.TYPE_DE_DOC, TypeDeDocGrouping.NO_TYPE).get(1).getCount());
    }

    @Test
    public void testGetGenreNombre() throws IOException {
        Database db = new Database();
        assertEquals(745, db.getParamByTypeDeDoc(ParametreType.GENRE, TypeDeDocGrouping.NO_TYPE).get(1).getCount());
    }


    @Test
    public void testNullParam() throws IOException {
        Database db = new Database();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> db.getParamByTypeDeDoc(null, TypeDeDocGrouping.NO_TYPE));
        assertEquals("Erreur: Les paramètres ne peuvent pas être null ou la limite ne peut pas être 0", exception.getMessage());
    }

    @Test
    public void testNullGroupBy() throws IOException {
        Database db = new Database();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> db.getParamByTypeDeDoc(ParametreType.LANGUE, null));
        assertEquals("Erreur: Les paramètres ne peuvent pas être null ou la limite ne peut pas être 0", exception.getMessage());
    }

    @Test
    public void testNoNetwork() throws IOException {
        Database db = new Database();
        Exception exception = assertThrows(IOException.class, () -> db.getParamByTypeDeDoc(ParametreType.LANGUE, TypeDeDocGrouping.NO_TYPE));
        assertEquals("Hôte inconnu (data.mongodb-api.com)", exception.getMessage());
    }

    /*
    @Test
    public void testGetLanguagesManuelle() throws IOException {
        List<Parametre> expectedLanguages = new ArrayList<>();
        Database db = new Database();

        expectedLanguages.add(new Parametre());
        expectedLanguages.add(new Parametre("Anglais", "en"));
        expectedLanguages.add(new Parametre("Arabe", "ar"));
        expectedLanguages.add(new Parametre("Chinois", "zh"));
        expectedLanguages.add(new Parametre("Espagnol", "es"));
        expectedLanguages.add(new Parametre("Français", "fr"));
        expectedLanguages.add(new Parametre("Italien", "it"));
        expectedLanguages.add(new Parametre("Japonais", "ja"));
        expectedLanguages.add(new Parametre("Néerlandais", "nl"));
        expectedLanguages.add(new Parametre("Polonais", "pl"));
        expectedLanguages.add(new Parametre("Portugais", "pt"));
        expectedLanguages.add(new Parametre("Russe", "ru"));

        List<Parametre> actualLanguages = db.getLanguages();

        Assertions.assertEquals(expectedLanguages, actualLanguages);
    }*/

 }