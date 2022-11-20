/*
 * Copyright (c) 2022 Konrad Wajs, All rights reserved.
 */

package tech.wajs.battleship.dto;

import lombok.Getter;
import lombok.Setter;
import tech.wajs.battleship.BattleshipSettings;

public class Grid {
    private final Location[][] grid;
    @Getter
    @Setter
    private Integer ships;

    public Grid(int rows, int columns) {
        grid = new Location[rows][columns];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[j][i] = new Location();
            }
        }
        ships = BattleshipSettings.SHIPS.size();
    }

    public Location getLocation(int row, int column) {
        return grid[row][column];
    }
}
