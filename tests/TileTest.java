import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TileTest {
	
	private Tile testTile = Tile.forPixels(34430575, 49899071, 19);
	

	@Test
	void fromGoogleTest() {
		Tile googleTile = Tile.fromGoogle(67,44,19);
		assert googleTile == testTile;
	}
	
	@Test
	void fromTmsTest() {
		Tile tmsTile = Tile.fromTms(67, 83, 19);
		assert tmsTile == testTile;
	}
	
	@Test
	void fromTasmaniaTest() {
		int[] tmsTasmania = {1853,758};
		Tile tasmania = Tile.fromGoogle(1853, 1289, 19);
		assert tasmania.getTms()== tmsTasmania;
	}
	
	@Test
	void fromQuadTreeTest() throws Exception {
		Tile quadTreeTile = Tile.fromQuadTree("1202211");
		assert quadTreeTile.getTms() == testTile.getTms();
		assert quadTreeTile.zoom == testTile.zoom;
	}
	
	@Test
	void crossCheckTest() throws Exception {
		Tile crossCheckTile = Tile.fromQuadTree("1202211");
		assert crossCheckTile.getTms() == testTile.getTms();
		assert crossCheckTile.zoom == testTile.zoom;
		assert crossCheckTile.getGoogle() == testTile.getGoogle();
		assert crossCheckTile.quadTree().equals(testTile.quadTree());
	}
	
	
}
