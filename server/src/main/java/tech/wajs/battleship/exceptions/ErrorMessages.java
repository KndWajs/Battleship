/*
 * Copyright (c) 2022 Konrad Wajs, All rights reserved.
 */

package tech.wajs.battleship.exceptions;

public enum ErrorMessages {
    NOT_STARTED("The game has not started yet!"),
    WRONG_INPUT("Wrong Input, only: [char from A-J][number 1-10]!"),
    CHAR_OUT_OF_GRID("Character %s is out of grid!"),
    NUMBER_OUT_OF_GRID("Number %s is out of grid!"),
    NUMBER_NOT_PARSABLE("Text %s can not parse to number!"),
    SENSELESS_SHOT("Don't waste bullets!");

    public String description;

    ErrorMessages(String description) {
        this.description = description;
    }
}
