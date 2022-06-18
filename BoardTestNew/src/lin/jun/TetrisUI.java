package lin.jun;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
	static String PLAYERNAME;
	static int score = 0;
	static int linesCleared = 0;
	static int level = 0;
	static int droughtCounter = 0;
	static Shape nextShape = spawnShape();
	static Shape john = spawnShapeOnBoard(nextShape);
	static Label levelLabel = new Label("Level: " + level);
	static Label scoreLabel = new Label("Score: " + score);
	static Label lineLabel = new Label("Lines: " + linesCleared);
	static Label droughtCount = new Label("Longbar drought: " + droughtCounter);
	static int fallSpeed;
	static Timer time = new Timer();
	static TimerTask fall;
	static Controller123_GOaddsionStopstealingtoiletpaperpleasebecausetheworldisrunninglowontreesdotoyouroverstealingoftoiletpaperalongwiththatyouneedtoreturnthatnerfgun control = new Controller123_GOaddsionStopstealingtoiletpaperpleasebecausetheworldisrunninglowontreesdotoyouroverstealingoftoiletpaperalongwiththatyouneedtoreturnthatnerfgun();

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		StackPane gamingMomentSquare = new StackPane();
		Line separation = new Line(WIDTH, 0, WIDTH, HEIGHT);
		Line separation2 = new Line(0, 0, WIDTH, HEIGHT);
		GridPane gameScreen = new GridPane();

		Rectangle gameRect = new Rectangle(0, 0, WIDTH, HEIGHT);
		gameScreen.setShape(gameRect);

		VBox gameStats = new VBox(10);
		Rectangle statsBoxSize = new Rectangle(WIDTH, HEIGHT, WIDTH / 2, HEIGHT / 2);
		gameStats.setShape(statsBoxSize);

		VBox scoreAndLines = new VBox(5);
		Font labelFont = new Font("Arial", SIZE);

		Label nextLabel = new Label("NEXT");
		Font nextLabelFont = new Font("Arial", SIZE);
		nextLabel.setTranslateX(SIZE);
		nextLabel.setTranslateY(SIZE);
		droughtCount.setTranslateY(SIZE * 5);
		
		scoreLabel.setFont(labelFont);
		levelLabel.setFont(labelFont);
		lineLabel.setFont(labelFont);
		nextLabel.setFont(nextLabelFont);
		droughtCount.setFont(labelFont);
		scoreAndLines.getChildren().addAll(levelLabel, scoreLabel, lineLabel, nextLabel, droughtCount);

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
		gameScreen.setAlignment(Pos.CENTER);
		screen.getChildren().addAll(separation2, gameScreen, separation, gameStats);
		gamingMomentSquare.getChildren().addAll(screen);
		Scene game = new Scene(gamingMomentSquare, WIDTH * 2, HEIGHT);

		nextShape = spawnShape();
		screen.getChildren().addAll(john.r1, john.r2, john.r3, john.r4);
		screen.getChildren().addAll(nextShape.r1, nextShape.r2, nextShape.r3, nextShape.r4);

		fall = new TimerTask() {
			public void run() {
				if (isOutBottom(john)) {
					tetrisBoard.fillCell((int) john.r1.getY() / SIZE, (int) john.r1.getX() / SIZE);
					tetrisBoard.fillCell((int) john.r2.getY() / SIZE, (int) john.r2.getX() / SIZE);
					tetrisBoard.fillCell((int) john.r3.getY() / SIZE, (int) john.r3.getX() / SIZE);
					tetrisBoard.fillCell((int) john.r4.getY() / SIZE, (int) john.r4.getX() / SIZE);
					control.resetRotation();
					Platform.runLater(() -> deleteLines());
					// tetrisBoard.displayBoard();
				} else {
					control.moveDown(john);
				}
			}
		};
		
		VBox inputScreen = new VBox(10);
		inputScreen.setStyle("-fx-background-color: #97AAE0");
		inputScreen.setAlignment(Pos.CENTER);
		Label info = new Label("Enter name 				 Enter Level (0-19)");
		Label errorMessage = new Label("Please enter a valid level 0-19");
		HBox playerInputBox = new HBox(20);
		TextField playerName = new TextField("PlayerName");
		TextField levelWanted = new TextField("0");
		playerInputBox.getChildren().addAll(playerName, levelWanted);
		playerInputBox.setAlignment(Pos.CENTER);
		Button confirmation = new Button("Confirm");
		confirmation.setMinSize(200, 50);
		inputScreen.getChildren().addAll(info, playerInputBox, confirmation);
		Scene playerInputScene = new Scene(inputScreen, 350, 200);
		errorMessage.setVisible(false);
		inputScreen.getChildren().add(errorMessage);
		errorMessage.setAlignment(Pos.CENTER);
		confirmation.setOnAction(e -> {
			if 	(Integer.valueOf(levelWanted.getText()) < 20 && Integer.valueOf(levelWanted.getText()) > -1) {
				PLAYERNAME = playerName.getText();
				level = Integer.valueOf(levelWanted.getText());
				levelLabel.setText(String.valueOf("Level: " + level));
				stage.setScene(game);
				initialFallPiece();
			}
			else {
				errorMessage.setVisible(true);
				
			}
			
		});

		game.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S) {
				if (isOutBottom(john)) {
					tetrisBoard.fillCell((int) john.r1.getY() / SIZE, (int) john.r1.getX() / SIZE);
					tetrisBoard.fillCell((int) john.r2.getY() / SIZE, (int) john.r2.getX() / SIZE);
					tetrisBoard.fillCell((int) john.r3.getY() / SIZE, (int) john.r3.getX() / SIZE);
					tetrisBoard.fillCell((int) john.r4.getY() / SIZE, (int) john.r4.getX() / SIZE);
					control.resetRotation();
					deleteLines();
					// tetrisBoard.displayBoard();
				} else {
					control.moveDown(john);
					score += 1;
					scoreLabel.setText("Score: " + score);
				}
			}
			if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A) {
				if (!isOutLeft(john)) {
					control.moveLeft(john);
				}
			}
			if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) {
				if (!isOutRight(john)) {
					control.moveRight(john);
				}
			}
			if (e.getCode() == KeyCode.SPACE) {
				while (!isOutBottom(john)) {
					control.moveDown(john);
					score += 2;
					scoreLabel.setText("Score: " + score);
				}
				tetrisBoard.fillCell((int) john.r1.getY() / SIZE, (int) john.r1.getX() / SIZE);
				tetrisBoard.fillCell((int) john.r2.getY() / SIZE, (int) john.r2.getX() / SIZE);
				tetrisBoard.fillCell((int) john.r3.getY() / SIZE, (int) john.r3.getX() / SIZE);
				tetrisBoard.fillCell((int) john.r4.getY() / SIZE, (int) john.r4.getX() / SIZE);
				control.resetRotation();
				deleteLines();
			}
			if (e.getCode() == KeyCode.Z) {
				if (john.getShapeType().equals("I")) {
					if ((john.r2.getX() - SIZE) >= 0 && (john.r2.getX() + SIZE) < WIDTH && (john.r2.getY() + SIZE) < HEIGHT 
							&& (john.r2.getX() - SIZE * 2) >= 0 && !tetrisBoard.checkRotationPoint(john)){
						control.rotateLeft(john);
					}
				}
				else if ((john.r2.getX() - SIZE) >= 0 && (john.r2.getX() + SIZE) < WIDTH && (john.r2.getY() + SIZE) < HEIGHT
						&& !tetrisBoard.checkRotationPoint(john)) {
					control.rotateLeft(john);
				}
			}
			if (e.getCode() == KeyCode.X) {
				if (john.getShapeType().equals("I")) {
					if ((john.r2.getX() - SIZE) >= 0 && (john.r2.getX() + SIZE) < WIDTH && (john.r2.getY() + SIZE) < HEIGHT
							&& (john.r2.getX() - SIZE * 2) >= 0 && !tetrisBoard.checkRotationPoint(john)) {
						control.rotateRight(john);
					}
				}
				else if ((john.r2.getX() - SIZE) >= 0 && (john.r2.getX() + SIZE) < WIDTH && (john.r2.getY() + SIZE) < HEIGHT
						&& !tetrisBoard.checkRotationPoint(john)) {
					control.rotateRight(john);
				}
			}
		});

		stage.setScene(playerInputScene);
		stage.setTitle("TETRIS");
		stage.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	public static TimerTask getTask() {
		return fall;
	}
	
	public static void initialFallPiece() {
		if (level <= 8) {
			fallSpeed = 1000 - 104 * level;
		}
		else if (level == 9) {
			fallSpeed = 125;
		}
		else if (level <= 12) {
			fallSpeed = 104;
		}
		else if (level <= 15) {
			fallSpeed = 84;
		}
		else if (level <= 18) {
			fallSpeed = 64;
		}
		else {
			fallSpeed = 42;
		}
		time.schedule(fall, 2000, fallSpeed);
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
			block = new Shape(a, b, c, d, "T");
			block.setLocation(block.r1, 11 * SIZE, 9 * SIZE);
			block.setLocation(block.r2, 12 * SIZE, 9 * SIZE);
			block.setLocation(block.r3, 13 * SIZE, 9 * SIZE);
			block.setLocation(block.r4, 12 * SIZE, 8 * SIZE);
			droughtCounter++;
			break;
		case 2:
			block = new Shape(a, b, c, d, "L");
			block.setLocation(block.r1, 11 * SIZE, 9 * SIZE);
			block.setLocation(block.r2, 12 * SIZE, 9 * SIZE);
			block.setLocation(block.r3, 13 * SIZE, 9 * SIZE);
			block.setLocation(block.r4, 13 * SIZE, 8 * SIZE);
			droughtCounter++;
			break;
		case 3:
			block = new Shape(a, b, c, d, "J");
			block.setLocation(block.r1, 11 * SIZE, 9 * SIZE);
			block.setLocation(block.r2, 12 * SIZE, 9 * SIZE);
			block.setLocation(block.r3, 13 * SIZE, 9 * SIZE);
			block.setLocation(block.r4, 13 * SIZE, 8 * SIZE);
			droughtCounter++;
			break;
		case 4:
			block = new Shape(a, b, c, d, "O");
			block.setLocation(block.r1, 12 * SIZE, 8 * SIZE);
			block.setLocation(block.r2, 13 * SIZE, 8 * SIZE);
			block.setLocation(block.r3, 12 * SIZE, 9 * SIZE);
			block.setLocation(block.r4, 13 * SIZE, 9 * SIZE);
			droughtCounter++;
			break;
		case 5:
			block = new Shape(a, b, c, d, "I");
			block.setLocation(block.r1, 11 * SIZE, 8 * SIZE);
			block.setLocation(block.r2, 12 * SIZE, 8 * SIZE);
			block.setLocation(block.r3, 13 * SIZE, 8 * SIZE);
			block.setLocation(block.r4, 14 * SIZE, 8 * SIZE);
			droughtCounter = 0;
			break;
		case 6:
			block = new Shape(a, b, c, d, "Z");
			block.setLocation(block.r1, 11 * SIZE, 8 * SIZE);
			block.setLocation(block.r2, 12 * SIZE, 8 * SIZE);
			block.setLocation(block.r3, 12 * SIZE, 9 * SIZE);
			block.setLocation(block.r4, 13 * SIZE, 9 * SIZE);
			droughtCounter++;
			break;
		case 7:
			block = new Shape(a, b, c, d, "S");
			block.setLocation(block.r4, 11 * SIZE, 9 * SIZE);
			block.setLocation(block.r3, 12 * SIZE, 9 * SIZE);
			block.setLocation(block.r2, 12 * SIZE, 8 * SIZE);
			block.setLocation(block.r1, 13 * SIZE, 8 * SIZE);
			droughtCounter++;
			break;
		}
		return block;
	}

	public static Shape spawnShapeOnBoard(Shape block) {

		String shapeType = block.getShapeType();
		Shape nextShape = block;

		if (shapeType.equals("T")) {
			block.setLocation(block.r1, 3 * SIZE, 1 * SIZE);
			block.setLocation(block.r2, 4 * SIZE, 1 * SIZE);
			block.setLocation(block.r3, 5 * SIZE, 1 * SIZE);
			block.setLocation(block.r4, 4 * SIZE, 0 * SIZE);
		} else if (shapeType.equals("L")) {
			block.setLocation(block.r1, 3 * SIZE, 1 * SIZE);
			block.setLocation(block.r2, 4 * SIZE, 1 * SIZE);
			block.setLocation(block.r3, 5 * SIZE, 1 * SIZE);
			block.setLocation(block.r4, 5 * SIZE, 0 * SIZE);
		} else if (shapeType.equals("J")) {
			block.setLocation(block.r1, 3 * SIZE, 1 * SIZE);
			block.setLocation(block.r2, 4 * SIZE, 1 * SIZE);
			block.setLocation(block.r3, 5 * SIZE, 1 * SIZE);
			block.setLocation(block.r4, 3 * SIZE, 0 * SIZE);
		} else if (shapeType.equals("O")) {
			block.setLocation(block.r1, 4 * SIZE, 0 * SIZE);
			block.setLocation(block.r2, 5 * SIZE, 0 * SIZE);
			block.setLocation(block.r3, 4 * SIZE, 1 * SIZE);
			block.setLocation(block.r4, 5 * SIZE, 1 * SIZE);
		} else if (shapeType.equals("I")) {
			block.setLocation(block.r1, 6 * SIZE, 0 * SIZE);
			block.setLocation(block.r2, 5 * SIZE, 0 * SIZE);
			block.setLocation(block.r3, 4 * SIZE, 0 * SIZE);
			block.setLocation(block.r4, 3 * SIZE, 0 * SIZE);
		} else if (shapeType.equals("Z")) {
			block.setLocation(block.r1, 3 * SIZE, 0 * SIZE);
			block.setLocation(block.r2, 4 * SIZE, 0 * SIZE);
			block.setLocation(block.r3, 4 * SIZE, 1 * SIZE);
			block.setLocation(block.r4, 5 * SIZE, 1 * SIZE);
		} else if (shapeType.equals("S")) {
			block.setLocation(block.r4, 3 * SIZE, 1 * SIZE);
			block.setLocation(block.r3, 4 * SIZE, 1 * SIZE);
			block.setLocation(block.r2, 4 * SIZE, 0 * SIZE);
			block.setLocation(block.r1, 5 * SIZE, 0 * SIZE);
		}
		return nextShape;
	}

	public static void deleteLines() {
		ArrayList<Integer> linesFilled = new ArrayList<Integer>();
		ArrayList<Node> blocks = new ArrayList<Node>();
		int lineBlocks = 0;

		for (int i = 0; i < tetrisBoard.getMESH().length; i++) {
			for (int o = 0; o < tetrisBoard.getMESH()[0].length; o++) {
				if (tetrisBoard.getMESH()[i][o] == 1) { // If the individual cell is filled or not
					lineBlocks++;
				} else {
					break;
				}
			}
			if (lineBlocks == NUM_COLS) {
				linesFilled.add(i); // Add the filled row index
			}
			lineBlocks = 0;

		}
		if (linesFilled.size() == 1) {
			score += 40 * (level + 1);
		} else if (linesFilled.size() == 2) {
			score += 100 * (level + 1);
		} else if (linesFilled.size() == 3) {
			score += 300 * (level + 1);
		} else if (linesFilled.size() == 4) {
			score += 1200 * (level + 1);
		}
		linesCleared += linesFilled.size();
		
		if (linesCleared / 10 > level) {
			level = (linesCleared / 10);
		}
		levelLabel.setText("Level: " + (level));

		lineLabel.setText("Lines: " + linesCleared);
		scoreLabel.setText("Score: " + score);
		if (linesFilled.size() > 0) {
			do {
				for (Node block : screen.getChildren()) { // Gets all of the rectangles that are in the
					if (block instanceof Rectangle && ((Rectangle) block).getX() < WIDTH) {// Filter out the Rectangles
																							// (Just in case)
						blocks.add(block);
					}
				}
				// Removing the blocks and moving them down
				for (Node block : blocks) {
					Rectangle tempBlock = (Rectangle) block;
					if (tempBlock.getY() == linesFilled.get(0) * SIZE) {
						tetrisBoard.getMESH()[(int) (tempBlock.getY() / SIZE)][(int) (tempBlock.getX() / SIZE)] = 0;
						screen.getChildren().remove(block);
					}
				}

				for (Node block : blocks) {
					Rectangle tempBlock = (Rectangle) block;
					if (tempBlock.getY() < linesFilled.get(0) * SIZE && tempBlock.getX() < WIDTH) {
						tetrisBoard.getMESH()[(int) (tempBlock.getY() / SIZE)][(int) (tempBlock.getX() / SIZE)] = 0;
						tempBlock.setY(tempBlock.getY() + SIZE);
					}
				}

				linesFilled.remove(0);
				blocks.clear();
				for (Node block : screen.getChildren()) { // Gets all of the rectangles that are in the
					if (block instanceof Rectangle && ((Rectangle) block).getX() < WIDTH) {// Filter out the Rectangles
																							// (Just in case)
						Rectangle tempBlock = (Rectangle) block;
						tetrisBoard.getMESH()[(int) (tempBlock.getY() / SIZE)][(int) (tempBlock.getX() / SIZE)] = 1;
					}
				}

			} while (linesFilled.size() > 0);
		}
		john = spawnShapeOnBoard(nextShape);
		droughtCount.setText("Longbar drought:" + droughtCounter);
		nextShape = spawnShape();
		screen.getChildren().addAll(nextShape.r1, nextShape.r2, nextShape.r3, nextShape.r4);
	}

	public boolean isOutLeft(Shape block) {
		if ((block.r1.getX() - SIZE) >= 0 && (block.r2.getX() - SIZE) >= 0 && (block.r3.getX() - SIZE) >= 0
				&& (block.r4.getX() - SIZE) >= 0 && !tetrisBoard.checkLeft(block)) {
			return false;
		}
		return true;
	}

	public boolean isOutRight(Shape block) {
		if ((block.r1.getX() + SIZE) < WIDTH && (block.r2.getX() + SIZE) < WIDTH && (block.r3.getX() + SIZE) < WIDTH
				&& (block.r4.getX() + SIZE) < WIDTH && !tetrisBoard.checkRight(block)) {
			return false;
		}
		return true;
	}

	public boolean isOutTop(Shape block) {
		if ((block.r1.getY() - SIZE) >= 0 && (block.r2.getY() - SIZE) >= 0 && (block.r3.getY() - SIZE) >= 0
				&& (block.r4.getY() - SIZE) >= 0 && !tetrisBoard.checkDown(block)) {
			return false;
		}
		return true;
	}

	public static boolean isOutBottom(Shape block) {
		if ((block.r1.getY() + SIZE) < HEIGHT && (block.r2.getY() + SIZE) < HEIGHT && (block.r3.getY() + SIZE) < HEIGHT
				&& (block.r4.getY() + SIZE) < HEIGHT && !tetrisBoard.checkDown(block)) {
			return false;
		}
		return true;
	}
}
