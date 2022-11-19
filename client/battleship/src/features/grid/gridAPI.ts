// A mock function to mimic making an async request for data
import {LocationStatus} from "../../shared/enums/LocationStatus";

export function shotCall(coordinates: string) {
    return new Promise<{ status: LocationStatus,  row: number, column: number }>((resolve) =>
        setTimeout(() => resolve({status: LocationStatus.WRECK, row: 3,
            column: 5}), 500)
    );
}

export function startNewGame() {
    return new Promise<void>((resolve) =>
        setTimeout(() => resolve(), 500)
    );
}

