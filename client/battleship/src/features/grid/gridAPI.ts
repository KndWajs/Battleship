// A mock function to mimic making an async request for data
import {LocationStatus} from "../../shared/enums/LocationStatus";
import axios, * as others from 'axios';
import Response from "../../shared/response";

export function shotCall(coordinates: string) : Promise<{data: Response}>{
    return axios.put('/api/shot?coordinates=' + coordinates);
}

export function startNewGame() {
    return axios.post('/api/start');
}

