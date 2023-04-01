package dao;
import modele.utils.SortBy;
import modele.parametre.ParametreType;
import org.junit.jupiter.api.Test;
import okhttp3.*;
import java.io.IOException;


import static modele.utils.TypeDeDocGrouping.NO_TYPE;
import static org.junit.jupiter.api.Assertions.*;

/**
 *  <h1> Test database </h1>
 *
 * <p>
 *     Cette classe permet de tester la classe database et les données récupérées
 * </p>
 *
 * @Author: Céline et Alice
 * @Version: 2.0
 * @since: 01/04/2023
 */

public class DatabaseTest {

    // Variables initialisées correctement
    /**
     * Permet de tester le constructeur
     * @return un booléen en fonction de si les deux parties sont identiques ou non
     **/
    @Test
    public void testConstructeur() {
        Database db = new Database();
        assertNotNull(db.client);
        assertNotNull(db.mediaType);
        assertEquals(db.mediaType, MediaType.parse("application/json"));
    }

    // Vérifie si les méthodes ne lèvent pas d'exceptions
    // si les requêtes sont correctement construites
    /*@Test
    public void testGetRequest() {
        Database db = new Database();
        RequestBody request = db.getRequestBody("test", "test", "total_exemplaires", "10");
        assertNotNull(request);
        assertEquals("https://data.mongodb-api.com/app/data-moehb/endpoint/data/v1/action/aggregate", request.url().toString());
        assertEquals("POST", request.method());
        assertEquals("application/json", request.header("Content-Type"));
        assertEquals("*", request.header("Access-Control-Request-Headers"));
        assertEquals("xALvC4U1PdzK3K5y48tsvdpQar51gpnLLmKiNPQU4t2wOt11TqbyQ1mAabx8wAi6", request.header("api-key"));
        assertNotNull(request.body());
    }*/

    @Test
    public void testGetLanguages() {
        Database db = new Database();
        assertDoesNotThrow(() -> db.getParamByTypeDeDoc(ParametreType.LANGUE, NO_TYPE, SortBy.EXEMPLAIRES, 7));
    }

    @Test
    public void testGetLanguagesNombre() throws IOException {
        Database db = new Database();
        //compter pour français?
        assertEquals(14, db.getParamByTypeDeDoc(ParametreType.LANGUE, NO_TYPE, SortBy.EXEMPLAIRES, 7).get(0).getTotalExemplaires());
        assertEquals(14, db.getParamByTypeDeDoc(ParametreType.LANGUE, NO_TYPE, SortBy.EXEMPLAIRES, 7).get(0).getTotalPrets());
        assertEquals(14, db.getParamByTypeDeDoc(ParametreType.LANGUE, NO_TYPE, SortBy.EXEMPLAIRES, 7).get(0).getCount());
    }

   /* @Test
    public void testGetTypeDeDoc() {
        Database db = new Database();
        assertDoesNotThrow(() -> db.getByTypeDeDoc());
    }

    @Test
    public void testGetTypeDeDocNombre() throws IOException {
        Database db = new Database();
        assertEquals(45, db.getByTypeDeDoc().get(1).getCount());
    }

    @Test
    public void testGetAuteur() {
        Database db = new Database();
       // assertDoesNotThrow(() -> db.getAuteur());
    }

    @Test
    public void testGetAuteurNombre() throws IOException {
        Database db = new Database();
        //assertEquals(2, db.getAuteur().get(40).getCount());
    }

    @Test
    public void testGetCategorie() {
        Database db = new Database();
       // assertDoesNotThrow(() -> db.getCategorie());
    }

    @Test
    public void testGetCategorieNombre() throws IOException {
        Database db = new Database();
        //assertEquals(745, db.getCategorie().get(1).getCount());
    }

    /*@Test
    public void testGetListTypesDoc() {
        Database db = new Database();
        assertDoesNotThrow(() -> db.getListTypesDoc());
    }*/

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
    }


/*
    @Test
    public void testConstructeur(){
        Database d = new Database();
        assertEquals(new Database(), d, "Database");
    }

    @Test
    public void testgetLanguage() throws IOException {
        Database d = new Database();
        List<Parametre> l = d.getLanguages();
        assertEquals(new Database().getLanguages(), l, "langue");
    }
*/
}
