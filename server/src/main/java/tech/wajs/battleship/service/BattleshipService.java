/*
 * Copyright (c) 2022 Konrad Wajs, All rights reserved.
 */

package tech.wajs.battleship.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.wajs.battleship.BattleshipSettings;
import tech.wajs.battleship.dto.Grid;
import tech.wajs.battleship.dto.Location;
import tech.wajs.battleship.dto.ResponseDTO;
import tech.wajs.battleship.dto.Ship;
import tech.wajs.battleship.enums.LocationType;
import tech.wajs.battleship.enums.ShipType;
import tech.wajs.battleship.enums.ShotType;

import java.util.Random;
import java.util.function.BiFunction;

@Service
@Slf4j
public class BattleshipService {

    public void start() {
    }

    public ResponseDTO shot(String coordinates) {
    }
}
