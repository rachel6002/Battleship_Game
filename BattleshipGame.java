import java.util.Scanner;

/**
 * The class for setting up the game, accept shots from the user as coordinates,
 * display the results, print final scores and ask the user if they want to play
 * again.
 * 
 * * @author Huifang Ye & Rachel Kong
 */

public class BattleshipGame {
	public static Scanner scnr;

	public static void main(String[] args) {
		scnr = new Scanner(System.in);
		while (true) {
			oneRound();
			if (!nextRound()) {
				break;
			}
		}
	}

	/**
	 * The method to print the map and all necessary information
	 * @param ocean
	 */
	private static void printMessage(Ocean ocean) {
		ocean.print();
		System.out.println("Hits: S   Misses: -   Not shot: .   Sunken: x");
		System.out.println("Number of shots:        " + ocean.getShotsFired());
		System.out.println("Number of hits:         " + ocean.getHitCount());
		System.out.println("Number of ships sunken: " + ocean.getShipsSunk());
	}

	/**
	 * Play one round of the game
	 */
	private static void oneRound() {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		while (!ocean.isGameOver()) {
			printMessage(ocean);
			int row;
			int col;

			while (true) {
				System.out.println("Please enter the row to shoot:");
				row = scnr.nextInt();
				if (row < 0 || row >= 10) {
					System.out.println("Please enter an integer from 0 to 9");
				} else {
					break;
				}
			}
			while (true) {
				System.out.println("Please enter the column to shoot:");
				col = scnr.nextInt();
				if (col < 0 || col >= 10) {
					System.out.println("Please enter an integer from 0 to 9");
				} else {
					break;
				}
			}
			ocean.shootAt(row, col);
		}
		ocean.print();
		System.out.println("Game is over, your score is " + ocean.getShotsFired());
	}

	/**
	 * Ask for whether to play next round
	 * @return true if play next round, false if exit program
	 */
	private static boolean nextRound() {
		System.out.println("Do you want to start over? Enter [y] to restart, or the program will exit");
		String restart = scnr.next();
		if (restart.equals("y")) {
			return true;
		} else {
			return false;
		}
	}
}
