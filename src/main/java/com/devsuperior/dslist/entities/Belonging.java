package com.devsuperior.dslist.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_belonging")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Belonging {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private BelongingPK id = new BelongingPK();

    private Integer position;

    public Belonging(Game game, GameList gameList, Integer position) {
        id.setGame(game);
        id.setGameList(gameList);
        this.position = position;
    }
}
