package dao;
import modele.parametre.Parametre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import okhttp3.*;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


import static modele.parametre.TypesParametre.LANGUE;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    // Variables initialisées correctement
    @Test
    public void testConstructeur() {
        Database db = new Database();
        assertNotNull(db.client);
        assertNotNull(db.mediaType);
        //assertTrue(db.client instanceof OkHttpClient);
        assertEquals(db.mediaType, MediaType.parse("application/json"));
    }

    // Vérifie si les méthodes ne lèvent pas d'exceptions
    // si les requêtes sont correctement construites
    @Test
    public void testGetRequest() {
        Database db = new Database();
        Request request = db.getRequest("test");
        assertNotNull(request);
        assertEquals("https://data.mongodb-api.com/app/data-moehb/endpoint/data/v1/action/aggregate", request.url().toString());
        assertEquals("POST", request.method());
        assertEquals("application/json", request.header("Content-Type"));
        assertEquals("*", request.header("Access-Control-Request-Headers"));
        assertEquals("xALvC4U1PdzK3K5y48tsvdpQar51gpnLLmKiNPQU4t2wOt11TqbyQ1mAabx8wAi6", request.header("api-key"));
        assertNotNull(request.body());
    }

    @Test
    public void testGetAllDocuments() {
        Database db = new Database();
        assertDoesNotThrow(() -> db.getAllDocuments());
    }

    @Test
    public void testGetLanguages() {
        Database db = new Database();
        assertDoesNotThrow(() -> db.getLanguages());
    }

    @Test
    public void testGetLanguagesNombre() throws IOException {
        Database db = new Database();
        assertEquals(14, db.getLanguages().get(1).getCount());
    }

    @Test
    public void testGetTypeDeDoc() {
        Database db = new Database();
        assertDoesNotThrow(() -> db.getTypeDeDoc());
    }

    @Test
    public void testGetTypeDeDocNombre() throws IOException {
        Database db = new Database();
        assertEquals(45, db.getTypeDeDoc().get(1).getCount());
    }

    @Test
    public void testGetAuteur() {
        Database db = new Database();
        assertDoesNotThrow(() -> db.getAuteur());
    }

    @Test
    public void testGetAuteurNombre() throws IOException {
        Database db = new Database();
        assertEquals(2, db.getAuteur().get(40).getCount());
    }

    @Test
    public void testGetCategorie() {
        Database db = new Database();
        assertDoesNotThrow(() -> db.getCategorie());
    }

    @Test
    public void testGetCategorieNombre() throws IOException {
        Database db = new Database();
        assertEquals(745, db.getCategorie().get(1).getCount());
    }

    @Test
    public void testGetListTypesDoc() {
        Database db = new Database();
        assertDoesNotThrow(() -> db.getListTypesDoc());
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
