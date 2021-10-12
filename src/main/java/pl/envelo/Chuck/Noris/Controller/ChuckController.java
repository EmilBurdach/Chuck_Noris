package pl.envelo.Chuck.Noris.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.envelo.Chuck.Noris.Service.ChuckService;

import java.io.IOException;

@Controller
@RequestMapping("api/")
@RequiredArgsConstructor
public class ChuckController {

    private final String NO_JOKES = "THERE IS NO CONTENT IN CHUCK NORRIS API JOKE";
    private final String BAD_JSON_PROCESSING = "CAN'T CONVERT JSON TO POJO";
    @Autowired
    ChuckService service;

    @GetMapping(value = "/random")
    public String getRandomJoke(Model model) {
        try {
            String joke = service.Joke();
            model.addAttribute("joke", joke);
            return "joke";
        } catch (JsonProcessingException e) {
            model.addAttribute("joke", BAD_JSON_PROCESSING);
            e.printStackTrace();
            return "joke";
        } catch (IOException e) {
            model.addAttribute("joke", NO_JOKES);
            return "joke";
        }
    }

    @GetMapping(value = "/view")
    public String getAllJokes(Model model) {
        model.addAttribute("jokes",service.allJokes());
        return "jokes";
    }
}

