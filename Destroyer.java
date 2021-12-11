/**
 * A ship with a length of two tiles.
 *
 * @author Huifang Ye & Rachel Kong
 */
public class Destroyer extends Ship {
	/**
	 * Sets the inherited length variable and initializes the hit array, based on
	 * the size of this ship (2 tiles).
	 */
	public Destroyer() {
		length = 2;
	}

	/**
	 *
	 * @return "Destroyer", indicating that this is a Destroyer.
	 */
	@Override
	public String getShipType() {
		return "Destroyer";
	}
}
