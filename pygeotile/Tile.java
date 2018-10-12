import java.lang.Math;
//import Point;
//import java.util.regex;

public class Tile {
	int zoom;
	int tmsX;
	int tmsY;
	float tileSize = Meta.TILE_SIZE;

	private Tile(int tms_x, int tms_y, int zoom) {
		tms_x = this.tmsX;
		tms_y = this.tmsY;
		zoom = this.zoom;
	}

	public Tile fromQuadTree(String quadTree) throws Exception {
		// """Creates a tile from a Microsoft QuadTree"""
		zoom = quadTree.length();
		int googleX = 0;
		int googleY = 0;

		for (int i = zoom; i > 0; --i) {
			int mask = 1 << (i - 1);
			switch (quadTree.toCharArray()[zoom - i]) {
			case '0':
				break;

			case '1':
				googleX |= mask;

			case '2':
				googleY |= mask;

			case '3':
				googleX |= mask;
				googleY |= mask;

			default:
				throw new Exception("QuadTree value can only consists of the digits 0, 1, 2 and 3.");
			}
		}
		tmsX = googleX;
		tmsY = googleY;

		return new Tile(tmsX, tmsY, zoom);
	}

	public Tile fromTms(int zoom, int tmsX, int tmsY) {
		// """Creates a tile from Tile Map Service (TMS) X Y and zoom"""
		int max_tile = (2 * zoom) - 1;
		if (0 > tmsX || tmsX > max_tile) {
			System.out.println("TMS X needs to be a value between 0 and (2^zoom) -1.");
		}

		if (0 > tmsY || tmsY > max_tile) {
			System.out.println("TMS Y needs to be a value between 0 and (2^zoom) -1.");
		}
		//
		return new Tile(tmsX, tmsY, zoom);
	}

	public Tile fromGoogle(int googleX, int googleY, int zoom) {
		// """Creates a tile from Google format X Y and zoom"""
		int max_tile = (2 * zoom) - 1;
		if (0 > googleX || googleX > max_tile) {
			System.out.println("Google X needs to be a value between 0 and (2^zoom) -1.");
		}
		if (0 > googleY || googleY > max_tile) {
			System.out.println("Google Y needs to be a value between 0 and (2^zoom) -1.");
		}
		tmsX = googleX;
		tmsY = (2 * zoom - 1) - googleY;
		return new Tile(tmsX, tmsY, zoom);
	}

	public void forPoint(/*Point point,*/ int zoom) {
		// """Creates a tile for given point"""
		//TODO when Point is implemented
	}

	public Tile forPixels(int pixelX, int pixelY, int zoom) {
		// """Creates a tile from pixels X Y Z (zoom) in pyramid"""
		tmsX = (int) (Math.ceil(pixelX / tileSize)-1);
		tmsY = (int) (Math.ceil(pixelY / tileSize)-1);
		
		return new Tile(tmsX, tmsY, zoom);
	}

	public void forMeters(int meter_x, int meter_y, int zoom) {
		// """Creates a tile from X Y meters in Spherical Mercator EPSG:900913"""
		
	}

	public void forLatitudeLongitude(int latitude, int longitude, int zoom) {
		// """Creates a tile from lat/lon in WGS84"""
	}
	public int[] getTms() {
		//gives out tmsX and tmsY in an int array
		int [] tms = {tmsX,tmsY};
		return tms;
	}
	public int getTmsX() {
		return tmsX;
	}

	public int getTmsY() {
		return tmsY;
	}

	public String quadTree() {
		// """Gets the tile in the Microsoft QuadTree format, converted from TMS"""
		StringBuilder quadKey = new StringBuilder();
		for(int i = zoom; i > 0; --i) {
			char digit = '0';
			int mask = 1 << (i -1);
			if ((tmsX & mask) != 0) {
				digit++;
			}
			if((tmsY & mask)!=0) {
				digit++;
				digit++;
			}
			quadKey.append(digit);
		}
		return quadKey.toString();

	}

	public int[] getGoogle() {
		// """Gets the tile in the Google format, converted from TMS"""
		int[] google = {tmsX, (2*zoom-1)-tmsY};
		return google;
	}

	public void bounds() {
		// """Gets the bounds of a tile represented as the most west and south point and
		// the most east and north point"""

	}
}
