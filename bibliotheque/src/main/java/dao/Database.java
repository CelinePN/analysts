package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import modele.utils.SortBy;
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
public class Database {
    OkHttpClient client;
    MediaType mediaType;

    /**
     * Constructeur
     * A l'instanciation de cette classe, on crée un client Http et on définit le mediaType en json
     * */
    public Database()  {
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
     * @param sortBy: l'ordre dans lequel on veut trier notre liste
     * @param limit: le nombre de paramètre que l'on veut récupérer au maximum dans notre liste
     * */
    public List<Parametre> getParamByTypeDeDoc(ParametreType typeParam, TypeDeDocGrouping typeDeDocEnum, SortBy sortBy, int limit) throws IOException {

        RequestBody body = getRequestBody(typeParam.getString(), typeDeDocEnum.enumToString(), sortBy.getSortingString(), String.valueOf(limit));

        Request request = new Request.Builder()
                .url("https://data.mongodb-api.com/app/data-moehb/endpoint/data/v1/action/aggregate")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Access-Control-Request-Headers", "*")
                .addHeader("api-key", "xALvC4U1PdzK3K5y48tsvdpQar51gpnLLmKiNPQU4t2wOt11TqbyQ1mAabx8wAi6")
                .build();
        Response response = client.newCall(request).execute();
        String jsonData = response.body().string(); // Store response body in a variable
        //System.out.println(jsonData);
        response.close();
        return serializeParam(jsonData, typeParam);
    }

    /**
     * Cette méthode crée le body d'une requête correspondant aux params définis
     *
     * @param valGroup : valeur sur laquelle on va regrouper nos données (le type de paramètre choisi)
     * @param typeDeDocGroupMatch : valeur avec laquelle on va match nos données (le type de document). Cette chaîne de caractères inclus la liste des type de documents regroupé dans un type global (voir enum)
     * @param  sortBy : la manière dont on trie les données
     * @param  limit: le nombre maximum de données dans la liste
     * */
    public RequestBody getRequestBody(String valGroup, String typeDeDocGroupMatch, String sortBy, String limit){
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
                "              \"$sort\": { \""+sortBy+"\": -1 }\n" +
                "          },\n" +
                "          {\n" +
                "              \"$limit\": "+limit+"\n" +
                "          }\n" +
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

    //3 chaine de caracetre static final
    /*public Request getRequest(String valGroup, SortBy sortBy, int limit){
        //utilisé pour : langues, types de doc, (auteur.. ?)
        RequestBody body = RequestBody.create("{\n" +
                "      \"dataSource\": \"Cluster0\",\n" +
                "      \"database\": \"book_analysts\",\n" +
                "      \"collection\": \"bibliotheques_paris\",\n" +
                "      \"pipeline\": [ \n" +
            "               {\n" +
                "              \"$match\": { \"$and\": [ { \""+valGroup+"\": {\"$ne\": null}}, { \"\""+valGroup+"\"\": {\"$ne\": \"sans\"}}, {\"\""+valGroup+"\"\": {\"$ne\": \"langue indéterminée\"}}]}\n" +
                "          },"+
                "          {\n" +
                "            \"$group\": { \n" +
                "                \n" +
                "                  \"_id\": \"$" + valGroup +"\",\n" +
                "                  \"count\" : { \"$sum\": 1 },\n" +
                "                  \"total_prets\" : { \"$sum\": \"$Nombre de prêts total\"},\n" +
                "                  \"total_exemplaires\" : { \"$sum\": \"$Nombre d'exemplaires\"}\n" +
                "               }\n" +
                "          },\n" +
                "          {\n" +
                "              \"$sort\": { \""+sortBy+"\": -1 }\n" +
                "          },\n" +
                "          {\n" +
                "               \"$limit\": "+limit+"\n" +
                "          }"+
                "      ]\n" +
                "  }", mediaType);
        return new Request.Builder()
                .url("https://data.mongodb-api.com/app/data-moehb/endpoint/data/v1/action/aggregate")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Access-Control-Request-Headers", "*")
                .addHeader("api-key", "xALvC4U1PdzK3K5y48tsvdpQar51gpnLLmKiNPQU4t2wOt11TqbyQ1mAabx8wAi6")
                .build();
    }*/

    /*public void getAllDocuments() throws IOException {

        RequestBody body = RequestBody.create("{\n    \"collection\":\"bibliotheques_paris\",\n    \"database\":\"book_analysts\",\n    \"dataSource\":\"Cluster0\"\n}", mediaType);
        Request request = new Request.Builder()
        .url("https://data.mongodb-api.com/app/data-moehb/endpoint/data/v1/action/find")
        .method("POST", body)
        .addHeader("Content-Type", "application/json")
        .addHeader("Access-Control-Request-Headers", "*")
        .addHeader("api-key", "xALvC4U1PdzK3K5y48tsvdpQar51gpnLLmKiNPQU4t2wOt11TqbyQ1mAabx8wAi6")
        .build();
        Response response = client.newCall(request).execute();
    }

    public List<Parametre> getByLanguages() throws IOException {

        Response response = client.newCall(getRequestMatchBy("Langue", "", SortBy.EXEMPLAIRES, 7)).execute();
        String jsonData = response.body().string(); // Store response body in a variable
        //System.out.println(jsonData);
        response.close();
        List<Parametre> allLanguages = serializeParam(jsonData, LANGUE);
        List<Parametre> firstFiveLanguages = allLanguages.subList(0, Math.min(5, allLanguages.size()));
        return firstFiveLanguages;
    }


     public List<Parametre> getByTypeDeDoc(SortBy sortBy, int limit) throws IOException {
        // reste à faire la fonction tri pour pas tout récupérer mais trier dans les groupes
        Response response = client.newCall(getRequestMatchBy("Type de document", "", sortBy, limit)).execute();
        String jsonData = response.body().string(); // Store response body in a variable
         response.close();
         return serializeParam(jsonData, TYPE_DE_DOC);
    }

    public List<Parametre> getAuteurByTypeDeDoc(TypeDeDocEnum typeDeDocEnum, SortBy sortBy, int limit) throws IOException {
        //match par type de doc
        Response response = client.newCall(getRequestMatchBy("Auteur Nom", typeDeDocEnum.enumToString(), sortBy, limit )).execute();

        String jsonData = response.body().string(); // Store response body in a variable
        response.close();
        return serializeParam(jsonData, AUTEUR);
    }

    public List<Parametre> getGenreByTypeDeDoc(TypeDeDocEnum typeDeDocEnum, SortBy sortBy, int limit) throws IOException {
        //match par type de doc
        Response response = client.newCall(getRequestMatchBy("Catégorie statistique 1", typeDeDocEnum.enumToString(), sortBy, limit)).execute();
        String jsonData = response.body().string(); // Store response body in a variable
        //System.out.println(jsonData);
        response.close();
        return serializeParam(jsonData, GENRE);
    }*/


}

