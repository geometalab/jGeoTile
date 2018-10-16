import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class PointTest {

	double latitude = 41.85;
	double longitude = -87.65;
	double meterX = -9757148.442088600;
	double meterY = 5138517.444985110;
	int pixelX = 34430575;
	int pixelY = 49899071;
	int zoom = 19;
	
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
		Point point = Point.fromPixel(pixelX, pixelY, zoom);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
		assertEquals(latitude, point.getLatitude(), 0.001);
		assertEquals(longitude, point.getLongitude(), 0.001);
	}
	
	@Test
	public void testGetLatitude() {
		assertEquals(latitude, 41.85, 0.000001);
	}
	
	@Test
	public void testGetLongitude() {
		assertEquals(longitude, -87.65, 0.000001);
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
		assertEquals(meterX, -9757148.442088600, 0.00000001);
	}
	
	@Test
	public void testGetMeterY() {
		assertEquals(meterY, 5138517.444985110, 0.00000001);
	}
	
	@Test
	public void testSignMeterX() {
		Point.signMeterX(meterX, pixelX, zoom);
		assertEquals(-9757148.442088600, meterX, 0.00000001);
		System.out.println("testSignMeterX: " + Point.signMeterX(meterX, pixelX, zoom));
	}
	
	@Test
	public void testSignMeterY() {
		Point.signMeterY(meterY, pixelY, zoom);
		assertEquals(5138517.444985110, meterY, 0.00000001);
		System.out.println("testSignMeterY: " + Point.signMeterX(meterX, pixelX, zoom));
	}
}
