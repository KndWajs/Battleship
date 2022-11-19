export enum ShotType {
    HIT = "HIT",
    MISS = "MISS",
    SINK = "SINK"
}

export interface LocationType {
    abbrev: string
}

export const ShotTypeProperties = new Map<ShotType, LocationType>([
    [ShotType.HIT, {abbrev: "H"}],
    [ShotType.MISS, {abbrev: "M"}],
    [ShotType.SINK, {abbrev: "S"}],
])

export const getShotTypeName = (locationStatus: ShotType): string | undefined =>
    ShotTypeProperties.get(locationStatus)?.abbrev

