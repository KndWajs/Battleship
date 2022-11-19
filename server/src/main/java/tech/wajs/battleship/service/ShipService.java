/*
 * Copyright (c) 2022 Konrad Wajs, All rights reserved.
 */

package tech.wajs.battleship.service;

import org.springframework.stereotype.Service;
import tech.wajs.battleship.dto.Ship;
import tech.wajs.battleship.enums.ShotType;

@Service
public class ShipService {

    public ShotType hit(Ship ship) {
        if (ship.getType().length == ship.getHits()) {
            return ShotType.SINK;
        }
        ship.hit();
        if (ship.getType().length > ship.getHits()) {
            return ShotType.HIT;
        }
        return ShotType.SINK;
    }
}
