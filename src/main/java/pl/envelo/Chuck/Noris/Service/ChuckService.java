package pl.envelo.Chuck.Noris.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.envelo.Chuck.Noris.Model.Joke;
import pl.envelo.Chuck.Noris.Repository.JokeRepository;

import java.io.IOException;
import java.util.List;

@Service
public class ChuckService {

    @Autowired
    JokeRepository jokeRepository;

    private final String JOKE_URL = "https://api.chucknorris.io/jokes/random";
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();
    private Request request;

    public String Joke(boolean save) throws IOException {
        request = new Request.Builder().url(JOKE_URL).get().addHeader("accept", "application/json").build();
        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            Joke joke = convertJsonToJoke(responseBody);
            if(save) jokeRepository.save(joke);
            return joke.getValue();
        }
    }

    public List<Joke> allJokes(){
        return jokeRepository.findAll();
    }

    private Joke convertJsonToJoke(String json) throws JsonProcessingException {
        return mapper.readValue(json, Joke.class);
    }
}
