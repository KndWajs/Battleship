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
                    {grid.map((columns, row) =>
                        columns.map((loc, column) =>
                            <div className="location">
                                {getLocationStatusName(loc)}
                            </div>
                        )
                    )}
                </div>
            }
        </>
    );
}
