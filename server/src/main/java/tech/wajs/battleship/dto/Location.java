/*
 * Copyright (c) 2022 Konrad Wajs, All rights reserved.
 */

package tech.wajs.battleship.dto;

import tech.wajs.battleship.enums.LocationType;

public class Location {

    private int row;
    private int column;
    private boolean isShipHorizontal; // todo maybe move to Ship
    private Ship ship;
    private LocationType type;

    public Location() {
        this.type = LocationType.EMPTY;
    }

    public Location(int row, int column, boolean isShipHorizontal, Ship ship) {
        this.row = row;
        this.column = column;
        this.isShipHorizontal = isShipHorizontal;
        this.ship = ship;
    }
}
