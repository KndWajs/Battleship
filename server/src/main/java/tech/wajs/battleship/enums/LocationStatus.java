/*
 * Copyright (c) 2022 Konrad Wajs, All rights reserved.
 */

package tech.wajs.battleship.enums;

public enum LocationStatus {
    WRECK("W"),
    OCCUPIED("O"),
    MISS("M"),
    EMPTY(" ");

    public String abbrev;

    LocationStatus(String abbrev) {
        this.abbrev = abbrev;
    }
}
