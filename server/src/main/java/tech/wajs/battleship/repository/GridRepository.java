package tech.wajs.battleship.repository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import tech.wajs.battleship.BattleshipSettings;
import tech.wajs.battleship.dto.Grid;
import tech.wajs.battleship.exceptions.KnownException;

@Repository
public class GridRepository {
    private Grid grid;

    public Grid createNewGrid() {
        grid = new Grid(BattleshipSettings.GRID_ROWS, BattleshipSettings.GRID_COLUMNS);
        return grid;
    }

    public Grid getGrid() {
        if(grid==null){
            throw new KnownException("The game has not started yet!", HttpStatus.NOT_ACCEPTABLE);
        }
        return grid;
    }
}
