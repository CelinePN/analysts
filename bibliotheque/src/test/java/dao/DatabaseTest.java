package dao;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    @Test
    public void testConstructeur(){
        Database d = new Database();
        assertEquals(new Database(), d, "Database");
    }

}
