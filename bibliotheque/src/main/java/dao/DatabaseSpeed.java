package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import modele.utils.TypeDeDocGrouping;
import modele.parametre.Parametre;
import modele.parametre.ParametreWrapper;
import modele.parametre.ParametreType;
import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 *  <h1> Database </h1>
 *
 * <p>
 *     Cette classe est la couche d'accès aux données
 *     Elle permet de faire des requêtes http sur notre BDD en passant par l'API de MongoDB Atlas
 * </p>
 *
 * @Author: Marine
 * @Version: 1.0
 * @since: 10/03/2023
 */
public class DatabaseSpeed {
    OkHttpClient client;
    MediaType mediaType;

    /**
     * Constructeur
     * A l'instanciation de cette classe, on crée un client Http et on définit le mediaType en json
     * */
    public DatabaseSpeed()  {
         client = new OkHttpClient().newBuilder().build();
         mediaType = MediaType.parse("application/json");
    }

    /**
     * Cette méthode lance une requête http POST sur l'url de notre BDD pour récupérer des données avec aggregate
     * L'objectif est de récupérer une liste d'objet Paramètre(ex: anglais) dont on
     * pourra récupérer la somme des exemplaires et la somme des prêts
     * selon le Type de paramètre choisi
     * et de les match par type de document
     *
     * Elle convertie notamment les types enums en string pour la création du body de la requête
     *
     * @return une List<Parametre> serialisée de la réponse en json de la requête
     * @param typeParam : le paramètre choisi (ex: LANGUE)
     * @param typeDeDocEnum : le type de document sur lequel on veut se centrer uniquement (ex: LIVRE)
     * */
    public List<Parametre> getParamByTypeDeDoc(ParametreType typeParam, TypeDeDocGrouping typeDeDocEnum) throws IOException {

        if(typeParam == null || typeDeDocEnum == null ){
            throw new IllegalArgumentException("Erreur: Les paramètres ne peuvent pas être null ou la limite ne peut pas être 0");
        }
        String jsonData;
        try {
            RequestBody body = getRequestBody(typeParam.getString(), typeDeDocEnum.enumToString());
            Request request = new Request.Builder()
                    .url("https://data.mongodb-api.com/app/data-moehb/endpoint/data/v1/action/aggregate")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Access-Control-Request-Headers", "*")
                    .addHeader("api-key", "xALvC4U1PdzK3K5y48tsvdpQar51gpnLLmKiNPQU4t2wOt11TqbyQ1mAabx8wAi6")
                    .build();
            Response response = client.newCall(request).execute();
            jsonData = response.body().string(); // Store response body in a variable
            //System.out.println(jsonData);
            response.close();
            return serializeParam(jsonData, typeParam);
        }

        catch(IllegalArgumentException illegalArgumentException){

            System.out.println(illegalArgumentException.getMessage());
            return null;
        }
    }

    /**
     * Cette méthode crée le body d'une requête correspondant aux params définis
     *
     * @param valGroup : valeur sur laquelle on va regrouper nos données (le type de paramètre choisi)
     * @param typeDeDocGroupMatch : valeur avec laquelle on va match nos données (le type de document). Cette chaîne de caractères inclus la liste des type de documents regroupé dans un type global (voir enum)
     * */
    public RequestBody getRequestBody(String valGroup, String typeDeDocGroupMatch){
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
                "          }\n" +
                /*
                "          {\n" +
                "              \"$sort\": { \""+ "_id" + "\": 1 }\n" +
                "          }\n"        +

                "          {\n" +
                "              \"$limit\": 100 " +
                "          }\n"        +
                */

                "\n" +
                "      ]\n" +
                "  }", mediaType);
    }

    public List<Parametre> serializeParam(String json, ParametreType type) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        ParametreWrapper wrapper = objectMapper.readValue(json, ParametreWrapper.class);
        List<Parametre> parametres = wrapper.getParametres();
        for (int i=0; i<parametres.size(); i++){
            parametres.get(i).setType_param(type);
        }

        return parametres;
    }
}

