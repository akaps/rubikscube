package commandline;

import java.util.Scanner;

import model.CubeFace;
import model.RubiksCube;

public class RubiksCubeCommand {

	private static RubiksCube cube;

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);

		cube = new RubiksCube();

		System.out.println("Enter a number for how many moves to scramble the cube");
		int numMoves = keyboard.nextInt();
		keyboard.nextLine();
		cube.shuffle(numMoves);

		do {
			System.out.println(cube.prettyPrint());
			System.out.println("Enter a rotation command (<FBUDLR><'>, reset, or shuffle)");
			String input = keyboard.nextLine();
			interpretCommand(input);
		} while (!cube.isSolved());

		System.out.println("You did it! :D");
		keyboard.close();
	}

	private static void interpretCommand(String input) {
		if (input.length() > 2) {
			if (input.equalsIgnoreCase("reset")) {
				cube.reset();
			} else if (input.equalsIgnoreCase("shuffle")) {
				cube.shuffle(10);
			}
		} else {
			char direction = input.charAt(0);
			CubeFace face=null;
			switch (direction) {
			case 'U':
				face = CubeFace.UP;
				break;
			case 'D':
				face = CubeFace.DOWN;
				break;
			case 'L':
				face = CubeFace.LEFT;
				break;
			case 'R':
				face = CubeFace.RIGHT;
				break;
			case 'F':
				face = CubeFace.FRONT;
				break;
			case 'B':
				face = CubeFace.BACK;
				break;
			default:
				System.out.println("No known direction for + direction");
				return;
			}
			boolean clockwise = true;
			if (input.length() > 1) {
				clockwise = input.charAt(1) == '\'';
			}
			cube.rotate(face, 1, clockwise);
		}
	}

}
