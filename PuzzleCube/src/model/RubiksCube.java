package model;

import java.util.Random;

/**
 * PuzzleCube represents an arbitrarily-sized Rubik's cube puzzle with length n
 * 
 * @author akaps
 *
 */
public class RubiksCube {

	public final int DIMENSION = 3;
	private int[][][] pieces;

	public RubiksCube() {
		this.reset();
	}

	public void reset() {
		this.pieces = new int[CubeFace.MAX_SIDES][DIMENSION][DIMENSION];
		for (CubeFace face : CubeFace.values()) {
			for (int i = 0; i < DIMENSION; i++) {
				for (int j = 0; j < DIMENSION; j++) {
					this.pieces[face.ordinal()][i][j] = face.ordinal();
				}
			}
		}
	}

	public boolean isSolved() {
		for (CubeFace face : CubeFace.values()) {
			for (int i = 0; i < DIMENSION; i++) {
				for (int j = 0; j < DIMENSION; j++) {
					if (pieces[face.ordinal()][i][j] != face.ordinal()) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public String prettyPrint() {
		String result = "";
		// lots of cludging, knowing that the structure of a row and how many
		// can be missing
		// first 3 rows, just UP. Has a gap on the left side
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				result += "  ";
			}
			for (int j = 0; j < 3; j++) {
				result += pieces[CubeFace.UP.ordinal()][i][j] + " ";
			}
			result = result + "\n";
		}
		// middle 3 rows, shows LEFT, FRONT, RIGHT, and BACK. No padding
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				result += pieces[CubeFace.LEFT.ordinal()][i][j] + " ";
			}
			for (int j = 0; j < 3; j++) {
				result += pieces[CubeFace.FRONT.ordinal()][i][j] + " ";
			}
			for (int j = 0; j < 3; j++) {
				result += pieces[CubeFace.RIGHT.ordinal()][i][j] + " ";
			}
			for (int j = 0; j < 3; j++) {
				result += pieces[CubeFace.BACK.ordinal()][i][j] + " ";
			}
			result = result + "\n";
		}
		// last 3 rows, shows DOWN with padding on the left side
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				result += "  ";
			}
			for (int j = 0; j < 3; j++) {
				result += pieces[CubeFace.DOWN.ordinal()][i][j] + " ";
			}
			result = result + "\n";
		}
		return result;
	}

	public String toString() {
		String result = "";
		for (CubeFace face : CubeFace.values()) {
			result += face.toString() + "\n";
			result += toStringForSide(face) + "\n";
		}
		return result.trim();
	}

	public String toStringForSide(CubeFace face) {
		String result = "";
		for (int i = 0; i < DIMENSION; i++) {
			for (int j = 0; j < DIMENSION; j++) {
				result += pieces[face.ordinal()][i][j] + " ";
			}
			result = result.trim();
			result += "\n";
		}
		return result.trim();
	}

	public void rotate(CubeFace face, int howMuch, boolean clockwise) {
		for (int i = 0; i < howMuch; i++) {
			// rotate all the elements on a side
			rotateFace(face, clockwise);
			// rotate the associated segments on sides
			switch (face) {
			case FRONT:
				rotateForFront(clockwise);
				break;
			case BACK:
				rotateForBack(clockwise);
				break;
			case UP:
				rotateForUp(clockwise);
				break;
			case DOWN:
				rotateForDown(clockwise);
				break;
			case LEFT:
				rotateForLeft(clockwise);
				break;
			case RIGHT:
				rotateForRight(clockwise);
				break;
			default:
				System.out.println("Unreachable branch for face " + face);
				System.exit(1);
			}
		}
	}

	private void rotateForRight(boolean clockwise) {
		CubeCoordinate coord1;
		CubeCoordinate coord2;
		CubeCoordinate coord3;
		CubeCoordinate coord4;
		// left corner
		coord1 = new CubeCoordinate(CubeFace.UP, 2, 2);
		coord2 = new CubeCoordinate(CubeFace.BACK, 0, 0);
		coord3 = new CubeCoordinate(CubeFace.DOWN, 2, 2);
		coord4 = new CubeCoordinate(CubeFace.FRONT, 2, 2);
		fourWayRotate(coord1, coord2, coord3, coord4, clockwise);

		// middle
		coord1 = new CubeCoordinate(CubeFace.UP, 1, 2);
		coord2 = new CubeCoordinate(CubeFace.BACK, 1, 0);
		coord3 = new CubeCoordinate(CubeFace.DOWN, 1, 2);
		coord4 = new CubeCoordinate(CubeFace.FRONT, 1, 2);
		fourWayRotate(coord1, coord2, coord3, coord4, clockwise);

		// right corner
		coord1 = new CubeCoordinate(CubeFace.UP, 0, 2);
		coord2 = new CubeCoordinate(CubeFace.BACK, 2, 0);
		coord3 = new CubeCoordinate(CubeFace.DOWN, 0, 2);
		coord4 = new CubeCoordinate(CubeFace.FRONT, 0, 2);
		fourWayRotate(coord1, coord2, coord3, coord4, clockwise);
	}

	private void rotateForLeft(boolean clockwise) {
		CubeCoordinate coord1;
		CubeCoordinate coord2;
		CubeCoordinate coord3;
		CubeCoordinate coord4;
		// left corner
		coord1 = new CubeCoordinate(CubeFace.UP, 0, 0);
		coord2 = new CubeCoordinate(CubeFace.FRONT, 0, 0);
		coord3 = new CubeCoordinate(CubeFace.DOWN, 0, 0);
		coord4 = new CubeCoordinate(CubeFace.BACK, 2, 2);
		fourWayRotate(coord1, coord2, coord3, coord4, clockwise);

		// middle
		coord1 = new CubeCoordinate(CubeFace.UP, 1, 0);
		coord2 = new CubeCoordinate(CubeFace.FRONT, 1, 0);
		coord3 = new CubeCoordinate(CubeFace.DOWN, 1, 0);
		coord4 = new CubeCoordinate(CubeFace.BACK, 1, 2);
		fourWayRotate(coord1, coord2, coord3, coord4, clockwise);

		// right corner
		coord1 = new CubeCoordinate(CubeFace.UP, 2, 0);
		coord2 = new CubeCoordinate(CubeFace.FRONT, 2, 0);
		coord3 = new CubeCoordinate(CubeFace.DOWN, 2, 0);
		coord4 = new CubeCoordinate(CubeFace.BACK, 0, 2);
		fourWayRotate(coord1, coord2, coord3, coord4, clockwise);
	}

	private void rotateForDown(boolean clockwise) {
		CubeCoordinate coord1;
		CubeCoordinate coord2;
		CubeCoordinate coord3;
		CubeCoordinate coord4;
		// left corner
		coord1 = new CubeCoordinate(CubeFace.FRONT, 2, 0);
		coord2 = new CubeCoordinate(CubeFace.RIGHT, 2, 0);
		coord3 = new CubeCoordinate(CubeFace.BACK, 2, 0);
		coord4 = new CubeCoordinate(CubeFace.LEFT, 2, 0);
		fourWayRotate(coord1, coord2, coord3, coord4, clockwise);

		// middle
		coord1 = new CubeCoordinate(CubeFace.FRONT, 2, 1);
		coord2 = new CubeCoordinate(CubeFace.RIGHT, 2, 1);
		coord3 = new CubeCoordinate(CubeFace.BACK, 2, 1);
		coord4 = new CubeCoordinate(CubeFace.LEFT, 2, 1);
		fourWayRotate(coord1, coord2, coord3, coord4, clockwise);

		// right corner
		coord1 = new CubeCoordinate(CubeFace.FRONT, 2, 2);
		coord2 = new CubeCoordinate(CubeFace.RIGHT, 2, 2);
		coord3 = new CubeCoordinate(CubeFace.BACK, 2, 2);
		coord4 = new CubeCoordinate(CubeFace.LEFT, 2, 2);
		fourWayRotate(coord1, coord2, coord3, coord4, clockwise);
	}

	private void rotateForUp(boolean clockwise) {
		CubeCoordinate coord1;
		CubeCoordinate coord2;
		CubeCoordinate coord3;
		CubeCoordinate coord4;
		// left corner
		coord1 = new CubeCoordinate(CubeFace.BACK, 0, 2);
		coord2 = new CubeCoordinate(CubeFace.RIGHT, 0, 2);
		coord3 = new CubeCoordinate(CubeFace.FRONT, 0, 2);
		coord4 = new CubeCoordinate(CubeFace.LEFT, 0, 2);
		fourWayRotate(coord1, coord2, coord3, coord4, clockwise);

		// middle
		coord1 = new CubeCoordinate(CubeFace.BACK, 0, 1);
		coord2 = new CubeCoordinate(CubeFace.RIGHT, 0, 1);
		coord3 = new CubeCoordinate(CubeFace.FRONT, 0, 1);
		coord4 = new CubeCoordinate(CubeFace.LEFT, 0, 1);
		fourWayRotate(coord1, coord2, coord3, coord4, clockwise);

		// right corner
		coord1 = new CubeCoordinate(CubeFace.BACK, 0, 0);
		coord2 = new CubeCoordinate(CubeFace.RIGHT, 0, 0);
		coord3 = new CubeCoordinate(CubeFace.FRONT, 0, 0);
		coord4 = new CubeCoordinate(CubeFace.LEFT, 0, 0);
		fourWayRotate(coord1, coord2, coord3, coord4, clockwise);
	}

	private void rotateForBack(boolean clockwise) {
		CubeCoordinate coord1;
		CubeCoordinate coord2;
		CubeCoordinate coord3;
		CubeCoordinate coord4;
		// left corner
		coord1 = new CubeCoordinate(CubeFace.UP, 0, 2);
		coord2 = new CubeCoordinate(CubeFace.LEFT, 0, 0);
		coord3 = new CubeCoordinate(CubeFace.DOWN, 2, 0);
		coord4 = new CubeCoordinate(CubeFace.RIGHT, 2, 2);
		fourWayRotate(coord1, coord2, coord3, coord4, clockwise);

		// middle
		coord1 = new CubeCoordinate(CubeFace.UP, 0, 1);
		coord2 = new CubeCoordinate(CubeFace.LEFT, 1, 0);
		coord3 = new CubeCoordinate(CubeFace.DOWN, 2, 1);
		coord4 = new CubeCoordinate(CubeFace.RIGHT, 1, 2);
		fourWayRotate(coord1, coord2, coord3, coord4, clockwise);

		// right corner
		coord1 = new CubeCoordinate(CubeFace.UP, 0, 0);
		coord2 = new CubeCoordinate(CubeFace.LEFT, 2, 0);
		coord3 = new CubeCoordinate(CubeFace.DOWN, 2, 2);
		coord4 = new CubeCoordinate(CubeFace.RIGHT, 0, 2);
		fourWayRotate(coord1, coord2, coord3, coord4, clockwise);
	}

	private void rotateForFront(boolean clockwise) {
		CubeCoordinate coord1;
		CubeCoordinate coord2;
		CubeCoordinate coord3;
		CubeCoordinate coord4;
		// left corner
		coord1 = new CubeCoordinate(CubeFace.UP, 2, 0);
		coord2 = new CubeCoordinate(CubeFace.RIGHT, 0, 0);
		coord3 = new CubeCoordinate(CubeFace.DOWN, 0, 2);
		coord4 = new CubeCoordinate(CubeFace.LEFT, 2, 2);
		fourWayRotate(coord1, coord2, coord3, coord4, clockwise);

		// middle
		coord1 = new CubeCoordinate(CubeFace.UP, 2, 1);
		coord2 = new CubeCoordinate(CubeFace.RIGHT, 1, 0);
		coord3 = new CubeCoordinate(CubeFace.DOWN, 0, 1);
		coord4 = new CubeCoordinate(CubeFace.LEFT, 1, 2);
		fourWayRotate(coord1, coord2, coord3, coord4, clockwise);

		// right corner
		coord1 = new CubeCoordinate(CubeFace.UP, 2, 2);
		coord2 = new CubeCoordinate(CubeFace.RIGHT, 2, 0);
		coord3 = new CubeCoordinate(CubeFace.DOWN, 0, 0);
		coord4 = new CubeCoordinate(CubeFace.LEFT, 0, 2);
		fourWayRotate(coord1, coord2, coord3, coord4, clockwise);
	}

	private void rotateFace(CubeFace face, boolean clockwise) {
		rotateFaceCorners(face, clockwise);
		rotateFaceEdges(face, clockwise);
	}

	private void rotateFaceCorners(CubeFace face, boolean clockwise) {
		CubeCoordinate coord1 = new CubeCoordinate(face, 0, 0);
		CubeCoordinate coord2 = new CubeCoordinate(face, 0, 2);
		CubeCoordinate coord3 = new CubeCoordinate(face, 2, 2);
		CubeCoordinate coord4 = new CubeCoordinate(face, 2, 0);
		fourWayRotate(coord1, coord2, coord3, coord4, clockwise);
	}

	private void rotateFaceEdges(CubeFace face, boolean clockwise) {
		CubeCoordinate coord1 = new CubeCoordinate(face, 0, 1);
		CubeCoordinate coord2 = new CubeCoordinate(face, 1, 2);
		CubeCoordinate coord3 = new CubeCoordinate(face, 2, 2);
		CubeCoordinate coord4 = new CubeCoordinate(face, 1, 0);
		fourWayRotate(coord1, coord2, coord3, coord4, clockwise);
	}

	private void fourWayRotate(CubeCoordinate first, CubeCoordinate second, CubeCoordinate third, CubeCoordinate fourth,
			boolean clockwise) {
		int swap = pieces[first.FACE][first.X][first.Y];
		if (clockwise) {
			pieces[first.FACE][first.X][first.Y] = pieces[second.FACE][second.X][second.Y];
			pieces[second.FACE][second.X][second.Y] = pieces[third.FACE][third.X][third.Y];
			pieces[third.FACE][third.X][third.Y] = pieces[fourth.FACE][fourth.X][fourth.Y];
			pieces[fourth.FACE][fourth.X][fourth.Y] = swap;
		} else {
			pieces[first.FACE][first.X][first.Y] = pieces[fourth.FACE][fourth.X][fourth.Y];
			pieces[fourth.FACE][fourth.X][fourth.Y] = pieces[third.FACE][third.X][third.Y];
			pieces[third.FACE][third.X][third.Y] = pieces[second.FACE][second.X][second.Y];
			pieces[second.FACE][second.X][second.Y] = swap;
		}
	}

	public void shuffle(int numMoves) {
		Random rand = new Random();
		for (int i = 0; i < numMoves; i++) {
			// pick a side
			CubeFace side = CubeFace.values()[rand.nextInt(CubeFace.MAX_SIDES)];
			// pick between quarter and half-turn
			int howMuch = rand.nextInt(1) + 1;
			// if quarter, choose direction
			boolean counterclockwise = rand.nextBoolean();
			rotate(side, howMuch, counterclockwise);
		}
	}

}
