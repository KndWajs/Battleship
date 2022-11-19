import React, {useEffect} from 'react';

import './location.scss';
import {getLocationStatusName, LocationStatus} from "../../shared/enums/LocationStatus";
import {useAppSelector} from "../../app/hooks";
import {selectGrid, selectStatus} from "./gridSlice";
import {GameStatus} from "../../shared/enums/GameStatus";

export function Grid() {

    const grid = useAppSelector(selectGrid);
    let status = useAppSelector(selectStatus);


    return (
        <>
            {status == GameStatus.BEFORE ? null :
                <div className="grid">
                    <div className="location header">
                    </div>
                    {[...Array(10)].map((x, i) =>
                        <div className="location header">
                            <strong>{i+1}</strong>
                        </div>
                    )}
                    {grid.map((columns, row) =>
                        <>
                            <div className="location header">
                                <strong>{"ABCDEFGHIJ".charAt(row)}</strong>
                            </div>
                            {
                                columns.map((loc, column) =>
                                    <div className="location">
                                        {getLocationStatusName(loc)}
                                    </div>
                                )}
                        </>
                    )}
                </div>
            }
        </>
    );
}
