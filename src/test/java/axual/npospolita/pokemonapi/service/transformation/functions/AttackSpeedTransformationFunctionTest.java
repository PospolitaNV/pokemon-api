package axual.npospolita.pokemonapi.service.transformation.functions;

import axual.npospolita.pokemonapi.model.Pokemon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = AttackSpeedTransformationFunction.class)
@TestPropertySource(properties = {
        "transformation.attack_speed.value=1.1",
        "transformation.attack_speed.first_type=Bug",
        "transformation.attack_speed.second_type=Flying"
})
@ExtendWith(SpringExtension.class)
class AttackSpeedTransformationFunctionTest {

    @Autowired
    AttackSpeedTransformationFunction transformationFunction;

    @Test
    @DisplayName("Suitable pokemons could be processed and transformed by correct value")
    public void test() {
        Pokemon suitablePokemon = getSuitablePokemon();
        assertTrue(transformationFunction.couldApply(suitablePokemon));
        assertEquals(110.0, transformationFunction.apply(suitablePokemon).getSpeed(), 0.01);

        assertFalse(transformationFunction.couldApply(new Pokemon()));
    }

    private Pokemon getSuitablePokemon() {
        Pokemon pokemon = new Pokemon();
        pokemon.setFirstType("Bug");
        pokemon.setSecondType("Flying");
        pokemon.setSpeed(100.0);
        return pokemon;
    }
}
