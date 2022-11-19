export enum LocationStatus {
    WRECK = "WRECK",
    MISS = "MISS",
    EMPTY = "EMPTY"
}

export interface LocationType {
    abbrev: string
}

export const LocationStatusProperties = new Map<LocationStatus, LocationType>([
    [LocationStatus.WRECK, {abbrev: "O"}],
    [LocationStatus.MISS, {abbrev: "X"}],
    [LocationStatus.EMPTY, {abbrev: ""}],
])

export const getLocationStatusName = (locationStatus: LocationStatus): string | undefined =>
    LocationStatusProperties.get(locationStatus)?.abbrev

