package ch.hsr.ifsgeometalab.jgeotile;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import ch.hsr.ifs.geometalab.jgeotile.Point;
import ch.hsr.ifs.geometalab.jgeotile.Tile;

class TileTest {
	
	private static final int tmsX = 67;
	private static final int tmsY = 83;
	private static final int googleX = 67;
	private static final int googleY = 44;
	private static final String quadTree = "1202211";
	private static final int zoom = 7;
	private static final double epsilon = 0.1;
	
	@Test
	void testFromGoogle() {
		Tile tile = Tile.fromGoogle(googleX, googleY, zoom);
		assertEquals(tmsX, tile.getTmsX());
		assertEquals(tmsY, tile.getTmsY());
	}
	
	@Test
	void testFromGoogleTasmania() {
		Tile tile = Tile.fromGoogle(1853, 1289, 11);
		assertEquals(1853, tile.getTmsX());
		assertEquals(758, tile.getTmsY());
	}
	
	@Test
	void testFromTms() {
		Tile tile = Tile.fromTms(tmsX, tmsY, zoom);
		assertEquals(tmsX, tile.getTmsX());
		assertEquals(tmsY, tile.getTmsY());
	}
	
	@Test
	void testFromQuadTree() {
		Tile tile = Tile.fromQuadTree(quadTree);
		assertEquals(tmsX, tile.getTmsX());
		assertEquals(tmsY, tile.getTmsY());
		assertEquals(zoom, tile.getZoom());
	}
	
	@Test
	void testCrossCheck() {
		Tile tile = Tile.fromQuadTree(quadTree);
		assertEquals(tmsX, tile.getTmsX());
		assertEquals(tmsY, tile.getTmsY());
		assertEquals(zoom, tile.getZoom());
		assertEquals(googleX, tile.getGoogleX());
		assertEquals(googleY, tile.getGoogleY());
		assertEquals(quadTree, tile.getQuadTree());
	}
	
	@Test
	void testForPixelChicago() {
		Tile tile = Tile.forPixels(Chicago.pixelX, Chicago.pixelY, Chicago.zoom);
		assertEquals(Chicago.tmsX, tile.getTmsX());
		assertEquals(Chicago.tmsY, tile.getTmsY());
	}
	
	@Test
	void testForMetersChicago() {
		Point point = Point.fromPixel(Chicago.pixelX, Chicago.pixelY, Chicago.zoom);
		Tile tile = Tile.forMeters(point.getMeterX(), point.getMeterY(), Chicago.zoom);
		assertEquals(Chicago.tmsX, tile.getTmsX());
		assertEquals(Chicago.tmsY, tile.getTmsY());
	}
	
	@Test
	void testPixelBoundsChicago() {
		Tile tile = Tile.fromQuadTree(Chicago.quadTree);
		Point pointMin = tile.getBoundMin();
		Point pointMax = tile.getBoundMax();
		assertEquals(Chicago.lowerPixelBoundX, pointMin.getPixelX(tile.getZoom()));
		assertEquals(Chicago.lowerPixelBoundY, pointMin.getPixelY(tile.getZoom()));
		assertEquals(Chicago.upperPixelBoundX, pointMax.getPixelX(tile.getZoom()));
		assertEquals(Chicago.upperPixelBoundY, pointMax.getPixelY(tile.getZoom()));
	}
	
	@Test
	void testTileBounds1() {
		Tile tile =  Tile.fromTms(0, 1, 1);
		Point pointMin = tile.getBoundMin();
		Point pointMax = tile.getBoundMax();
		assertEquals(0.0, pointMin.getLatitude(), epsilon);
		assertEquals(-180.0, pointMin.getLongitude(), epsilon);
		assertEquals(85.05, pointMax.getLatitude(), epsilon);
		assertEquals(0.0 ,pointMax.getLongitude(), epsilon);
	}
	
	@Test
	void testTileBounds2() {
		Tile tile =  Tile.fromTms(1, 1, 1);
		Point pointMin = tile.getBoundMin();
		Point pointMax = tile.getBoundMax();
		assertEquals(0.0, pointMin.getLatitude(), epsilon);
		assertEquals(0.0, pointMin.getLongitude(), epsilon);
		assertEquals(85.05, pointMax.getLatitude(), epsilon);
		assertEquals(180.0 ,pointMax.getLongitude(), epsilon);
	}
	
	@Test
	void testTileBounds3() {
		Tile tile =  Tile.fromTms(0, 0, 1);
		Point pointMin = tile.getBoundMin();
		Point pointMax = tile.getBoundMax();
		assertEquals(-85.05, pointMin.getLatitude(), epsilon);
		assertEquals(-180.0, pointMin.getLongitude(), epsilon);
		assertEquals(0.0, pointMax.getLatitude(), epsilon);
		assertEquals(0.0 ,pointMax.getLongitude(), epsilon);
	}
	
	@Test
	void testTileBounds4() {
		Tile tile =  Tile.fromTms(1, 0, 1);
		Point pointMin = tile.getBoundMin();
		Point pointMax = tile.getBoundMax();
		assertEquals(-85.05, pointMin.getLatitude(), epsilon);
		assertEquals(0.0, pointMin.getLongitude(), epsilon);
		assertEquals(0.0, pointMax.getLatitude(), epsilon);
		assertEquals(180.0 ,pointMax.getLongitude(), epsilon);
	}
	
	@Test
	void testForLatitudeLongitude() {
		Tile tile = Tile.forLatitudeLongitude(Chicago.latitude, Chicago.longitude, Chicago.zoom);
		assertEquals(Chicago.tmsX, tile.getTmsX());
		assertEquals(Chicago.tmsY, tile.getTmsY());
	}
	
	@Test
	void testForPoint() {
		Point point = Point.fromLatitudeLongitude(Chicago.latitude, Chicago.longitude);
		Tile tile = Tile.forPoint(point, Chicago.zoom);
		assertEquals(Chicago.tmsX, tile.getTmsX());
		assertEquals(Chicago.tmsY, tile.getTmsY());
		assertEquals(Chicago.zoom, tile.getZoom());
	}
	
	@Test
	void testAssertTmsX1() {
		assertThrows(AssertionError.class,
				()->{
					Tile.fromTms(-1, 0, 2);
				}, "TMS X needs to be a value between 0 and (2^zoom) -1.");
	}
	
	@Test
	void testAssertTmsX2() {
		assertThrows(AssertionError.class,
				()->{
					Tile.fromTms(-5, 0, 2);
				}, "TMS X needs to be a value between 0 and (2^zoom) -1.");
	}
	
	@Test
	void testAssertTmsX3() {
		assertThrows(AssertionError.class,
				()->{
					Tile.fromTms(4, 0, 2);
				}, "TMS X needs to be a value between 0 and (2^zoom) -1.");
	}
	
	@Test
	void testAssertTmsX4() {
		assertThrows(AssertionError.class,
				()->{
					Tile.fromTms(10, 0, 2);
				}, "TMS X needs to be a value between 0 and (2^zoom) -1.");
	}
	
	@Test
	void testNoAssertTmsX1() {
		assertAll(()->Tile.fromTms(0, 0, 2));
	}
	
	@Test
	void testNoAssertTmsX2() {
		assertAll(()->Tile.fromTms(1, 0, 2));
	}
	
	@Test
	void testNoAssertTmsX3() {
		assertAll(()->Tile.fromTms(2, 0, 2));
	}
	
	@Test
	void testNoAssertTmsX4() {
		assertAll(()->Tile.fromTms(3, 0, 2));
	}
	
	@Test
	void testAssertTmsY1() {
		assertThrows(AssertionError.class,
				()->{
					Tile.fromTms(0, -1, 2);
				}, "TMS Y needs to be a value between 0 and (2^zoom) -1.");
	}
	
	@Test
	void testAssertTmsY2() {
		assertThrows(AssertionError.class,
				()->{
					Tile.fromTms(0, -5, 2);
				}, "TMS Y needs to be a value between 0 and (2^zoom) -1.");
	}
	
	@Test
	void testAssertTmsY3() {
		assertThrows(AssertionError.class,
				()->{
					Tile.fromTms(0, 4, 2);
				}, "TMS Y needs to be a value between 0 and (2^zoom) -1.");
	}
	
	@Test
	void testAssertTmsY4() {
		assertThrows(AssertionError.class,
				()->{
					Tile.fromTms(0, 10, 2);
				}, "TMS Y needs to be a value between 0 and (2^zoom) -1.");
	}
	
	@Test
	void testNoAssertTmsY1() {
		assertAll(()-> Tile.fromTms(0, 0, 2));
	}
	
	@Test
	void testNoAssertTmsY2() {
		assertAll(()-> Tile.fromTms(0, 1, 2));
	}
	
	@Test
	void testNoAssertTmsY3() {
		assertAll(()-> Tile.fromTms(0, 2, 2));
	}
	
	@Test
	void testNoAssertTmsY4() {
		assertAll(()-> Tile.fromTms(0, 3, 2));
	}
	
	@Test
	void testAssertGoogleX1() {
		assertThrows(AssertionError.class,
				()->{
					Tile.fromGoogle(-1, 0, 2);
				}, "Google X needs to be a value between 0 and (2^zoom) -1.");
	}
	
	@Test
	void testAssertGoogleX2() {
		assertThrows(AssertionError.class,
				()->{
					Tile.fromGoogle(-5, 0, 2);
				}, "Google X needs to be a value between 0 and (2^zoom) -1.");
	}
	
	@Test
	void testAssertGoogleX3() {
		assertThrows(AssertionError.class,
				()->{
					Tile.fromGoogle(4, 0, 2);
				}, "Google X needs to be a value between 0 and (2^zoom) -1.");
	}
	
	@Test
	void testAssertGoogleX4() {
		assertThrows(AssertionError.class,
				()->{
					Tile.fromGoogle(10, 0, 2);
				}, "Google X needs to be a value between 0 and (2^zoom) -1.");
	}
	
	@Test
	void testNoAssertGoogleX1() {
		assertAll(()->Tile.fromGoogle(0, 0, 2));
	}
	
	@Test
	void testNoAssertGoogleX2() {
		assertAll(()->Tile.fromGoogle(1, 0, 2));
	}
	
	@Test
	void testNoAssertGoogleX3() {
		assertAll(()->Tile.fromGoogle(2, 0, 2));
	}
	
	@Test
	void testNoAssertGoogleX4() {
		assertAll(()->Tile.fromGoogle(3, 0, 2));
	}
	
	@Test
	void testAssertGoogleY1() {
		assertThrows(AssertionError.class,
				()->{
					Tile.fromGoogle(0, -1, 2);
				}, "Google Y needs to be a value between 0 and (2^zoom) -1.");
	}
	
	@Test
	void testAssertGoogleY2() {
		assertThrows(AssertionError.class,
				()->{
					Tile.fromGoogle(0, -5, 2);
				}, "Google Y needs to be a value between 0 and (2^zoom) -1.");
	}
	
	@Test
	void testAssertGoogleY3() {
		assertThrows(AssertionError.class,
				()->{
					Tile.fromGoogle(0, 4, 2);
				}, "Google Y needs to be a value between 0 and (2^zoom) -1.");
	}
	
	@Test
	void testAssertGoogleY4() {
		assertThrows(AssertionError.class,
				()->{
					Tile.fromGoogle(0, 10, 2);
				}, "Google Y needs to be a value between 0 and (2^zoom) -1.");
	}
	
	@Test
	void testNoAssertGoogleY1() {
		assertAll(()-> Tile.fromGoogle(0, 0, 2));
	}
	
	@Test
	void testNoAssertGoogleY2() {
		assertAll(()-> Tile.fromGoogle(0, 1, 2));
	}
	
	@Test
	void testNoAssertGoogleY3() {
		assertAll(()-> Tile.fromGoogle(0, 2, 2));
	}
	
	@Test
	void testNoAssertGoogleY4() {
		assertAll(()-> Tile.fromGoogle(0, 3, 2));
	}
	
	@Test
	void testAssertQuadTree1() {
		assertThrows(AssertionError.class,
				()->{
					Tile.fromQuadTree("-1");
				}, "QuadTree value can only consists of the digits 0, 1, 2 and 3.");
	}
	
	@Test
	void testAssertQuadTree2() {
		assertThrows(AssertionError.class,
				()->{
					Tile.fromQuadTree("1235");
				}, "QuadTree value can only consists of the digits 0, 1, 2 and 3.");
	}
	
	@Test
	void testAssertQuadTree3() {
		assertThrows(AssertionError.class,
				()->{
					Tile.fromQuadTree("aba");
				}, "QuadTree value can only consists of the digits 0, 1, 2 and 3.");
	}
	
	@Test
	void testAssertQuadTree4() {
		assertThrows(AssertionError.class,
				()->{
					Tile.fromQuadTree("9988");
				}, "QuadTree value can only consists of the digits 0, 1, 2 and 3.");
	}
	
	@Test
	void testNoAssertQuadTree1() {
		assertAll(()-> Tile.fromQuadTree("1"));
	}
	
	@Test
	void testNoAssertQuadTree2() {
		assertAll(()-> Tile.fromQuadTree("0123"));
	}
	
	@Test
	void testNoAssertQuadTree3() {
		assertAll(()-> Tile.fromQuadTree("1231230"));
	}
	
	@Test
	void testNoAssertQuadTree4() {
		assertAll(()-> Tile.fromQuadTree("00012"));
	}
	
}
