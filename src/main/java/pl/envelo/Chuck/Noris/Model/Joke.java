package pl.envelo.Chuck.Noris.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
//@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Joke {

    @Id
    @GeneratedValue
    private Long jokeId;
    private String value;
}
