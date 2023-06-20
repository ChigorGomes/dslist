package com.devsuperior.dslist.services;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class GameListService {
    @Autowired
    private GameListRepository gameListRepository;

    @Autowired
    private GameRepository gameRepository;


    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        return gameListRepository.findAll().stream().map(gameList -> new GameListDTO(gameList)).toList();
    }

    @Transactional()
    public void move(Long listId, int sourceIndex, int destionationIndex) {
        List<GameMinProjection> listGame = gameRepository.searchByList(listId);
        GameMinProjection obj = listGame.remove(sourceIndex);
        listGame.add(destionationIndex, obj);

        int min = Math.min(sourceIndex, destionationIndex);
        int max = Math.max(sourceIndex, destionationIndex);

        for (int i = min; i <= max; i++) {
            gameListRepository.updateBelongingPosition(listId, listGame.get(i).getId(), i);
        }
    }
}
