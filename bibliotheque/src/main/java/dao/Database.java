package dao;

import okhttp3.*;

import java.io.IOException;

public class Database {

    public void getAllDocuments() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("{\n    \"collection\":\"bibliotheques_paris\",\n    \"database\":\"book_analysts\",\n    \"dataSource\":\"Cluster0\"\n}", mediaType);
        Request request = new Request.Builder()
        .url("https://data.mongodb-api.com/app/data-moehb/endpoint/data/v1/action/find")
        .method("POST", body)
        .addHeader("Content-Type", "application/json")
        .addHeader("Access-Control-Request-Headers", "*")
        .addHeader("api-key", "xALvC4U1PdzK3K5y48tsvdpQar51gpnLLmKiNPQU4t2wOt11TqbyQ1mAabx8wAi6")
        .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    public void getLanguages() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("{\n" +
                "      \"dataSource\": \"Cluster0\",\n" +
                "      \"database\": \"book_analysts\",\n" +
                "      \"collection\": \"bibliotheques_paris\",\n" +
                "      \"pipeline\": [\n" +
                "          {\n" +
                "            \"$group\": {\n" +
                "                \"_id\": \"$Langue\",\n" +
                "                 \"Nombre d'exemplaires\": { \"$sum\": 1 }\n" +
                "            }\n" +
                "         }\n" +
                "      ]\n" +
                "  }", mediaType);
        Request request = new Request.Builder()
                .url("https://data.mongodb-api.com/app/data-moehb/endpoint/data/v1/action/aggregate")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Access-Control-Request-Headers", "*")
                .addHeader("api-key", "xALvC4U1PdzK3K5y48tsvdpQar51gpnLLmKiNPQU4t2wOt11TqbyQ1mAabx8wAi6")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }


     public void getTypdeDoc() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("{\n" +
                "      \"dataSource\": \"Cluster0\",\n" +
                "      \"database\": \"book_analysts\",\n" +
                "      \"collection\": \"bibliotheques_paris\",\n" +
                "      \"pipeline\": [\n" +
                "          {\n" +
                "            \"$group\": {\n" +
                "                \"_id\": \"$Type de document\",\n" +
                "                 \"Nombre d'exemplaires\": { \"$sum\": 1 }\n" +
                "            }\n" +
                "         }\n" +
                "      ]\n" +
                "  }", mediaType);
        Request request = new Request.Builder()
                .url("https://data.mongodb-api.com/app/data-moehb/endpoint/data/v1/action/aggregate")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Access-Control-Request-Headers", "*")
                .addHeader("api-key", "xALvC4U1PdzK3K5y48tsvdpQar51gpnLLmKiNPQU4t2wOt11TqbyQ1mAabx8wAi6")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
  }

    public void getAuteur() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("{\n" +
                "      \"dataSource\": \"Cluster0\",\n" +
                "      \"database\": \"book_analysts\",\n" +
                "      \"collection\": \"bibliotheques_paris\",\n" +
                "      \"pipeline\": [\n" +
                "          {\n" +
                "            \"$group\": {\n" +
                "                \"_id\": \"$Auteur Nom\",\n" +
                "                 \"Nom de l'Auteur\": { \"$sum\": 1 }\n" +
                "            }\n" +
                "         }\n" +
                "      ]\n" +
                "  }", mediaType);
        Request request = new Request.Builder()
                .url("https://data.mongodb-api.com/app/data-moehb/endpoint/data/v1/action/aggregate")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Access-Control-Request-Headers", "*")
                .addHeader("api-key", "xALvC4U1PdzK3K5y48tsvdpQar51gpnLLmKiNPQU4t2wOt11TqbyQ1mAabx8wAi6")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    public void getCategorie() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("{\n" +
                "      \"dataSource\": \"Cluster0\",\n" +
                "      \"database\": \"book_analysts\",\n" +
                "      \"collection\": \"bibliotheques_paris\",\n" +
                "      \"pipeline\": [\n" +
                "          {\n" +
                "            \"$group\": {\n" +
                "                \"_id\": \"$Catégorie statistique 1\",\n" +
                "                 \"Nombre dans cette catégorie\": { \"$sum\": 1 }\n" +
                "            }\n" +
                "         }\n" +
                "      ]\n" +
                "  }", mediaType);
        Request request = new Request.Builder()
                .url("https://data.mongodb-api.com/app/data-moehb/endpoint/data/v1/action/aggregate")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Access-Control-Request-Headers", "*")
                .addHeader("api-key", "xALvC4U1PdzK3K5y48tsvdpQar51gpnLLmKiNPQU4t2wOt11TqbyQ1mAabx8wAi6")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    public void getNBTypDoc() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("{\n" +
                "      \"dataSource\": \"Cluster0\",\n" +
                "      \"database\": \"book_analysts\",\n" +
                "      \"collection\": \"bibliotheques_paris\",\n" +
                "      \"pipeline\": [\n" +
                "          {\n" +
                "            \"$group\": {\n" +
                "                \"_id\": \"$Catégorie statistique 1\",\n" +
                "                 \"Nombre dans cette catégorie\": { \"$sum\": 1 }\n" +
                "            }\n" +
                "         }\n" +
                "      ]\n" +
                "  }", mediaType);
        Request request = new Request.Builder()
                .url("https://data.mongodb-api.com/app/data-moehb/endpoint/data/v1/action/aggregate")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Access-Control-Request-Headers", "*")
                .addHeader("api-key", "xALvC4U1PdzK3K5y48tsvdpQar51gpnLLmKiNPQU4t2wOt11TqbyQ1mAabx8wAi6")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}

