package lin.jun;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The class for initializing the different shapes for the game
 * @author Jun Lin
 * @author Kenneth Ou
 */

public class Shape {
	public Rectangle r1, r2, r3, r4;
	private Color shapeColor;
	private String shapeType;
	
	/**
	 * Constructor for a new shape, depending on what shape it is, the colour of the shape is assigned differently.
	 * @param a Rectangle Parts of the shape
	 * @param b Rectangle Parts of the shape
	 * @param c Rectangle Parts of the shape
	 * @param d Rectangle Parts of the shape
	 * @param shapeName Determine what type of shape it is
	 */
	public Shape(Rectangle a, Rectangle b, Rectangle c, Rectangle d, String shapeName) {
		r1 = a;
		r2 = b;
		r3 = c;
		r4 = d;
		shapeType = shapeName;
		if (shapeType.equals("T")) {
			shapeColor = Color.MEDIUMPURPLE;
		}
		else if (shapeType.equals("L")) {
			shapeColor = Color.BLACK;
		}		
		else if (shapeType.equals("J")) {
			shapeColor = Color.DARKGOLDENROD;
		}		
		else if (shapeType.equals("O")) {
			shapeColor = Color.YELLOWGREEN;
		}		
		else if (shapeType.equals("Z")) {
			shapeColor = Color.HOTPINK;
		}		
		else if (shapeType.equals("S")) {
			shapeColor = Color.SANDYBROWN;
		}
		else if (shapeType.equals("I")) {
			shapeColor = Color.MEDIUMAQUAMARINE;
		}
		this.r1.setFill(shapeColor);
		this.r2.setFill(shapeColor);
		this.r3.setFill(shapeColor);
		this.r4.setFill(shapeColor);
	}
	
	/**
	 * Returns the shape type of a block
	 * @return returns the shape type of a block
	 */
	public String getShapeType() {
		return shapeType;
	}
	
	/**
	 * Sets the x and y positions of a rectangle on the screen
	 * @param r the selected rectangle on the current shape
	 * @param x the x coordinate to be set for the rectangle
	 * @param y the y coordinate to be set for the rectangle
	 */
	public void setLocation(Rectangle r, int x, int y) {
		r.setX(x);
		r.setY(y);
	}
}