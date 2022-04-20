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

@ContextConfiguration(classes = DefenceTransformationFunction.class)
@TestPropertySource(properties = {
        "transformation.defence.value=5",
        "transformation.defence.excluded_letter=G",
        "transformation.defence.starts_with=G"
})
@ExtendWith(SpringExtension.class)
class DefenceTransformationFunctionTest {

    @Autowired
    DefenceTransformationFunction transformationFunction;

    @Test
    @DisplayName("Suitable pokemons could be processed and transformed by correct value")
    public void test() {
        Pokemon suitablePokemon = getSuitablePokemon();
        assertTrue(transformationFunction.couldApply(suitablePokemon));
        assertEquals(120.00, transformationFunction.apply(suitablePokemon).getDefense(), 0.01);

        // this pokemon can't be processed
        assertFalse(transformationFunction.couldApply(new Pokemon()));
    }

    private Pokemon getSuitablePokemon() {
        Pokemon pokemon = new Pokemon();
        pokemon.setName("Garry");
        pokemon.setDefense(100.0);
        return pokemon;
    }
}
