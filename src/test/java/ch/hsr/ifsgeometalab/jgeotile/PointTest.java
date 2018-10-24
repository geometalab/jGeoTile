package ch.hsr.ifsgeometalab.jgeotile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ch.hsr.ifs.geometalab.jgeotile.Meta;
import ch.hsr.ifs.geometalab.jgeotile.Point;

public class PointTest {

	private static final double latitude = 47.0;
	private static final double longitude = 8.0;
	private static final double epsilon = 0.1;
	
	@Test
	public void testFromLatitudeLongitude() {
		Point point = Point.fromLatitudeLongitude(latitude, longitude);
		assertEquals(latitude, point.getLatitude(), epsilon);
		assertEquals(longitude, point.getLongitude(), epsilon);
	}
	
	@Test
	public void testFromMeters() {
		Point point = Point.fromMeters(Chicago.meterX, Chicago.meterY);
		assertEquals(Chicago.meterX, point.getMeterX(), epsilon);
		assertEquals(Chicago.meterY, point.getMeterY(), epsilon);
		assertEquals(Chicago.latitude, point.getLatitude(), epsilon);
		assertEquals(Chicago.longitude, point.getLongitude(), epsilon);
	}
	
	@Test
	public void testFromPixel() {
		Point point = Point.fromPixel(Chicago.pixelX, Chicago.pixelY, Chicago.zoom);
		assertEquals(Chicago.pixelX, point.getPixelX(Chicago.zoom));
		assertEquals(Chicago.pixelY, point.getPixelY(Chicago.zoom));
		assertEquals(Chicago.latitude, point.getLatitude(), epsilon);
		assertEquals(Chicago.longitude, point.getLongitude(), epsilon);
	}
	
	@Test
	public void testPixelsToMeters1() {
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
	}
	
	@Test
	public void testPixelsToMeters2() {
		int pixelX = 256;
		int pixelY = 0;
		int zoom = 1;
		double expectedX = 0.0;
		double expectedY = Meta.ORIGIN_SHIFT;
		Point point = Point.fromPixel(pixelX, pixelY, zoom);
		assertEquals(expectedX, point.getMeterX(), epsilon);
		assertEquals(expectedY, point.getMeterY(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
	}
	
	@Test
	public void testPixelsToMeters3() {
		int pixelX = 512;
		int pixelY = 0;
		int zoom = 1;
		double expectedX = Meta.ORIGIN_SHIFT;
		double expectedY = Meta.ORIGIN_SHIFT;
		Point point = Point.fromPixel(pixelX, pixelY, zoom);
		assertEquals(expectedX, point.getMeterX(), epsilon);
		assertEquals(expectedY, point.getMeterY(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
	}
	
	@Test
	public void testPixelsToMeters4() {
		int pixelX = 0;
		int pixelY = 256;
		int zoom = 1;
		double expectedX = -Meta.ORIGIN_SHIFT;
		double expectedY = 0.0;
		Point point = Point.fromPixel(pixelX, pixelY, zoom);
		assertEquals(expectedX, point.getMeterX(), epsilon);
		assertEquals(expectedY, point.getMeterY(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
	}
	
	@Test
	public void testPixelsToMeters5() {
		int pixelX = 256;
		int pixelY = 256;
		int zoom = 1;
		double expectedX = 0.0;
		double expectedY = 0.0;
		Point point = Point.fromPixel(pixelX, pixelY, zoom);
		assertEquals(expectedX, point.getMeterX(), epsilon);
		assertEquals(expectedY, point.getMeterY(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
	}
	
	@Test
	public void testPixelsToMeters6() {
		int pixelX = 512;
		int pixelY = 256;
		int zoom = 1;
		double expectedX = Meta.ORIGIN_SHIFT;
		double expectedY = 0.0;
		Point point = Point.fromPixel(pixelX, pixelY, zoom);
		assertEquals(expectedX, point.getMeterX(), epsilon);
		assertEquals(expectedY, point.getMeterY(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
	}
	
	@Test
	public void testPixelsToMeters7() {
		int pixelX = 0;
		int pixelY = 512;
		int zoom = 1;
		double expectedX = -Meta.ORIGIN_SHIFT;
		double expectedY = -Meta.ORIGIN_SHIFT;
		Point point = Point.fromPixel(pixelX, pixelY, zoom);
		assertEquals(expectedX, point.getMeterX(), epsilon);
		assertEquals(expectedY, point.getMeterY(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
	}
	
	@Test
	public void testPixelsToMeters8() {
		int pixelX = 256;
		int pixelY = 512;
		int zoom = 1;
		double expectedX = 0.0;
		double expectedY = -Meta.ORIGIN_SHIFT;
		Point point = Point.fromPixel(pixelX, pixelY, zoom);
		assertEquals(expectedX, point.getMeterX(), epsilon);
		assertEquals(expectedY, point.getMeterY(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
	}
	
	@Test
	public void testPixelsToMeters9() {
		int pixelX = 512;
		int pixelY = 512;
		int zoom = 1;
		double expectedX = Meta.ORIGIN_SHIFT;
		double expectedY = -Meta.ORIGIN_SHIFT;
		Point point = Point.fromPixel(pixelX, pixelY, zoom);
		assertEquals(expectedX, point.getMeterX(), epsilon);
		assertEquals(expectedY, point.getMeterY(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
	}
	
	@Test
	public void testPixelsToLatitudeLongitude1() {
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
	}
	
	@Test
	public void testPixelsToLatitudeLongitude2() {
		int pixelX = 256;
		int pixelY = 0;
		int zoom = 1;
		double expectedX = 85.05;
		double expectedY = 0.0;
		Point point = Point.fromPixel(pixelX, pixelY, zoom);
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
	}
	
	@Test
	public void testPixelsToLatitudeLongitude3() {
		int pixelX = 512;
		int pixelY = 0;
		int zoom = 1;
		double expectedX = 85.05;
		double expectedY = 180.0;
		Point point = Point.fromPixel(pixelX, pixelY, zoom);
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
	}
	
	@Test
	public void testPixelsToLatitudeLongitude4() {
		int pixelX = 0;
		int pixelY = 256;
		int zoom = 1;
		double expectedX = 0.0;
		double expectedY = -180.0;
		Point point = Point.fromPixel(pixelX, pixelY, zoom);
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
	}
	
	@Test
	public void testPixelsToLatitudeLongitude5() {
		int pixelX = 256;
		int pixelY = 256;
		int zoom = 1;
		double expectedX = 0.0;
		double expectedY = 0.0;
		Point point = Point.fromPixel(pixelX, pixelY, zoom);
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
	}
	
	@Test
	public void testPixelsToLatitudeLongitude6() {
		int pixelX = 512;
		int pixelY = 256;
		int zoom = 1;
		double expectedX = 0.0;
		double expectedY = 180.0;
		Point point = Point.fromPixel(pixelX, pixelY, zoom);
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
	}
	
	@Test
	public void testPixelsToLatitudeLongitude7() {
		int pixelX = 0;
		int pixelY = 512;
		int zoom = 1;
		double expectedX = -85.05;
		double expectedY = -180.0;
		Point point = Point.fromPixel(pixelX, pixelY, zoom);
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
	}
	
	@Test
	public void testPixelsToLatitudeLongitude8() {
		int pixelX = 256;
		int pixelY = 512;
		int zoom = 1;
		double expectedX = -85.05;
		double expectedY = 0.0;
		Point point = Point.fromPixel(pixelX, pixelY, zoom);
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
	}
	
	@Test
	public void testPixelsToLatitudeLongitude9() {
		int pixelX = 512;
		int pixelY = 512;
		int zoom = 1;
		double expectedX = -85.05;
		double expectedY = 180.0;
		Point point = Point.fromPixel(pixelX, pixelY, zoom);
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(pixelX, point.getPixelX(zoom));
		assertEquals(pixelY, point.getPixelY(zoom));
	}
	
	@Test
	public void testMetersToPixels1() {
		double meterX = -Meta.ORIGIN_SHIFT;
		double meterY = Meta.ORIGIN_SHIFT;
		double expectedX = 85.05;
		double expectedY = -180.0;
		Point point = Point.fromMeters(meterX, meterY);
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(meterX, point.getMeterX(), epsilon);
		assertEquals(meterY, point.getMeterY(), epsilon);
	}
	
	@Test
	public void testMetersToPixels2() {
		double meterX = 0.0;
		double meterY = Meta.ORIGIN_SHIFT;
		double expectedX = 85.05;
		double expectedY = 0.0;
		Point point = Point.fromMeters(meterX, meterY);
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(meterX, point.getMeterX(), epsilon);
		assertEquals(meterY, point.getMeterY(), epsilon);
	}
	
	@Test
	public void testMetersToPixels3() {
		double meterX = Meta.ORIGIN_SHIFT;
		double meterY = Meta.ORIGIN_SHIFT;
		double expectedX = 85.05;
		double expectedY = 180.0;
		Point point = Point.fromMeters(meterX, meterY);
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(meterX, point.getMeterX(), epsilon);
		assertEquals(meterY, point.getMeterY(), epsilon);
	}
	
	@Test
	public void testMetersToPixels4() {
		double meterX = -Meta.ORIGIN_SHIFT;
		double meterY = 0.0;
		double expectedX = 0.0;
		double expectedY = -180.0;
		Point point = Point.fromMeters(meterX, meterY);
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(meterX, point.getMeterX(), epsilon);
		assertEquals(meterY, point.getMeterY(), epsilon);
	}
	
	@Test
	public void testMetersToPixels5() {
		double meterX = 0.0;
		double meterY = 0.0;
		double expectedX = 0.0;
		double expectedY = 0.0;
		Point point = Point.fromMeters(meterX, meterY);
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(meterX, point.getMeterX(), epsilon);
		assertEquals(meterY, point.getMeterY(), epsilon);
	}
	
	@Test
	public void testMetersToPixels6() {
		double meterX = Meta.ORIGIN_SHIFT;
		double meterY = 0.0;
		double expectedX = 0.0;
		double expectedY = 180.0;
		Point point = Point.fromMeters(meterX, meterY);
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(meterX, point.getMeterX(), epsilon);
		assertEquals(meterY, point.getMeterY(), epsilon);
	}
	
	@Test
	public void testMetersToPixels7() {
		double meterX = -Meta.ORIGIN_SHIFT;
		double meterY = -Meta.ORIGIN_SHIFT;
		double expectedX = -85.05;
		double expectedY = -180.0;
		Point point = Point.fromMeters(meterX, meterY);
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(meterX, point.getMeterX(), epsilon);
		assertEquals(meterY, point.getMeterY(), epsilon);
	}
	
	@Test
	public void testMetersToPixels8() {
		double meterX = 0.0;
		double meterY = -Meta.ORIGIN_SHIFT;
		double expectedX = -85.05;
		double expectedY = 0.0;
		Point point = Point.fromMeters(meterX, meterY);
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(meterX, point.getMeterX(), epsilon);
		assertEquals(meterY, point.getMeterY(), epsilon);
	}
	
	@Test
	public void testMetersToPixels9() {
		double meterX = Meta.ORIGIN_SHIFT;
		double meterY = -Meta.ORIGIN_SHIFT;
		double expectedX = -85.05;
		double expectedY = 180.0;
		Point point = Point.fromMeters(meterX, meterY);
		assertEquals(expectedX, point.getLatitude(), epsilon);
		assertEquals(expectedY, point.getLongitude(), epsilon);
		assertEquals(meterX, point.getMeterX(), epsilon);
		assertEquals(meterY, point.getMeterY(), epsilon);
	}
	
	@Test
	public void testAssertLongitude1() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromLatitudeLongitude(0.0, -180.1);
				}, "Longitude needs to be a value between -180.0 and 180.0.");
	}
	
	@Test
	public void testAssertLongitude2() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromLatitudeLongitude(0.0, -200.0);
				}, "Longitude needs to be a value between -180.0 and 180.0.");
	}
	
	@Test
	public void testAssertLongitude3() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromLatitudeLongitude(0.0, -181.0);
				}, "Longitude needs to be a value between -180.0 and 180.0.");
	}
	
	@Test
	public void testAssertLongitude4() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromLatitudeLongitude(0.0, 181.0);
				}, "Longitude needs to be a value between -180.0 and 180.0.");
	}
	
	@Test
	public void testAssertLongitude5() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromLatitudeLongitude(0.0, 180.01);
				}, "Longitude needs to be a value between -180.0 and 180.0.");
	}
	
	@Test
	public void testAssertLongitude6() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromLatitudeLongitude(0.0, 200.0);
				}, "Longitude needs to be a value between -180.0 and 180.0.");
	}
	
	@Test
	public void testNoAssertLongitude1() {
		assertAll(()->Point.fromLatitudeLongitude(10.0, 180.0));
	}
	
	@Test
	public void testNoAssertLongitude2() {
		assertAll(()->Point.fromLatitudeLongitude(10.0, -180.0));
	}
	
	@Test
	public void testNoAssertLongitude3() {
		assertAll(()->Point.fromLatitudeLongitude(10.0, 0.0));
	}
	
	@Test
	public void testNoAssertLongitude4() {
		assertAll(()->Point.fromLatitudeLongitude(10.0, 90.0));
	}
	
	@Test
	public void testAssertLatitude1() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromLatitudeLongitude(-90.1, 0.0);
				}, "Latitude needs to be a value between -90.0 and 90.0.");
	}
	
	@Test
	public void testAssertLatitude2() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromLatitudeLongitude(-91.0, 0.0);
				}, "Latitude needs to be a value between -90.0 and 90.0.");
	}
	
	@Test
	public void testAssertLatitude3() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromLatitudeLongitude(90.1, 0.0);
				}, "Latitude needs to be a value between -90.0 and 90.0.");
	}
	
	@Test
	public void testAssertLatitude4() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromLatitudeLongitude(91.0, 0.0);
				}, "Latitude needs to be a value between -90.0 and 90.0.");
	}
	
	@Test
	public void testAssertLatitude5() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromLatitudeLongitude(200.0, 0.0);
				}, "Latitude needs to be a value between -90.0 and 90.0.");
	}
	
	@Test
	public void testAssertLatitude6() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromLatitudeLongitude(-200.0, 0.0);
				}, "Latitude needs to be a value between -90.0 and 90.0.");
	}
	
	@Test
	public void testNoAssertLatitude1() {
		assertAll(()->Point.fromLatitudeLongitude(90.0, 10.0));
	}
	
	@Test
	public void testNoAssertLatitude2() {
		assertAll(()->Point.fromLatitudeLongitude(-90.0, 10.0));
	}
	
	@Test
	public void testNoAssertLatitude3() {
		assertAll(()->Point.fromLatitudeLongitude(0.0, 10.0));
	}
	
	@Test
	public void testAssertPixelX1() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromPixel(-10, 1, 1);
				}, "Point X needs to be a value between 0 and (2^zoom) * 256.");
	}
	
	@Test
	public void testAssertPixelX2() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromPixel(-1, 1, 1);
				}, "Point X needs to be a value between 0 and (2^zoom) * 256.");
	}
	
	@Test
	public void testAssertPixelX3() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromPixel(513, 1, 1);
				}, "Point X needs to be a value between 0 and (2^zoom) * 256.");
	}
	
	@Test
	public void testAssertPixelX4() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromPixel(1025, 1, 2);
				}, "Point X needs to be a value between 0 and (2^zoom) * 256.");
	}
	
	@Test
	public void testNoAssertPixelX1() {
		assertAll(()->Point.fromPixel(10, 10, 1));
	}
	
	@Test
	public void testNoAssertPixelX2() {
		assertAll(()->Point.fromPixel(0, 10, 1));
	}
	
	@Test
	public void testNoAssertPixelX3() {
		assertAll(()->Point.fromPixel(512, 10, 1));
	}
	
	@Test
	public void testNoAssertPixelX4() {
		assertAll(()->Point.fromPixel(1024, 10, 2));
	}
	
	@Test
	public void testAssertPixelY1() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromPixel(1, -10, 1);
				}, "Point Y needs to be a value between 0 and (2^zoom) * 256.");
	}
	
	@Test
	public void testAssertPixelY2() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromPixel(1, -1, 1);
				}, "Point Y needs to be a value between 0 and (2^zoom) * 256.");
	}
	
	@Test
	public void testAssertPixelY3() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromPixel(1, 513, 1);
				}, "Point Y needs to be a value between 0 and (2^zoom) * 256.");
	}
	
	@Test
	public void testAssertPixelY4() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromPixel(1, 1025, 2);
				}, "Point Y needs to be a value between 0 and (2^zoom) * 256.");
	}
	
	@Test
	public void testNoAssertPixelY1() {
		assertAll(()->Point.fromPixel(10, 10, 1));
	}
	
	@Test
	public void testNoAssertPixelY2() {
		assertAll(()->Point.fromPixel(10, 0, 1));
	}
	
	@Test
	public void testNoAssertPixelY3() {
		assertAll(()->Point.fromPixel(10, 512, 1));
	}
	
	@Test
	public void testNoAssertPixelY4() {
		assertAll(()->Point.fromPixel(10, 1024, 2));
	}
	
	@Test
	public void testAssertMeterX1() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromMeters(-0.1-Meta.ORIGIN_SHIFT, 0.0);
				}, "Meter X needs to be a value between " + -Meta.ORIGIN_SHIFT + " and " + Meta.ORIGIN_SHIFT + ".");
	}
	
	@Test
	public void testAssertMeterX2() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromMeters(-200.0-Meta.ORIGIN_SHIFT, 0.0);
				}, "Meter X needs to be a value between " + -Meta.ORIGIN_SHIFT + " and " + Meta.ORIGIN_SHIFT + ".");
	}
	
	@Test
	public void testAssertMeterX3() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromMeters(Meta.ORIGIN_SHIFT+0.1, 0.0);
				}, "Meter X needs to be a value between " + -Meta.ORIGIN_SHIFT + " and " + Meta.ORIGIN_SHIFT + ".");
	}
	
	@Test
	public void testAssertMeterX4() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromMeters(6378137000.0, 0.0);
				}, "Meter X needs to be a value between " + -Meta.ORIGIN_SHIFT + " and " + Meta.ORIGIN_SHIFT + ".");
	}
	
	@Test
	public void testNoAssertMeterX1() {
		assertAll(()->Point.fromMeters(-Meta.ORIGIN_SHIFT, 10.0));
	}
	
	@Test
	public void testNoAssertMeterX2() {
		assertAll(()->Point.fromMeters(-Meta.ORIGIN_SHIFT+0.1, 10.0));
	}
	
	@Test
	public void testNoAssertMeterX3() {
		assertAll(()->Point.fromMeters(Meta.ORIGIN_SHIFT, 10.0));
	}
	
	@Test
	public void testNoAssertMeterX4() {
		assertAll(()->Point.fromMeters(Meta.ORIGIN_SHIFT-0.1, 10.0));
	}
	
	@Test
	public void testAssertMeterY1() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromMeters(0.0, -0.1-Meta.ORIGIN_SHIFT);
				}, "Meter Y needs to be a value between " + -Meta.ORIGIN_SHIFT + " and " + Meta.ORIGIN_SHIFT + ".");
	}
	
	@Test
	public void testAssertMeterY2() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromMeters(0.0, -200.0-Meta.ORIGIN_SHIFT);
				}, "Meter Y needs to be a value between " + -Meta.ORIGIN_SHIFT + " and " + Meta.ORIGIN_SHIFT + ".");
	}
	
	@Test
	public void testAssertMeterY3() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromMeters(0.0, Meta.ORIGIN_SHIFT+0.1);
				}, "Meter Y needs to be a value between " + -Meta.ORIGIN_SHIFT + " and " + Meta.ORIGIN_SHIFT + ".");
	}
	
	@Test
	public void testAssertMeterY4() {
		assertThrows(AssertionError.class,
				()->{
					Point.fromMeters(0.0, 6378137000.0);
				}, "Meter Y needs to be a value between " + -Meta.ORIGIN_SHIFT + " and " + Meta.ORIGIN_SHIFT + ".");
	}
	
	@Test
	public void testNoAssertMeterY1() {
		assertAll(()->Point.fromMeters(10.0, -Meta.ORIGIN_SHIFT));
	}
	
	@Test
	public void testNoAssertMeterY2() {
		assertAll(()->Point.fromMeters(10.0, -Meta.ORIGIN_SHIFT+0.1));
	}
	
	@Test
	public void testNoAssertMeterY3() {
		assertAll(()->Point.fromMeters(10.0, Meta.ORIGIN_SHIFT));
	}
	
	@Test
	public void testNoAssertMeterY4() {
		assertAll(()->Point.fromMeters(10.0, Meta.ORIGIN_SHIFT-0.1));
	}
	
}
