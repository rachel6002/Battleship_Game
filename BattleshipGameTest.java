/**
 * Test class for BattleshipGame
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BattleshipGameTest {
	/**
	 * test the getLength() in Battleship.java
	 */
	@Test
	void testBattleshipLength() {
		Battleship bts = new Battleship();
		assertEquals(4, bts.getLength());
	}

	/**
	 * test the getShipType() in Battleship.java
	 */
	@Test
	void testBattleshipType() {
		Battleship bts = new Battleship();
		assertEquals("Battleship", bts.getShipType());
	}

	/**
	 * test the getLength() in Cruiser.java
	 */
	@Test
	void testCruiserLength() {
		Cruiser crs = new Cruiser();
		assertEquals(3, crs.getLength());
	}

	/**
	 * test the getShipType() in Cruiser.java
	 */
	@Test
	void testCruiserType() {
		Cruiser crs = new Cruiser();
		assertEquals("Cruiser", crs.getShipType());
	}

	/**
	 * test the getLength() in Destroyer.java
	 */
	@Test
	void testDestroyerLength() {
		Destroyer des = new Destroyer();
		assertEquals(2, des.getLength());
	}

	/**
	 * test the getShipType() in Destroyer.java
	 */
	@Test
	void testDestroyerType() {
		Destroyer des = new Destroyer();
		assertEquals("Destroyer", des.getShipType());
	}

	/**
	 * test the getLength() in Submarine.java
	 */
	@Test
	void testSubmarineLength() {
		Submarine sub = new Submarine();
		assertEquals(1, sub.getLength());
	}

	/**
	 * test the getShipType() in Submarine.java
	 */
	@Test
	void testSubmarineType() {
		Submarine sub = new Submarine();
		assertEquals("Submarine", sub.getShipType());
	}

	/**
	 * test the getLength() in EmptySea.java
	 */
	@Test
	void testEmptySeaLength() {
		EmptySea emp = new EmptySea();
		assertEquals(1, emp.getLength());
	}

	/**
	 * test the getShipType() in EmptySea.java
	 */
	@Test
	void testEmptySeaType() {
		EmptySea emp = new EmptySea();
		assertEquals("empty", emp.getShipType());
	}

	/**
	 * test the shootAt() in EmptySea.java
	 */
	@Test
	void testEmptyShootAt() {
		EmptySea emp = new EmptySea();
		assertFalse(emp.shootAt(0, 0));
	}

	/**
	 * test the isSunk() in EmptySea.java
	 */
	@Test
	void testEmptyIsSunk() {
		EmptySea emp = new EmptySea();
		assertFalse(emp.isSunk());
	}

	/**
	 * test the toString() in EmptySea.java
	 */
	@Test
	void testEmptyToString() {
		EmptySea emp = new EmptySea();
		assertEquals("S", emp.toString());
	}

	/**
	 * Test the getBowColumn(), setBowColumn()
	 * getBowRow(), setBowRow(), isHorizontal, setHorizontal()
	 * methods in Ship.java
	 */
	@Test
	void testShipGetterAndSetter() {
		EmptySea emp = new EmptySea();
		emp.setBowColumn(9);
		emp.setBowRow(8);
		emp.setHorizontal(true);
		assertEquals(9, emp.getBowColumn());
		assertEquals(8, emp.getBowRow());
		assertTrue(emp.horizontal);
	}

	/**
	 * test placeShipAt() in Ship.java
	 */
	@Test
	void testPlaceShipAt() {
		Ocean ocean = new Ocean();
		Destroyer des = new Destroyer();
		des.placeShipAt(0, 0, false, ocean);
		assertEquals("Destroyer", ocean.getShipArray()[0][0].getShipType());
		assertEquals("Destroyer", ocean.getShipArray()[1][0].getShipType());
		assertEquals("empty", ocean.getShipArray()[0][1].getShipType());
	}

	/**
	 * test oKToPlaceShipAt() in Ship.java
	 */
	@Test
	void testOkToPlaceShipAt() {
		Ocean ocean = new Ocean();
		Battleship bat1 = new Battleship();
		bat1.placeShipAt(0, 0, true, ocean);
		Submarine sub1 = new Submarine();
		// test out of range
		assertFalse(sub1.okToPlaceShipAt(-1, 0, true, ocean));
		// test occupied by another ship
		assertFalse(sub1.okToPlaceShipAt(0, 2, true, ocean));
		// test adjacent horizontally
		assertFalse(sub1.okToPlaceShipAt(0, 4, true, ocean));
		// test adjacent vertically
		assertFalse(sub1.okToPlaceShipAt(1, 0, true, ocean));
		// test adjacent diagonally
		assertFalse(sub1.okToPlaceShipAt(1, 4, true, ocean));
		// test other places
		assertTrue(sub1.okToPlaceShipAt(1, 5, true, ocean));
	}

	/**
	 * test shootAt() in Ship.java
	 */
	@Test
	void testShipShootAt() {
		Ocean ocean = new Ocean();
		Destroyer des = new Destroyer();
		des.placeShipAt(0, 0, true, ocean);
		assertTrue(des.shootAt(0, 1));
		assertFalse(des.shootAt(1, 0));
		assertFalse(des.hit[0]);
		assertTrue(des.hit[1]);
	}

	/**
	 * test isSunk() and toString() in Ship.java
	 */
	@Test
	void testIsSunkAndToString() {
		Ocean ocean = new Ocean();
		Destroyer des = new Destroyer();
		des.placeShipAt(0, 0, true, ocean);
		des.shootAt(0, 0);
		assertFalse(des.isSunk());
		assertEquals("S", des.toString());
		des.shootAt(0, 1);
		assertTrue(des.isSunk());
		assertEquals("x", des.toString());
	}

	@Test
	void testIsOccupied() {
		Ocean ocean = new Ocean();
		Destroyer des = new Destroyer();
		des.placeShipAt(0, 0, true, ocean);
		assertTrue(ocean.isOccupied(0, 1));
		assertFalse(ocean.isOccupied(1, 0));
		assertFalse(ocean.isOccupied(-1 ,0));
	}

	/**
	 * test placeAllShipsRandomly() in Ocean.java
	 */
	@Test
	void testPlaceAllShipsRandomly() {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		int[][] dirs = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (ocean.isOccupied(i, j)) {
					// check that if its neighbor has other ships
					for (int idx = 0; idx < dirs.length; idx++) {
						if (i + dirs[idx][0] < 0 || i + dirs[idx][0] >= 10 || j + dirs[idx][1] < 0 || j + dirs[idx][1] >= 10) {
							continue;
						}
						assertFalse(ocean.isOccupied(i + dirs[idx][0], j + dirs[idx][1]) &&
								(!ocean.getShipArray()[i][j].equals(ocean.getShipArray()[i + dirs[idx][0]][j + dirs[idx][1]])));
					}
				}
			}
		}
	}

	/**
	 * test the shootAt() and getShotsFired(), getHitCount(), getShipsSunk()
	 * in Ocean.java
	 */
	@Test
	void testOceanShootAtAndGetters() {
		Ocean ocean = new Ocean();
		Destroyer des = new Destroyer();
		des.placeShipAt(0, 0, true, ocean);
		assertEquals(0, ocean.getShotsFired());
		assertEquals(0, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		assertTrue(ocean.shootAt(0, 0));
		assertEquals(1, ocean.getShotsFired());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		assertTrue(ocean.shootAt(0, 0));
		// as long as the ship is not sunk, hit at the place hit before will increase the hitCount
		assertEquals(2, ocean.getShotsFired());
		assertEquals(2, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		assertFalse(ocean.shootAt(1, 0)); // shoot misses
		assertEquals(3, ocean.getShotsFired());
		assertEquals(2, ocean.getHitCount());
		assertTrue(ocean.shootAt(0, 1));
		assertEquals(1, ocean.getShipsSunk());
	}

	/**
	 * test the getShipArray() in Ocean.java
	 */
	@Test
	void testGetShipArray() {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		assertEquals(ocean.ships, ocean.getShipArray());
	}

	@Test
	void testIsGameOver() {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				ocean.shootAt(i, j);
			}
		}
		assertTrue(ocean.isGameOver());
	}
}
