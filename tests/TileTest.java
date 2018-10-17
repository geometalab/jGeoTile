import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TileTest {
	
	private Tile testTile = Tile.forPixels(34430575, 49899071, 19);
	

	@Test
	void fromGoogleTest() {
		Tile googleTile = Tile.fromGoogle(67,44,19);
		assertEquals(testTile.getTms()[0],googleTile.getTms()[0]);
		assertEquals(testTile.getTms()[1],googleTile.getTms()[1]);
	}
	
	@Test
	void fromTmsTest() {
		Tile tmsTile = Tile.fromTms(67, 83, 19);
		assertEquals(testTile.getTms()[0],tmsTile.getTms()[0]);
		assertEquals(testTile.getTms()[1],tmsTile.getTms()[1]);
	}
	
	@Test
	void fromTasmaniaTest() {
		int[] tmsTasmania = {1853,758};
		Tile tasmania = Tile.fromGoogle(1853, 1289, 19);
		assertEquals(tmsTasmania,tasmania.getTms());
	}
	
	@Test
	void fromQuadTreeTest() throws Exception {
		Tile quadTreeTile = Tile.fromQuadTree("1202211");
		assertEquals(testTile.getTms(),quadTreeTile.getTms());
		assertEquals(testTile.getZoom(),quadTreeTile.getZoom());
	}
	
	@Test
	void crossCheckTest() throws Exception {
		Tile crossCheckTile = Tile.fromQuadTree("1202211");
		assertEquals(testTile.getTms(),crossCheckTile.getTms());
		assertEquals(testTile.getZoom(),crossCheckTile.getZoom());
		assertEquals(testTile.getGoogle(),crossCheckTile.getGoogle());
		assertEquals(testTile.quadTree(),crossCheckTile.quadTree());
	}
	
	@Test
	void forPixelsTest() {
		Tile pixelsTile = Tile.forPixels(34430575, 49899071, 19);
		assertEquals(testTile.getTms()[0],pixelsTile.getTms()[0]);
		assertEquals(testTile.getTms()[1],pixelsTile.getTms()[1]);
	}

	@Test
	void forMetersTest() {
		Tile chicagoTile = Tile.forLatitudeLongitude(41.85, -87.64999999999998, 19);
		Point testPoint = Point.fromPixel(34430575, 49899071, 19);
		double meterx = testPoint.getMeterX();
		double metery = testPoint.getMeterY();
		Tile tile = Tile.forMeters(meterx, metery, 19);
		assertEquals(chicagoTile.getTms(),tile.getTms());
	}

	@Test
	void pixelBoundsTest() throws Exception {
		Tile boundsTile = Tile.fromQuadTree("1202211");
		Point pointMin = Point.fromPixel(34430464, 49899264,19);
		Point pointMax = Point.fromPixel(34430720, 49899008,19);

		assertEquals(pointMin,boundsTile.bounds()[0]);
		assertEquals(pointMax,boundsTile.bounds()[1]);
	}
	
	@Test
	void testTileBounds1() {
		Tile tile =  Tile.fromTms(0, 1, 3);
		Point pointMin = tile.bounds()[0];
		Point pointMax = tile.bounds()[1];
		Point expectedMin= new Point(0.0, -180.0);
		Point expectedMax = new Point(85.05, 0.0);
		assertEquals(expectedMin.getLatitude(),pointMin.getLatitude());
		assertEquals(expectedMin.getLongitude(),pointMin.getLongitude());
		assertEquals(expectedMax.getLatitude(),pointMax.getLatitude());
		assertEquals(expectedMax.getLongitude(),pointMax.getLongitude());
	}
	
	@Test
	void testTileBounds2() {
		Tile tile =  Tile.fromTms(1, 1, 3);
		Point pointMin = tile.bounds()[0];
		Point pointMax = tile.bounds()[1];
		Point expectedMin= new Point(0.0, 0.0);
		Point expectedMax = new Point(85.05, 180.0);
		assertEquals(expectedMin.getLatitude(),pointMin.getLatitude());
		assertEquals(expectedMin.getLongitude(),pointMin.getLongitude());
		assertEquals(expectedMax.getLatitude(),pointMax.getLatitude());
		assertEquals(expectedMax.getLongitude(),pointMax.getLongitude());
	}
	
	@Test
	void testTileBounds3() {
		Tile tile =  Tile.fromTms(0, 0, 3);
		Point pointMin = tile.bounds()[0];
		Point pointMax = tile.bounds()[1];
		Point expectedMin= new Point(-85.05, -180.0);
		Point expectedMax = new Point(0.0, 0.0);
		assertEquals(expectedMin.getLatitude(),pointMin.getLatitude());
		assertEquals(expectedMin.getLongitude(),pointMin.getLongitude());
		assertEquals(expectedMax.getLatitude(),pointMax.getLatitude());
		assertEquals(expectedMax.getLongitude(),pointMax.getLongitude());
	}
	
	@Test
	void testTileBounds4() {
		Tile tile =  Tile.fromTms(1, 0, 3);
		Point pointMin = tile.bounds()[0];
		Point pointMax = tile.bounds()[1];
		Point expectedMin= new Point(-85.05, 0.0);
		Point expectedMax = new Point(0.0, 180.0);
		assertEquals(expectedMin.getLatitude(),pointMin.getLatitude());
		assertEquals(expectedMin.getLongitude(),pointMin.getLongitude());
		assertEquals(expectedMax.getLatitude(),pointMax.getLatitude());
		assertEquals(expectedMax.getLongitude(),pointMax.getLongitude());
	}

	@Test
	void latitudeLongitudeTest() {
		Tile llTile = Tile.forLatitudeLongitude(41.85, -87.65, 19);
		assertEquals(testTile.getTms()[0],llTile.getTms()[0]);
		assertEquals(testTile.getTms()[1],llTile.getTms()[1]);
	}

	@Test
	void pointTest() {
		Point point = Point.fromLatitudeLongitude(41.85, -87.65);
		Tile pointTile = Tile.forPoint(point, 19);
		assertEquals(testTile.getTms(),pointTile.getTms());
		assertEquals(testTile.getZoom(),pointTile.getZoom());
	}

	@Test
	void assertTmsX() {
		assertThrows(AssertionError.class,() -> Tile.fromTms(-1, 0, 1));
		assertThrows(AssertionError.class,() -> Tile.fromTms(4, 0, 1));
	}
	
	@Test
	void notAssertTmsX() {
		assertAll(()-> Tile.fromTms(0, 0, 2));
		assertAll(()-> Tile.fromTms(3, 0, 2));
	}
	
	@Test
	void assertTmsY() {
		assertThrows(AssertionError.class,() -> Tile.fromTms(0, -1, 1));
		assertThrows(AssertionError.class,() -> Tile.fromTms(0, 4, 1));
	}
	
	@Test
	void notAssertTmsY() {
		assertAll(()-> Tile.fromTms(0, 0, 2));
		assertAll(()-> Tile.fromTms(0, 3, 2));
	}
	
	@Test
	void asserGoogleX() {
		assertThrows(AssertionError.class,() -> Tile.fromGoogle(-1, 44, 2));
		assertThrows(AssertionError.class,() -> Tile.fromGoogle(3, 44, 2));
	}
	
	@Test
	void notAssertGoogleX() {
		assertAll(()-> Tile.fromGoogle(0, 44, 10));
		assertAll(()-> Tile.fromGoogle(2, 44, 10));
	}
	
	void asserGoogleY() {
		assertThrows(AssertionError.class,() -> Tile.fromGoogle(0, -1, 2));
		assertThrows(AssertionError.class,() -> Tile.fromGoogle(0, 3, 2));
	}
	
	@Test
	void notAssertGoogleY() {
		assertAll(()-> Tile.fromGoogle(0, 0, 2));
		assertAll(()-> Tile.fromGoogle(0, 2, 2));
	}
	
	@Test
	void assertQuadTree(){
		
		assertThrows(AssertionError.class,() -> Tile.fromQuadTree("A202211"));
		assertThrows(AssertionError.class,() -> Tile.fromQuadTree("4202211"));
	}
	
	@Test
	void notAssertQuadTree() {
		assertAll(()-> Tile.fromQuadTree("0202211"));
		assertAll(()-> Tile.fromQuadTree("3202211"));
	}
}
