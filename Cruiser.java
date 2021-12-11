/**
 * A ship with a length of three tiles.
 *
 * @author Huifang Ye & Rachel Kong
 */
public class Cruiser extends Ship {
	/**
	 * Sets the inherited length variable and initializes the hit array, based on
	 * the size of this ship (3 tiles).
	 */
	public Cruiser() {
		length = 3;
	}

	/**
	 *
	 * @return "Cruiser", indicating that this is a Cruiser.
	 */
	@Override
	public String getShipType() {
		return "Cruiser";
	}
}
