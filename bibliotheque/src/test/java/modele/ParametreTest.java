package modele;


import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParametreTest {
    /**
     *  <h1> Test des parametres </h1>
     *
     * <p>
     *     Cette classe permet de tester la classe parametre
     * </p>
     *
     * @author : Celine
     * @version : 1.0
     * @since : 25/05/2023
     */

    /**
     * Les cinq prochains tests permettent de verifier les getters et setters de la classe parametres
     * @return un booleen en fonction de si les deux parties sont identiques ou non
     **/
    @Test
    public void testSetGetNom() {
        Parametre p = new Parametre();
        p.setNom("Hello");
        assertEquals("Hello", p.getNom());
    }

    @Test
    public void testSetGetTypeParam() {
        Parametre p = new Parametre();
        p.setType_param(ParametreType.AUTEUR);
        assertEquals(ParametreType.AUTEUR, p.getType_param());
    }

    @Test
    public void testSetGetCount() {
        Parametre p = new Parametre();
        p.setCount(12);
        assertEquals(12, p.getCount());
    }

    @Test
    public void testSetGetTotalPrets() {
        Parametre p = new Parametre();
        p.setTotalPrets(45);
        assertEquals(45, p.getTotalPrets());
    }

    @Test
    public void testSetGetTotalExemplaires() {
        Parametre p = new Parametre();
        p.setTotalExemplaires(62);
        assertEquals(62, p.getTotalExemplaires());
    }
}


