
public class Point {
	
	private double latitude = 0.0;
	private double longitude = 0.0;
	private double meterX = 0.0;
	private double meterY = 0.0;
	private int pixelX = 0;
	private int pixelY = 0;
	private int zoom = 0;
	
//	/**
//	 * Creates a point from lat/lon in WGS84
//	 * @return 
//	 */
//	public double fromLatitudeLongitude(double latitude, double longitude) {
//        assert -180.0 <= longitude <= 180.0; // Longitude needs to be a value between -180.0 and 180.0.
//        assert -90.0 <= latitude <= 90.0; //Latitude needs to be a value between -90.0 and 90.0.
//        this.latitude = latitude;
//        this.longitude = longitude;
//        return latitude, longitude;
//	}
//	
//	/**
//	 * Creates a point from pixels X Y Z (zoom) in pyramid
//	 * @return 
//	 */
//	public int fromPixel(int pixelX, int pixelY, int zoom) {
//        int maxPixel = (Math.pow(2,zoom)) * TILE_SIZE;
//        assert 0 <= pixelX <= max_pixel, //Point X needs to be a value between 0 and (2^zoom) * 256.
//        assert 0 <= pixelY <= max_pixel, //Point Y needs to be a value between 0 and (2^zoom) * 256.
//        meterX = pixelX * resolution(zoom) - ORIGIN_SHIFT;
//        meterY = pixelY * resolution(zoom) - ORIGIN_SHIFT;
//        meterX, meterY = cls._sign_meters(meters=(meterX, meterY), pixels=(pixelX, pixelY), zoom=zoom);
//        return cls.from_meters(meterX=meterX, meterY=meterY);
//	}
//	
//	/**
//	 * Creates a point from X Y Z (zoom) meters in Spherical Mercator EPSG:900913
//	 * @return 
//	 */
//	public double fromMeters(double meterX, double meterY) {
//        assert -ORIGIN_SHIFT <= meter_x <= ORIGIN_SHIFT, \
//            'Meter X needs to be a value between -{0} and {0}.'.format(ORIGIN_SHIFT);
//        assert -ORIGIN_SHIFT <= meter_y <= ORIGIN_SHIFT, \
//            'Meter Y needs to be a value between -{0} and {0}.'.format(ORIGIN_SHIFT);
//        longitude = (meterX / ORIGIN_SHIFT) * 180.0;
//        latitude = (meterY / ORIGIN_SHIFT) * 180.0;
//        latitude = 180.0 / Math.PI * (2 * Math.atan(Math.exp(latitude * Math.PI / 180.0)) - Math.PI / 2.0);
//        this.latitude = latitude;
//        this.longitude = longitude;
//        return latitude, longitude;
//	}
	
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
	 * @return pixelX
	 */
	public int getPixelX(int zoom) {
        meterX = getMeterX();
        pixelX = (int)((meterX + Meta.ORIGIN_SHIFT) / Meta.resolution(zoom));
        return Math.abs(Math.round(pixelX));
	}
	
	/**
	 * Gets pixel Y of the EPSG:4326 pyramid by a specific zoom, converted from lat/lon in WGS84
	 * @return pixelY
	 */
	public int getPixelY(int zoom) {
        meterY = getMeterY();
        pixelY = (int)((meterY - Meta.ORIGIN_SHIFT) / Meta.resolution(zoom));
        return Math.abs(Math.round(pixelY));
	}
	
	/**
	 * Gets the X meter in Spherical Mercator EPSG:900913, converted from longitude in WGS84
	 * @return meterX
	 */
	public double getMeterX() {
        longitude = getLongitude();
        meterX = longitude * Meta.ORIGIN_SHIFT / 180.0;
        return meterX;
	}
	
	/**
	 * Gets the Y meter in Spherical Mercator EPSG:900913, converted from latitude in WGS84
	 * @return meterY
	 */
	public double getMeterY() {
        latitude = getLatitude();
        meterY = Math.log(Math.tan((90.0 + latitude) * Math.PI / 360.0)) / (Math.PI / 180.0);
        meterY = meterY * Meta.ORIGIN_SHIFT / 180.0;
        return meterY;
	}
	
	/**
	 * 
	 * @return meterX
	 */
	public double signMeterX(double meterX, double meterY, int pixelX, int pixelY, int zoom) {
		int halfSize = (int)((Meta.TILE_SIZE * Math.pow(2,zoom)) / 2);
		this.pixelX = pixelX;
		this.meterX = meterX;
		meterX = Math.abs(meterX);
		if (pixelX < halfSize) {
			meterX *= -1;
		}
		return meterX;
	}
	
	/**
	 * 
	 * @return meterY
	 */
	public double signMeterY(double meterX, double meterY, int pixelX, int pixelY, int zoom) {
		int halfSize = (int)((Meta.TILE_SIZE * Math.pow(2,zoom)) / 2);
		this.pixelY = pixelY;
		this.meterY = meterY;
		meterY =  Math.abs(meterY);
		if (pixelY > halfSize) {
			meterY *= -1;
		}
		return meterY;
	}
}
