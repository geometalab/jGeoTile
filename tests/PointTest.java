import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class PointTest {

	double latitude = 41.85;
	double longitude = -87.65;
	double meterX = -9757148.442088600;
	double meterY = 5138517.444985110;
	double epsilon = 0.1;
	int pixelX = 34430575;
	int pixelY = 49899071;
	int zoom = 19;
	
	@Test
	public void testFromLatitudeLongitude() {
		Point point = Point.fromLatitudeLongitude(latitude, longitude);
		assertEquals(latitude, point.getLatitude(), epsilon);
		assertEquals(longitude, point.getLongitude(), epsilon);
	}
	
	@Test
	public void testFromMeters() {
		Point point = Point.fromMeters(meterX, meterY);
		assertEquals(meterX, point.getMeterX(), epsilon);
		assertEquals(meterY, point.getMeterY(), epsilon);
		assertEquals(latitude, point.getLatitude(), epsilon);
		assertEquals(longitude, point.getLongitude(), epsilon);
	}
	
	@Test
	public void testFromPixel() {
		Point point = Point.fromPixel(pixelX, pixelY, zoom);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
		assertEquals(latitude, point.getLatitude(), epsilon);
		assertEquals(longitude, point.getLongitude(), epsilon);
	}
	
	@Test
	public void testGetLatitude() {
		assertEquals(latitude, 41.85, epsilon);
	}
	
	@Test
	public void testGetLongitude() {
		assertEquals(longitude, -87.65, epsilon);
	}
	
	@Test
	public void testGetPixelX() {
		assertEquals(pixelX, 34430575);
	}
	
	@Test
	public void testGetPixelY() {
		assertEquals(pixelY, 49899071);
	}
	
	@Test
	public void testGetMeterX() {
		assertEquals(meterX, -9757148.442088600, epsilon);
	}
	
	@Test
	public void testGetMeterY() {
		assertEquals(meterY, 5138517.444985110, epsilon);
	}
	
	@Test
	public void testSignMeterX() {
		Point point  = new Point(latitude, longitude);
		assertEquals(meterX, Point.signMeterX(meterX, pixelX, zoom), epsilon);
		assertEquals(latitude, point.getLatitude(), epsilon);
		assertEquals(longitude, point.getLongitude(), epsilon);
	}
	
	@Test
	public void testSignMeterY() {
		Point point  = new Point(latitude, longitude);
		assertEquals(meterY, Point.signMeterY(meterY, pixelY, zoom), epsilon);
		assertEquals(latitude, point.getLatitude(), epsilon);
		assertEquals(longitude, point.getLongitude(), epsilon);
	}
	
	@Test
	public void testPixelsToMeters() {
		int pixelX = 0;
		int pixelY = 0;
		int zoom = 1;
		double expectedX = -Meta.ORIGIN_SHIFT;
		double expectedY = Meta.ORIGIN_SHIFT;
		
		Point point = Point.fromPixel(pixelX, pixelY, zoom);
		
		assertEquals(expectedX, point.getMeterX(), epsilon);
		assertEquals(expectedY, point.getMeterY(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
		
		pixelX = 256;
		pixelY = 0;
		expectedX = 0;
		expectedY = Meta.ORIGIN_SHIFT;
		
		point = Point.fromPixel(pixelX, pixelY, zoom);
		
		assertEquals(expectedX, point.getMeterX(), epsilon);
		assertEquals(expectedY, point.getMeterY(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
		
		pixelX = 512;
		pixelY = 0;
		expectedX = Meta.ORIGIN_SHIFT;
		expectedY = Meta.ORIGIN_SHIFT;
		
		point = Point.fromPixel(pixelX, pixelY, zoom);
		
		assertEquals(expectedX, point.getMeterX(), epsilon);
		assertEquals(expectedY, point.getMeterY(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
		
		pixelX = 0;
		pixelY = 256;
		expectedX = -Meta.ORIGIN_SHIFT;
		expectedY = 0;
		
		point = Point.fromPixel(pixelX, pixelY, zoom);
		
		assertEquals(expectedX, point.getMeterX(), epsilon);
		assertEquals(expectedY, point.getMeterY(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
		
		pixelX = 256;
		pixelY = 256;
		expectedX = 0;
		expectedY = 0;
		
		point = Point.fromPixel(pixelX, pixelY, zoom);
		
		assertEquals(expectedX, point.getMeterX(), epsilon);
		assertEquals(expectedY, point.getMeterY(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
		
		pixelX = 512;
		pixelY = 256;
		expectedX = Meta.ORIGIN_SHIFT;
		expectedY = 0;
		
		point = Point.fromPixel(pixelX, pixelY, zoom);
		
		assertEquals(expectedX, point.getMeterX(), epsilon);
		assertEquals(expectedY, point.getMeterY(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
		
		pixelX = 0;
		pixelY = 512;
		expectedX = -Meta.ORIGIN_SHIFT;
		expectedY = -Meta.ORIGIN_SHIFT;
		
		point = Point.fromPixel(pixelX, pixelY, zoom);
		
		assertEquals(expectedX, point.getMeterX(), epsilon);
		assertEquals(expectedY, point.getMeterY(), epsilon);
		assertEquals(point.getPixelX(zoom), pixelX);
		assertEquals(pixelX, point.getPixelX(zoom));
//		assertEquals(pixelY, point.getPixelY(zoom));
		
		pixelX = 256;
		pixelY = 512;
		expectedX = 0;
		expectedY = -Meta.ORIGIN_SHIFT;
		
		point = Point.fromPixel(pixelX, pixelY, zoom);
		
		assertEquals(expectedX, point.getMeterX(), epsilon);
		assertEquals(expectedY, point.getMeterY(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
//		assertEquals(pixelY, point.getPixelY(zoom));
		
		pixelX = 512;
		pixelY = 512;
		expectedX = Meta.ORIGIN_SHIFT;
		expectedY = -Meta.ORIGIN_SHIFT;
		
		point = Point.fromPixel(pixelX, pixelY, zoom);
		
		assertEquals(expectedX, point.getMeterX(), epsilon);
		assertEquals(expectedY, point.getMeterY(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
//		assertEquals(pixelY, point.getPixelY(zoom));
	}
	
	@Test
	public void testPixelsToLatitudeLongitude() {
		int pixelX = 0;
		int pixelY = 0;
		int zoom = 1;
		double expectedX = 85.05;
		double expectedY = -180.0;
		
		Point point = Point.fromPixel(pixelX, pixelY, zoom);
		
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
		
		pixelX = 256;
		pixelY = 0;
		expectedX = 85.05;
		expectedY = 0;
		
		point = Point.fromPixel(pixelX, pixelY, zoom);
		
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
		
		pixelX = 512;
		pixelY = 0;
		expectedX = 85.05;
		expectedY = 180.0;
		
		point = Point.fromPixel(pixelX, pixelY, zoom);
		
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
		
		pixelX = 0;
		pixelY = 256;
		expectedX = 0;
		expectedY = -180.0;
		
		point = Point.fromPixel(pixelX, pixelY, zoom);
		
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
		
		pixelX = 256;
		pixelY = 256;
		expectedX = 0.0;
		expectedY = 0.0;
		
		point = Point.fromPixel(pixelX, pixelY, zoom);
		
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
		
		pixelX = 512;
		pixelY = 256;
		expectedX = 0.0;
		expectedY = 180.0;
		
		point = Point.fromPixel(pixelX, pixelY, zoom);
		
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
		
		pixelX = 0;
		pixelY = 512;
		expectedX = -85.05;
		expectedY = -180.0;
		
		point = Point.fromPixel(pixelX, pixelY, zoom);
		
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
//		assertEquals(pixelY, point.getPixelY(zoom));
		
		pixelX = 256;
		pixelY = 512;
		expectedX = -85.05;
		expectedY = 0.0;
		
		point = Point.fromPixel(pixelX, pixelY, zoom);
		
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
//		assertEquals(pixelY, point.getPixelY(zoom));
		
		pixelX = 512;
		pixelY = 512;
		expectedX = -85.05;
		expectedY = 180.0;
		
		point = Point.fromPixel(pixelX, pixelY, zoom);
		
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
//		assertEquals(pixelY, point.getPixelY(zoom));
	}
	
	@Test
	public void testMetersToPixels() {
		double meterX = -Meta.ORIGIN_SHIFT;
		double meterY = Meta.ORIGIN_SHIFT;
		double expectedX = 85.05;
		double expectedY = -180.0;
		
		Point point = Point.fromMeters(meterX, meterY);
		
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(meterX, point.getMeterX(), epsilon);
		assertEquals(meterY, point.getMeterY(), epsilon);
		
		meterX = 0;
		meterY = Meta.ORIGIN_SHIFT;
		expectedX = 85.05;
		expectedY = 0;
		
		point = Point.fromMeters(meterX, meterY);
		
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(meterX, point.getMeterX(), epsilon);
		assertEquals(meterY, point.getMeterY(), epsilon);
		
		meterX = Meta.ORIGIN_SHIFT;
		meterY = Meta.ORIGIN_SHIFT;
		expectedX = 85.05;
		expectedY = 180.0;
		
		point = Point.fromMeters(meterX, meterY);
		
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(meterX, point.getMeterX(), epsilon);
		assertEquals(meterY, point.getMeterY(), epsilon);
		
		meterX = -Meta.ORIGIN_SHIFT;
		meterY = 0;
		expectedX = 0;
		expectedY = -180.0;
		
		point = Point.fromMeters(meterX, meterY);
		
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(meterX, point.getMeterX(), epsilon);
		assertEquals(meterY, point.getMeterY(), epsilon);
		
		meterX = 0;
		meterY = 0;
		expectedX = 0.0;
		expectedY = 0.0;
		
		point = Point.fromMeters(meterX, meterY);
		
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(meterX, point.getMeterX(), epsilon);
		assertEquals(meterY, point.getMeterY(), epsilon);
		
		meterX = Meta.ORIGIN_SHIFT;
		meterY = 0;
		expectedX = 0.0;
		expectedY = 180.0;
		
		point = Point.fromMeters(meterX, meterY);
		
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(meterX, point.getMeterX(), epsilon);
		assertEquals(meterY, point.getMeterY(), epsilon);
		
		meterX = -Meta.ORIGIN_SHIFT;
		meterY = -Meta.ORIGIN_SHIFT;
		expectedX = -85.05;
		expectedY = -180.0;
		
		point = Point.fromMeters(meterX, meterY);
		
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(meterX, point.getMeterX(), epsilon);
		assertEquals(meterY, point.getMeterY(), epsilon);
		
		meterX = 0;
		meterY = -Meta.ORIGIN_SHIFT;
		expectedX = -85.05;
		expectedY = 0.0;
		
		point = Point.fromMeters(meterX, meterY);
		
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(meterX, point.getMeterX(), epsilon);
		assertEquals(meterY, point.getMeterY(), epsilon);
		
		meterX = Meta.ORIGIN_SHIFT;
		meterY = -Meta.ORIGIN_SHIFT;
		expectedX = -85.05;
		expectedY = 180.0;
		
		point = Point.fromMeters(meterX, meterY);
		
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(meterX, point.getMeterX(), epsilon);
		assertEquals(meterY, point.getMeterY(), epsilon);
	}
	
	@Test
	public void testAssertLongitude() {
		
	}
	
	@Test
	public void testNoAssertLongitude() {
		
	}
	
	@Test
	public void testAssertLatitude() {
		
	}
	
	@Test
	public void testNoAssertLatitude() {
		
	}
	
	@Test
	public void testAssertPixelX() {
		
	}
	
	@Test
	public void testNoAssertPixelX() {
		
	}
	
	@Test
	public void testAssertPixelY() {
		
	}
	
	@Test
	public void testNoAssertPixelY() {
		
	}
	
	@Test
	public void testAssertMeterX() {
		
	}
	
	@Test
	public void testNoAssertMeterX() {
		
	}
	
	@Test
	public void testAssertMeterY() {
		
	}
	
	@Test
	public void testNoAssertMeterY() {
		
	}
}
