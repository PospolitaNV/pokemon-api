package axual.npospolita.pokemonapi;

import axual.npospolita.pokemonapi.model.Pokemon;
import axual.npospolita.pokemonapi.repo.PokemonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PokemonApiApplicationTests {

    @Autowired
    PokemonRepository pokemonRepository;

    @Test
    void pokemonRepositoryIsWorking() {
        Pokemon pokemon = new Pokemon();
        pokemon.setName("test");
        pokemonRepository.save(pokemon);
        Assertions.assertEquals(pokemon, pokemonRepository.findById("test").get());
    }

}
