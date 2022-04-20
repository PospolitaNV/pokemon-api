package axual.npospolita.pokemonapi.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PokemonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Search and repo works with LessThanOrEqual param with page number")
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/pokemon?hp[lte]=10&page=0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content().json("{\"content\":[{\"name\":\"Diglett\",\"firstType\":\"Ground\",\"secondType\":\"\",\"total\":265.0,\"health\":10.0,\"attack\":55.0,\"defense\":25.0,\"specialAttack\":35.0,\"specialDefense\":45.0,\"speed\":95.0,\"generation\":1,\"legendary\":false}],"
                                       + "\"pageable\":{\"sort\":{\"empty\":true,\"unsorted\":true,\"sorted\":false},\"offset\":0,\"pageNumber\":0,\"pageSize\":20,\"paged\":true,\"unpaged\":false},\"totalPages\":1,\"totalElements\":1,\"last\":true,\"size\":20,\"number\":0,\"sort\":{\"empty\":true,\"unsorted\":true,\"sorted\":false},\"numberOfElements\":1,\"first\":true,\"empty\":false}")
                );
        this.mockMvc.perform(get("/pokemon?hp[lte]=10&page=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content().json("{\"content\":[],"
                                       + "\"pageable\":{\"sort\":{\"empty\":true,\"unsorted\":true,\"sorted\":false},\"offset\":20,\"pageNumber\":1,\"pageSize\":20,\"paged\":true,\"unpaged\":false},\"totalPages\":1,\"totalElements\":1,\"last\":true,\"size\":20,\"number\":1,\"sort\":{\"empty\":true,\"unsorted\":true,\"sorted\":false},\"numberOfElements\":0,\"first\":false,\"empty\":true}")
                );
    }
}
