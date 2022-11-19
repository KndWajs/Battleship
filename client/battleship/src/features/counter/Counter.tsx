import React, {useState} from 'react';

import styles from './Counter.module.css';
import {useAppDispatch, useAppSelector} from "../../app/hooks";
import {selectGrid, selectLocation, selectStatus, setLocation, shot, startNewGameAction} from "../grid/gridSlice";
import {Classes} from "@blueprintjs/core";
import {GameStatus} from "../../shared/enums/GameStatus";
import axios from "axios";
import {createAsyncThunk} from "@reduxjs/toolkit";
import {startNewGame} from "../grid/gridAPI";

export function Counter() {
    const dispatch = useAppDispatch();
    let status = useAppSelector(selectStatus);
    let location = useAppSelector(selectLocation);

    const handleVoucherData = (e: React.FormEvent<HTMLInputElement>) => {
        dispatch(setLocation(e.currentTarget.value))
    }

    return (
        <div>
            <div className={styles.row}>
                {status == GameStatus.BEFORE ? null : <><input
                    id="code"
                    // value={voucherCode || ""}
                    className={Classes.INPUT}
                    placeholder={"A5"}
                    onChange={handleVoucherData}
                />

                    <button
                        className={styles.button}
                        onClick={() => dispatch(shot(location))}
                    >
                        Hit!
                    </button>
                </>}
            </div>
            <div className={styles.row}>

                <button
                    className={styles.button}
                    onClick={() => dispatch(startNewGameAction())}
                >
                    {status == GameStatus.BEFORE ? 'Start New Game' : 'Restart Game'}
                </button>
            </div>
        </div>
    );
}
