package lin.jun;

public class Board {
	
	final int rows;
	final int cols;
	static int currentRow = 0;
	static int currentCol = 4;
	static int [][] MESH;
	static int SIZE = TetrisUI.SIZE;
	
	public Board(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		MESH = new int[rows][cols];
	}
	
	public void displayBoard() {
		for(int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.printf("%s ", MESH[i][j]);
			}
			System.out.println();
		}
	}
	
	public void clearBoard() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				MESH[i][j] = 0;
			}
		}
	}
	public int[][] getMESH(){
		return MESH;
	}
	public void fillCell(int r, int c) {
		MESH[r][c] = 1;
	}
		
	public void clearLine() {
		int count = 0;
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (MESH[r][c] != 0) {
					count++;
				}
				if (count >= cols) {
					// Clear Line TODO
					count = 0;
				}
			}
			count = 1;
		}
	}
	
	public boolean isTopOut(Shape block) {
		for (int i : MESH[0]) {
			if (i == 1) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkRight(Shape block) {
		if (block.r2.getY() == 0) {
			return (MESH[(int)block.r2.getY()/SIZE][(int)block.r2.getX()/SIZE + 1] == 1
					|| MESH[(int)block.r3.getY()/SIZE][(int)block.r3.getX()/SIZE + 1] == 1);
		}
		else {
			return (MESH[(int)block.r1.getY()/SIZE][(int)block.r1.getX()/SIZE + 1] == 1
					|| MESH[(int)block.r2.getY()/SIZE][(int)block.r2.getX()/SIZE + 1] == 1
					|| MESH[(int)block.r3.getY()/SIZE][(int)block.r3.getX()/SIZE + 1] == 1
					|| MESH[(int)block.r4.getY()/SIZE][(int)block.r4.getX()/SIZE + 1] == 1);
		}
	}
	
	public boolean checkLeft(Shape block) {
		if (block.r2.getY() == 0) {
			return (MESH[(int)block.r2.getY()/SIZE][(int)block.r2.getX()/SIZE - 1] == 1
					|| MESH[(int)block.r3.getY()/SIZE][(int)block.r3.getX()/SIZE - 1] == 1);
		}
		else {
			return (MESH[(int)block.r1.getY()/SIZE][(int)block.r1.getX()/SIZE - 1] == 1
					|| MESH[(int)block.r2.getY()/SIZE][(int)block.r2.getX()/SIZE - 1] == 1
					|| MESH[(int)block.r3.getY()/SIZE][(int)block.r3.getX()/SIZE - 1] == 1
					|| MESH[(int)block.r4.getY()/SIZE][(int)block.r4.getX()/SIZE - 1] == 1);
		}
	}
	
	public boolean checkDown(Shape block) {
		return (MESH[(int)block.r1.getY()/SIZE + 1][(int)block.r1.getX()/SIZE] == 1
				|| MESH[(int)block.r2.getY()/SIZE + 1][(int)block.r2.getX()/SIZE] == 1
				|| MESH[(int)block.r3.getY()/SIZE + 1][(int)block.r3.getX()/SIZE] == 1
				|| MESH[(int)block.r4.getY()/SIZE + 1][(int)block.r4.getX()/SIZE] == 1);
	}
	
	public boolean checkRotationPoint(Shape block) {
		if (block.r2.getY() == 0) {
			return (MESH[(int)block.r2.getY()/SIZE][(int)block.r2.getX()/SIZE - 1] == 1
					|| MESH[(int)block.r2.getY()/SIZE][(int)block.r2.getX()/SIZE + 1] == 1
					|| MESH[(int)block.r2.getY()/SIZE + 1][(int)block.r2.getX()/SIZE] == 1);
		}
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

	
	public void spawnPiece() {
		MESH[0][4] = 1;
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
}

