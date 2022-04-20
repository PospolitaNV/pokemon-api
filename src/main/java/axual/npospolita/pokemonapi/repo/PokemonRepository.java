package axual.npospolita.pokemonapi.repo;

import axual.npospolita.pokemonapi.model.Pokemon;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PokemonRepository extends PagingAndSortingRepository<Pokemon, String> {

}
