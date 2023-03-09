//import com.squareup.okhttp.*;
import dao.Database;

import java.io.IOException;

//public class main ???
class Main {
    public static void main(String[] args) throws IOException {
        Database db = new Database();
        db.getAllDocuments();
    }
}