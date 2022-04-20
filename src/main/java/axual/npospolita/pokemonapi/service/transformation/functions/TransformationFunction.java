package axual.npospolita.pokemonapi.service.transformation.functions;

public interface TransformationFunction<T> {

    boolean couldApply(T obj);

    T apply(T obj);
}
