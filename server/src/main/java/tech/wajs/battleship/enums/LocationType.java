/*
 * Copyright (c) 2022 Konrad Wajs, All rights reserved.
 */

package tech.wajs.battleship.enums;

public enum LocationType {
    WRECK("W"),
    OCCUPIED("O"),
    MISS("M"),
    EMPTY(" ");

    public String abbrev;

    LocationType(String abbrev) {
        this.abbrev = abbrev;
    }
}
