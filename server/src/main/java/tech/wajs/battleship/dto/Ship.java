/*
 * Copyright (c) 2022 Konrad Wajs, All rights reserved.
 */

package tech.wajs.battleship.dto;

import tech.wajs.battleship.enums.ShipType;

public class Ship {
    private ShipType type;
    private Integer hits;

    public Ship(ShipType type) {
        this.type = type;
        hits = 0;
    }
}
