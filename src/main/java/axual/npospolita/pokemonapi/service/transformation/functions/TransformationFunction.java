package axual.npospolita.pokemonapi.service.transformation.functions;

/**
 * Interface for providing extendable way of adding new transformations
 * @param <T>
 */
public interface TransformationFunction<T> {

    boolean couldApply(T obj);

    T apply(T obj);
}
