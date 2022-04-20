package axual.npospolita.pokemonapi.repo;

import axual.npospolita.pokemonapi.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PokemonRepository extends JpaRepository<Pokemon, String>, JpaSpecificationExecutor<Pokemon> {

}
