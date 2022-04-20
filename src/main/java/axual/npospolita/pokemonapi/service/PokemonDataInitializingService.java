package axual.npospolita.pokemonapi.service;

import axual.npospolita.pokemonapi.model.Pokemon;
import axual.npospolita.pokemonapi.repo.PokemonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Slf4j
@RequiredArgsConstructor
@Service
public class PokemonDataInitializingService implements InitializingBean {

    private final PokemonRepository pokemonRepository;
    private final PokemonCsvParsingService pokemonParsingService;
    private final ResourceLoader resourceLoader;

    @Override
    public void afterPropertiesSet() throws Exception {
        Resource pokemonFile = resourceLoader.getResource("classpath:pokemon.csv");
        List<Pokemon> pokemons = pokemonParsingService.parse(pokemonFile, exclude().negate());
        pokemonRepository.saveAll(pokemons);
    }

    private Predicate<? super Pokemon> exclude() {
        return pokemon -> pokemon.getLegendary()
                          || pokemon.getFirstType().equals("Ghost")
                          || pokemon.getSecondType().equals("Ghost");
    }
}
