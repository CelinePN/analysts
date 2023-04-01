//import com.squareup.okhttp.*;
import dao.Database;
import modele.utils.SortBy;
import modele.utils.TypeDeDocGrouping;
import modele.parametre.Parametre;
import modele.parametre.ParametreType;

import java.io.IOException;
import java.util.List;

//public class main ???
class Main {
    public static void main(String[] args) throws IOException {
        Database db = new Database();
        List<Parametre> liste = db.getParamByTypeDeDoc(ParametreType.LANGUE, TypeDeDocGrouping.BD);
        for (Parametre param : liste){
            System.out.println("---");
            System.out.println(param.getCount());
            System.out.println(param.getNom());
            System.out.println(param.getTotalExemplaires());
            System.out.println(param.getTotalPrets());
            System.out.println(param.getType_param());
        }
    }
}