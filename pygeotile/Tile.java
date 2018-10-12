import java.lang.Math;
//import Point;
//import java.util.regex;

public class Tile {
	int zoom;
	int tms_x;
	int tms_y;

	private Tile(int tms_x, int tms_y, int zoom) {
		tms_x = this.tms_x;
		tms_y = this.tms_y;
		zoom = this.zoom;
	}

	public Tile fromQuadTree(String quadTree) throws Exception {
		// """Creates a tile from a Microsoft QuadTree"""
		zoom = quadTree.length();

		double offset = (Math.pow(2, zoom)) - 1;
		int google_x = 0;
		int google_y = 0;

		for (int i = zoom; i > 0; --i) {
			int mask = 1 << (i - 1);
			switch (quadTree.toCharArray()[zoom - i]) {
			case '0':
				break;

			case '1':
				google_x |= mask;

			case '2':
				google_y |= mask;

			case '3':
				google_x |= mask;
				google_y |= mask;

			default:
				throw new Exception("QuadTree value can only consists of the digits 0, 1, 2 and 3.");
			}
		}
		tms_x = google_x;
		tms_y = google_y;

		return new Tile(tms_x, tms_y, zoom);
	}

	public Tile fromTms(int zoom, int tms_x, int tms_y) {
		// """Creates a tile from Tile Map Service (TMS) X Y and zoom"""
		int max_tile = (2 * zoom) - 1;
		// assert 0 <= tms_x <= max_tile, 'TMS X needs to be a value between 0 and
		// (2^zoom) -1.';
		// assert 0 <= tms_y <= max_tile, 'TMS Y needs to be a value between 0 and
		// (2^zoom) -1.';
		// Java
		if (0 > tms_x || tms_x > max_tile) {
			System.out.println("TMS X needs to be a value between 0 and (2^zoom) -1.");
		}

		if (0 > tms_y || tms_y > max_tile) {
			System.out.println("TMS Y needs to be a value between 0 and (2^zoom) -1.");
		}
		//
		return new Tile(tms_x, tms_y, zoom);
	}

	public Tile fromGoogle(int google_x,int google_y,int zoom) {
			//"""Creates a tile from Google format X Y and zoom"""
	        int max_tile = (2 * zoom) - 1;
	        //assert 0 <= google_x <= max_tile, 'Google X needs to be a value between 0 and (2^zoom) -1.'
	        //assert 0 <= google_y <= max_tile, 'Google Y needs to be a value between 0 and (2^zoom) -1.'
	        //Java
	        if(0 > google_x || google_x > max_tile) {
	        	System.out.println("Google X needs to be a value between 0 and (2^zoom) -1.");
	        }
	        if(0 > google_y || google_y > max_tile) {
	        	System.out.println("Google Y needs to be a value between 0 and (2^zoom) -1.");
	        }
			tms_x = google_x;
			tms_y = (2*zoom-1)-google_y;
			return new Tile(tms_x, tms_y, zoom);
		}

	public void forPoint(int point, int zoom) {
		// """Creates a tile for given point"""
	}

	public void forPixels(int pixel_x, int pixel_y, int zoom) {
		// """Creates a tile from pixels X Y Z (zoom) in pyramid"""
	}

	public void forMeters(int meter_x, int meter_y, int zoom) {
		// """Creates a tile from X Y meters in Spherical Mercator EPSG:900913"""
	}

	public void forLatitudeLongitude(int latitude, int longitude, int zoom) {
		// """Creates a tile from lat/lon in WGS84"""
	}

	public void getTmsX() {
	}

	public void getTmsY() {
	}

	public void quadTree() {
		// """Gets the tile in the Microsoft QuadTree format, converted from TMS"""

	}

	public void getGoogle() {
		// """Gets the tile in the Google format, converted from TMS"""
	}

	public void bounds() {
		// """Gets the bounds of a tile represented as the most west and south point and
		// the most east and north point"""

	}
}
