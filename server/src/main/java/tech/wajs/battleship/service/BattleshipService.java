/*
 * Copyright (c) 2022 Konrad Wajs, All rights reserved.
 */

package tech.wajs.battleship.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.wajs.battleship.BattleshipSettings;
import tech.wajs.battleship.dto.Grid;
import tech.wajs.battleship.dto.Location;
import tech.wajs.battleship.dto.ResponseDTO;
import tech.wajs.battleship.dto.Ship;
import tech.wajs.battleship.enums.LocationType;
import tech.wajs.battleship.enums.ShipType;
import tech.wajs.battleship.repository.GridRepository;

import java.util.Random;
import java.util.function.BiFunction;

@Service
@Slf4j
@AllArgsConstructor
public class BattleshipService {

    private GridRepository repository;

    public void start() { //TODO test it as a reset too
        Grid grid = repository.createNewGrid();

        for (ShipType ship : BattleshipSettings.SHIPS) {
            placeShip(grid, ship);
        }
        logGrid();
    }

    public ResponseDTO shot(String coordinates) {
        return null;
    }

    private void placeShip(Grid grid, ShipType shipType) {
        Location locationWithShip;
        do {
            locationWithShip = getRandomPosition(shipType);
        } while (!checkIfLocationIsEmpty(grid, locationWithShip));

        putShipOnGrid(locationWithShip, grid);
    }

    private Location getRandomPosition(ShipType shipType) {
        boolean isHorizontal = new Random().nextBoolean();
        int columns = isHorizontal ? BattleshipSettings.GRID_COLUMNS - shipType.length :
                BattleshipSettings.GRID_COLUMNS;
        int rows = isHorizontal ? BattleshipSettings.GRID_ROWS : BattleshipSettings.GRID_ROWS - shipType.length;

        int column = new Random().nextInt(columns);
        int row = new Random().nextInt(rows);

        return new Location(row, column, isHorizontal, new Ship(shipType));
    }

    private boolean checkIfLocationIsEmpty(Grid grid, Location location) {
        int x = location.isShipHorizontal() ? location.getRow() : location.getColumn();
        int y = location.isShipHorizontal() ? location.getColumn() : location.getRow();
        BiFunction<Integer, Integer, Location> getLocation = location.isShipHorizontal() ?
                (row, column) -> grid.getLocation(row, column) : (row, column) -> grid.getLocation(column, row);

        for (int alongShipCoordinate = y; alongShipCoordinate < y + location.getShip().getType().length; alongShipCoordinate++) {
            if (!LocationType.EMPTY.equals(getLocation.apply(x, alongShipCoordinate).getType())) {
                return false;
            }
        }
        return true;
    }

    private void putShipOnGrid(Location location, Grid grid) {

        int x = location.isShipHorizontal() ? location.getRow() : location.getColumn();
        int y = location.isShipHorizontal() ? location.getColumn() : location.getRow();
        BiFunction<Integer, Integer, Location> getLocation = location.isShipHorizontal() ?
                (row, column) -> grid.getLocation(row, column) : (row, column) -> grid.getLocation(column, row);

        //        reservePlaceForShip()
        for (int constantCoordinate = x - 1; constantCoordinate <= x + 1; constantCoordinate++) {
            for (int alongShipCoordinate = y - 1; alongShipCoordinate < y + location.getShip().getType().length + 1; alongShipCoordinate++) {
                try {
                    getLocation.apply(constantCoordinate, alongShipCoordinate).setType(LocationType.OCCUPIED);
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }

        for (int alongShipCoordinate = y; alongShipCoordinate < y + location.getShip().getType().length; alongShipCoordinate++) {
            getLocation.apply(x, alongShipCoordinate).setShip(location.getShip());
        }
    }

    private void logGrid() {
        for (int row = 0; row < BattleshipSettings.GRID_ROWS; row++) {
            StringBuilder rowString = new StringBuilder();
            for (int column = 0; column < BattleshipSettings.GRID_COLUMNS; column++) {
                Location location = repository.getGrid().getLocation(row, column);
                if (location.getShip() == null) {
                    rowString.append("| " + location.getType().abbrev + " ");
                } else {
                    String locationSign = location.getType() == LocationType.WRECK? LocationType.WRECK.abbrev : "S";
                    rowString.append("| " + locationSign + " ");
                }
            }
            rowString.append("|");
            log.info(rowString.toString());
        }
    }
}
