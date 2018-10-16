import java.lang.Math;

public class Tile {
	static int zoom;
	static int tmsX;
	static int tmsY;
	static float tileSize = Meta.TILE_SIZE;

	/**
	 * Constructor for Tile
	 * 
	 * @param longitude
	 * @param latitude
	 * @param zoom
	 */
	private Tile(double longitude, double latitude, int zoom) {
		longitude = this.tmsX;
		latitude = this.tmsY;
		zoom = this.zoom;
	}

	/**
	 * 
	 * @param quadTree
	 * @return
	 * @throws Exception
	 */
	public static Tile fromQuadTree(String quadTree) {
		// """Creates a tile from a Microsoft QuadTree"""
		zoom = quadTree.length();
		int googleX = 0;
		int googleY = 0;
		assert quadTree.matches("^[0-3]*$");
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
				System.out.print("Error");
			}
		}
		tmsX = googleX;
		tmsY = googleY;
		
		return new Tile(tmsX, tmsY, zoom);
	}

	/**
	 * 
	 * @param zoom
	 * @param tmsX
	 * @param tmsY
	 * @return
	 */
	public static Tile fromTms(int tmsX, int tmsY,int zoom) {
		// """Creates a tile from Tile Map Service (TMS) X Y and zoom"""
		double max_tile = Math.pow(2 , zoom) - 1;
		assert 0 <= tmsX && tmsX <= max_tile:"TMS X needs to be a value between 0 and (2^zoom) -1.";
		assert 0 <= tmsY && tmsY <= max_tile:"TMS Y needs to be a value between 0 and (2^zoom) -1.";

		//
		return new Tile(tmsX, tmsY, zoom);
	}

	/**
	 * 
	 * @param googleX
	 * @param googleY
	 * @param zoom
	 * @return
	 */
	public static Tile fromGoogle(int googleX, int googleY, int zoom) {
		// """Creates a tile from Google format X Y and zoom"""
		double max_tile = Math.pow(2 , zoom) - 1;
		assert 0 <= googleX && googleX <= max_tile:"Google X needs to be a value between 0 and (2^zoom) -1.";
		assert 0 <= googleY && googleY <= max_tile:"Google Y needs to be a value between 0 and (2^zoom) -1.";
		tmsX = googleX;
		tmsY = (int) (Math.pow(2 , zoom - 1) - googleY);
		return new Tile(tmsX, tmsY, zoom);
	}

	/**
	 * 
	 * @param point
	 * @param zoom
	 * @return
	 */
	public static Tile forPoint(Point point, int zoom) {
		// """Creates a tile for given point"""
		double latitude = point.getLatitude();
		double longitude = point.getLongitude();
		return new Tile(longitude, latitude, zoom);
	}

	/**
	 * 
	 * @param pixelX
	 * @param pixelY
	 * @param zoom
	 * @return
	 */
	public static Tile forPixels(int pixelX, int pixelY, int zoom) {
		// """Creates a tile from pixels X Y Z (zoom) in pyramid"""
		tmsX = (int) (Math.ceil(pixelX / tileSize) - 1);
		tmsY = (int) (Math.ceil(pixelY / tileSize) - 1);
		tmsY = (int) (Math.pow(2, zoom-1)-tmsY);

		return new Tile(tmsX, tmsY, zoom);
	}

	/**
	 * 
	 * @param meterX
	 * @param meterY
	 * @param zoom
	 * @return
	 */
	public static Tile forMeters(double meterX, double meterY, int zoom) {
		// """Creates a tile from X Y meters in Spherical Mercator EPSG:900913"""
		Point point = Point.fromMeters(meterX, meterY);
		int pixelX2 = point.getPixelX(zoom);
		int pixelY2 = point.getPixelY(zoom);
		return forPixels(pixelX2, pixelY2, zoom);
	}

	/**
	 * 
	 * @param latitude
	 * @param longitude
	 * @param zoom
	 * @return
	 */
	public static Tile forLatitudeLongitude(double latitude, double longitude, int zoom) {
		// """Creates a tile from lat/lon in WGS84"""
		Point point = Point.fromLatitudeLongitude(latitude, longitude);
		int pixelX = point.getPixelX(zoom);
		int pixelY = point.getPixelY(zoom);
		return forPixels(pixelX, pixelY, zoom);
	}

	/**
	 * 
	 * @return retunrs an Int array containing the X and Y values from this TMS
	 */
	public int[] getTms() {
		// gives out tmsX and tmsY in an int array
		int[] tms = { tmsX, tmsY };
		return tms;
	}

	/**
	 * 
	 * @return Returns the QuadTree String converted from TMS
	 */
	public static String quadTree() {
		// """Gets the tile in the Microsoft QuadTree format, converted from TMS"""
		StringBuilder quadKey = new StringBuilder();
		for (int i = zoom; i > 0; --i) {
			char digit = '0';
			int mask = 1 << (i - 1);
			if ((tmsX & mask) != 0) {
				digit++;
			}
			if ((tmsY & mask) != 0) {
				digit++;
				digit++;
			}
			quadKey.append(digit);
		}
		return quadKey.toString();

	}

	/**
	 * 
	 * @return An int Array containing the X and Y values for the Google format,
	 *         converted from TMS
	 */
	public static int[] getGoogle() {
		// """Gets the tile in the Google format, converted from TMS"""
		int[] google = { tmsX, (int) (Math.pow(2 , zoom - 1) - tmsY) };
		return google;
	}

	/**
	 * 
	 * @return A Point Array that containst bottom right and the top Left point of
	 *         the Tile.
	 * 
	 */
	public static Point[] bounds() {
		// """Gets the bounds of a tile represented as the most west and south point and
		// the most east and north point"""
		int googleX = getGoogle()[0];
		int googleY = getGoogle()[1];
		int pixelXWest = (int) (googleX * tileSize);
		int pixelYNorth = (int) (googleY * tileSize);
		int pixelXEast = (int) ((googleX + 1) * tileSize);
		int pixelYSouth = (int) ((googleY + 1) * tileSize);

		Point pointMin = Point.fromPixel(pixelXWest, pixelYSouth, zoom);
		Point pointMax = Point.fromPixel(pixelXEast, pixelYNorth, zoom);
		Point[] bounds = { pointMin, pointMax };
		return bounds;

	}
}
