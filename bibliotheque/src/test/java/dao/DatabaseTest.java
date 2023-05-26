package dao;

import modele.utils.Mode;
import modele.parametre.ParametreType;
import modele.utils.TypeDeDocGrouping;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


/**
 *  <h1> Test database </h1>
 *
 * <p>
 *     Cette classe permet de tester la classe database et les donnees recuperees
 * </p>
 *
 * @Author: Celine
 * @Version: 3.0
 * @since: 23/04/2023
 */

public class DatabaseTest {
    /**
     * Les cinq prochains tests permettent de verifier les types de parametres ainsi que les groupes dans les cas des exemplaires
     * @return un booleen en fonction de si les deux parties sont identiques ou non
     **/
    @Test
    public void testGetLanguages() {
        assertDoesNotThrow(() -> Database.getParamByTypeDeDoc(ParametreType.LANGUE, TypeDeDocGrouping.NO_TYPE, Mode.EXEMPLAIRES));
    }

    @Test
    public void testGetLanguagesNombre() throws IOException {
        //compter pour français?
        //assertEquals(14, Database.getParamByTypeDeDoc(ParametreType.LANGUE, NO_TYPE).get(0).getTotalExemplaires());
        //assertEquals(14, Database.getParamByTypeDeDoc(ParametreType.LANGUE, NO_TYPE).get(0).getTotalPrets());
        //assertEquals(14, db.getParamByTypeDeDoc(ParametreType.LANGUE, NO_TYPE).get(0).getTotalExemplaires());
        //assertEquals(14, db.getParamByTypeDeDoc(ParametreType.LANGUE, NO_TYPE).get(0).getTotalPrets());
        assertEquals(559003, Database.getParamByTypeDeDoc(ParametreType.LANGUE, TypeDeDocGrouping.NO_TYPE, Mode.EXEMPLAIRES).get(0).getCount());
    }

    @Test
    public void testGetAuteurNombre() throws IOException {
        assertEquals(15, Database.getParamByTypeDeDoc(ParametreType.AUTEUR, TypeDeDocGrouping.JEUX, Mode.EXEMPLAIRES).get(0).getTotalExemplaires());
    }

    @Test
    public void testGetTypeNombre() throws IOException {
        assertEquals(339082, Database.getParamByTypeDeDoc(ParametreType.TYPE_DE_DOC, TypeDeDocGrouping.NO_TYPE, Mode.EXEMPLAIRES).get(0).getCount());
    }

    @Test
    public void testGetGenreNombre() throws IOException {
        assertEquals(408, Database.getParamByTypeDeDoc(ParametreType.GENRE, TypeDeDocGrouping.JEUX, Mode.EXEMPLAIRES).get(0).getTotalExemplaires());
    }

    /**
     * Permet de tester les emprunts
     * @return un booleen en fonction de si les deux parties sont identiques ou non
     **/
    @Test
    public void testGetLangueJeux() throws IOException {
        assertEquals(8873, Database.getParamByTypeDeDoc(ParametreType.LANGUE, TypeDeDocGrouping.JEUX, Mode.EMPRUNTS).get(0).getTotalPrets());
    }

    /**
     * Les trois prochains tests permettent de tester les cas où un parametre est null
     * @return un booleen en fonction de si les deux parties sont identiques ou non
     **/

    @Test
    public void testNullParam() throws IOException {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Database.getParamByTypeDeDoc(null, TypeDeDocGrouping.NO_TYPE, Mode.EXEMPLAIRES));
        assertEquals("Erreur: Les parametres ne peuvent pas être null", exception.getMessage());
    }

    @Test
    public void testNullGroupBy() throws IOException {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Database.getParamByTypeDeDoc(ParametreType.LANGUE, null, Mode.EXEMPLAIRES));
        assertEquals("Erreur: Les parametres ne peuvent pas être null", exception.getMessage());
    }

    @Test
    public void testNullSortBy() throws IOException {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Database.getParamByTypeDeDoc(ParametreType.LANGUE, TypeDeDocGrouping.NO_TYPE, null));
        assertEquals("Erreur: Les parametres ne peuvent pas être null", exception.getMessage());
    }

    /**
     * Permet de tester l'absence de reseau'
     * @return un booleen en fonction de si les deux parties sont identiques ou non
     **/

    @Test
    public void testNoNetwork() throws IOException {
        Exception exception = assertThrows(IOException.class, () -> Database.getParamByTypeDeDoc(ParametreType.LANGUE, TypeDeDocGrouping.NO_TYPE, Mode.EXEMPLAIRES));
        assertEquals("Hôte inconnu (data.mongoDatabase-api.com)", exception.getMessage());
    }

    /**
     * Permet de tester le constructeur
     * @return un booleen en fonction de si les deux parties sont identiques ou non
     **/
    /*@Test
    public void testConstructeur() {
        Database Database = new Database();
        assertNotNull(Database.client);
        assertNotNull(Database.mediaType);
        assertEquals(Database.mediaType, MediaType.parse("application/json"));
    }*/

    // Verifie si les methodes ne levent pas d'exceptions
    // si les requêtes sont correctement construites
    /*@Test
    public void testGetRequest() {
        Database Database = new Database();
        RequestBody request = Database.getRequestBody("test", "test", "total_exemplaires", "10");
        assertNotNull(request);
        assertEquals("https://data.mongoDatabase-api.com/app/data-moehb/endpoint/data/v1/action/aggregate", request.url().toString());
        assertEquals("POST", request.method());
        assertEquals("application/json", request.header("Content-Type"));
        assertEquals("*", request.header("Access-Control-Request-Headers"));
        assertEquals("xALvC4U1PdzK3K5y48tsvdpQar51gpnLLmKiNPQU4t2wOt11TqbyQ1mAabx8wAi6", request.header("api-key"));
        assertNotNull(request.body());
    }*/
 }