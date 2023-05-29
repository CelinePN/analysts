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
 * @author : Celine
 * @version : 3.0
 * @since : 23/04/2023
 */

public class DatabaseTest {
    /**
     * Les cinq prochains tests permettent de verifier les types de parametres ainsi que les groupes dans les cas des exemplaires
     **/
    @Test
    public void testGetLanguages() {
        assertDoesNotThrow(() -> Database.getParamByTypeDeDoc(ParametreType.LANGUE, TypeDeDocGrouping.NO_TYPE, Mode.EXEMPLAIRES));
    }

    @Test
    public void testGetLanguagesNombre() throws IOException {
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
     **/
    @Test
    public void testGetLangueJeux() throws IOException {
        assertEquals(8873, Database.getParamByTypeDeDoc(ParametreType.LANGUE, TypeDeDocGrouping.JEUX, Mode.EMPRUNTS).get(0).getTotalPrets());
    }

    /**
     * Les trois prochains tests permettent de tester les cas où un parametre est null
     **/

    @Test
    public void testNullParam() throws IOException {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Database.getParamByTypeDeDoc(null, TypeDeDocGrouping.NO_TYPE, Mode.EXEMPLAIRES));
        assertEquals("Erreur: Les paramètres ne peuvent pas être null", exception.getMessage());
    }

    @Test
    public void testNullGroupBy() throws IOException {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Database.getParamByTypeDeDoc(ParametreType.LANGUE, null, Mode.EXEMPLAIRES));
        assertEquals("Erreur: Les paramètres ne peuvent pas être null", exception.getMessage());
    }

    @Test
    public void testNullSortBy() throws IOException {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Database.getParamByTypeDeDoc(ParametreType.LANGUE, TypeDeDocGrouping.NO_TYPE, null));
        assertEquals("Erreur: Les paramètres ne peuvent pas être null", exception.getMessage());
    }

    /**
     * Permet de tester l'absence de reseau
     **/

    /*@Test
    public void testNoNetwork() throws IOException {
        Exception exception = assertThrows(IOException.class, () -> Database.getParamByTypeDeDoc(ParametreType.LANGUE, TypeDeDocGrouping.NO_TYPE, Mode.EXEMPLAIRES));
        assertEquals("Hôte inconnu (data.mongodb-api.com)", exception.getMessage());
    }*/

 }