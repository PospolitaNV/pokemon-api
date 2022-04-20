package axual.npospolita.pokemonapi.service.transformation;

import axual.npospolita.pokemonapi.model.Pokemon;
import axual.npospolita.pokemonapi.service.transformation.functions.TransformationFunction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PokemonTransformationService implements TransformationService<Pokemon> {

    private final List<TransformationFunction<Pokemon>> transformationFunctions;

    @Override
    public Pokemon transform(Pokemon pokemon) {
        log.debug("Started transformation of pokemon: [{}]", pokemon);
        for (TransformationFunction<Pokemon> transformationFunction : transformationFunctions) {
            if (transformationFunction.couldApply(pokemon)) {
                log.trace("Transforming pokemon [{}] using [{}]", pokemon, transformationFunction);
                pokemon = transformationFunction.apply(pokemon);
                log.trace("Transformation complete - pokemon [{}]", pokemon);
            }
        log.debug("Finished transformation of pokemon: [{}]", pokemon);
        }
        return pokemon;
    }

}
