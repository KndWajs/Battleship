/*
 * Copyright (c) 2022 Konrad Wajs, All rights reserved.
 */

package tech.wajs.battleship.enums;

public enum ShipType {
    BATTLESHIP(5),
    DESTROYER(4);

    public final int length;

    ShipType(int length) {
        this.length = length;
    }
}
