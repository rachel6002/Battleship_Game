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
