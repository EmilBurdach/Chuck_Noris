package pl.envelo.Chuck.Noris.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class JokeManager {
    @GeneratedValue
    @Id
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Joke> allJokes;

}
