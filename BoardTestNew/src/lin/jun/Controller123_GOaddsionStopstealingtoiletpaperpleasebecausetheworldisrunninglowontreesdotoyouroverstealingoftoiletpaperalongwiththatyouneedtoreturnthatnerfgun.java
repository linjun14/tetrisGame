package lin.jun;

public class Controller123_GOaddsionStopstealingtoiletpaperpleasebecausetheworldisrunninglowontreesdotoyouroverstealingoftoiletpaperalongwiththatyouneedtoreturnthatnerfgun {
	final static int SIZE = TetrisUI.SIZE;
	final static int WIDTH = TetrisUI.WIDTH;
	final int HEIGHT = TetrisUI.HEIGHT;
	
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
	
	public void SMACK(Shape block) {
	
}
	
}
