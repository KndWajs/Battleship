/*
 * Copyright (c) 2022 Konrad Wajs, All rights reserved.
 */

package tech.wajs.battleship.dto;

import lombok.Getter;
import tech.wajs.battleship.enums.ShipType;

@Getter
public class Ship {
    private ShipType type;
    private Integer hits;
    private int row;
    private int column;
    private boolean horizontal;

    public Ship(ShipType type, int row, int column, boolean horizontal) {
        this.type = type;
        this.row = row;
        this.column = column;
        this.horizontal = horizontal;
    }
}
