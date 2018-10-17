# jGeoTile

Java package to handle tiles and points of different projections, in particular WGS 84 (Latitude, Longitude), Spherical Mercator (Meters), Pixel Pyramid and Tiles (TMS, Google, QuadTree). This package is inspired by pyGeoTile, which is the same in Python.

## Usage

The package jGeoTile consist of two main classes, namely Point and Tile.
As already mentioned they allow you to convert various geo projections.

### Point

Example of the class Point.

```java
// Meters in Spherical Mercator EPSG:900913
double meterX = -9757148.442088600;
double meterY = 5138517.444985110;
int zoom = 19;

Point point = Point.fromMeters(meterX, meterY);

// Pixels:  (34430592, 49899136)
System.out.println("Pixels: " + point.pixels(zoom));

// Lat/Lon:  (41.84987190947754, -87.64995574951166)
System.out.println("Lat/Lon: " + point.latitudeLongitude());
```

### Tile

Example of the class Tile.

```java
// TODO
```

## Installation

To install jGeoTile, simply:

```bash
//TODO
```

## Notes

This repository is inspired by:

 - pyGeoTile: https://github.com/geometalab/pyGeoTile
 - Tiles à la Google Maps: http://www.maptiler.org/google-maps-coordinates-tile-bounds-projection/
 - Bing Maps Tile System: https://msdn.microsoft.com/en-us/library/bb259689.aspx
 - Showing pixel and tile coordinates: https://developers.google.com/maps/documentation/javascript/examples/map-coordinates?hl=de
 - Mercantile: https://github.com/mapbox/mercantile
