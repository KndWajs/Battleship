/*
 * Copyright (c) 2022 Konrad Wajs, All rights reserved.
 */

package tech.wajs.battleship.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tech.wajs.battleship.BattleshipSettings;
import tech.wajs.battleship.dto.Grid;
import tech.wajs.battleship.dto.Location;
import tech.wajs.battleship.dto.ResponseDTO;
import tech.wajs.battleship.dto.Ship;
import tech.wajs.battleship.enums.LocationStatus;
import tech.wajs.battleship.enums.ShipType;
import tech.wajs.battleship.enums.ShotType;
import tech.wajs.battleship.exceptions.ErrorMessages;
import tech.wajs.battleship.exceptions.KnownException;
import tech.wajs.battleship.repository.GridRepository;

import java.util.Random;
import java.util.function.BiFunction;

@Service
@Slf4j
@AllArgsConstructor
public class BattleshipService {

    private GridRepository repository;
    private LocationService locationService;

    public void start() {
        Grid grid = repository.createNewGrid();

        for (ShipType ship : BattleshipSettings.SHIPS) {
            placeShip(grid, ship);
        }
        logGrid();
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
        log.info("Print grid:");
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

    public ResponseDTO shot(String coordinates) {
        inputValidation(coordinates);
        int row = BattleshipSettings.ROWS.indexOf(getX(coordinates));
        int column = getY(coordinates) - 1;
        ShotType shot = locationService.hit(repository.getGrid().getLocation(row, column));
        logGrid();

        return new ResponseDTO(shot, row, column);
    }

    private void inputValidation(String coordinates) {
        if (!StringUtils.hasText(coordinates) || coordinates.length() > 3) {
            throw new KnownException(ErrorMessages.WRONG_INPUT.description, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    private Character getX(String coordinates) {
        Character character = Character.toUpperCase(coordinates.charAt(0));
        if (!BattleshipSettings.ROWS.contains(character)) {
            throw new KnownException(String.format(ErrorMessages.CHAR_OUT_OF_GRID.description, character),
                    HttpStatus.NOT_ACCEPTABLE);
        }
        return character;
    }

    private int getY(String coordinates) {
        String substring = coordinates.substring(1);

        try {
            int integer = Integer.parseInt(substring);
            if (integer > BattleshipSettings.GRID_COLUMNS || integer <= 0) {
                throw new KnownException(String.format(ErrorMessages.NUMBER_OUT_OF_GRID.description, integer),
                        HttpStatus.NOT_ACCEPTABLE);
            }
            return integer;
        } catch (NumberFormatException e) {
            throw new KnownException(String.format(ErrorMessages.NUMBER_NOT_PARSABLE.description, substring),
                    HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
