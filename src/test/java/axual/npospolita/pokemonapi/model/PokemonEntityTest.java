package axual.npospolita.pokemonapi.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonEntityTest {

    @Test
    @DisplayName("Pokemon equals works by name")
    public void test() {
        Pokemon first = new Pokemon();
        first.setName("test");
        Pokemon second = new Pokemon();
        second.setName("test");
        assertEquals(first, second);
    }
}
