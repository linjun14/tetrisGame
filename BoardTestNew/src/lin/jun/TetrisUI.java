package lin.jun;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TetrisUI extends Application {

	final static int NUM_ROWS = 20;
	final static int NUM_COLS = 10;
	final static int SIZE = 35;
	final static int WIDTH = SIZE * NUM_COLS;
	final static int HEIGHT = SIZE * NUM_ROWS;
	static Board tetrisBoard = new Board(NUM_ROWS, NUM_COLS);
	static Pane screen = new Pane();
	static int score = 0;
	static int linesCleared = 0;
	static int level = 1;
	static Shape john = spawnShape();

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		StackPane gamingMomentSquare = new StackPane();
		Pane TETRIS = new Pane();
		Line separation = new Line(WIDTH, 0, WIDTH, HEIGHT);
		GridPane gameScreen = new GridPane();

		Rectangle gameRect = new Rectangle(0, 0, WIDTH, HEIGHT);
		gameScreen.setShape(gameRect);

		VBox gameStats = new VBox(10);
		Rectangle statsBoxSize = new Rectangle(WIDTH, HEIGHT, WIDTH / 2, HEIGHT / 2);
		gameStats.setShape(statsBoxSize);

		VBox scoreAndLines = new VBox(5);
		Font labelFont = new Font("Arial", 15);
		Label scoreLabel = new Label("Score: " + score);
		Label lineLabel = new Label("Lines: " + linesCleared);
		scoreLabel.setFont(labelFont);
		lineLabel.setFont(labelFont);
		scoreAndLines.getChildren().addAll(scoreLabel, lineLabel);

		VBox pieceCounts = new VBox(5);

		gameStats.getChildren().addAll(scoreAndLines, pieceCounts);
		gameStats.setTranslateX(WIDTH + 15);
		gameStats.setTranslateY(15);

		for (int r = 0; r < NUM_ROWS; r++) {
			for (int c = 0; c < NUM_COLS; c++) {
				Rectangle square = new Rectangle(SIZE - 1, SIZE - 1);
				square.setStyle("-fx-fill: #F1F2F5; -fx-stroke: white; -fx-stroke-width: 1;");
				gameScreen.add(square, c, r);
			}
		}

		screen.getChildren().addAll(gameScreen, separation, gameStats);
		gamingMomentSquare.getChildren().addAll(screen, TETRIS);
		Scene game = new Scene(gamingMomentSquare, WIDTH * 1.5, HEIGHT);
		screen.getChildren().addAll(john.r1, john.r2, john.r3, john.r4);

		Controller123_GOaddsionStopstealingtoiletpaperpleasebecausetheworldisrunninglowontreesdotoyouroverstealingoftoiletpaperalongwiththatyouneedtoreturnthatnerfgun control = new Controller123_GOaddsionStopstealingtoiletpaperpleasebecausetheworldisrunninglowontreesdotoyouroverstealingoftoiletpaperalongwiththatyouneedtoreturnthatnerfgun();

		game.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S) {
				if (!((john.r1.getY() + SIZE) < HEIGHT && (john.r2.getY() + SIZE) < HEIGHT
						&& (john.r3.getY() + SIZE) < HEIGHT && (john.r4.getY() + SIZE) < HEIGHT
						&& !tetrisBoard.checkDown(john))) {
					tetrisBoard.fillCell((int) john.r1.getY() / SIZE, (int) john.r1.getX() / SIZE);
					tetrisBoard.fillCell((int) john.r2.getY() / SIZE, (int) john.r2.getX() / SIZE);
					tetrisBoard.fillCell((int) john.r3.getY() / SIZE, (int) john.r3.getX() / SIZE);
					tetrisBoard.fillCell((int) john.r4.getY() / SIZE, (int) john.r4.getX() / SIZE);
					control.resetRotation();
					john = spawnShape();
					screen.getChildren().addAll(john.r1, john.r2, john.r3, john.r4);
					tetrisBoard.displayBoard();
				} else {
					control.moveDown(john);
				}
			}
			if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A) {
				if ((john.r1.getX() - SIZE) >= 0 && (john.r2.getX() - SIZE) >= 0 && (john.r3.getX() - SIZE) >= 0
						&& (john.r4.getX() - SIZE) >= 0 && !tetrisBoard.checkLeft(john)) {
					control.moveLeft(john);
				}
			}
			if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) {
				if ((john.r1.getX() + SIZE) < WIDTH && (john.r2.getX() + SIZE) < WIDTH
						&& (john.r3.getX() + SIZE) < WIDTH && (john.r4.getX() + SIZE) < WIDTH
						&& !tetrisBoard.checkRight(john)) {
					control.moveRight(john);
				}
			}
			if (e.getCode() == KeyCode.SPACE) {
				while ((john.r1.getY() + SIZE) < HEIGHT && (john.r2.getY() + SIZE) < HEIGHT
						&& (john.r3.getY() + SIZE) < HEIGHT && (john.r4.getY() + SIZE) < HEIGHT
						&& !tetrisBoard.checkDown(john)) {
					control.moveDown(john);
				}
				tetrisBoard.fillCell((int) john.r1.getY() / SIZE, (int) john.r1.getX() / SIZE);
				tetrisBoard.fillCell((int) john.r2.getY() / SIZE, (int) john.r2.getX() / SIZE);
				tetrisBoard.fillCell((int) john.r3.getY() / SIZE, (int) john.r3.getX() / SIZE);
				tetrisBoard.fillCell((int) john.r4.getY() / SIZE, (int) john.r4.getX() / SIZE);
				control.resetRotation();
				deleteLines();
				john = spawnShape();
				screen.getChildren().addAll(john.r1, john.r2, john.r3, john.r4);
				
			}
			if (e.getCode() == KeyCode.Z) {
				// TODO
			}
			if (e.getCode() == KeyCode.X) {
				control.rotateRight(john);
			}
		});

		stage.setScene(game);
		stage.setTitle("TETRIS");
		stage.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	public static void fallPiece(int seconds) {
		TimerTask fall = new TimerTask() {
			public void run() {
				System.out.println("john li");
			}
		};

		Timer time = new Timer();
		time.scheduleAtFixedRate(fall, 0, seconds);
	}

	public static Shape spawnShape() {

		Random rand = new Random();
		int shapeType = rand.nextInt(7);

		Rectangle a = new Rectangle(SIZE - 1, SIZE - 1);
		Rectangle b = new Rectangle(SIZE - 1, SIZE - 1);
		Rectangle c = new Rectangle(SIZE - 1, SIZE - 1);
		Rectangle d = new Rectangle(SIZE - 1, SIZE - 1);

		Shape block = null;

		switch (shapeType + 1) {
		case 1:
			System.out.println("T");
			block = new Shape(a, b, c, d, "T");
			block.setLocation(block.r1, 3 * SIZE, 1 * SIZE);
			block.setLocation(block.r2, 4 * SIZE, 1 * SIZE);
			block.setLocation(block.r3, 5 * SIZE, 1 * SIZE);
			block.setLocation(block.r4, 4 * SIZE, 0 * SIZE);
			break;
		case 2:
			System.out.println("L");
			block = new Shape(a, b, c, d, "L");
			block.setLocation(block.r1, 3 * SIZE, 1 * SIZE);
			block.setLocation(block.r2, 4 * SIZE, 1 * SIZE);
			block.setLocation(block.r3, 5 * SIZE, 1 * SIZE);
			block.setLocation(block.r4, 5 * SIZE, 0 * SIZE);
			break;
		case 3:
			System.out.println("J");
			block = new Shape(a, b, c, d, "J");
			block.setLocation(block.r1, 3 * SIZE, 1 * SIZE);
			block.setLocation(block.r2, 4 * SIZE, 1 * SIZE);
			block.setLocation(block.r3, 5 * SIZE, 1 * SIZE);
			block.setLocation(block.r4, 3 * SIZE, 0 * SIZE);
			break;
		case 4:
			System.out.println("O");
			block = new Shape(a, b, c, d, "O");
			block.setLocation(block.r1, 4 * SIZE, 0 * SIZE);
			block.setLocation(block.r2, 5 * SIZE, 0 * SIZE);
			block.setLocation(block.r3, 4 * SIZE, 1 * SIZE);
			block.setLocation(block.r4, 5 * SIZE, 1 * SIZE);
			break;
		case 5:
			System.out.println("I");
			block = new Shape(a, b, c, d, "I");
			block.setLocation(block.r1, 3 * SIZE, 0 * SIZE);
			block.setLocation(block.r2, 4 * SIZE, 0 * SIZE);
			block.setLocation(block.r3, 5 * SIZE, 0 * SIZE);
			block.setLocation(block.r4, 6 * SIZE, 0 * SIZE);
			break;
		case 6:
			System.out.println("Z");
			block = new Shape(a, b, c, d, "Z");
			block.setLocation(block.r1, 3 * SIZE, 0 * SIZE);
			block.setLocation(block.r2, 4 * SIZE, 0 * SIZE);
			block.setLocation(block.r3, 4 * SIZE, 1 * SIZE);
			block.setLocation(block.r4, 5 * SIZE, 1 * SIZE);
			break;
		case 7:
			System.out.println("S");
			block = new Shape(a, b, c, d, "S");
			block.setLocation(block.r1, 3 * SIZE, 1 * SIZE);
			block.setLocation(block.r2, 4 * SIZE, 1 * SIZE);
			block.setLocation(block.r3, 4 * SIZE, 0 * SIZE);
			block.setLocation(block.r4, 5 * SIZE, 0 * SIZE);
			break;
		}
		return block;
	}

	public void deleteLines() {
		ArrayList<Integer> linesFilled = new ArrayList<Integer>(); //Wh
		ArrayList<Node> blocks = new ArrayList<Node>();
		int lineBlocks = 0;

		for (int i = 0; i < tetrisBoard.getMESH().length; i++) {
			for (int o = 0; o < tetrisBoard.getMESH()[0].length; o++) {
				if (tetrisBoard.getMESH()[i][o] == 1) { // If the individual cell is filled or not
					lineBlocks++;
				}
				else {
					break;
				}
			}
			System.out.println(lineBlocks);
			if (lineBlocks == NUM_COLS) {
				linesFilled.add(i); // Add the filled row index
			}
			lineBlocks = 0;
			
		}
		if (linesFilled.size() > 0) {
			do {
				for (Node block : screen.getChildren()) { // Gets all of the rectangles that are in the
					if (block instanceof Rectangle) {// Filter out the Rectangles (Just in case)
						blocks.add(block);
					}
				}
				// Removing the blocks and moving them down
				for (Node block : blocks) {
					Rectangle tempBlock = (Rectangle) block;
					if (tempBlock.getY() == linesFilled.get(0) * SIZE) {
						tetrisBoard.getMESH()[(int) (tempBlock.getY()/SIZE)][(int) (tempBlock.getX()/SIZE)] = 0;
						screen.getChildren().remove(block);
					} 
					
				}
				
				for (Node block : blocks) {
					Rectangle tempBlock = (Rectangle) block;
					if (tempBlock.getY() < linesFilled.get(0)*SIZE) {
						tetrisBoard.getMESH()[(int) (tempBlock.getY()/SIZE)][(int) (tempBlock.getX()/SIZE)] = 0;
						tempBlock.setY(tempBlock.getY() + SIZE);
						tetrisBoard.getMESH()[(int) (tempBlock.getY()/SIZE)][(int) (tempBlock.getX()/SIZE)] = 1;
					}
				}
				
				linesFilled.remove(0);
				blocks.clear();


		} while (linesFilled.size() > 0); 
		}
		
	}

}
