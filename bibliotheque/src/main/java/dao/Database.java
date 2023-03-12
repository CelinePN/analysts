package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import modele.parametre.Parametre;
import modele.parametre.ParametreWrapper;
import modele.parametre.TypesParametre;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

import static modele.parametre.TypesParametre.*;

public class Database {

    OkHttpClient client = new OkHttpClient().newBuilder().build();
    MediaType mediaType = MediaType.parse("application/json");

    public Request getRequest(String val){
        RequestBody body = RequestBody.create("{\n" +
                "      \"dataSource\": \"Cluster0\",\n" +
                "      \"database\": \"book_analysts\",\n" +
                "      \"collection\": \"bibliotheques_paris\",\n" +
                "      \"pipeline\": [ \n" +
                "          {\n" +
                "            \"$group\": { \n" +
                "                \n" +
                "                  \"_id\": \"$" + val +"\",\n" +
                "                  \"count\" : { \"$sum\": 1 },\n" +
                "                  \"total_prets\" : { \"$sum\": \"$Nombre de prêts total\"},\n" +
                "                  \"total_exemplaires\" : { \"$sum\": \"$Nombre d'exemplaires\"}\n" +
                "               }\n" +
                "          },\n" +
                "          {\n" +
                "              \"$sort\": { \"_id\": 1 }\n" +
                "          }\n" +
                "      ]\n" +
                "  }", mediaType);
        return new Request.Builder()
                .url("https://data.mongodb-api.com/app/data-moehb/endpoint/data/v1/action/aggregate")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Access-Control-Request-Headers", "*")
                .addHeader("api-key", "xALvC4U1PdzK3K5y48tsvdpQar51gpnLLmKiNPQU4t2wOt11TqbyQ1mAabx8wAi6")
                .build();
    }

    public void getAllDocuments() throws IOException {

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

    public void getLanguages() throws IOException {

        Response response = client.newCall(getRequest("Langue")).execute();
        String jsonData = response.body().string(); // Store response body in a variable
        serializeParam(jsonData, LANGUE);
        response.close();
    }


     public void getTypdeDoc() throws IOException {

        Response response = client.newCall(getRequest("Type de document")).execute();
        String jsonData = response.body().string(); // Store response body in a variable
        serializeParam(jsonData, TYPE_DE_DOC);
        response.close();
  }

    public void getAuteur() throws IOException {
        Response response = client.newCall(getRequest("Auteur Nom")).execute();
        String jsonData = response.body().string(); // Store response body in a variable
        serializeParam(jsonData, AUTEUR);
        response.close();
    }

    public void getCategorie() throws IOException {

        Response response = client.newCall(getRequest("Catégorie statistique 1")).execute();
        String jsonData = response.body().string(); // Store response body in a variable
        serializeParam(jsonData, CATEGORIE);
        response.close();    }

    public void getNBTypDoc() throws IOException {
        Response response = client.newCall(getRequest("Catégorie statistique 1")).execute();
        String jsonData = response.body().string(); // Store response body in a variable
        serializeParam(jsonData, CATEGORIE);
        response.close();    }

    public void serializeParam(String json, TypesParametre type) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        ParametreWrapper wrapper = objectMapper.readValue(json, ParametreWrapper.class);
        List<Parametre> parametres = wrapper.getParametres();
        for (int i=0; i<parametres.size(); i++){
            parametres.get(i).setType_param(type);
        }
        String nom = parametres.get(0).getNom();
        int count = parametres.get(0).getCount();
        int totalPrets = parametres.get(0).getTotalPrets();
        int totalExemplaires = parametres.get(0).getTotalExemplaires();

        System.out.println(nom);
        System.out.println(count);
        System.out.println(totalPrets);
        System.out.println(totalExemplaires);
        System.out.println(parametres.get(0).getType_param());
    }
}

