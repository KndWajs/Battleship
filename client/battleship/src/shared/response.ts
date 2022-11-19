import {ShotType} from "./enums/ShotType";

export default interface Response {
 shot: ShotType,
 row: number,
 column: number
}
