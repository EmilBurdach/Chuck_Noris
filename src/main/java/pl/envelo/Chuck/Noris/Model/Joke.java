package pl.envelo.Chuck.Noris.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//@Data
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Joke {

    public String value;
    @Id
    private String id;
}
