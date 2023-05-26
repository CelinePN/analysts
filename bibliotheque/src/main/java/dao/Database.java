package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import modele.parametre.Parametre;
import modele.parametre.ParametreType;
import modele.parametre.ParametreWrapper;
import modele.utils.Mode;
import modele.utils.TypeDeDocGrouping;
import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 *  <h1> Database </h1>
 *
 * <p>
 *     Cette classe est la couche d'acces aux donnees
 *     Elle permet de faire des requetes http sur notre BDD en passant par l'API de MongoDB Atlas
 * </p>
 *
 * @author Marine
 * @author Celine (pour le body de la requete)
 * @author Mathilde (pour la creation de l'API MongoDB et la recuperation de l'API KEY)
 * @version 1.0
 * @since 10/03/2023
 */
public class Database {
    private static final OkHttpClient client = new OkHttpClient().newBuilder().build();
    private static final MediaType mediaType = MediaType.parse("application/json");

    /**
     * Cette methode lance une requete http POST sur l'url de notre BDD pour recuperer des donnees avec aggregate
     * L'objectif est de recuperer une liste d'objet Parametre(ex: anglais) dont on
     * pourra recuperer la somme des exemplaires et la somme des prets
     * selon le Type de parametre choisi
     * et de les match par type de document
     *
     * Elle convertit notamment les types enums en string pour la creation du body de la requete
     * @author Mathilde & Celine
     * @return une List<Parametre> serialisee de la reponse en json de la requete
     * @param typeParam : le parametre choisi (ex: LANGUE)
     * @param typeDeDocEnum : le type de document sur lequel on veut se centrer uniquement (ex: LIVRE)
     * */
    public static List<Parametre> getParamByTypeDeDoc(ParametreType typeParam, TypeDeDocGrouping typeDeDocEnum, Mode sortBy) throws IOException {

        if(typeParam == null || typeDeDocEnum == null || sortBy == null){
            throw new IllegalArgumentException("Erreur: Les paramètres ne peuvent pas être null");
        }
        String jsonData="";
        try {
            RequestBody body = getRequestBody(typeParam.getString(), typeDeDocEnum.enumToString(), sortBy.getSortingString());
            Request request = new Request.Builder()
                    .url("https://data.mongodb-api.com/app/data-moehb/endpoint/data/v1/action/aggregate")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Access-Control-Request-Headers", "*")
                    .addHeader("api-key", "xALvC4U1PdzK3K5y48tsvdpQar51gpnLLmKiNPQU4t2wOt11TqbyQ1mAabx8wAi6")
                    .build();
            Response response = client.newCall(request).execute();
            if(response.body()!=null){
                jsonData = response.body().string();
            }
            else{
                throw new IOException("response body is null");
            }
            response.close();
            return serializeParam(jsonData, typeParam);
        }
        catch(IllegalArgumentException illegalArgumentException){
            System.out.println(illegalArgumentException.getMessage());
            return null;
        }
    }

    /**
     * Cette methode cree le body d'une requete correspondant aux params definis.
     * On fait un pretaitement avec de l'aggregation MongoDB afin de ne recuperer que les donnees qui nous interressent.
     * On aura une limite de 100 pour eviter de charger des donnees inutiles sachant qu'on affichera que les premiers. 
     * @author Celine & Marine
     * @param valGroup : valeur sur laquelle on va regrouper nos donnees (le type de parametre choisi)
     * @param typeDeDocGroupMatch : valeur avec laquelle on va match nos donnees (le type de document). Cette chaîne de caracteres inclus la liste des type de documents regroupe dans un type global (voir enum)
     * @param sort : valeur par ordre duquel on va trier les donnees selon le mode choisi (offre ou demande)
     * */
    public static RequestBody getRequestBody(String valGroup, String typeDeDocGroupMatch, String sort){
        String valMatch;
        if(!Objects.equals(typeDeDocGroupMatch, "")){
            valMatch = "           {\n" +
                    "              \"$match\": { \"$or\": ["+typeDeDocGroupMatch+"]}\n" +
                    "          },\n";
        }
        else{
            valMatch="";
        }
        return RequestBody.create("{\n" +
                "      \"dataSource\": \"Cluster0\",\n" +
                "      \"database\": \"book_analysts\",\n" +
                "      \"collection\": \"bibliotheques_paris\",\n" +
                "      \"pipeline\": [ \n" +
                "          {\n" +
                // tout ce dont on ne veut pas
                "              \"$match\": { \"$and\": [ { \""+valGroup+"\": {\"$ne\": null}}, { \""+valGroup+"\": {\"$ne\": \"sans\"}}, {\""+valGroup+"\": {\"$ne\": \"langue indéterminée\"}}]}\n" +
                "          },\n" +
                // match par type de document
                            valMatch +
                "          {\n" +
                "          \n" +
                "            \"$group\": { \n" +
                "                \n" +
                "                  \"_id\": \"$"+valGroup+"\",\n" +
                "                  \"count\" : { \"$sum\": 1 },\n" +
                "                  \"total_prets\" : { \"$sum\": \"$Nombre de prêt total\"},\n" +
                "                  \"total_exemplaires\" : { \"$sum\": \"$Nombre d'exemplaires\"}\n" +
                "               }\n" +
                "          },\n" +

                "          {\n" +
                "              \"$sort\": { \""+ sort + "\": -1 }\n" +
                "          },\n"        +

                "          {\n" +
                "              \"$limit\": 100 " +
                "          }\n"        +


                "\n" +
                "      ]\n" +
                "  }", mediaType);
    }

    /**
     * Cette methode associe en serialisant les donnees du json (elements de la liste recuperee) a une liste de parametres du modele
     * @author Marine
     * @param json : contenu de la liste recuperee par la requete en json
     * @param type : le type de parametre des parametres de la liste
     *
     * */
    public static List<Parametre> serializeParam(String json, ParametreType type) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        ParametreWrapper wrapper = objectMapper.readValue(json, ParametreWrapper.class);
        List<Parametre> parametres = wrapper.getParametres();
        if(parametres.isEmpty()){
            throw new IOException("liste vide");
        }
        for (Parametre parametre : parametres) {
            parametre.setType_param(type);
        }

        return parametres;
    }
}

