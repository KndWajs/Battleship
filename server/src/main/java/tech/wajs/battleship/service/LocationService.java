/*
 * Copyright (c) 2022 Konrad Wajs, All rights reserved.
 */

package tech.wajs.battleship.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tech.wajs.battleship.dto.Location;
import tech.wajs.battleship.enums.LocationStatus;
import tech.wajs.battleship.enums.ShotType;
import tech.wajs.battleship.exceptions.KnownException;

@Service
@AllArgsConstructor
public class LocationService {

    private ShipService shipService;

    public ShotType hit(Location location) {
        if (location.getStatus().equals(LocationStatus.MISS) || location.getStatus().equals(LocationStatus.WRECK)) {
            throw new KnownException("Don't waste bullets!", HttpStatus.NOT_ACCEPTABLE);
        }
        if (location.getShip() != null) {
            location.setStatus(LocationStatus.WRECK);
            return shipService.hit(location.getShip());
        }
        location.setStatus(LocationStatus.MISS);
        return ShotType.MISS;
    }
}
