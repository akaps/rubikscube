package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.CubeFace;
import model.RubiksCube;

public class RubiksCubeTest {

	@Test
	public void testInitRubiksCube() {
		RubiksCube test = new RubiksCube();
		assertTrue(test.isSolved());
		assertEquals(3, test.DIMENSION);
	}

	@Test
	public void testRubiksCubeToStringForSides() {
		RubiksCube cube = new RubiksCube();
		assertEquals(cube.toStringForSide(CubeFace.FRONT), "0 0 0\n0 0 0\n0 0 0");
		assertEquals(cube.toStringForSide(CubeFace.BACK), "1 1 1\n1 1 1\n1 1 1");
		assertEquals(cube.toStringForSide(CubeFace.UP), "2 2 2\n2 2 2\n2 2 2");
		assertEquals(cube.toStringForSide(CubeFace.DOWN), "3 3 3\n3 3 3\n3 3 3");
		assertEquals(cube.toStringForSide(CubeFace.LEFT), "4 4 4\n4 4 4\n4 4 4");
		assertEquals(cube.toStringForSide(CubeFace.RIGHT), "5 5 5\n5 5 5\n5 5 5");
	}

	@Test
	public void testRubiksCubeToString() {
		RubiksCube cube = new RubiksCube();
		assertEquals(cube.toString(),
				"FRONT\n" + "0 0 0\n0 0 0\n0 0 0\n" + "BACK\n" + "1 1 1\n1 1 1\n1 1 1\n" + "UP\n"
						+ "2 2 2\n2 2 2\n2 2 2\n" + "DOWN\n" + "3 3 3\n3 3 3\n3 3 3\n" + "LEFT\n"
						+ "4 4 4\n4 4 4\n4 4 4\n" + "RIGHT\n" + "5 5 5\n5 5 5\n5 5 5");
	}

	@Test
	public void testRubiksFrontIdentity() {
		RubiksCube cube = new RubiksCube();
		cube.rotate(CubeFace.FRONT, 1, true);
		assertFalse(cube.isSolved());
		
		cube.rotate(CubeFace.FRONT, 1, true);
		assertFalse(cube.isSolved());
		
		cube.rotate(CubeFace.FRONT, 1, true);
		assertFalse(cube.isSolved());
		
		cube.rotate(CubeFace.FRONT, 1, true);
		assertTrue(cube.isSolved());

		cube.rotate(CubeFace.FRONT, 2, true);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.FRONT, 2, true);
		assertTrue(cube.isSolved());

		cube.rotate(CubeFace.FRONT, 1, false);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.FRONT, 1, false);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.FRONT, 1, false);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.FRONT, 1, false);
		assertTrue(cube.isSolved());

		cube.rotate(CubeFace.FRONT, 2, true);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.FRONT, 2, false);
		assertTrue(cube.isSolved());
	}

	@Test
	public void testRubiksBackIdentity() {
		RubiksCube cube = new RubiksCube();
		cube.rotate(CubeFace.BACK, 1, true);
		assertFalse(cube.isSolved());
		assertEquals(cube.toStringForSide(CubeFace.LEFT), "3 4 4\n3 4 4\n3 4 4");
		assertEquals(cube.toStringForSide(CubeFace.UP), "4 4 4\n2 2 2\n2 2 2");
		assertEquals(cube.toStringForSide(CubeFace.RIGHT), "5 5 2\n5 5 2\n5 5 2");
		assertEquals(cube.toStringForSide(CubeFace.DOWN), "3 3 3\n3 3 3\n5 5 5");
		
		cube.rotate(CubeFace.BACK, 1, true);
		assertFalse(cube.isSolved());
		
		cube.rotate(CubeFace.BACK, 1, true);
		assertFalse(cube.isSolved());

		cube.rotate(CubeFace.BACK, 1, true);
		assertTrue(cube.isSolved());
		
		cube.rotate(CubeFace.BACK, 2, true);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.BACK, 2, true);
		assertTrue(cube.isSolved());

		cube.rotate(CubeFace.BACK, 1, false);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.BACK, 1, false);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.BACK, 1, false);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.BACK, 1, false);
		assertTrue(cube.isSolved());

		cube.rotate(CubeFace.BACK, 2, true);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.BACK, 2, false);
		assertTrue(cube.isSolved());
	}

	@Test
	public void testRubiksLeftIdentity() {
		RubiksCube cube = new RubiksCube();
		cube.rotate(CubeFace.LEFT, 1, true);
		assertFalse(cube.isSolved());
		
		cube.rotate(CubeFace.LEFT, 1, true);
		assertFalse(cube.isSolved());
		
		cube.rotate(CubeFace.LEFT, 1, true);
		assertFalse(cube.isSolved());
		
		cube.rotate(CubeFace.LEFT, 1, true);
		assertTrue(cube.isSolved());
		
		cube.rotate(CubeFace.LEFT, 2, true);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.LEFT, 2, true);
		assertTrue(cube.isSolved());

		cube.rotate(CubeFace.LEFT, 1, false);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.LEFT, 1, false);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.LEFT, 1, false);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.LEFT, 1, false);
		assertTrue(cube.isSolved());

		cube.rotate(CubeFace.LEFT, 2, true);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.LEFT, 2, false);
		assertTrue(cube.isSolved());
	}

	@Test
	public void testRubiksRightIdentity() {
		RubiksCube cube = new RubiksCube();
		cube.rotate(CubeFace.RIGHT, 1, true);
		assertFalse(cube.isSolved());
		
		cube.rotate(CubeFace.RIGHT, 1, true);
		assertFalse(cube.isSolved());
		
		cube.rotate(CubeFace.RIGHT, 1, true);
		assertFalse(cube.isSolved());
		
		cube.rotate(CubeFace.RIGHT, 1, true);
		assertTrue(cube.isSolved());
		
		cube.rotate(CubeFace.RIGHT, 2, true);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.RIGHT, 2, true);
		assertTrue(cube.isSolved());

		cube.rotate(CubeFace.RIGHT, 1, false);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.RIGHT, 1, false);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.RIGHT, 1, false);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.RIGHT, 1, false);
		assertTrue(cube.isSolved());

		cube.rotate(CubeFace.RIGHT, 2, true);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.RIGHT, 2, false);
		assertTrue(cube.isSolved());
	}

	@Test
	public void testRubiksUpIdentity() {
		RubiksCube cube = new RubiksCube();
		cube.rotate(CubeFace.UP, 1, true);
		assertFalse(cube.isSolved());
		
		cube.rotate(CubeFace.UP, 1, true);
		assertFalse(cube.isSolved());
		
		cube.rotate(CubeFace.UP, 1, true);
		assertFalse(cube.isSolved());
		
		cube.rotate(CubeFace.UP, 1, true);
		assertTrue(cube.isSolved());
		
		cube.rotate(CubeFace.UP, 2, true);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.UP, 2, true);
		assertTrue(cube.isSolved());

		cube.rotate(CubeFace.UP, 1, false);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.UP, 1, false);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.UP, 1, false);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.UP, 1, false);
		assertTrue(cube.isSolved());

		cube.rotate(CubeFace.UP, 2, true);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.UP, 2, false);
		assertTrue(cube.isSolved());
	}

	@Test
	public void testRubiksDownIdentity() {
		RubiksCube cube = new RubiksCube();
		cube.rotate(CubeFace.DOWN, 1, true);
		assertFalse(cube.isSolved());
		
		cube.rotate(CubeFace.DOWN, 1, true);
		assertFalse(cube.isSolved());
		
		cube.rotate(CubeFace.DOWN, 1, true);
		assertFalse(cube.isSolved());
		
		cube.rotate(CubeFace.DOWN, 1, true);
		assertTrue(cube.isSolved());
		
		cube.rotate(CubeFace.DOWN, 2, true);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.DOWN, 2, true);
		assertTrue(cube.isSolved());

		cube.rotate(CubeFace.DOWN, 1, false);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.DOWN, 1, false);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.DOWN, 1, false);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.DOWN, 1, false);
		assertTrue(cube.isSolved());

		cube.rotate(CubeFace.DOWN, 2, true);
		assertFalse(cube.isSolved());
		cube.rotate(CubeFace.DOWN, 2, false);
		assertTrue(cube.isSolved());
	}

	@Test
	public void testRubiksShuffle() {
		RubiksCube cube = new RubiksCube();
		assertTrue(cube.isSolved());
		cube.shuffle(1);
		assertFalse(cube.isSolved());
		cube.shuffle(100);
		assertFalse(cube.isSolved());
	}

	@Test
	public void testRubiksResetAfterShuffle() {
		RubiksCube cube = new RubiksCube();
		assertTrue(cube.isSolved());
		cube.shuffle(20);
		assertFalse(cube.isSolved());
		cube.reset();
		assertTrue(cube.isSolved());
	}

	@Test
	public void testPrettyPrint() {
		RubiksCube cube = new RubiksCube();
		assertEquals(cube.prettyPrint(),
				"      2 2 2 \n" + "      2 2 2 \n" + "      2 2 2 \n" + "4 4 4 0 0 0 5 5 5 1 1 1 \n"
						+ "4 4 4 0 0 0 5 5 5 1 1 1 \n" + "4 4 4 0 0 0 5 5 5 1 1 1 \n" + "      3 3 3 \n"
						+ "      3 3 3 \n" + "      3 3 3 \n");
		cube.rotate(CubeFace.DOWN, 1, true);
		assertEquals(cube.prettyPrint(),
				"      2 2 2 \n" + "      2 2 2 \n" + "      2 2 2 \n" + "4 4 4 0 0 0 5 5 5 1 1 1 \n"
						+ "4 4 4 0 0 0 5 5 5 1 1 1 \n" + "0 0 0 5 5 5 1 1 1 4 4 4 \n" + "      3 3 3 \n"
						+ "      3 3 3 \n" + "      3 3 3 \n");
	}
}
