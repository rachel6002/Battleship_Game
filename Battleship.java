/**
 * A Ship with a length of four tiles.
 *
 * @author Huifang Ye
 */
public class Battleship extends Ship{
    /**
     * Sets the inherited length variable and initializes the
     * hit array, based on the size of this ship (4 tiles).
     */
    public Battleship() {

    }

    /**
     *
     * @return "Battleship", indicating that this is a Battleship.
     */
    @Override
    public String getShipType() {
        return "Battleship";
    }
}
