package lin.jun;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.geometry.Pos;
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
	final static int SIZE = 25;
	final static int WIDTH = SIZE * NUM_COLS;
	final static int HEIGHT = SIZE * NUM_ROWS;
	static Board tetrisBoard = new Board(NUM_ROWS, NUM_COLS);
	static Pane screen = new Pane();
	static int selectedColumn = 5;
	static int selectedRow = 0;
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
				if (!((john.r1.getY() + SIZE) < HEIGHT && (john.r2.getY() + SIZE) < HEIGHT && (john.r3.getY() + SIZE) < HEIGHT && (john.r4.getY() + SIZE) < HEIGHT && !tetrisBoard.checkDown(john))) {
					tetrisBoard.fillCell((int)john.r1.getY()/SIZE, (int)john.r1.getX()/SIZE);
					tetrisBoard.fillCell((int)john.r2.getY()/SIZE, (int)john.r2.getX()/SIZE);
					tetrisBoard.fillCell((int)john.r3.getY()/SIZE, (int)john.r3.getX()/SIZE);
					tetrisBoard.fillCell((int)john.r4.getY()/SIZE, (int)john.r4.getX()/SIZE);
					john = spawnShape();
					screen.getChildren().addAll(john.r1, john.r2, john.r3, john.r4);
					tetrisBoard.displayBoard();
				}
				else {
					control.moveDown(john);
				}
			}
			if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A) {
				if ((john.r1.getX() - SIZE) >= 0 && (john.r2.getX() - SIZE) >= 0 && (john.r3.getX() - SIZE) >= 0 && (john.r4.getX() - SIZE) >= 0 && !tetrisBoard.checkLeft(john)) {
					control.moveLeft(john);
				}
			}
			if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) {
				if ((john.r1.getX() + SIZE) < WIDTH && (john.r2.getX() + SIZE) < WIDTH && (john.r3.getX() + SIZE) < WIDTH && (john.r4.getX() + SIZE) < WIDTH && !tetrisBoard.checkRight(john)) {
					control.moveRight(john);
				}
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
		case 1 :
			System.out.println("T");
			block = new Shape(a, b, c, d, "T");
			block.setLocation(block.r1, 75, 25);
			block.setLocation(block.r2, 100, 25);
			block.setLocation(block.r3, 125, 25);
			block.setLocation(block.r4, 100, 0);
			break;
		case 2: 
			System.out.println("L");
			block = new Shape(a, b, c, d, "L");
			block.setLocation(block.r1, 75, 25);
			block.setLocation(block.r2, 100, 25);
			block.setLocation(block.r3, 125, 25);
			block.setLocation(block.r4, 125, 0);
			break;
		case 3:
			System.out.println("J");
			block = new Shape(a, b, c, d, "J");
			block.setLocation(block.r1, 75, 25);
			block.setLocation(block.r2, 100, 25);
			block.setLocation(block.r3, 125, 25);
			block.setLocation(block.r4, 75, 0);
			break;
		case 4:
			System.out.println("O");
			block = new Shape(a, b, c, d, "O");
			block.setLocation(block.r1, 100, 0);
			block.setLocation(block.r2, 125, 0);
			block.setLocation(block.r3, 100, 25);
			block.setLocation(block.r4, 125, 25);
			break;
		case 5:
			System.out.println("I");
			block = new Shape(a, b, c, d, "I");
			block.setLocation(block.r1, 75, 0);
			block.setLocation(block.r2, 100, 0);
			block.setLocation(block.r3, 125, 0);
			block.setLocation(block.r4, 150, 0);
			break;
		case 6:
			System.out.println("Z");
			block = new Shape(a, b, c, d, "Z");
			block.setLocation(block.r1, 75, 0);
			block.setLocation(block.r2, 100, 0);
			block.setLocation(block.r3, 100, 25);
			block.setLocation(block.r4, 125, 25);
			break;
		case 7:
			System.out.println("S");
			block = new Shape(a, b, c, d, "S");
			block.setLocation(block.r1, 75, 25);
			block.setLocation(block.r2, 100, 25);
			block.setLocation(block.r3, 100, 0);
			block.setLocation(block.r4, 125, 0);
			break;
		}
		return block;
	}
}
