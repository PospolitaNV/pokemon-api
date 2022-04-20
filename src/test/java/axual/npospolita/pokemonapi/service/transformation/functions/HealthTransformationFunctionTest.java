package axual.npospolita.pokemonapi.service.transformation.functions;

import axual.npospolita.pokemonapi.model.Pokemon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = HealthTransformationFunction.class)
@TestPropertySource(properties = {
        "transformation.health.value=2.0",
        "transformation.health.types=Steel,Water"
})
@ExtendWith(SpringExtension.class)
class HealthTransformationFunctionTest {

    @Autowired
    HealthTransformationFunction transformationFunction;

    @Test
    @DisplayName("Suitable pokemons could be processed and transformed by correct value")
    public void test() {
        Pokemon suitablePokemon = getSuitablePokemon();
        assertTrue(transformationFunction.couldApply(suitablePokemon));
        assertEquals(200.0, transformationFunction.apply(suitablePokemon).getHealth(), 0.01);

        assertFalse(transformationFunction.couldApply(new Pokemon()));
    }

    private Pokemon getSuitablePokemon() {
        Pokemon pokemon = new Pokemon();
        pokemon.setFirstType("Steel");
        pokemon.setHealth(100.0);
        return pokemon;
    }
}
