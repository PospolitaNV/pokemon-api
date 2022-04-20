package axual.npospolita.pokemonapi.controller;

import axual.npospolita.pokemonapi.model.Pokemon;
import axual.npospolita.pokemonapi.repo.PokemonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    private final PokemonRepository pokemonRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Pokemon> getPokemons(
            @And({
                    @Spec(path="health", params = "hp[gte]", spec = GreaterThanOrEqual.class),
                    @Spec(path="health", params = "hp[lte]", spec = LessThanOrEqual.class),
                    @Spec(path="health", params = "hp", spec = Equal.class),
                    @Spec(path="defense", params = "defense[gte]", spec = GreaterThanOrEqual.class),
                    @Spec(path="defense", params = "defense[lte]", spec = LessThanOrEqual.class),
                    @Spec(path="defense", params = "defense", spec = Equal.class),
                    @Spec(path="attack", params = "attack[gte]", spec = GreaterThanOrEqual.class),
                    @Spec(path="attack", params = "attack[lte]", spec = LessThanOrEqual.class),
                    @Spec(path="attack", params = "attack", spec = Equal.class),
                    @Spec(path = "name", params = "name", spec = Like.class)
            }) Specification<Pokemon> pokemonSpecification,
            Pageable page) {

        return pokemonRepository.findAll(pokemonSpecification, page);
    }

}
