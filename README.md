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

System.out.println("Pixels: (" + point.getPixelX(zoom) + ", " + point.getPixelY(zoom) + ")"); // Pixels: (34430592, 49899136)
System.out.println("Lat/Lon: (" + point.getLatitude() + ", " + getLongitude() + ")"); // Lat/Lon: (41.84987190947754, -87.64995574951166)
```

### Tile

Example of the class Tile.

```java
int tmsX = 134494;
int tmsY = 329369;
int zoom = 19;

// Tile Map Service (TMS) X Y and zoom
Tile tile = Tile.fromTms(tmsX, tmsY, zoom)

System.out.println("QuadTree: " + getQuadTree());  // QuadTree: 0302222310303211330
System.out.println("Google: (" + tile.getGoogleX() + ", " + tile.getGoogleY() + ")");  // Google: (134494, 194918)
```

## Installation

To install jGeoTile, simply:

```bash
# TODO
```

## Notes

This repository is inspired by:

 - pyGeoTile: https://github.com/geometalab/pyGeoTile
 - Tiles à la Google Maps: http://www.maptiler.org/google-maps-coordinates-tile-bounds-projection/
 - Bing Maps Tile System: https://msdn.microsoft.com/en-us/library/bb259689.aspx
 - Showing pixel and tile coordinates: https://developers.google.com/maps/documentation/javascript/examples/map-coordinates?hl=de
 - Mercantile: https://github.com/mapbox/mercantile
