import java.util.Random;
/**
 * This class manages the game state by keeping track of what entity is
 * contained in each position on the game board.
 * 
 * @author Huifang Ye & Rachel Kong
 *
 */
public class Ocean implements OceanInterface {

	/**
	 * A 10x10 2D array of Ships, which can be used to quickly determine which ship
	 * is in any given location.
	 */
	protected Ship[][] ships;

	/**
	 * The total number of shots fired by the user
	 */
	protected int shotsFired;

	/**
	 * The number of times a shot hit a ship. If the user shoots the same part of a
	 * ship more than once, every hit is counted, even though the additional "hits"
	 * don't do the user any good.
	 */
	protected int hitCount;

	/**
	 * The number of ships totally sunk.
	 *
	 */
	protected int shipsSunk;

	/**
	 * Track the places shot at
	 */
	boolean[][] shotAt;

	/**
	 * Creates an "empty" ocean, filling every space in the <code>ships</code> array
	 * with EmptySea objects. Should also initialize the other instance variables
	 * appropriately.
	 */
	public Ocean() {
		ships = new Ship[10][10];
		shotAt = new boolean[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j ++) {
				ships[i][j] = new EmptySea();
				shotAt[i][j] = false;
			}
		}
		shotsFired = 0;
		hitCount = 0;
		shipsSunk = 0;
	}

	/**
	 * Place all ten ships randomly on the (initially empty) ocean. Larger ships
	 * must be placed before smaller ones to avoid cases where it may be impossible
	 * to place the larger ships.
	 *
	 * @see java.util.Random
	 */
	public void placeAllShipsRandomly() {
		for (int i = 0; i < 1; i++) {
			helperPlaceAllShipsRandomly(new Battleship());
		}
		for (int i = 0; i < 2; i++) {
			helperPlaceAllShipsRandomly(new Cruiser());
		}
		for (int i = 0; i < 3; i++) {
			helperPlaceAllShipsRandomly(new Destroyer());
		}
		for (int i = 0; i < 4; i++) {
			helperPlaceAllShipsRandomly(new Submarine());
		}
	}

	/**
	 * helper function of the placeAllShipsRandomly()
	 * @param ship The ship to be placed
	 */
	private void helperPlaceAllShipsRandomly(Ship ship) {
		Random random = new Random();
		while (true) {
			int row = random.nextInt(getShipArray().length);
			int col = random.nextInt(getShipArray()[0].length);
			boolean horizontal = random.nextBoolean();
			if (ship.okToPlaceShipAt(row, col, horizontal, this)) {
				ship.placeShipAt(row, col, horizontal, this);
				break;
			}
		}
	}

	/**
	 * Checks if this coordinate is not empty; that is, if this coordinate does not
	 * contain an EmptySea reference.
	 *
	 * @param row    the row (0 to 9) in which to check for a floating ship
	 * @param column the column (0 to 9) in which to check for a floating ship
	 * @return {@literal true} if the given location contains a ship, and
	 *         {@literal false} otherwise.
	 */
	public boolean isOccupied(int row, int column) {
		if (row < ships.length && row >= 0 && column < ships.length && column >= 0) {
			if (!ships[row][column].getShipType().equals("empty")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Fires a shot at this coordinate. This will update the number of shots that
	 * have been fired (and potentially the number of hits, as well). If a location
	 * contains a real, not sunk ship, this method should return {@literal true}
	 * every time the user shoots at that location. If the ship has been sunk,
	 * additional shots at this location should return {@literal false}.
	 *
	 * @param row    the row (0 to 9) in which to shoot
	 * @param column the column (0 to 9) in which to shoot
	 * @return {@literal true} if the given location contains an afloat ship (not an
	 *         EmptySea), {@literal false} if it does not.
	 */
	public boolean shootAt(int row, int column) {
		shotsFired++;
		shotAt[row][column] = true;
		if (isOccupied(row, column) && !getShipArray()[row][column].isSunk()) {
			hitCount++;
			Ship ship = getShipArray()[row][column];
			ship.shootAt(row, column);
			if (ship.isSunk()) {
				shipsSunk++;
				System.out.println("You just sunk a " + ship.getShipType());
			}
			return true;
		}
		return false;
	}

	/**
	 * @return the number of shots fired in this game.
	 */
	public int getShotsFired() {
		return this.shotsFired;
	}

	/**
	 * @return the number of hits recorded in this game.
	 */
	public int getHitCount() {
		return this.hitCount;
	}

	/**
	 * @return the number of ships sunk in this game.
	 */
	public int getShipsSunk() {
		return this.shipsSunk;
	}

	/**
	 * @return {@literal true} if all ships have been sunk, otherwise
	 *         {@literal false}.
	 */
	public boolean isGameOver() {
		return getShipsSunk() == 10;
	}

	/**
	 * Provides access to the grid of ships in this Ocean. The methods in the Ship
	 * class that take an Ocean parameter must be able to read and even modify the
	 * contents of this array. While it is generally undesirable to allow methods in
	 * one class to directly access instancce variables in another class, in this
	 * case there is no clear and elegant alternatives.
	 *
	 * @return the 10x10 array of ships.
	 */
	public Ship[][] getShipArray() {
		return ships;
	}

	/**
	 * Prints the ocean. To aid the user, row numbers should be displayed along the
	 * left edge of the array, and column numbers should be displayed along the top.
	 * Numbers should be 0 to 9, not 1 to 10. The top left corner square should be
	 * 0, 0.
	 * <ul>
	 * <li>Use 'S' to indicate a location that you have fired upon and hit a (real)
	 * ship</li>
	 * <li>'-' to indicate a location that you have fired upon and found nothing
	 * there</li>
	 * <li>'x' to indicate a location containing a sunken ship</li>
	 * <li>'.' (a period) to indicate a location that you have never fired
	 * upon.</li>
	 * </ul>
	 *
	 * This is the only method in Ocean that has any printing capability, and it
	 * should never be called from within the Ocean class except for the purposes of
	 * debugging.
	 *
	 */
	public void print() {
		String[] strs = new String[getShipArray().length + 1];
		StringBuilder sb = new StringBuilder();
		sb.append("  ");
		for (int i = 0; i < getShipArray()[0].length; i++) {
			sb.append(String.valueOf(i));
			sb.append(" ");
		}
		strs[0] = sb.toString();
		for (int i = 0; i < getShipArray().length; i++) {
			sb = new StringBuilder();
			sb.append(String.valueOf(i));
			sb.append(" ");
			for (int j = 0; j < getShipArray()[0].length; j++) {
				if (!shotAt[i][j]) {
					sb.append(".");
				} else {
					Ship ship = getShipArray()[i][j];
					if (ship.getShipType().equals("empty")) {
						sb.append("-");
					} else {
						sb.append(ship.toString());
					}
				}
				sb.append(" ");
			}
			strs[i + 1] = sb.toString();
		}

		for (int i = 0; i < strs.length; i++) {
			System.out.println(strs[i]);
		}
	}
}

