package modele;

import dao.Database;
import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.utils.Mode;
import modele.utils.TypeDeDocGrouping;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  <h1> Test Cache </h1>
 *
 * <p>
 *     Cette classe permet de tester la classe Cache et les donnees recuperees
 * </p>
 *
 * @author : Celine
 * @version : 1.0
 * @since : 15/04/2023
 */

public class CacheTest {

    @Test
    public void testGetLimitOverListSize() {
        assertDoesNotThrow(() -> Cache.get(ParametreType.LANGUE, TypeDeDocGrouping.NO_TYPE, Mode.EMPRUNTS,Integer.MAX_VALUE));
        assertEquals(new ArrayList<>() , Cache.get(ParametreType.LANGUE, TypeDeDocGrouping.NO_TYPE, Mode.EMPRUNTS,5));
    }

    @Test
    public void testGetOnEmptyCache() {
        Cache.clearCache();
        assertDoesNotThrow(() -> Cache.get(ParametreType.LANGUE, TypeDeDocGrouping.NO_TYPE, Mode.EMPRUNTS,5));
        assertEquals(new ArrayList<>() , Cache.get(ParametreType.LANGUE, TypeDeDocGrouping.NO_TYPE, Mode.EMPRUNTS,5));
    }

    @Test
    public void testClearCache() throws IOException {
        Parametre p = new Parametre();
        p.setType_param(ParametreType.AUTEUR);
        p.setNom("Petersson");
        p.setTotalExemplaires(17);
        p.setTotalPrets(2);
        List<Parametre> lEx = new ArrayList<>();
        List<Parametre> lEm = new ArrayList<>();
        lEx.add(p);
        lEm.add(p);
        Cache.clearCache();
        assertEquals(new ArrayList<>(), Cache.get(ParametreType.AUTEUR, TypeDeDocGrouping.LIVRES, Mode.EXEMPLAIRES, 1));
    }

    @Test
    public void testPutCacheNbExemplaires() throws IOException {
        Cache.clearCache();
        Parametre p = new Parametre();
        p.setType_param(ParametreType.AUTEUR);
        p.setNom("Petersson");
        p.setTotalExemplaires(17);
        p.setTotalPrets(2);
        List<Parametre> lEx = new ArrayList<>();
        List<Parametre> lEm = new ArrayList<>();
        lEx.add(p);
        lEm.add(p);
        Cache.put(ParametreType.AUTEUR, TypeDeDocGrouping.LIVRES, lEx, lEm);
        assertEquals(17, Cache.get(ParametreType.AUTEUR, TypeDeDocGrouping.LIVRES, Mode.EXEMPLAIRES, 1).get(0).getTotalExemplaires());
    }

    @Test
    public void testPutCacheAuteur() throws IOException {
        Cache.clearCache();
        Parametre p = new Parametre();
        p.setType_param(ParametreType.AUTEUR);
        p.setNom("Petersson");
        p.setTotalExemplaires(17);
        p.setTotalPrets(2);
        List<Parametre> lEx = new ArrayList<>();
        List<Parametre> lEm = new ArrayList<>();
        lEx.add(p);
        lEm.add(p);
        Cache.put(ParametreType.AUTEUR, TypeDeDocGrouping.LIVRES, lEx, lEm);
        assertEquals("Petersson", Cache.get(ParametreType.AUTEUR, TypeDeDocGrouping.LIVRES, Mode.EXEMPLAIRES, 1).get(0).getNom());
    }

    @Test
    public void testPutCacheType() throws IOException {
        Cache.clearCache();
        Parametre p = new Parametre();
        p.setType_param(ParametreType.AUTEUR);
        p.setNom("Petersson");
        p.setTotalExemplaires(17);
        p.setTotalPrets(2);
        List<Parametre> lEx = new ArrayList<>();
        List<Parametre> lEm = new ArrayList<>();
        lEx.add(p);
        lEm.add(p);
        Cache.put(ParametreType.AUTEUR, TypeDeDocGrouping.LIVRES, lEx, lEm);
        assertEquals(ParametreType.AUTEUR, Cache.get(ParametreType.AUTEUR, TypeDeDocGrouping.LIVRES, Mode.EXEMPLAIRES, 1).get(0).getType_param());
    }

    @Test
    public void testPutCacheEmprunts() throws IOException {
        Cache.clearCache();
        Parametre p = new Parametre();
        p.setType_param(ParametreType.AUTEUR);
        p.setNom("Petersson");
        p.setTotalExemplaires(17);
        p.setTotalPrets(2);
        List<Parametre> lEx = new ArrayList<>();
        List<Parametre> lEm = new ArrayList<>();
        lEx.add(p);
        lEm.add(p);
        Cache.put(ParametreType.AUTEUR, TypeDeDocGrouping.LIVRES, lEx, lEm);
        assertEquals(2, Cache.get(ParametreType.AUTEUR, TypeDeDocGrouping.LIVRES, Mode.EMPRUNTS, 1).get(0).getTotalPrets());
    }

    @Test
    public void testPutVide() throws IOException {
        Cache.clearCache();
        Parametre p = new Parametre();
        List<Parametre> lEx = new ArrayList<>();
        List<Parametre> lEm = new ArrayList<>();
        lEx.add(p);
        lEm.add(p);
        Cache.put(ParametreType.AUTEUR, TypeDeDocGrouping.LIVRES, lEx, lEm);
        assertEquals(0, Cache.get(ParametreType.AUTEUR, TypeDeDocGrouping.LIVRES, Mode.EMPRUNTS, 1).get(0).getTotalPrets());
    }

    @Test
    public void testGetTypeDiff() throws IOException {
        Cache.clearCache();
        Parametre p = new Parametre();
        List<Parametre> lEx = new ArrayList<>();
        List<Parametre> lEm = new ArrayList<>();
        lEx.add(p);
        lEm.add(p);
        Cache.put(ParametreType.AUTEUR, TypeDeDocGrouping.LIVRES, lEx, lEm);
        assertEquals(new ArrayList<>(), Cache.get(ParametreType.LANGUE, TypeDeDocGrouping.LIVRES, Mode.EMPRUNTS, 1));
    }

    @Test
    public void testGetTypeNull() throws IOException {
        Cache.clearCache();
        Parametre p = new Parametre();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Cache.get(null, TypeDeDocGrouping.LIVRES, Mode.EMPRUNTS, 1));
        assertEquals("Erreur: Les parametres ne peuvent pas être null", exception.getMessage());
    }

    @Test
    public void testGetDocNull() throws IOException {
        Cache.clearCache();
        Parametre p = new Parametre();
        List<Parametre> lEx = new ArrayList<>();
        List<Parametre> lEm = new ArrayList<>();
        lEx.add(p);
        lEm.add(p);
        Cache.put(ParametreType.AUTEUR, TypeDeDocGrouping.LIVRES, lEx, lEm);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Cache.get(ParametreType.AUTEUR, null, Mode.EMPRUNTS, 1));
        assertEquals("Erreur: Les parametres ne peuvent pas être null", exception.getMessage());
    }
    @Test
    public void testGetModeNull() throws IOException {
        Cache.clearCache();
        Parametre p = new Parametre();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Cache.get(ParametreType.AUTEUR, TypeDeDocGrouping.LIVRES, null, 1));
        assertEquals("Erreur: Les parametres ne peuvent pas être null", exception.getMessage());
    }

    @Test
    public void testGetLimitNull() throws IOException {
        Cache.clearCache();
        Parametre p = new Parametre();
        /*Exception exception = assertThrows(IllegalArgumentException.class, () -> Cache.get(ParametreType.AUTEUR, TypeDeDocGrouping.LIVRES, Mode.EMPRUNTS, 0));
        assertEquals("Erreur: Les parametres ne peuvent pas être null", exception.getMessage());*/
        assertEquals(new ArrayList<>(), Cache.get(ParametreType.LANGUE, TypeDeDocGrouping.LIVRES, Mode.EMPRUNTS, 0));
    }
 }