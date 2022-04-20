package axual.npospolita.pokemonapi.controller;

import axual.npospolita.pokemonapi.model.Pokemon;
import axual.npospolita.pokemonapi.repo.PokemonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.iterableWithSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class PokemonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PokemonRepository pokemonRepository;

    @BeforeEach
    public void initData() {
        pokemonRepository.deleteAll();
        for (int i = 0; i < 100; i++) {
            Pokemon pokemon = new Pokemon();
            pokemon.setName("Poke" + i);
            pokemon.setHealth((double) i);
            pokemon.setAttack((double) i);
            pokemon.setDefense((double) i);
            pokemonRepository.save(pokemon);
        }
    }

    @Test
    @DisplayName("Pagination with params works")
    public void paginationTest() throws Exception {
        this.mockMvc.perform(get("/pokemon?hp[lte]=30&page=0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.content", iterableWithSize(20))
                );
        this.mockMvc.perform(get("/pokemon?hp[lte]=30&page=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.content", iterableWithSize(11))
                );
    }


    @Test
    @DisplayName("Searching by name is by Like-content")
    public void requestParamsTest() throws Exception {
        this.mockMvc.perform(get("/pokemon?name=Poke&page=0&size=101"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.content", iterableWithSize(100))
                );
    }

    @Test
    @DisplayName("Searching by several parameters")
    public void complexParamsTest() throws Exception {
        this.mockMvc.perform(get("/pokemon?size=100&"
                                 + "hp[gte]=50&"
                                 + "hp[lte]=75"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.content", iterableWithSize(26))
                );

        this.mockMvc.perform(get("/pokemon?size=100&"
                                 + "hp[gte]=50&"
                                 + "hp[lte]=75&"
                                 + "defense[gte]=60"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.content", iterableWithSize(16))
                );

        this.mockMvc.perform(get("/pokemon?size=100&"
                                 + "hp[gte]=50&"
                                 + "hp[lte]=75&"
                                 + "defense[gte]=60&"
                                 + "attack[lte]=70"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.content", iterableWithSize(11))
                );

        this.mockMvc.perform(get("/pokemon?size=100&"
                                 + "name=7&"
                                 + "hp[gte]=50&"
                                 + "hp[lte]=75&"
                                 + "defense[gte]=60&"
                                 + "attack[lte]=70"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.content", iterableWithSize(2))
                );
    }
}
