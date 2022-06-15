package lin.jun;

import javafx.scene.shape.Rectangle;

public class Controller123_GOaddsionStopstealingtoiletpaperpleasebecausetheworldisrunninglowontreesdotoyouroverstealingoftoiletpaperalongwiththatyouneedtoreturnthatnerfgun {
	final static int SIZE = TetrisUI.SIZE;
	final static int WIDTH = TetrisUI.WIDTH;
	final int HEIGHT = TetrisUI.HEIGHT;
	int rotation = 1;
	
	public void moveRight(Shape block) {
		block.r1.setX(block.r1.getX() + SIZE);
		block.r2.setX(block.r2.getX() + SIZE);
		block.r3.setX(block.r3.getX() + SIZE);
		block.r4.setX(block.r4.getX() + SIZE);
	}
	
	public void moveLeft(Shape block) {
		block.r1.setX(block.r1.getX() - SIZE);
		block.r2.setX(block.r2.getX() - SIZE);
		block.r3.setX(block.r3.getX() - SIZE);
		block.r4.setX(block.r4.getX() - SIZE);
	}
	
	public void moveDown(Shape block) {
		block.r1.setY(block.r1.getY() + SIZE);
		block.r2.setY(block.r2.getY() + SIZE);
		block.r3.setY(block.r3.getY() + SIZE);
		block.r4.setY(block.r4.getY() + SIZE);
	}
	
	public void rotateRight(Shape block) {
		
		if (rotation != 4) {
			rotation++;
		}
		else {
			rotation = 1;
		}
		
		rotateShape(block);
	}
	
	public void rotateShape(Shape block) {
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
				moveDownLeft(block.r1, 2, 0);
				moveDownRight(block.r4, 0, 2);
			}
			else if (rotation == 2) {
				moveUpRight(block.r1, 2, 0);
				moveUpLeft(block.r4, 0, 2);
			}
			else if (rotation == 3) {
				moveDownLeft(block.r1, 2, 0);
				moveDownRight(block.r4, 0, 2);
			}
			else if (rotation == 4) {
				moveUpRight(block.r1, 2, 0);
				moveUpLeft(block.r4, 0, 2);
			}
		}
		else if (block.getShapeType().equals("S")) {
			if (rotation == 1) {
				moveDownLeft(block.r1, 0, 2);
				moveUpRight(block.r4, 2, 0);
			}
			else if (rotation == 2) {
				moveUpRight(block.r1, 0, 2);
				moveDownLeft(block.r4, 2, 0);
			}
			else if (rotation == 3) {
				moveDownLeft(block.r1, 0, 2);
				moveUpRight(block.r4, 2, 0);
			}
			else if (rotation == 4) {
				moveUpRight(block.r1, 0, 2);
				moveDownLeft(block.r4, 2, 0);
			}
		}
		else if (block.getShapeType().equals("I")) {
			if (rotation == 1) {
				moveDownLeft(block.r1, 2, 1);
				moveDownLeft(block.r2, 1, 0);
				moveUpRight(block.r3, 0, 1);
				moveUpRight(block.r4, 1, 2);
			}
			else if (rotation == 2) {
				moveUpRight(block.r1, 2, 1);
				moveUpRight(block.r2, 1, 0);
				moveDownLeft(block.r3, 0, 1);
				moveDownLeft(block.r4, 1, 2);
			}
			else if (rotation == 3) {
				moveDownLeft(block.r1, 2, 1);
				moveDownLeft(block.r2, 1, 0);
				moveUpRight(block.r3, 0, 1);
				moveUpRight(block.r4, 1, 2);
			}
			else if (rotation == 4) {
				moveUpRight(block.r1, 2, 1);
				moveUpRight(block.r2, 1, 0);
				moveDownLeft(block.r3, 0, 1);
				moveDownLeft(block.r4, 1, 2);
			}
		}
	}
	
	public void moveUpLeft (Rectangle r, int h, int v) {
		r.setY(r.getY() - SIZE * v);
		r.setX(r.getX() - SIZE * h);
	}
	
	public void moveUpRight (Rectangle r, int h, int v) {
		r.setY(r.getY() - SIZE * v);
		r.setX(r.getX() + SIZE * h);
	}
	
	public void moveDownRight (Rectangle r, int h, int v) {
		r.setY(r.getY() + SIZE * v);
		r.setX(r.getX() + SIZE * h);
	}
	
	public void moveDownLeft (Rectangle r, int h, int v) {
		r.setY(r.getY() + SIZE * v);
		r.setX(r.getX() - SIZE * h);
	}
	
	public void resetRotation() {
		rotation = 1;
	}
}
