package axual.npospolita.pokemonapi.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Pokemon {
    @Id
    private String name;
    private String firstType;
    private String secondType;
    private Integer total;
    private Integer health;
    private Integer attack;
    private Integer defense;
    private Integer specialAttack;
    private Integer specialDefense;
    private Integer speed;
    private Integer generation;
    private Boolean legendary;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Pokemon pokemon = (Pokemon) o;
        return name != null && Objects.equals(name, pokemon.name);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
