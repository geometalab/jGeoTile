[![Build Status](https://travis-ci.org/geometalab/pyGeoTile.svg?branch=master)](https://travis-ci.org/geometalab/pyGeoTile)
# javGeoTile
Java package to handle tiles and points of different projections, in particular WGS 84 (Latitude, Longitude), Spherical Mercator (Meters), Pixel Pyramid and Tiles (TMS, Google, QuadTree)

## Usage
The package javGeoTile consist of two main classes, namely Point and Tile.
As already mentioned they allow you to convert various geo projections.

The full API documentation could be found under //LINK

### Point
Example of the class Point.
```java
// meters in Spherical Mercator EPSG:900913
meterX = -9757148.442088600;
meterY = 5138517.444985110
zoom = 19

point = Point.from_meters(meter_x=meter_x, meter_y=meter_y)

print('Pixels: ', point.pixels(zoom=zoom))  # Pixels:  (34430592, 49899136)
print('Lat/Lon: ', point.latitude_longitude)  # Lat/Lon:  (41.84987190947754, -87.64995574951166)
```

### Tile
Example of the class Tile.
```python
from pygeotile.tile import Tile

tms_x, tms_y, zoom = 134494, 329369, 19
tile = Tile.from_tms(tms_x=tms_x, tms_y=tms_y, zoom=19)  # Tile Map Service (TMS) X Y and zoom

print('QuadTree: ', tile.quad_tree)  # QuadTree:  0302222310303211330
print('Google: ', tile.google)  # Google:  (134494, 194918)
```

## Installation
To install javGeoTile, simply:
```bash
//TODO

```
javGeoTile officially supports Java //TODO

## Notes
This repository is inspired by:
 - pyGeoTile from Samuel
pyGeoTile is inspired by:
 - Tiles à la Google Maps: http://www.maptiler.org/google-maps-coordinates-tile-bounds-projection/
 - Bing Maps Tile System: https://msdn.microsoft.com/en-us/library/bb259689.aspx
 - Showing pixel and tile coordinates: https://developers.google.com/maps/documentation/javascript/examples/map-coordinates?hl=de
 - Mercantile: https://github.com/mapbox/mercantile
