/*
 * Copyright (c) 2022 Konrad Wajs, All rights reserved.
 */

package tech.wajs.battleship;

import tech.wajs.battleship.enums.ShipType;

import java.util.List;
import java.util.stream.Collectors;

public class BattleshipSettings {
        public static final int GRID_COLUMNS = 10;
        public static final int GRID_ROWS = 10;
        public static final List<Character> ROWS = "ABCDEFGHIJ".chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        public static final List<ShipType> SHIPS = List.of(ShipType.BATTLESHIP, ShipType.DESTROYER, ShipType.DESTROYER);
}
