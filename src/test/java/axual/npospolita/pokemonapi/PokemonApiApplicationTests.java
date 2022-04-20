package axual.npospolita.pokemonapi;

import axual.npospolita.pokemonapi.model.Pokemon;
import axual.npospolita.pokemonapi.repo.PokemonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.StreamSupport;

@SpringBootTest
class PokemonApiApplicationTests {

    @Autowired
    PokemonRepository pokemonRepository;

    @Test
    void pokemonRepositoryIsWorking() {
        List<Pokemon> pokemons = StreamSupport
                .stream(pokemonRepository.findAll().spliterator(), false)
                .toList();
        System.out.println(pokemons);
    }

}
