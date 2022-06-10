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
	final int WIDTH = SIZE * NUM_COLS;
	final int HEIGHT = SIZE * NUM_ROWS;
	static Board tetrisBoard = new Board(NUM_ROWS, NUM_COLS);
	static int[][] MESH = new int[NUM_ROWS][NUM_COLS];
	static Pane screen = new Pane();
	static int selectedColumn = 5;
	static int selectedRow = 0;
	static int score = 0;
	static int linesCleared = 0;
	static int level = 1;
	static int yooo = 1;
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
		
		game.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.DOWN) {
				spawnShape();
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
	
	public static void spawnShape() {
		
		Random rand = new Random();
		int shapeType = rand.nextInt(7);
			
		Rectangle a = new Rectangle(SIZE - 1, SIZE - 1);
		Rectangle b = new Rectangle(SIZE - 1, SIZE - 1);
		Rectangle c = new Rectangle(SIZE - 1, SIZE - 1);
		Rectangle d = new Rectangle(SIZE - 1, SIZE - 1);
		
		switch (shapeType + 1) {
		case 1 :
			System.out.println("T");
			Shape T_BLOCK = new Shape(a, b, c, d, "T");
			a.relocate(75, 25);
			b.relocate(100, 25);
			c.relocate(125, 25);
			d.relocate(100, 0);
			MESH[1][3] = 1;
			MESH[1][4] = 1;
			MESH[1][5] = 1;
			MESH[0][4] = 1;
			break;
		case 2: 
			System.out.println("L");
			a.relocate(75, 25);
			b.relocate(100, 25);
			c.relocate(125, 25);
			d.relocate(125, 0);
			MESH[1][3] = 1;
			MESH[1][4] = 1;
			MESH[1][5] = 1;
			MESH[0][5] = 1;
			break;
		case 3:
			System.out.println("J");
			a.relocate(75, 25);
			b.relocate(100, 25);
			c.relocate(125, 25);
			d.relocate(75, 0);
			MESH[1][3] = 1;
			MESH[1][4] = 1;
			MESH[1][5] = 1;
			MESH[0][3] = 1;
			break;
		case 4:
			System.out.println("O");
			a.relocate(100, 0);
			b.relocate(125, 0);
			c.relocate(100, 25);
			d.relocate(125, 25);
			MESH[0][4] = 1;
			MESH[0][5] = 1;
			MESH[1][4] = 1;
			MESH[1][5] = 1;
			break;
		case 5:
			System.out.println("I");
			a.relocate(75, 0);
			b.relocate(100, 0);
			c.relocate(125, 0);
			d.relocate(150, 0);
			MESH[0][3] = 1;
			MESH[0][4] = 1;
			MESH[0][5] = 1;
			MESH[0][6] = 1;
			break;
		case 6:
			System.out.println("Z");
			a.relocate(75, 0);
			b.relocate(100, 0);
			c.relocate(100, 25);
			d.relocate(125, 25);
			MESH[0][3] = 1;
			MESH[0][4] = 1;
			MESH[1][4] = 1;
			MESH[1][5] = 1;
			break;
		case 7:
			System.out.println("S");
			a.relocate(75, 25);
			b.relocate(100, 25);
			c.relocate(100, 0);
			d.relocate(125, 0);
			MESH[1][3] = 1;
			MESH[1][4] = 1;
			MESH[0][4] = 1;
			MESH[0][5] = 1;
			break;
		}
		screen.getChildren().addAll(a, b, c, d);
	}
}
