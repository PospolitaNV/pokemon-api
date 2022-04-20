package axual.npospolita.pokemonapi.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
    private Double total;
    @CsvBindByName(column = "HP")
    private Double health;
    @CsvBindByName(column = "Attack")
    private Double attack;
    @CsvBindByName(column = "Defense")
    private Double defense;
    @CsvBindByName(column = "Sp. Atk")
    private Double specialAttack;
    @CsvBindByName(column = "Sp. Def")
    private Double specialDefense;
    @CsvBindByName(column = "Speed")
    private Double speed;
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

    public boolean isTypeOf(String type) {
        return Objects.equals(this.getFirstType(), type)
        || Objects.equals(this.getSecondType(), type);
    }
}
