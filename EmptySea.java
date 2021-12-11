/**
 * Considered a type of Ship so that the Ocean's ship 2D array can consist of
 * EmptySea references for empty tiles and proper ships for tiles with ships
 * actually inside of them.
 *
 * @author Huifang Ye & Rachel Kong
 */
public class EmptySea extends Ship {
	/**
	 * Sets the inherited length variable and initializes the hit array, based on
	 * the size of this Empty Sea (1 tiles).
	 */
	public EmptySea() {
		length = 1;
	}

	/**
	 *
	 * @return "empty", indicating that this is an Empty sea tile.
	 */
	@Override
	public String getShipType() {
		return "empty";
	}

	/**
	 * Since an EmptySea is empty by definition, shooting at one will always be a
	 * miss.
	 *
	 * @param row the row of the shot
	 * @param col the column of the shot
	 * @return false always, since nothing will be hit.
	 */
	@Override
	public boolean shootAt(int row, int col) {
		return false;
	}

	/**
	 * Since an EmptySea is empty by definition, it is not possible that one can be
	 * sunk.
	 *
	 * @return false always, since nothing will be hit.
	 */
	@Override
	public boolean isSunk() {
		return false;
	}

	/**
	 *
	 * @return "x" if this ship has been sunk, and "S" otherwise.
	 */
	@Override
	public String toString() {

	}
}
