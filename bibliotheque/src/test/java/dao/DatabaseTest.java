package dao;

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
    /*@Test
    public void testConstructeur() {
        Database db = new Database();
        assertNotNull(db.client);
        assertNotNull(db.mediaType);
        assertEquals(db.mediaType, MediaType.parse("application/json"));
    }*/

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

    /*@Test
    public void testGetLanguages() {
        Database db = new Database();
        assertDoesNotThrow(() -> db.getParamByTypeDeDoc(ParametreType.LANGUE, TypeDeDocGrouping.NO_TYPE));
    }

    @Test
    public void testGetLanguagesNombre() throws IOException {
        Database db = new Database();
        //compter pour français?
        //assertEquals(14, db.getParamByTypeDeDoc(ParametreType.LANGUE, NO_TYPE).get(0).getTotalExemplaires());
        //assertEquals(14, db.getParamByTypeDeDoc(ParametreType.LANGUE, NO_TYPE).get(0).getTotalPrets());
        assertEquals(67, db.getParamByTypeDeDoc(ParametreType.LANGUE, TypeDeDocGrouping.NO_TYPE).get(0).getCount());
    }

    @Test
    public void testGetAuteurNombre() throws IOException {
        Database db = new Database();
        assertEquals(1, db.getParamByTypeDeDoc(ParametreType.AUTEUR, TypeDeDocGrouping.LIVRES).get(0).getCount());
    }

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

    @Test
    public void testGetLangueLivre() throws IOException {
        Database db = new Database();
        assertEquals(2447, db.getParamByTypeDeDoc(ParametreType.LANGUE, TypeDeDocGrouping.LIVRES).get(0).getCount());
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