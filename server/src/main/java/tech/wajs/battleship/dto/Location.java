/*
 * Copyright (c) 2022 Konrad Wajs, All rights reserved.
 */

package tech.wajs.battleship.dto;

import lombok.Getter;
import lombok.Setter;
import tech.wajs.battleship.enums.LocationStatus;

@Getter
public class Location {
    @Setter
    private Ship ship;
    @Setter
    private LocationStatus status;

    public Location() {
        this.status = LocationStatus.EMPTY;
    }
}
