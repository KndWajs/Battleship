/*
 * Copyright (c) 2022 Konrad Wajs, All rights reserved.
 */

package tech.wajs.battleship.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.wajs.battleship.dto.Location;
import tech.wajs.battleship.dto.Ship;
import tech.wajs.battleship.enums.ShipType;
import tech.wajs.battleship.enums.ShotType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {
    @InjectMocks
    LocationService locationService;

    @Mock
    ShipService shipService;

    @Test
    void shouldReturnMISS() {
        //given
        Location location = new Location();

        //when
        ShotType shotType = locationService.hit(location);

        //then
        assertThat(shotType).isEqualTo(ShotType.MISS);
    }

    @Test
    void shouldReturnHIT() {
        //given
        Location location = new Location();
        Ship ship = new Ship(ShipType.BATTLESHIP, 5, 5, true);
        location.setShip(ship);

        //when
        when(shipService.hit(ship)).thenReturn(ShotType.HIT);
        ShotType shotType = locationService.hit(location);

        //then
        assertThat(shotType).isEqualTo(ShotType.HIT);
    }

    @Test
    void shouldReturnSINK() {
        //given
        Location location = new Location();
        Ship ship = new Ship(ShipType.BATTLESHIP, 5, 5, true);
        location.setShip(ship);

        //when
        when(shipService.hit(ship)).thenReturn(ShotType.SINK);
        ShotType shotType = locationService.hit(location);

        //then
        assertThat(shotType).isEqualTo(ShotType.SINK);
    }
}
