package axual.npospolita.pokemonapi.service.transformation;

import axual.npospolita.pokemonapi.model.Pokemon;
import axual.npospolita.pokemonapi.repo.PokemonRepository;
import axual.npospolita.pokemonapi.service.csv.PokemonCsvParsingService;
import axual.npospolita.pokemonapi.service.transformation.functions.AttackSpeedTransformationFunction;
import axual.npospolita.pokemonapi.service.transformation.functions.AttackTransformationFunction;
import axual.npospolita.pokemonapi.service.transformation.functions.DefenceTransformationFunction;
import axual.npospolita.pokemonapi.service.transformation.functions.HealthTransformationFunction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@TestPropertySource(properties = {
        "pokemon_data_file_path: classpath:test_pokemon.csv" // could use any test data here
})
class PokemonDataInitializationIntegrationTest {

    @SpyBean
    PokemonCsvParsingService pokemonCsvParsingService;
    @SpyBean
    PokemonTransformationService transformationService;
    @SpyBean
    AttackSpeedTransformationFunction attackSpeedTransformationFunction;
    @SpyBean
    AttackTransformationFunction attackTransformationFunction;
    @SpyBean
    HealthTransformationFunction healthTransformationFunction;
    @SpyBean
    DefenceTransformationFunction defenceTransformationFunction;

    @Autowired
    PokemonRepository pokemonRepository;

    @Test
    @DisplayName("Success data initialization with filtering and transformation applied")
    public void integrationTest() {
        verify(pokemonCsvParsingService, times(1)).parse(any(), any());
        verify(transformationService, times(692)).transform(any());

        List<Pokemon> pokemons = StreamSupport.stream(pokemonRepository.findAll().spliterator(), false).toList();

        // Pokemons are filtered properly
        assertEquals(692, pokemons.size());
        assertFalse(pokemons.stream().anyMatch(Pokemon::getLegendary));
        assertFalse(pokemons.stream().anyMatch(pokemon -> pokemon.isTypeOf("Ghost")));

        // Pokemons are transformed right amount of times
        int healthTransformation = (int) pokemons.stream().filter(pokemon -> pokemon.isTypeOf("Steel")).count();
        verify(healthTransformationFunction, times(healthTransformation)).apply(any());

        int defenceTransformation = (int) pokemons.stream().filter(pokemon -> pokemon.getName().startsWith("G")).count();
        verify(defenceTransformationFunction, times(defenceTransformation)).apply(any());

        int speedTransformation = (int) pokemons.stream().filter(pokemon -> pokemon.isTypeOf("Bug") && pokemon.isTypeOf("Flying")).count();
        verify(attackSpeedTransformationFunction, times(speedTransformation)).apply(any());

        int attackTransformation = (int) pokemons.stream().filter(pokemon -> pokemon.isTypeOf("Fire")).count();
        verify(attackTransformationFunction, times(attackTransformation)).apply(any());
    }

}
