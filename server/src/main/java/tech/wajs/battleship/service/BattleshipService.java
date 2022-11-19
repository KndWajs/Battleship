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
import tech.wajs.battleship.enums.LocationStatus;
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
        Ship ship;
        do {
            ship = getRandomPosition(shipType);
        } while (!checkIfLocationIsEmpty(grid, ship));

        putShipOnGrid(ship, grid);
    }

    private Ship getRandomPosition(ShipType shipType) {
        boolean isHorizontal = new Random().nextBoolean();
        int availableRows = isHorizontal ? BattleshipSettings.GRID_ROWS :
                BattleshipSettings.GRID_ROWS - shipType.length;
        int availableColumns = isHorizontal ? BattleshipSettings.GRID_COLUMNS - shipType.length :
                BattleshipSettings.GRID_COLUMNS;

        int randomRow = new Random().nextInt(availableRows);
        int randomColumn = new Random().nextInt(availableColumns);

        return new Ship(shipType, randomRow, randomColumn, isHorizontal);
    }

    private boolean checkIfLocationIsEmpty(Grid grid, Ship ship) {
        for (int x = ship.getVariableCoordinate(); x < ship.getVariableCoordinate() + ship.getType().length; x++) {
            LocationStatus status = getLocationForShip(grid, ship).apply(ship.getConstantCoordinate(), x).getStatus();
            if (!LocationStatus.EMPTY.equals(status)) {
                return false;
            }
        }
        return true;
    }

    private void putShipOnGrid(Ship ship, Grid grid) {
        // reservePlaceForShip
        for (int constant = ship.getConstantCoordinate() - 1; constant <= ship.getConstantCoordinate() + 1; constant++) {
            for (int along = ship.getVariableCoordinate() - 1;
                 along < ship.getVariableCoordinate() + ship.getType().length + 1; along++) {
                try {
                    getLocationForShip(grid, ship).apply(constant, along).setStatus(LocationStatus.OCCUPIED);
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
        // put ship on grid
        for (int along = ship.getVariableCoordinate();
             along < ship.getVariableCoordinate() + ship.getType().length; along++) {
            getLocationForShip(grid, ship).apply(ship.getConstantCoordinate(), along).setShip(ship);
        }
    }

    private BiFunction<Integer, Integer, Location> getLocationForShip(Grid grid, Ship ship) {
        return ship.isHorizontal() ? grid::getLocation : (row, column) -> grid.getLocation(column, row);
    }

    private void logGrid() {
        for (int row = 0; row < BattleshipSettings.GRID_ROWS; row++) {
            StringBuilder rowString = new StringBuilder();
            for (int column = 0; column < BattleshipSettings.GRID_COLUMNS; column++) {
                Location location = repository.getGrid().getLocation(row, column);
                if (location.getShip() == null) {
                    rowString.append("| " + location.getStatus().abbrev + " ");
                } else {
                    String locationSign = location.getStatus() == LocationStatus.WRECK ? LocationStatus.WRECK.abbrev
                            : "S";
                    rowString.append("| " + locationSign + " ");
                }
            }
            rowString.append("|");
            log.info(rowString.toString());
        }
    }
}
