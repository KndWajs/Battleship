export enum ShotType {
    HIT = "HIT",
    MISS = "MISS",
    SINK = "SINK",
    END = "END"
}

export interface LocationType {
    abbrev: string
}

export const ShotTypeProperties = new Map<ShotType, LocationType>([
    [ShotType.HIT, {abbrev: "H"}],
    [ShotType.MISS, {abbrev: "M"}],
    [ShotType.SINK, {abbrev: "S"}],
    [ShotType.END, {abbrev: ""}],
])

export const getShotTypeName = (locationStatus: ShotType): string | undefined =>
    ShotTypeProperties.get(locationStatus)?.abbrev

