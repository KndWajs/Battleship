/*
 * Copyright (c) 2022 Konrad Wajs, All rights reserved.
 */

package tech.wajs.battleship.dto;

import lombok.Getter;
import tech.wajs.battleship.enums.ShotType;

@Getter
public class ResponseDTO {
    ShotType shot;
    Integer row;
    Integer column;

    public ResponseDTO(ShotType shot, Integer row, Integer column) {
        this.shot = shot;
        this.row = row;
        this.column = column;
    }
}
