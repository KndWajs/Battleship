/*
 * Copyright (c) 2022 Konrad Wajs, All rights reserved.
 */

package tech.wajs.battleship.dto;

public class Grid {
    private final Location[][] grid;

    public Grid(int rows, int columns) {
        grid = new Location[rows][columns];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[j][i] = new Location();
            }
        }
    }

    public Location getLocation(int row, int column) {
        return grid[row][column];
    }
}
