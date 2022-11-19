export enum LocationStatus {
    WRECK = "WRECK",
    MISS = "MISS",
    EMPTY = "EMPTY"
}

export interface LocationType {
    abbrev: string
}

export const LocationStatusProperties = new Map<LocationStatus, LocationType>([
    [LocationStatus.WRECK, {abbrev: "W"}],
    [LocationStatus.MISS, {abbrev: "M"}],
    [LocationStatus.EMPTY, {abbrev: "E"}],
])

export const getLocationStatusName = (locationStatus: LocationStatus): string | undefined =>
    LocationStatusProperties.get(locationStatus)?.abbrev

