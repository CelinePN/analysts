//import com.squareup.okhttp.*;
import dao.DatabaseSpeed;
import modele.utils.TypeDeDocGrouping;
import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import vue.frames.FirstScreenEco;

import java.io.IOException;
import java.util.List;

//public class main ???
class Main {
    public static void main(String[] args) throws IOException {
        /*DatabaseSpeed db = new DatabaseSpeed();
        List<Parametre> liste = db.getParamByTypeDeDoc(ParametreType.TYPE_DE_DOC, TypeDeDocGrouping.FILMS);
        for (Parametre param : liste){
            System.out.println("---");
            System.out.println(param.getCount());
            System.out.println(param.getNom());
            System.out.println(param.getTotalExemplaires());
            System.out.println(param.getTotalPrets());
            System.out.println(param.getType_param());
        }*/
        new FirstScreenEco();
    }
}