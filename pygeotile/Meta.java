public class Meta {

	private static final double EARTH_RADIUS = 6378137.0;
	public static final int TILE_SIZE = 256;
	public static final double ORIGIN_SHIFT = 2.0 * Math.PI * EARTH_RADIUS / 2.0;
	private static final double INITIAL_RESOLUTION = 2.0 * Math.PI * EARTH_RADIUS / (float)TILE_SIZE;
	
	public static double resolution(int zoom) {
		return INITIAL_RESOLUTION / (Math.pow(2,zoom));
	}
}