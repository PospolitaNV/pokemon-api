package axual.npospolita.pokemonapi.model;

import com.opencsv.bean.CsvBindByName;
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
    @CsvBindByName(column = "Name")
    private String name;
    @CsvBindByName(column = "Type 1")
    private String firstType;
    @CsvBindByName(column = "Type 2")
    private String secondType;
    @CsvBindByName(column = "Total")
    private Integer total;
    @CsvBindByName(column = "HP")
    private Integer health;
    @CsvBindByName(column = "Attack")
    private Integer attack;
    @CsvBindByName(column = "Defense")
    private Integer defense;
    @CsvBindByName(column = "Sp. Atk")
    private Integer specialAttack;
    @CsvBindByName(column = "Sp. Def")
    private Integer specialDefense;
    @CsvBindByName(column = "Speed")
    private Integer speed;
    @CsvBindByName(column = "Generation")
    private Integer generation;
    @CsvBindByName(column = "Legendary")
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
