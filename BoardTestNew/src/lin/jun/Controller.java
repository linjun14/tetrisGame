package lin.jun;

import javafx.scene.shape.Rectangle;

/**
 * This class defines all the movements that a block can perform
 * @author Jun Lin
 * @author Kenneth Ou
 *
 */
public class Controller {
	final static int SIZE = TetrisUI.SIZE;
	final static int WIDTH = TetrisUI.WIDTH;
	final int HEIGHT = TetrisUI.HEIGHT;
	int rotation = 1;
	
	/**
	 * Moves a block right by 1 square
	 * @param block the current block
	 */
	public void moveRight(Shape block) {
		block.r1.setX(block.r1.getX() + SIZE);
		block.r2.setX(block.r2.getX() + SIZE);
		block.r3.setX(block.r3.getX() + SIZE);
		block.r4.setX(block.r4.getX() + SIZE);
	}
	
	/**
	 * Moves a block left by 1 square
	 * @param block the current block
	 */
	public void moveLeft(Shape block) {
		block.r1.setX(block.r1.getX() - SIZE);
		block.r2.setX(block.r2.getX() - SIZE);
		block.r3.setX(block.r3.getX() - SIZE);
		block.r4.setX(block.r4.getX() - SIZE);
	}
	
	/**
	 * Moves a block down by 1 square
	 * @param block the current block
	 */
	public void moveDown(Shape block) {
		block.r1.setY(block.r1.getY() + SIZE);
		block.r2.setY(block.r2.getY() + SIZE);
		block.r3.setY(block.r3.getY() + SIZE);
		block.r4.setY(block.r4.getY() + SIZE);
	}
	
	/**
	 * Rotates a block right (clockwise)
	 * @param block the current block
	 */
	public void rotateRight(Shape block) {
		
		if (rotation != 4) {
			rotation++;
		}
		else {
			rotation = 1;
		}
		
		if (block.getShapeType().equals("T")) {
			if (rotation == 1) {
				moveUpLeft(block.r1, 1, 1);
				moveUpRight(block.r4, 1, 1);
				moveDownRight(block.r3, 1, 1);
			}
			else if (rotation == 2) {
				moveUpRight(block.r1, 1, 1);
				moveDownRight(block.r4, 1, 1);
				moveDownLeft(block.r3, 1, 1);
			}
			else if (rotation == 3) {
				moveDownRight(block.r1, 1, 1);
				moveDownLeft(block.r4, 1, 1);
				moveUpLeft(block.r3, 1, 1);
			}
			else if (rotation == 4) {
				moveDownLeft(block.r1, 1, 1);
				moveUpLeft(block.r4, 1, 1);
				moveUpRight(block.r3, 1, 1);
			}
		}
		else if (block.getShapeType().equals("L")) {
			if (rotation == 1) {
				moveUpLeft(block.r1, 1, 1);
				moveUpRight(block.r4, 2, 0);
				moveDownRight(block.r3, 1, 1);
			}
			else if (rotation == 2) {
				moveUpRight(block.r1, 1, 1);
				moveDownRight(block.r4, 0, 2);
				moveDownLeft(block.r3, 1, 1);
			}
			else if (rotation == 3) {
				moveDownRight(block.r1, 1, 1);
				moveDownLeft(block.r4, 2, 0);
				moveUpLeft(block.r3, 1, 1);
			}
			else if (rotation == 4) {
				moveDownLeft(block.r1, 1, 1);
				moveUpLeft(block.r4, 0, 2);
				moveUpRight(block.r3, 1, 1);
			}
		}
		else if (block.getShapeType().equals("J")) {
			if (rotation == 1) {
				moveUpLeft(block.r1, 1, 1);
				moveUpRight(block.r4, 0, 2);
				moveDownRight(block.r3, 1, 1);
			}
			else if (rotation == 2) {
				moveUpRight(block.r1, 1, 1);
				moveDownRight(block.r4, 2, 0);
				moveDownLeft(block.r3, 1, 1);
			}
			else if (rotation == 3) {
				moveDownRight(block.r1, 1, 1);
				moveDownLeft(block.r4, 0, 2);
				moveUpLeft(block.r3, 1, 1);
			}
			else if (rotation == 4) {
				moveDownLeft(block.r1, 1, 1);
				moveUpLeft(block.r4, 2, 0);
				moveUpRight(block.r3, 1, 1);
			}
		}
		else if (block.getShapeType().equals("Z")) {
			if (rotation == 1) {
				moveUpLeft(block.r1, 1, 1);
				moveDownLeft(block.r3, 1, 1);
				moveDownRight(block.r4, 0, 2);
			}
			else if (rotation == 2) {
				moveDownRight(block.r1, 1, 1);
				moveUpRight(block.r3, 1, 1);
				moveUpLeft(block.r4, 0, 2);
			}
			else if (rotation == 3) {
				moveUpLeft(block.r1, 1, 1);
				moveDownLeft(block.r3, 1, 1);
				moveDownRight(block.r4, 0, 2);
			}
			else if (rotation == 4) {
				moveDownRight(block.r1, 1, 1);
				moveUpRight(block.r3, 1, 1);
				moveUpLeft(block.r4, 0, 2);
			}
		}
		else if (block.getShapeType().equals("S")) {
			if (rotation == 1) {
				moveDownRight(block.r1, 1, 1);
				moveDownLeft(block.r3, 1, 1);
				moveDownLeft(block.r4, 2, 0);
			}
			else if (rotation == 2) {
				moveUpLeft(block.r1, 1, 1);
				moveUpRight(block.r3, 1, 1);
				moveUpRight(block.r4, 2, 0);
			}
			else if (rotation == 3) {
				moveDownRight(block.r1, 1, 1);
				moveDownLeft(block.r3, 1, 1);
				moveDownLeft(block.r4, 2, 0);
			}
			else if (rotation == 4) {
				moveUpLeft(block.r1, 1, 1);
				moveUpRight(block.r3, 1, 1);
				moveUpRight(block.r4, 2, 0);
			}
		}
		else if (block.getShapeType().equals("I")) {
			if (rotation == 1) {
				moveDownRight(block.r1, 1, 1);
				moveUpLeft(block.r3, 1, 1);
				moveUpLeft(block.r4, 2, 2);
			}
			else if (rotation == 2) {
				moveUpLeft(block.r1, 1, 1);
				moveDownRight(block.r3, 1, 1);
				moveDownRight(block.r4, 2, 2);
			}
			else if (rotation == 3) {
				moveDownRight(block.r1, 1, 1);
				moveUpLeft(block.r3, 1, 1);
				moveUpLeft(block.r4, 2, 2);
			}
			else if (rotation == 4) {
				moveUpLeft(block.r1, 1, 1);
				moveDownRight(block.r3, 1, 1);
				moveDownRight(block.r4, 2, 2);
			}
		}
	}
	
	/**
	 * Rotates a block left (counterclockwise)
	 * @param block the current block
	 */
	public void rotateLeft(Shape block) {
		
		if (rotation != 1) {
			rotation--;
		}
		else {
			rotation = 4;
		}
		
		if (block.getShapeType().equals("T")) {
			if (rotation == 1) {
				moveDownLeft(block.r1, 1, 1);
				moveUpLeft(block.r4, 1, 1);
				moveUpRight(block.r3, 1, 1);
			}
			else if (rotation == 2) {
				moveUpLeft(block.r1, 1, 1);
				moveUpRight(block.r4, 1, 1);
				moveDownRight(block.r3, 1, 1);
			}
			else if (rotation == 3) {
				moveUpRight(block.r1, 1, 1);
				moveDownRight(block.r4, 1, 1);
				moveDownLeft(block.r3, 1, 1);
			}
			else if (rotation == 4) {
				moveDownRight(block.r1, 1, 1);
				moveDownLeft(block.r4, 1, 1);
				moveUpLeft(block.r3, 1, 1);
			}
		}
		else if (block.getShapeType().equals("L")) {
			if (rotation == 1) {
				moveDownLeft(block.r1, 1, 1);
				moveUpLeft(block.r4, 0, 2);
				moveUpRight(block.r3, 1, 1);
			}
			else if (rotation == 2) {
				moveUpLeft(block.r1, 1, 1);
				moveUpRight(block.r4, 2, 0);
				moveDownRight(block.r3, 1, 1);
			}
			else if (rotation == 3) {
				moveUpRight(block.r1, 1, 1);
				moveDownRight(block.r4, 0, 2);
				moveDownLeft(block.r3, 1, 1);
			}
			else if (rotation == 4) {
				moveDownRight(block.r1, 1, 1);
				moveDownLeft(block.r4, 2, 0);
				moveUpLeft(block.r3, 1, 1);
			}
		}
		else if (block.getShapeType().equals("J")) {
			if (rotation == 1) {
				moveDownLeft(block.r1, 1, 1);
				moveUpLeft(block.r4, 2, 0);
				moveUpRight(block.r3, 1, 1);
			}
			else if (rotation == 2) {
				moveUpLeft(block.r1, 1, 1);
				moveUpRight(block.r4, 0, 2);
				moveDownRight(block.r3, 1, 1);
			}
			else if (rotation == 3) {
				moveUpRight(block.r1, 1, 1);
				moveDownRight(block.r4, 2, 0);
				moveDownLeft(block.r3, 1, 1);
			}
			else if (rotation == 4) {
				moveDownRight(block.r1, 1, 1);
				moveDownLeft(block.r4, 0, 2);
				moveUpLeft(block.r3, 1, 1);
			}
		}
		else if (block.getShapeType().equals("Z")) {
			if (rotation == 1) {
				moveUpLeft(block.r1, 1, 1);
				moveDownLeft(block.r3, 1, 1);
				moveDownRight(block.r4, 0, 2);
			}
			else if (rotation == 2) {
				moveDownRight(block.r1, 1, 1);
				moveUpRight(block.r3, 1, 1);
				moveUpLeft(block.r4, 0, 2);
			}
			else if (rotation == 3) {
				moveUpLeft(block.r1, 1, 1);
				moveDownLeft(block.r3, 1, 1);
				moveDownRight(block.r4, 0, 2);
			}
			else if (rotation == 4) {
				moveDownRight(block.r1, 1, 1);
				moveUpRight(block.r3, 1, 1);
				moveUpLeft(block.r4, 0, 2);
			}
		}
		else if (block.getShapeType().equals("S")) {
			if (rotation == 1) {
				moveUpRight(block.r1, 1, 1);
				moveDownRight(block.r3, 1, 1);
				moveDownRight(block.r4, 0, 2);
			}
			else if (rotation == 2) {
				moveDownLeft(block.r1, 1, 1);
				moveUpLeft(block.r3, 1, 1);
				moveUpLeft(block.r4, 0, 2);
			}
			else if (rotation == 3) {
				moveUpRight(block.r1, 1, 1);
				moveDownRight(block.r3, 1, 1);
				moveDownRight(block.r4, 0, 2);
			}
			else if (rotation == 4) {
				moveDownLeft(block.r1, 1, 1);
				moveUpLeft(block.r3, 1, 1);
				moveUpLeft(block.r4, 0, 2);
			}
		}
		else if (block.getShapeType().equals("I")) {
			if (rotation == 1) {
				moveDownRight(block.r1, 1, 1);
				moveUpLeft(block.r3, 1, 1);
				moveUpLeft(block.r4, 2, 2);
			}
			else if (rotation == 2) {
				moveUpLeft(block.r1, 1, 1);
				moveDownRight(block.r3, 1, 1);
				moveDownRight(block.r4, 2, 2);
			}
			else if (rotation == 3) {
				moveDownRight(block.r1, 1, 1);
				moveUpLeft(block.r3, 1, 1);
				moveUpLeft(block.r4, 2, 2);
			}
			else if (rotation == 4) {
				moveUpLeft(block.r1, 1, 1);
				moveDownRight(block.r3, 1, 1);
				moveDownRight(block.r4, 2, 2);
			}
		}
	}
	
	/**
	 * Moves a selected rectangle in the shape up and left by a number of squares
	 * @param r the selected rectangle in the current block
	 * @param h the number of times to move the block left
	 * @param v the number of times to move the block up
	 */
	public void moveUpLeft (Rectangle r, int h, int v) {
		r.setY(r.getY() - SIZE * v);
		r.setX(r.getX() - SIZE * h);
	}
	
	/**
	 * Moves a selected rectangle in the shape up and right by a number of squares
	 * @param r the selected rectangle in the current block
	 * @param h the number of times to move the block right
	 * @param v the number of times to move the block up
	 */
	public void moveUpRight (Rectangle r, int h, int v) {
		r.setY(r.getY() - SIZE * v);
		r.setX(r.getX() + SIZE * h);
	}
	
	/**
	 * Moves a selected rectangle in the shape down and right by a number of squares
	 * @param r the selected rectangle in the current block
	 * @param h the number of times to move the block right
	 * @param v the number of times to move the block down
	 */
	public void moveDownRight (Rectangle r, int h, int v) {
		r.setY(r.getY() + SIZE * v);
		r.setX(r.getX() + SIZE * h);
	}
	
	/**
	 * Moves a selected rectangle in the shape down and left by a number of squares
	 * @param r the selected rectangle in the current block
	 * @param h the number of times to move the block left
	 * @param v the number of times to move the block down
	 */
	public void moveDownLeft (Rectangle r, int h, int v) {
		r.setY(r.getY() + SIZE * v);
		r.setX(r.getX() - SIZE * h);
	}
	
	/**
	 * Resets the rotation number
	 */
	public void resetRotation() {
		rotation = 1;
	}
}
