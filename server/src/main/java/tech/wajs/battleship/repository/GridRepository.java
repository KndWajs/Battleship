package tech.wajs.battleship.repository;

import org.springframework.stereotype.Repository;
import tech.wajs.battleship.BattleshipSettings;
import tech.wajs.battleship.dto.Grid;

@Repository
public class GridRepository {
    private Grid grid;

    public Grid createNewGrid() {
        grid = new Grid(BattleshipSettings.GRID_ROWS, BattleshipSettings.GRID_COLUMNS);
        return grid;
    }

    public Grid getGrid() {
        return grid;
    }
}
