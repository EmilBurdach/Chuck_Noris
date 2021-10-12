package pl.envelo.Chuck.Noris.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.envelo.Chuck.Noris.Model.Joke;

public interface JokeRepository extends JpaRepository<Joke,Long> {

}
