import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TileTest {
	
	private Tile testTile = Tile.forPixels(34430575, 49899071, 19);

	@Test
	void tmsTest() {
		int [] expected = {134494,194918};
		assertArrayEquals(expected, testTile.getTms());
	}
	
	@Test
	void googleTest() {
		int [] expected = {134494,-194881};
		assertArrayEquals(expected, testTile.getGoogle());
		//outgoing result 194919
	}
	
	@Test
	void quadTreeTest() {
		String expected = "1202211";
		assertEquals(expected, testTile.quadTree());
		//shouldn't pass but does
	}
	
	@Test
	void boundsTest() {
		Point[] expected = {};
		assertEquals(expected, testTile.bounds());
	}
	
	
}
