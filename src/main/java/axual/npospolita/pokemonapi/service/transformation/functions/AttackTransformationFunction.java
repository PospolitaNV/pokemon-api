package axual.npospolita.pokemonapi.service.transformation.functions;

import axual.npospolita.pokemonapi.model.Pokemon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.UnaryOperator;

@Component
public class AttackTransformationFunction implements TransformationFunction<Pokemon> {

    @Value("${transformation.attack.value}")
    private Double changeValue;

    @Value("#{'${transformation.attack.types}'.split(',')}")
    private List<String> types;

    private final UnaryOperator<Pokemon> unaryOperator = pokemon -> {
        pokemon.setAttack(pokemon.getAttack() * changeValue);
        return pokemon;
    };

    @Override
    public boolean couldApply(Pokemon pokemon) {
        return types.stream().anyMatch(pokemon::isTypeOf);
    }

    @Override
    public Pokemon apply(Pokemon pokemon) {
        return unaryOperator.apply(pokemon);
    }

}
