package axual.npospolita.pokemonapi.service.csv;

import org.springframework.core.io.Resource;

import java.util.List;
import java.util.function.Predicate;

public interface CsvParsingService<T> {
    // filter predicate is provided for less memory consumption
    List<T> parse(Resource csvFile, Predicate<? super T> exclusionPredicate);
}
