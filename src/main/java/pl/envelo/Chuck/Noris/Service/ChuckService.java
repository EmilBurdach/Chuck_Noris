package pl.envelo.Chuck.Noris.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import pl.envelo.Chuck.Noris.Model.Joke;

import java.io.IOException;

@Service
public class ChuckService {

    private final String JOKE_URL = "https://api.chucknorris.io/jokes/random";
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();
    private Request request;

    public String Joke() throws IOException {
        request = new Request.Builder().url(JOKE_URL).build();
        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            Joke joke = convertJsonToJoke(responseBody);
            return joke.getValue();
        }
    }

    private Joke convertJsonToJoke(String json) throws JsonProcessingException {
        return mapper.readValue(json, Joke.class);
    }
}
