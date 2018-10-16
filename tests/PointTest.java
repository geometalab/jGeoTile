import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class PointTest {

	double latitude = 41.85;
	double longitude = -87.65;
	double meterX = -9757148.442088600;
	double meterY = 5138517.444985110;
	double epsilon = 0.001;
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
		
	}
	
	@Test
	public void testPixelsToLatitudeLongitude() {
		
	}
	
	@Test
	public void testMetersToPixels() {
		
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
