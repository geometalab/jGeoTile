package ch.hsr.ifs.jgeotile;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ch.hsr.ifs.jgeotile.Point;
import ch.hsr.ifs.jgeotile.Tile;

class TileTest {
	
	private Tile testTileChicago = Tile.forPixels(34430575, 49899071, 19);
	private Tile testTile = Tile.fromTms(67, 83, 7);
	//private Tile testTile2 = Tile.fromQuadTree("1202211");
	//TMS (67,83)
	//google (67,44)
	// quadtree "1202211"
	//zoom 7
	@Test
	void fromGoogleTest() {
		Tile googleTile = Tile.fromGoogle(67,44,7);
		assertEquals(testTile.getTms()[0],googleTile.getTms()[0]);
		assertEquals(testTile.getTms()[1],googleTile.getTms()[1]);
	}
	
	@Test
	void fromTmsTest() {
		Tile tmsTile = Tile.fromTms(67, 83, 7);
		assertEquals(testTile.getTms()[0],tmsTile.getTms()[0]);
		assertEquals(testTile.getTms()[1],tmsTile.getTms()[1]);
	}
	
	@Test
	void fromTasmaniaTest() {
		Tile tasmania = Tile.fromGoogle(1853, 1289, 11);
		assertEquals(1853,tasmania.getTms()[0]);
		assertEquals(758,tasmania.getTms()[1]);
	}
	
	@Test
	void fromQuadTreeTest() throws Exception {
		Tile quadTreeTile = Tile.fromQuadTree("1202211");
		assertEquals(testTile.getTms()[0],quadTreeTile.getTms()[0]);
		assertEquals(testTile.getTms()[1],quadTreeTile.getTms()[1]);
		assertEquals(testTile.getZoom(),quadTreeTile.getZoom());
	}
	
	@Test
	void crossCheckTest() throws Exception {
		Tile crossCheckTile = Tile.fromQuadTree("1202211");
		assertEquals(testTile.getTms()[0],crossCheckTile.getTms()[0]);
		assertEquals(testTile.getTms()[1],crossCheckTile.getTms()[1]);
		assertEquals(testTile.getZoom(),crossCheckTile.getZoom());
		assertEquals(testTile.getGoogle()[0],crossCheckTile.getGoogle()[0]);
		assertEquals(testTile.getGoogle()[1],crossCheckTile.getGoogle()[1]);
		assertEquals(testTile.quadTree(),crossCheckTile.quadTree());
	}
	
	@Test
	void forPixelsTest() {
		Tile pixelsTile = Tile.forPixels(34430575, 49899071, 19);
		assertEquals(testTileChicago.getTms()[0],pixelsTile.getTms()[0]);
		assertEquals(testTileChicago.getTms()[1],pixelsTile.getTms()[1]);
	}

	@Test
	void forMetersTest() {
		Point testPoint = Point.fromPixel(34430575, 49899071, 19);
		double meterx = testPoint.getMeterX();
		double metery = testPoint.getMeterY();
		Tile meterTile = Tile.forMeters(meterx, metery, 19);
		assertEquals(testTileChicago.getTms()[0],meterTile.getTms()[0]);
		assertEquals(testTileChicago.getTms()[1],meterTile.getTms()[1]);
	}

	@Test
	void pixelBoundsTest() throws Exception {
		Tile boundsTile = Tile.fromQuadTree("0302222310303211330");
		Point pointMin = Point.fromPixel(34430464, 49899264,19);
		Point pointMax = Point.fromPixel(34430720, 49899008,19);

		assertEquals(pointMin.getLatitude(),boundsTile.bounds()[0].getLatitude());
		assertEquals(pointMin.getLongitude(),boundsTile.bounds()[0].getLongitude());
		assertEquals(pointMax.getLatitude(),boundsTile.bounds()[1].getLatitude());
		assertEquals(pointMax.getLongitude(),boundsTile.bounds()[1].getLongitude());
	}
	private double epsilon = 0.1;
	@Test
	void testTileBounds1() {
		Tile tile =  Tile.fromTms(0, 1, 1);
		Point pointMin = tile.bounds()[0];
		Point pointMax = tile.bounds()[1];
		Point expectedMin= new Point(0.0, -180.0);
		Point expectedMax = new Point(85.05, 0.0);
		assertEquals(expectedMin.getLatitude(),pointMin.getLatitude(),epsilon);
		assertEquals(expectedMin.getLongitude(),pointMin.getLongitude(),epsilon);
		assertEquals(expectedMax.getLatitude(),pointMax.getLatitude(),epsilon);
		assertEquals(expectedMax.getLongitude(),pointMax.getLongitude(),epsilon);
	}
	
	@Test
	void testTileBounds2() {
		Tile tile =  Tile.fromTms(1, 1, 1);
		Point pointMin = tile.bounds()[0];
		Point pointMax = tile.bounds()[1];
		Point expectedMin= new Point(0.0, 0.0);
		Point expectedMax = new Point(85.05, 180.0);
		assertEquals(expectedMin.getLatitude(),pointMin.getLatitude(),epsilon);
		assertEquals(expectedMin.getLongitude(),pointMin.getLongitude(),epsilon);
		assertEquals(expectedMax.getLatitude(),pointMax.getLatitude(),epsilon);
		assertEquals(expectedMax.getLongitude(),pointMax.getLongitude(),epsilon);
	}
	
	@Test
	void testTileBounds3() {
		Tile tile =  Tile.fromTms(0, 0, 1);
		Point pointMin = tile.bounds()[0];
		Point pointMax = tile.bounds()[1];
		Point expectedMin= new Point(-85.05, -180.0);
		Point expectedMax = new Point(0.0, 0.0);
		assertEquals(expectedMin.getLatitude(),pointMin.getLatitude(),epsilon);
		assertEquals(expectedMin.getLongitude(),pointMin.getLongitude(),epsilon);
		assertEquals(expectedMax.getLatitude(),pointMax.getLatitude(),epsilon);
		assertEquals(expectedMax.getLongitude(),pointMax.getLongitude(),epsilon);
	}
	
	@Test
	void testTileBounds4() {
		Tile tile =  Tile.fromTms(1, 0, 1);
		Point pointMin = tile.bounds()[0];
		Point pointMax = tile.bounds()[1];
		Point expectedMin= new Point(-85.05, 0.0);
		Point expectedMax = new Point(0.0, 180.0);
		assertEquals(expectedMin.getLatitude(),pointMin.getLatitude(),epsilon);
		assertEquals(expectedMin.getLongitude(),pointMin.getLongitude(),epsilon);
		assertEquals(expectedMax.getLatitude(),pointMax.getLatitude(),epsilon);
		assertEquals(expectedMax.getLongitude(),pointMax.getLongitude(),epsilon);
	}

	@Test
	void latitudeLongitudeTest() {
		Tile llTile = Tile.forLatitudeLongitude(41.85, -87.65, 19);
		assertEquals(testTileChicago.getTms()[0],llTile.getTms()[0]);
		assertEquals(testTileChicago.getTms()[1],llTile.getTms()[1]);
	}

	@Test
	void pointTest() {
		Point point = Point.fromLatitudeLongitude(41.85, -87.64999999999998);
		Tile pointTile = Tile.forPoint(point, 19);
		assertEquals(testTileChicago.getTms()[0],pointTile.getTms()[0]);
		assertEquals(testTileChicago.getTms()[1],pointTile.getTms()[1]);
		assertEquals(testTileChicago.getZoom(),pointTile.getZoom());
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
