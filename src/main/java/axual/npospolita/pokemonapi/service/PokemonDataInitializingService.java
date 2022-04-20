package axual.npospolita.pokemonapi.service;

import axual.npospolita.pokemonapi.model.Pokemon;
import axual.npospolita.pokemonapi.repo.PokemonRepository;
import axual.npospolita.pokemonapi.service.csv.PokemonCsvParsingService;
import axual.npospolita.pokemonapi.service.transformation.PokemonTransformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PokemonDataInitializingService implements InitializingBean {

    private final PokemonRepository pokemonRepository;
    private final PokemonCsvParsingService pokemonParsingService;
    private final PokemonTransformationService pokemonTransformationService;
    private final ResourceLoader resourceLoader;

    @Value("${pokemon_data_file_path}")
    private Resource pokemonFile;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Initializing pokemon data");
        log.info("File loaded: {}", pokemonFile);
        List<Pokemon> pokemons = pokemonParsingService.parse(pokemonFile, exclude().negate());
        log.trace("Pokemons loaded: {}", pokemons);
        pokemons = pokemons.stream().map(pokemonTransformationService::transform).collect(Collectors.toList());
        log.trace("Pokemons transformed: {}", pokemons);
        pokemonRepository.saveAll(pokemons);
        log.info("Pokemons saved: {}", pokemons.size());
    }

    // could be a bean with types list or some more advanced reflection usage
    private Predicate<? super Pokemon> exclude() {
        return pokemon -> pokemon.getLegendary() || pokemon.isTypeOf("Ghost");
    }
}
