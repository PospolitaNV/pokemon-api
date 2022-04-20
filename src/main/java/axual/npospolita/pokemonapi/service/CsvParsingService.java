package axual.npospolita.pokemonapi.service;

import org.springframework.core.io.Resource;

import java.util.List;
import java.util.function.Predicate;

public interface CsvParsingService<T> {
    List<T> parse(Resource csvFile, Predicate<? super T> exclusionPredicate);
}
