//import com.squareup.okhttp.*;
import dao.Database;

import java.io.IOException;

//public class main ???
class Main {
    public static void main(String[] args) throws IOException {
        Database db = new Database();
        //db.getAllDocuments();
        //db.getLanguages();
        //System.out.println(db.getLanguages().get(0).getTotalExemplaires());
        //db.getTypdeDoc();
        //db.getAuteur();
        //System.out.println(db.getAuteur().get(40).getNom());
        //System.out.println(db.getAuteur().get(40).getCount());
        //db.getCategorie();
        db.getListTypesDoc();
    }
}