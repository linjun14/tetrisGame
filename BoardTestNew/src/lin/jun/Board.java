package lin.jun;

/**
 * The text version of the game board
 * @author Jun Lin
 * @author Kenneth Ou
 */
public class Board {
	
	private final int rows;
	private final int cols;
	public static int [][] MESH;
	private static int SIZE = TetrisUI.SIZE;
	
	/**
	 * Constructor for the Board class
	 * @param rows number of rows
	 * @param cols number of columns
	 */
	public Board(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		MESH = new int[rows][cols];
	}
	
	/**
	 * Displays the text version of the game board
	 */
	public void displayBoard() {
		for(int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.printf("%s ", MESH[i][j]);
			}
			System.out.println();
		}
	}
	
	/**
	 * Clears the whole game board
	 */
	public void clearBoard() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				MESH[i][j] = 0;
			}
		}
	}

	/**
	 * Returns the text version of the game board
	 * @return returns the text version of the game board
	 */
	public int[][] getMESH(){
		return MESH;
	}
	
	/**
	 * Fills a cell in MESH (The dropped piece will occupy a cell based on its position)
	 * @param r row index number
	 * @param c column index number
	 */
	public void fillCell(int r, int c) {
		MESH[r][c] = 1;
	}

	/**
	 * Checks if the player tops out (checks if any cell in the topmost row is occupied), in this case the player would losw
	 * @return returns true if the player loses, otherwise false
	 */
	public boolean isTopOut() {
		for (int i : MESH[0]) {
			if (i == 1) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks the cells to the right of the current block and whether or not they are occupied
	 * @param block the current block
	 * @return returns true if the block can move right, otherwise false
	 */
	public boolean checkRight(Shape block) {
		// Special case: If the player wants to rotate a block as it spawns, only check if the right of block.r2 and block.r3 are occupied
		if (block.r2.getY() == 0) {
			return (MESH[(int)block.r2.getY()/SIZE][(int)block.r2.getX()/SIZE + 1] == 1
					|| MESH[(int)block.r3.getY()/SIZE][(int)block.r3.getX()/SIZE + 1] == 1);
		}
		// Otherwise check all cells to the right of the block
		else {
			return (MESH[(int)block.r1.getY()/SIZE][(int)block.r1.getX()/SIZE + 1] == 1
					|| MESH[(int)block.r2.getY()/SIZE][(int)block.r2.getX()/SIZE + 1] == 1
					|| MESH[(int)block.r3.getY()/SIZE][(int)block.r3.getX()/SIZE + 1] == 1
					|| MESH[(int)block.r4.getY()/SIZE][(int)block.r4.getX()/SIZE + 1] == 1);
		}
	}
	
	/**
	 * Checks the cells to the left of the current block and whether or not they are occupied
	 * @param block the current block
	 * @return returns true if the block can move left, otherwise false
	 */
	public boolean checkLeft(Shape block) {
		// Special case: If the player wants to rotate a block as it spawns, only check if the left of block.r2 and block.r3 are occupied
		if (block.r2.getY() == 0) {
			return (MESH[(int)block.r2.getY()/SIZE][(int)block.r2.getX()/SIZE - 1] == 1
					|| MESH[(int)block.r3.getY()/SIZE][(int)block.r3.getX()/SIZE - 1] == 1);
		}
		// Otherwise check all cells to the right of the block
		else {
			return (MESH[(int)block.r1.getY()/SIZE][(int)block.r1.getX()/SIZE - 1] == 1
					|| MESH[(int)block.r2.getY()/SIZE][(int)block.r2.getX()/SIZE - 1] == 1
					|| MESH[(int)block.r3.getY()/SIZE][(int)block.r3.getX()/SIZE - 1] == 1
					|| MESH[(int)block.r4.getY()/SIZE][(int)block.r4.getX()/SIZE - 1] == 1);
		}
	}
	
	/**
	 * Checks the cells to the bottom of the current block and whether or not they are occupied
	 * @param block the current block
	 * @return returns true if the block can move down, otherwise false
	 */
	public boolean checkDown(Shape block) {
		return (MESH[(int)block.r1.getY()/SIZE + 1][(int)block.r1.getX()/SIZE] == 1
				|| MESH[(int)block.r2.getY()/SIZE + 1][(int)block.r2.getX()/SIZE] == 1
				|| MESH[(int)block.r3.getY()/SIZE + 1][(int)block.r3.getX()/SIZE] == 1
				|| MESH[(int)block.r4.getY()/SIZE + 1][(int)block.r4.getX()/SIZE] == 1);
	}
	
	/**
	 * Checks the rotation point of the current block (block.r2) and if the block is allowed to rotate as it is near an block that occupied the board
	 * @param block the current block
	 * @return returns true if the block can rotate, otherwise false
	 */
	public boolean checkRotationPoint(Shape block) {
		// Special case: If the rotation point is at the first row, check the cells to the left, right, and bottom of the rotation point
		if (block.r2.getY() == 0) {
			return (MESH[(int)block.r2.getY()/SIZE][(int)block.r2.getX()/SIZE - 1] == 1
					|| MESH[(int)block.r2.getY()/SIZE][(int)block.r2.getX()/SIZE + 1] == 1
					|| MESH[(int)block.r2.getY()/SIZE + 1][(int)block.r2.getX()/SIZE] == 1);
		}
		else {
			/*
			 * Special case: If the player wants to rotate a T-block, check all adjacent cells from the rotation point, excluding the cells that are 1 diagonal from it
			 * This ensures that the player can do a T-spin
			 */
			if (block.getShapeType().equals("T")) {
				return (MESH[(int)block.r2.getY()/SIZE][(int)block.r2.getX()/SIZE - 1] == 1
						|| MESH[(int)block.r2.getY()/SIZE][(int)block.r2.getX()/SIZE + 1] == 1
						|| MESH[(int)block.r2.getY()/SIZE - 1][(int)block.r2.getX()/SIZE] == 1
						|| MESH[(int)block.r2.getY()/SIZE + 1][(int)block.r2.getX()/SIZE] == 1);
			}
			/*
			 * Special case: If the player wants to rotate an I-block, check all adjacent cells from the rotation point, excluding the cells that are 1 diagonal from it
			 * In addition, check the cells that are 2 cells left and right from the rotation point
			 */
			else if (block.getShapeType().equals("I")) {
				return (MESH[(int)block.r2.getY()/SIZE][(int)block.r2.getX()/SIZE - 1] == 1
						|| MESH[(int)block.r2.getY()/SIZE][(int)block.r2.getX()/SIZE + 1] == 1
						|| MESH[(int)block.r2.getY()/SIZE - 1][(int)block.r2.getX()/SIZE] == 1
						|| MESH[(int)block.r2.getY()/SIZE + 1][(int)block.r2.getX()/SIZE] == 1
						|| MESH[(int)block.r2.getY()/SIZE][(int)block.r2.getX()/SIZE - 2] == 1
						|| MESH[(int)block.r2.getY()/SIZE + 2][(int)block.r2.getX()/SIZE] == 1);
			}
			// For all other cases, check all adjacent cells to the rotation point , including all cells that are 1 diagonal from it
			else {
				return (MESH[(int)block.r2.getY()/SIZE][(int)block.r2.getX()/SIZE - 1] == 1
						|| MESH[(int)block.r2.getY()/SIZE][(int)block.r2.getX()/SIZE + 1] == 1
						|| MESH[(int)block.r2.getY()/SIZE - 1][(int)block.r2.getX()/SIZE] == 1
						|| MESH[(int)block.r2.getY()/SIZE + 1][(int)block.r2.getX()/SIZE] == 1
						|| MESH[(int)block.r2.getY()/SIZE + 1][(int)block.r2.getX()/SIZE + 1] == 1
						|| MESH[(int)block.r2.getY()/SIZE + 1][(int)block.r2.getX()/SIZE - 1] == 1
						|| MESH[(int)block.r2.getY()/SIZE - 1][(int)block.r2.getX()/SIZE + 1] == 1
						|| MESH[(int)block.r2.getY()/SIZE - 1][(int)block.r2.getX()/SIZE - 1] == 1);
			}
		}
	}
}

