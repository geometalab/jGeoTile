
public class Meta {

	private final double EARTH_RADIUS = 6378137.0;
	public final int TILE_SIZE = 256;
	public final double ORIGIN_SHIFT = 2.0 * Math.PI * EARTH_RADIUS / 2.0;
	private final double INITIAL_RESOLUTION = 2.0 * Math.PI * EARTH_RADIUS / (float)TILE_SIZE;
	
	public double resolution(int zoom) {
		return INITIAL_RESOLUTION / (Math.pow(2,zoom));
	}
}
