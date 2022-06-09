package lin.jun;

public class Board {
	
	final int rows;
	final int cols;
	static int startRow = 0;
	static int startCol = 4;
	private int [][] MESH; 
	private int j = 12;
	
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
		MESH[0][4] = 1;
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
		
		boolean placed = false;
		
		while (!placed) {
			if(direction.equals("r")) {
				startCol++;
			}
			else if(direction.equals("l")) {
				startCol--;
			}
			else if(direction.equals("d")) {
				startRow++;
			}
			else if(direction.equals("space")) {
				startRow = rows;
			}
			
			if(startRow > 0) {
				// If the selected cell is occupied, move up 1 row, otherwise place the piece
				if(MESH[startRow - 1][startCol - 1] != 0) {
					startRow--;
				}
				else {
					MESH[startRow - 1][startCol - 1] = 1;
					placed = true; // At this point the piece has been placed
				}
			}
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
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
}

