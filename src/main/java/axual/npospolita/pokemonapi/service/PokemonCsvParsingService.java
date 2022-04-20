package axual.npospolita.pokemonapi.service;

import axual.npospolita.pokemonapi.model.Pokemon;
import axual.npospolita.pokemonapi.service.exception.ApplicationInitializationException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.function.Predicate;

@Slf4j
@Service
public class PokemonCsvParsingService implements CsvParsingService<Pokemon> {

    @Override
    public List<Pokemon> parse(Resource csvFile, Predicate<? super Pokemon> filterPredicate) {
        try (Reader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream()))) {

            // create csv bean reader
            CsvToBean<Pokemon> csvToBean =
                    new CsvToBeanBuilder<Pokemon>(reader)
                            .withType(Pokemon.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();

            // convert `CsvToBean` object to list of users
            return csvToBean.stream()
                    .filter(filterPredicate)
                    .toList();
        } catch (IOException e) {
            log.error("Error reading file", e);
            throw new ApplicationInitializationException("Error reading file", e);
        }
    }
}
