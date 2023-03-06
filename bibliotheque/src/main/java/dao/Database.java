package dao;

import okhttp3.*;

import java.io.IOException;

public class Database {

    public void createDb() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("{\n    \"collection\":\"bibliotheques_paris\",\n    \"database\":\"book_analysts\",\n    \"dataSource\":\"Cluster0\"\n}", mediaType);
        Request request = new Request.Builder()
        .url("https://data.mongodb-api.com/app/data-moehb/endpoint/data/v1/action/findOne")
        .method("POST", body)
        .addHeader("Content-Type", "application/json")
        .addHeader("Access-Control-Request-Headers", "*")
        .addHeader("api-key", "xALvC4U1PdzK3K5y48tsvdpQar51gpnLLmKiNPQU4t2wOt11TqbyQ1mAabx8wAi6")
        .addHeader("Accept", "application/ejson")
        .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}

