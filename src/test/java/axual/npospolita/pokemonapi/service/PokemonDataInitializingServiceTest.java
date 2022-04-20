package axual.npospolita.pokemonapi.service;

import axual.npospolita.pokemonapi.model.Pokemon;
import axual.npospolita.pokemonapi.repo.PokemonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PokemonDataInitializingServiceTest {

    @Autowired
    PokemonRepository pokemonRepository;

    @Test
    @DisplayName("Pokemon data is populated and filtered according rules on the app startup")
    void pokemonDataPopulated() {
        List<Pokemon> pokemons = StreamSupport
                .stream(pokemonRepository.findAll().spliterator(), false)
                .toList();
        assertEquals(0, pokemons.stream().filter(Pokemon::getLegendary).count());
        assertEquals(0, pokemons.stream()
                .filter(pokemon -> Objects.equals(pokemon.getFirstType(), "Ghost")
                                   || Objects.equals(pokemon.getSecondType(), "Ghost"))
                .count());
    }

}
