//import com.squareup.okhttp.*;
import okhttp3.*;

import java.io.IOException;

//public class main ???
class Main {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n    \"collection\":\"<COLLECTION_NAME>\",\n    \"database\":\"<DATABASE_NAME>\",\n    \"dataSource\":\"Cluster0\",\n    \"projection\": {\"_id\": 1}\n\n}");
        Request request = new Request.Builder()
                .url("https://data.mongodb-api.com/app/data-moehb/endpoint/data/v1/action/findOne")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Access-Control-Request-Headers", "*")
                .addHeader("api-key", "yE9iCCWZFK9NRbq0ei9IBu2H8HqP7LC2Wh448fawK0sx7dAtCkaQ8m716GeN3LoX")
                .build();
        Response response = client.newCall(request).execute();
    }
}