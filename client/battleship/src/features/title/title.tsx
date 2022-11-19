import React, {useState} from 'react';

import styles from './Counter.module.css';
import {useAppDispatch, useAppSelector} from "../../app/hooks";
import {selectGrid, selectLocation, selectStatus, setLocation, shot, startNewGameAction} from "../grid/gridSlice";
import {Classes} from "@blueprintjs/core";
import {GameStatus} from "../../shared/enums/GameStatus";

export function Title() {
    return (
        <div>
            Front-end app is only for testing back-end! It's not production version!
        </div>
    );
}
