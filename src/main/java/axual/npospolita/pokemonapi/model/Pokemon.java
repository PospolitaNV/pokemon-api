package axual.npospolita.pokemonapi.model;

import lombok.Data;

@Data
public class Pokemon {
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
}
