import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class PointTest {

	double latitude = 41.85;
	double longitude = -87.65;
	double meterX = -9757148.442088600;
	double meterY = 5138517.444985110;
	int pixelX = 34430575;
	int pixelY = 49899071;
	
	@Test
	public void testFromLatitudeLongitude() {
		Point point = Point.fromLatitudeLongitude(latitude, longitude);
		assertEquals(latitude, point.getLatitude(), 0.001);
		assertEquals(longitude, point.getLongitude(), 0.001);
	}
	
	@Test
	public void testFromMeters() {
		Point point = Point.fromMeters(meterX, meterY);
		assertEquals(meterX, point.getMeterX(), 0.001);
		assertEquals(meterY, point.getMeterY(), 0.001);
		assertEquals(latitude, point.getLatitude(), 0.001);
		assertEquals(longitude, point.getLongitude(), 0.001);
	}
	
	@Test
	public void testFromPixel() {
		Point point = Point.fromPixel(pixelX, pixelY, 19);
		System.out.println("testFromPixelLat: " + point.getLatitude());
		System.out.println("testFromPixelLon: " + point.getLongitude());
	}
	
	@Test
	public void testGetLatitude() {
		assertEquals(41.85, latitude, 0.000001);
	}
	
	@Test
	public void testGetLongitude() {
		assertEquals(-87.65, longitude, 0.000001);
	}
	
	@Test
	public void testGetPixelX() {
		Point point = new Point(latitude, longitude);
		assertEquals(34430575, pixelX, 1);
		System.out.println("testGetPixelX: " + point.getPixelX(19));
	}
	
	@Test
	public void testGetPixelY() {
		Point point = new Point(latitude, longitude);
		assertEquals(49899071, pixelY, 1);
		System.out.println("testGetPixelY: " + point.getPixelY(19));
	}
	
	@Test
	public void testGetMeterX() {
		Point point = new Point(latitude, longitude);
		assertEquals(-9757148.442088600, meterX, 0.00000001);
		System.out.println("testGetMeterX: " + point.getMeterX());
	}
	
	@Test
	public void testGetMeterY() {
		Point point = new Point(latitude, longitude);
		assertEquals(5138517.444985110, meterY, 0.00000001);
		System.out.println("testGetMeterY: " + point.getMeterY());
	}
	
	@Test
	public void testSignMeterX() {
		Point.signMeterX(meterX, pixelX, 19);
		assertEquals(-9757148.442088600, meterX, 0.00000001);
		System.out.println("testSignMeterX: " + Point.signMeterX(meterX, pixelX, 19));
	}
	
	@Test
	public void testSignMeterY() {
		Point.signMeterY(meterY, pixelY, 19);
		assertEquals(5138517.444985110, meterY, 0.00000001);
		System.out.println("testSignMeterY: " + Point.signMeterX(meterX, pixelX, 19));
	}
}
