package lin.jun;

import java.util.Random;
import java.util.Scanner;

public class BoardTest {
	
	final static int LENGTH_CELLS = 10;
	final static int HEIGHT_CELLS = 10;
	final int PIXEL_SIZE = 25;
	final int MAX_LENGTH = PIXEL_SIZE * LENGTH_CELLS;
	final int MAX_HEIGHT = PIXEL_SIZE * HEIGHT_CELLS;
	private static int [][] MESH = new int[LENGTH_CELLS][HEIGHT_CELLS]; 
			
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		Board gameBoard = new Board(HEIGHT_CELLS, LENGTH_CELLS);
		boolean done = false;
		
		gameBoard.displayBoard();
		
		while (!done) {
			System.out.println("Select a direction (l/r/d/space)");
			String direction = input.nextLine();
			gameBoard.placePiece(direction);
		}
	}
}
