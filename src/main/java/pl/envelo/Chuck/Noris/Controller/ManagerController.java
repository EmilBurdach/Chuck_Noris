package pl.envelo.Chuck.Noris.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.envelo.Chuck.Noris.Model.Joke;
import pl.envelo.Chuck.Noris.Model.JokeManager;
import pl.envelo.Chuck.Noris.Repository.JokeMenagerRepository;
import pl.envelo.Chuck.Noris.Service.ChuckService;

import java.io.IOException;
@Controller
@RequestMapping("api/")
@RequiredArgsConstructor
public class ManagerController {

    private final String NO_JOKES = "THERE IS NO CONTENT IN CHUCK NORRIS API JOKE";
    private final String BAD_JSON_PROCESSING = "CAN'T CONVERT JSON TO POJO";

    @Autowired
    JokeMenagerRepository jokeMenagerRepository;

    @Autowired
    ChuckService service;

    @GetMapping(value = "/add")
    public  String add(){
        JokeManager jokeManager = new JokeManager();
        jokeMenagerRepository.save(jokeManager);
        return "Completed";
    }
    @GetMapping(value = "/add/{id}")
    public  String addToManager(Model model,@PathVariable Long id){
        JokeManager jokeManager = jokeMenagerRepository.findById(id).orElseThrow();
        try {
            String joke = service.Joke(false);
            model.addAttribute("joke", joke);
            Joke saved = new Joke();
            saved.setValue(joke);
            jokeManager.getAllJokes().add(saved);
            jokeMenagerRepository.save(jokeManager);
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
    @GetMapping(value = "jokeManager/{id}")
    public String get(Model model, @PathVariable Long id){
        JokeManager jokeManager = jokeMenagerRepository.findById(id).orElseThrow();
        model.addAttribute("jokes", jokeManager.getAllJokes());
        return "jokes";
    }


    @GetMapping(value = "/managers")
    public String getAllManagers(Model model){
        model.addAttribute("managers",jokeMenagerRepository.findAll());
        return "managers";
    }
}
