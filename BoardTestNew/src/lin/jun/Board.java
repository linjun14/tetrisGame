package lin.jun;

public class Board {
	
	final int rows;
	final int cols;
	static int currentRow = 0;
	static int currentCol = 4;
	private int [][] MESH; 
	private int j = 15;
	
	public Board(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		MESH = new int[rows][cols];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				MESH[i][j] = 0;
			}
		}
	}
	
	public void displayBoard() {
		MESH[currentRow][currentCol] = 1;
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
	
	public void placePiece(String direction) {
		
		int prevCol = currentCol;
		int prevRow = currentRow;
		
		if (direction.equals("l")) {
			if(currentCol > 0) {
				currentCol--;
			}
		}
		else if (direction.equals("r")) {
			if(currentCol < cols - 1) {
				currentCol++;
			}
		}
		else if (direction.equals("d")) {
			if (currentRow < rows - 1) {
				currentRow++;
			}
		}
		else if (direction.equals("space")) {
			currentRow = rows - 1;
		}
		
		if (currentRow >= 0 && currentRow < rows && currentCol >= 0 && currentCol < cols) {
			MESH[prevRow][prevCol] = 0;
			MESH[currentRow][currentCol] = 1;
		}
		if(currentRow == rows - 1 || MESH[currentRow + 1][currentCol] == 1) {
			spawnPiece();
			currentRow = 0;
			currentCol = 4;
		}
		
		System.out.println(currentRow);
		
		for(int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.printf("%s ", MESH[i][j]);
			}
			System.out.println();
		}
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

