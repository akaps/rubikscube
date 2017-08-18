package model;

public class CubeCoordinate {

	public final int FACE;
	public final int X;
	public final int Y;

	public CubeCoordinate(CubeFace face, int x, int y) {
		FACE = face.ordinal();
		X = x;
		Y = y;
	}

}
