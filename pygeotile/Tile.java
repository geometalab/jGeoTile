import java.lang.Math;

public class Tile {
	private int zoom;
	private int tmsX;
	private int tmsY;
	static float tileSize = Meta.TILE_SIZE;

	/**
	 * Constructor for Tile
	 * 
	 * @param tmsY
	 * @param tmsX
	 * @param zoom
	 */
	private Tile(int tmsX, int tmsY, int zoom) {
		this.tmsY = tmsY;
		this.tmsX = tmsX;
		this.setZoom(zoom);
	}

	/**
	 * Creates a tile from a Microsoft QuadTree
	 * @param quadTree
	 * @return
	 * @throws Exception
	 */
	public static Tile fromQuadTree(String quadTree) {
		final int levelOfDetail = quadTree.length();
		int googleX = 0;
		int googleY = 0;
		int offset = (int) (Math.pow(2, levelOfDetail))-1;
		assert quadTree.matches("^[0-3]*$"):"QuadTree must only contain numbers from 0-3";
		for (int i = levelOfDetail; i > 0; i--) {
			int mask = 1 << (i - 1);
			switch (quadTree.charAt(levelOfDetail - i)) {
			case '0':
				break;

			case '1':
				googleX += mask;
				break;

			case '2':
				googleY += mask;
				break;

			case '3':
				googleX += mask;
				googleY += mask;
				break;

			default:
				break;
			}
		}
		int tmsX = googleX;
		int tmsY = offset - googleY;
		return new Tile(tmsX, tmsY, levelOfDetail);
	}

	/**
	 * Creates a tile from Tile Map Service (TMS) X Y and zoom
	 * @param zoom
	 * @param tmsX
	 * @param tmsY
	 * @return
	 */
	public static Tile fromTms(int tmsX, int tmsY, int zoom) {
		double max_tile = Math.pow(2, zoom) - 1;
		assert 0 <= tmsX && tmsX <= max_tile : "TMS X needs to be a value between 0 and (2^zoom) -1.";
		assert 0 <= tmsY && tmsY <= max_tile : "TMS Y needs to be a value between 0 and (2^zoom) -1.";

		//
		return new Tile(tmsX, tmsY, zoom);
	}

	/**
	 * Creates a tile from Google format X Y and zoom
	 * @param googleX
	 * @param googleY
	 * @param zoom
	 * @return
	 */
	public static Tile fromGoogle(int googleX, int googleY, int zoom) {
		double max_tile = Math.pow(2, zoom) - 1;
		assert 0 <= googleX && googleX <= max_tile : "Google X needs to be a value between 0 and (2^zoom) -1.";
		assert 0 <= googleY && googleY <= max_tile : "Google Y needs to be a value between 0 and (2^zoom) -1.";
		int tmsX = googleX;
		int tmsY = googleY;
		tmsY = (int) (Math.pow(2, zoom) - 1 - tmsY);
		return new Tile(tmsX, tmsY, zoom);
	}

	/**
	 * Creates a tile for given point
	 * @param point
	 * @param zoom
	 * @return
	 */
	public static Tile forPoint(Point point, int zoom) {
		double latitude = point.getLatitude();
		double longitude = point.getLongitude();
		return forLatitudeLongitude(latitude, longitude, zoom);
	}

	/**
	 * Creates a tile from pixels X Y Z (zoom) in pyramid
	 * @param pixelX
	 * @param pixelY
	 * @param zoom
	 * @return
	 */
	public static Tile forPixels(int pixelX, int pixelY, int zoom) {
		int tmsX = (int) (Math.ceil(pixelX / tileSize) - 1);
		int tmsY = (int) (Math.ceil(pixelY / tileSize) - 1);
		tmsY = (int) (Math.pow(2, zoom) - 1 - tmsY);

		return new Tile(tmsX, tmsY, zoom);
	}

	/**
	 * Creates a tile from X Y meters in Spherical Mercator EPSG:900913
	 * @param meterX
	 * @param meterY
	 * @param zoom
	 * @return
	 */
	public static Tile forMeters(double meterX, double meterY, int zoom) {
		Point point = Point.fromMeters(meterX, meterY);
		int pixelX = point.getPixelX(zoom);
		int pixelY = point.getPixelY(zoom);
		return forPixels(pixelX, pixelY, zoom);
	}

	/**
	 * Creates a tile from lat/lon in WGS84
	 * @param latitude
	 * @param longitude
	 * @param zoom
	 * @return
	 */
	public static Tile forLatitudeLongitude(double latitude, double longitude, int zoom) {
		Point point = Point.fromLatitudeLongitude(latitude, longitude);
		int pixelX = point.getPixelX(zoom);
		int pixelY = point.getPixelY(zoom);
		return forPixels(pixelX, pixelY, zoom);
	}

	/**
	 *  gives out tmsX and tmsY in an int array
	 * @return int[]
	 */
	public int[] getTms() {
		int[] tms = {tmsX, tmsY };
		return tms;
	}

	/**
	 * Gets the tile in the Microsoft QuadTree format, converted from TMS
	 * @return String
	 */
	public String quadTree() {
		StringBuilder quadKey = new StringBuilder();
		for (int i = getZoom(); i > 0; --i) {
			char digit = '0';
			int mask = 1 << (i - 1);
			if ((tmsX & mask) != 0) {
				digit++;
			}
			if ((tmsY & mask) != 0) {
				digit += 2;
			}
			quadKey.append(digit);
		}
		return quadKey.toString();

	}

	/**
	 * Gets the tile in the Google format, converted from TMS
	 * @return int[]
	 */
	public int[] getGoogle() {
		int[] google = { tmsX, (int) (Math.pow(2, getZoom()) - 1 - tmsY) };
		return google;
	}

	/**
	 * Gets the bounds of a tile represented as the most west and south point and
	 * the most east and north point
	 * @return A Point Array that containst bottom right and the top Left point of
	 *         the Tile.
	 * 
	 */
	public Point[] bounds() {
		int googleX = getGoogle()[0];
		int googleY = getGoogle()[1];
		int pixelXWest = (int) (googleX * tileSize);
		int pixelYNorth = (int) (googleY * tileSize);
		int pixelXEast = (int) ((googleX + 1) * tileSize);
		int pixelYSouth = (int) ((googleY + 1) * tileSize);

		Point pointMin = Point.fromPixel(pixelXWest, pixelYSouth, getZoom());
		Point pointMax = Point.fromPixel(pixelXEast, pixelYNorth, getZoom());
		Point[] bounds = { pointMin, pointMax };
		return bounds;

	}
	
	private int getZoom() {
		return zoom;
	}

	private void setZoom(int zoom) {
		this.zoom = zoom;
	}
	
}
