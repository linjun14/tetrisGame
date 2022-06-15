package lin.jun;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Shape {
	Rectangle r1, r2, r3, r4;
	Color shapeColor;
	private String shapeType;
	
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
	
	public String getShapeType() {
		return shapeType;
	}
	
	public void setLocation(Rectangle r, int x, int y) {
		r.setX(x);
		r.setY(y);
	}
}