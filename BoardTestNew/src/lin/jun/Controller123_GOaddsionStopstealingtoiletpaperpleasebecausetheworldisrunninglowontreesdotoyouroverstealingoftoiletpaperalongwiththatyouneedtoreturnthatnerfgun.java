package lin.jun;

public class Controller123_GOaddsionStopstealingtoiletpaperpleasebecausetheworldisrunninglowontreesdotoyouroverstealingoftoiletpaperalongwiththatyouneedtoreturnthatnerfgun {
	final static int SIZE = 25;
	final int WIDTH = SIZE * TetrisUI.NUM_COLS;
	final int HEIGHT = SIZE * TetrisUI.NUM_ROWS;
	
	public void moveRight(Shape block) {
		if ((block.r1.getX() + SIZE) < WIDTH && (block.r2.getX() + SIZE) < WIDTH && (block.r3.getX() + SIZE) < WIDTH && (block.r4.getX() + SIZE) < WIDTH) {
			block.r1.setX(block.r1.getX() + 25);
			block.r2.setX(block.r2.getX() + 25);
			block.r3.setX(block.r3.getX() + 25);
			block.r4.setX(block.r4.getX() + 25);
		}
	}
	
	public void moveLeft(Shape block) {
		if ((block.r1.getX()) > 0 && (block.r2.getX()) > 0 && (block.r3.getX()) > 0 && (block.r4.getX()) > 0) {
			block.r1.setX(block.r1.getX() - 25);
			block.r2.setX(block.r2.getX() - 25);
			block.r3.setX(block.r3.getX() - 25);
			block.r4.setX(block.r4.getX() - 25);
		}
	}
	
	public void moveDown(Shape block) {
		if ((block.r1.getY() + SIZE) < HEIGHT && (block.r2.getY() + SIZE) < HEIGHT && (block.r3.getY() + SIZE) < HEIGHT && (block.r4.getY() + SIZE) < HEIGHT) {
			block.r1.setY(block.r1.getY() + 25);
			block.r2.setY(block.r2.getY() + 25);
			block.r3.setY(block.r3.getY() + 25);
			block.r4.setY(block.r4.getY() + 25);
		}
	}
	
	public void SMACK(Shape block) {
	
}
	
}
