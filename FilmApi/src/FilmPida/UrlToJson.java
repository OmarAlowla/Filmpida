package FilmPida;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UrlToJson {

    private final String response;

    public UrlToJson(String url) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url).build();

        try (Response httpResponse = client.newCall(request).execute()) {
            if (!httpResponse.isSuccessful()) {
                throw new IOException("Unexpected response code: " + httpResponse.code());
            }
            assert httpResponse.body() != null;
            response = httpResponse.body().string();
        } catch (IOException e) {
            throw new RuntimeException("Error making HTTP request", e);
        }
    }

    public String getResponse() {
        return response;
    }

    public static void main(String[] args) {
        // Example usage:
        String apiUrl = "https://jsonplaceholder.typicode.com/todos/1"; // Replace with your API URL
        UrlToJson urlToJson = new UrlToJson(apiUrl);

        // Get and print the JSON response
        String jsonResponse = urlToJson.getResponse();
        System.out.println("JSON Response:\n" + jsonResponse);
    }
}
