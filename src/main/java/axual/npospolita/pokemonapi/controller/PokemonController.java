package axual.npospolita.pokemonapi.controller;

import axual.npospolita.pokemonapi.model.Pokemon;
import axual.npospolita.pokemonapi.repo.PokemonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
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
            @Spec(path="health", params = "hp[lte]", spec = LessThanOrEqual.class) Specification<Pokemon> pokemonSpecification,
            Pageable page) {

        return pokemonRepository.findAll(pokemonSpecification, page);
    }

}
