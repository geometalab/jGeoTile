/**
 * @author Alejandro Gloor
 * @date 2018/10/12
 * @version 1.0
 *
 */

public class Point {
	
	private double latitude;
	private double longitude;
	
	/**
	 * Constructor
	 * @param latitude
	 * @param longitude 
	 */
	public Point(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 * Creates a point from latitude/longitude in WGS84
	 * @param latitude
	 * @param longitude
	 * @return Point
	 */
	public static Point fromLatitudeLongitude(double latitude, double longitude) {
        assert -180.0 <= longitude && longitude <= 180.0 :
        	"Longitude needs to be a value between -180.0 and 180.0.";
        assert -90.0 <= latitude && latitude <= 90.0 :
        	"Latitude needs to be a value between -90.0 and 90.0.";
		return new Point(latitude, longitude);
	}
	
	/**
	 * Creates a point from pixels X Y Z (zoom) in pyramid
	 * @param pixelX
	 * @param pixelY
	 * @param zoom
	 * @return Point
	 */
	public static Point fromPixel(int pixelX, int pixelY, int zoom) {
		int maxPixel = (int)((Math.pow(2,zoom)) * Meta.TILE_SIZE);
		assert 0 <= pixelX && pixelX <= maxPixel :
			"Point X needs to be a value between 0 and (2^zoom) * 256.";
		assert 0 <= pixelY && pixelY <= maxPixel :
			"Point Y needs to be a value between 0 and (2^zoom) * 256.";
		double meterX = pixelX * Meta.resolution(zoom) - Meta.ORIGIN_SHIFT;
		double meterY = pixelY * Meta.resolution(zoom) - Meta.ORIGIN_SHIFT;
		meterX = signMeterX(meterX, pixelX, zoom);
		meterY = signMeterY(meterY, pixelY, zoom);
		return fromMeters(meterX, meterY);
	}
	
	/**
	 * Creates a point from X Y Z (zoom) meters in Spherical Mercator EPSG:900913
	 * @param meterX
	 * @param meterY
	 * @return Point
	 */
	public static Point fromMeters(double meterX, double meterY) {
	    assert -Meta.ORIGIN_SHIFT <= meterX && meterX <= Meta.ORIGIN_SHIFT :
	        "Meter X needs to be a value between " + -Meta.ORIGIN_SHIFT + " and " + Meta.ORIGIN_SHIFT + ".";
	    assert -Meta.ORIGIN_SHIFT <= meterY && meterY <= Meta.ORIGIN_SHIFT :
	       "Meter Y needs to be a value between " + -Meta.ORIGIN_SHIFT + " and " + Meta.ORIGIN_SHIFT + ".";
	    double longitude = (meterX / Meta.ORIGIN_SHIFT) * 180.0;
	    double  latitude = (meterY / Meta.ORIGIN_SHIFT) * 180.0;
	    latitude = 180.0 / Math.PI * (2 * Math.atan(Math.exp(latitude * Math.PI / 180.0)) - Math.PI / 2.0);
		return new Point(latitude, longitude);
	}
	
	/**
	 * Gets latitude in WGS84
	 * @return latitude-coordinate
	 */
	public double getLatitude() {
		return latitude;
	}
	
	/**
	 * Gets longitude in WGS84
	 * @return longitude-coordinate
	 */
	public double getLongitude() {
		return longitude;
	}
	
	/**
	 * Gets pixel X of the EPSG:4326 pyramid by a specific zoom, converted from lat/lon in WGS84
	 * @param zoom
	 * @return pixelX
	 */
	public int getPixelX(int zoom) {
        int pixelX = (int)((getMeterX() + Meta.ORIGIN_SHIFT) / Meta.resolution(zoom));
        return Math.abs(Math.round(pixelX));
	}
	
	/**
	 * Gets pixel Y of the EPSG:4326 pyramid by a specific zoom, converted from lat/lon in WGS84
	 * @param zoom
	 * @return pixelY
	 */
	public int getPixelY(int zoom) {
        int pixelY = (int)((getMeterY() - Meta.ORIGIN_SHIFT) / Meta.resolution(zoom));
        return Math.abs(Math.round(pixelY));
	}
	
	/**
	 * Gets the X meter in Spherical Mercator EPSG:900913, converted from longitude in WGS84
	 * @return meterX
	 */
	public double getMeterX() {
        double meterX = longitude * Meta.ORIGIN_SHIFT / 180.0;
        return meterX;
	}
	
	/**
	 * Gets the Y meter in Spherical Mercator EPSG:900913, converted from latitude in WGS84
	 * @return meterY
	 */
	public double getMeterY() {
        double meterY = Math.log(Math.tan((90.0 + latitude) * Math.PI / 360.0)) / (Math.PI / 180.0);
        meterY = meterY * Meta.ORIGIN_SHIFT / 180.0;
        return meterY;
	}
	
	/**
	 * Checks if meter X ist bigger than halfsize
	 * @param meterX
	 * @param pixelX
	 * @param zoom
	 * @return meterX
	 */
	public static double signMeterX(double meterX, int pixelX, int zoom) {
		int halfSize = (int)((Meta.TILE_SIZE * Math.pow(2,zoom)) / 2);
		meterX = Math.abs(meterX);
		if (pixelX < halfSize) {
			meterX *= -1;
		}
		return meterX;
	}
	
	/**
	 * Checks if meter Y ist bigger than halfsize
	 * @param meterY
	 * @param pixelY
	 * @param zoom
	 * @return meterY
	 */
	public static double signMeterY(double meterY, int pixelY, int zoom) {
		int halfSize = (int)((Meta.TILE_SIZE * Math.pow(2,zoom)) / 2);
		meterY =  Math.abs(meterY);
		if (pixelY > halfSize) {
			meterY *= -1;
		}
		return meterY;
	}
}
