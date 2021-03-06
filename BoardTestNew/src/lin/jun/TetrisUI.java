package lin.jun;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A simulation of the classic Tetris game
 * @author Jun Lin
 * @author Kenneth Ou
 */
public class TetrisUI extends Application {

	private final static int NUM_ROWS = 20;
	private final static int NUM_COLS = 10;
	public final static int SIZE = 35;
	public final static int WIDTH = SIZE * NUM_COLS; // Width of the graphical game board
	public final static int HEIGHT = SIZE * NUM_ROWS; // Height of the graphical game board
	private static Board tetrisBoard = new Board(NUM_ROWS, NUM_COLS); // The Board object for the tetris board
	private static StackPane gameScreen = new StackPane();
	private static Pane screen = new Pane(); // The screen for the game
	private static String PLAYERNAME;
	private static int score = 0;
	private static int linesCleared = 0;
	private static int level = 0;
	private static int droughtCounter = 0; // Number of consecutive pieces that is not the I-block
	private static int highScore = 0;
	private static Shape nextShape = spawnShape(); // Spawns the first shape
	public static Shape block = spawnShapeOnBoard(nextShape); // Spawns the first shape on the game board
	private static Label levelLabel = new Label("Level: " + level);
	private static Label scoreLabel = new Label("Score: " + score);
	private static Label lineLabel = new Label("Lines: " + linesCleared);
	private static Label highScoreLabel = new Label ("Highscore: " + highScore);
	private static Label droughtCount = new Label("Longbar drought: " + droughtCounter);
	private static Button restart = new Button("RESTART"); // Button for restarting the game
	private Rectangle nextBox = new Rectangle(SIZE * 5, SIZE * 4);
	private static int fallSpeed; // Auto drop speed of the blocks
	private static Timer time; // The timer for the game
	private boolean pause = false; // Pause game status
	private static boolean go = false; // Prevents piece from moving down when the timer is called
	private static Controller control = new Controller(); // The controller object for the game

	@Override
	public void start(Stage stage) throws Exception {
		
		// Game background
		GridPane gameBackground = new GridPane();
		Rectangle gameRect = new Rectangle(0, 0, WIDTH, HEIGHT);
		gameBackground.setShape(gameRect);

		// Game statistics 
		VBox gameStats = new VBox(10);
		Rectangle statsBoxSize = new Rectangle(WIDTH, HEIGHT, WIDTH / 2, HEIGHT / 2);
		gameStats.setShape(statsBoxSize);
		VBox gameInfo = new VBox(5);
		Line separation = new Line(WIDTH, 0, WIDTH, HEIGHT);
		Label nextLabel = new Label("NEXT");
		Label controlContent = new Label("************CONTROLS************ \n LEFT ARROW - MOVE LEFT \n RIGHT ARROW - MOVE RIGHT \n DOWN ARROW - MOVE DOWN "
										+ "\n SPACE - HARD DROP \n Z - ROTATE LEFT \n X - ROTATE RIGHT \n P - PAUSE");
		
		// Font styles for the labels
		Font labelFont = new Font("Arial", SIZE);
		Font contentFont = new Font ("Arial", SIZE/2);
		
		// Sets the labels with their corresponding fonts
		highScoreLabel.setFont(labelFont);
		scoreLabel.setFont(labelFont);
		levelLabel.setFont(labelFont);
		lineLabel.setFont(labelFont);
		droughtCount.setFont(labelFont);
		restart.setFont(labelFont);
		controlContent.setFont(contentFont);
		
		// Sets the properties of the next box
		nextBox.setStyle("-fx-fill: #F1F2F4; -fx-stroke: black; -fx-stroke-width: 1");
		nextLabel.setTranslateX(SIZE);
		nextLabel.setFont(labelFont);
		
		// Adds all necessary information display to gameInfo, then to gameStats
		gameInfo.getChildren().addAll(highScoreLabel, scoreLabel, levelLabel, lineLabel, nextLabel, nextBox, droughtCount, controlContent, restart);
		gameStats.getChildren().add(gameInfo);
		gameStats.setTranslateX(WIDTH + SIZE / 2);
		gameStats.setTranslateY(SIZE / 2);

		// Creates the game board background
		for (int r = 0; r < NUM_ROWS; r++) {
			for (int c = 0; c < NUM_COLS; c++) {
				Rectangle square = new Rectangle(SIZE - 1, SIZE - 1);
				square.setStyle("-fx-fill: #F1F2F4; -fx-stroke: white; -fx-stroke-width: 1;");
				gameBackground.add(square, c, r);
			}
		}
		
		gameBackground.setAlignment(Pos.CENTER);
		
		// Adds the background, separation line, and game statistics to the screen
		screen.getChildren().addAll(gameBackground, separation, gameStats);
		gameScreen.getChildren().addAll(screen);
		
		// The scene for the game
		Scene game = new Scene(gameScreen, WIDTH * 2, HEIGHT);
		
		// Design for the input screen (for entering player name and starting level)
		VBox inputScreen = new VBox(10);
		inputScreen.setStyle("-fx-background-color: #97AAE0");
		inputScreen.setAlignment(Pos.CENTER);
		Label info = new Label("Enter name 				 Enter Level (0-19)");
		Label errorMessage = new Label("Please enter a valid level 0-19");
		HBox playerInputBox = new HBox(20);
		
		// Text fields for user input
		TextField playerName = new TextField("PlayerName");
		TextField levelWanted = new TextField("0");
		playerInputBox.getChildren().addAll(playerName, levelWanted);
		playerInputBox.setAlignment(Pos.CENTER);
		
		// Button to start game
		Button confirmation = new Button("Confirm");
		confirmation.setMinSize(200, 50);
		inputScreen.getChildren().addAll(info, playerInputBox, confirmation);
		
		// The scene for entering player name and starting level
		Scene playerInputScene = new Scene(inputScreen, 350, 200);
		errorMessage.setVisible(false);
		inputScreen.getChildren().add(errorMessage);
		errorMessage.setAlignment(Pos.CENTER);
		
		playBGM(); // Plays the background music
		
		// Confirms the user inputs and starts the game based on the input
		confirmation.setOnAction(e -> {
			// If the level is valid (0 - 19), start the game, otherwise display an error message
			if 	(Integer.valueOf(levelWanted.getText()) < 20 && Integer.valueOf(levelWanted.getText()) > -1) {
				PLAYERNAME = playerName.getText();
				level = Integer.valueOf(levelWanted.getText());
				levelLabel.setText(String.valueOf("Level: " + level));
				restart.setVisible(false); // Sets the visibility of the restart button to false
				stage.setScene(game);
				// Spawns the initial shapes on the board
				nextShape = spawnShape();
				screen.getChildren().addAll(block.r1, block.r2, block.r3, block.r4);
				screen.getChildren().addAll(nextShape.r1, nextShape.r2, nextShape.r3, nextShape.r4);
				changeSpeed(); // Starts the timer task
			}
			else {
				errorMessage.setVisible(true);
			}
		});

		// Restarts the game
		restart.setOnAction(e -> {
			stage.setScene(playerInputScene);
			tetrisBoard.clearBoard();
			score = 0;
			linesCleared = 0;
			droughtCounter = 0;
			scoreLabel.setText("Score: " + score);
			lineLabel.setText("Lines: " + linesCleared);
			droughtCount.setText("Longbar drought:"+ droughtCounter);
			clearGraphicalBoard();
		});
		
		// This allows the user to move/rotate the current block
		game.setOnKeyPressed(e -> {
			// If down arrow is pressed, try to move the current block down
			if (e.getCode() == KeyCode.DOWN && pause == false) {
				/* If the current block reaches the last available row, drop the piece, cancel the timer, and call the timer again
				   Otherwise move the current block down by 1 square and add score by 1 */
				if (cannotMoveDown(block)) {
					tetrisBoard.fillCell((int) block.r1.getY() / SIZE, (int) block.r1.getX() / SIZE);
					tetrisBoard.fillCell((int) block.r2.getY() / SIZE, (int) block.r2.getX() / SIZE);
					tetrisBoard.fillCell((int) block.r3.getY() / SIZE, (int) block.r3.getX() / SIZE);
					tetrisBoard.fillCell((int) block.r4.getY() / SIZE, (int) block.r4.getX() / SIZE);
					control.resetRotation();
					// Cancels the timer and calls again once the block reaches the bottom
					time.cancel();
					changeSpeed();
				} else {
					control.moveDown(block);
					score += 1;
					scoreLabel.setText("Score: " + score);
				}
			}
			// If left arrow is pressed, try to move the current block left
			else if (e.getCode() == KeyCode.LEFT && pause == false) {
				// If the current block can move left, move it left by 1 square
				if (!canMoveLeft(block)) {
					control.moveLeft(block);
				}
			}
			// If the right arrow is pressed, try to move the current block right
			else if (e.getCode() == KeyCode.RIGHT && pause == false) {
				// If the current block can move right, move it right by 1 square
				if (!canMoveRight(block)) {
					control.moveRight(block);
				}
			}
			// If the space bar is pressed, move the current block all the way to the last available row
			else if (e.getCode() == KeyCode.SPACE && pause == false) {
				// If the current block can move down, keep moving it down by 1
				while (!cannotMoveDown(block)) {
					control.moveDown(block);
					score += 2;
					scoreLabel.setText("Score: " + score);
				}
				tetrisBoard.fillCell((int) block.r1.getY() / SIZE, (int) block.r1.getX() / SIZE);
				tetrisBoard.fillCell((int) block.r2.getY() / SIZE, (int) block.r2.getX() / SIZE);
				tetrisBoard.fillCell((int) block.r3.getY() / SIZE, (int) block.r3.getX() / SIZE);
				tetrisBoard.fillCell((int) block.r4.getY() / SIZE, (int) block.r4.getX() / SIZE);
				control.resetRotation();
				// Cancel the timer and call it again once the block reaches the bottom
				time.cancel();
				changeSpeed();
			}
			// If the "Z" key is pressed, try to rotate the current block left
			else if (e.getCode() == KeyCode.Z && pause == false) {
				// If the block can rotate left, rotate it left
				if (isRotate(block)) {
					control.rotateLeft(block);
				}
			}
			// If the "S" key is pressed, try to rotate the current block right
			else if (e.getCode() == KeyCode.X && pause == false) {
				// If the block can rotate right, rotate it left
				if (isRotate(block)) {
					control.rotateRight(block);
				}
			}
			else if (e.getCode() == KeyCode.P) {
				if (pause == false) {
					time.cancel(); // Cancel the timer
					pause = true;
					pauseGame(pause); // Displays the pause screen
				}
				else {
					changeSpeed(); // Calls the timer
					pause = false;
					pauseGame(pause); // Displays the pause Sscreen
				}
			}
		});
		
		// Sets the initial scene to the player input scene
		stage.setScene(playerInputScene);
		stage.setTitle("RUSSIAN BLOCK GAME");
		stage.show();
	}
	
	/**
	 * Creates the display to note that the game is paused
	 * @param pause if the game is paused or not
	 */
	private void pauseGame(boolean pause) {
		// If the game is pause, display the pause screen
		if (pause) {
			// Shape of the pause screen
			Rectangle endPanel = new Rectangle();
			endPanel.setHeight(125);
			endPanel.setWidth(350);
			endPanel.setArcHeight(35);
			endPanel.setArcWidth(35);

			// Contents in the pause screen
			Label pauseText = new Label("Paused");
			Label pauseText2 = new Label("Press P to continue");
			Font font = new Font("Helvetica", 50);
			Font continueFont = new Font("Helvetica", 25);
			pauseText.setTextFill(Color.GREEN);
			pauseText.setFont(font);
			pauseText2.setFont(continueFont);
			VBox pauseScreen = new VBox(20);
			pauseScreen.setMaxSize(350, 200);

			// Sets the properties of the pause screen
			pauseScreen.setShape(endPanel);
			pauseScreen.setStyle("-fx-background-color: #ECD384");
			pauseScreen.getChildren().addAll(pauseText, pauseText2);
			pauseScreen.setAlignment(Pos.CENTER);
			gameScreen.getChildren().add(pauseScreen);
		}
		// If the game is not paused, remove the pause screen from the game screen
		if (!pause) {
			gameScreen.getChildren().remove(1);
		}
	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	/**
	 * Changes the auto drop speed based on the level
	 */
	public static void changeSpeed() {
		time = new Timer(); // Creates a new timer
		
		// Assigns a drop speed based on the current level
		if (level <= 8) {
			fallSpeed = 1000 - 100 * level;
		}
		else if (level == 9) {
			fallSpeed = 148;
		}
		else if (level <= 12) {
			fallSpeed = 107;
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
		// Creates a new schedule every time its scheduled
		time.schedule(new TimerTask() {
			public void run() {
				// If the player tops out (first row is filled), end the game
				if (tetrisBoard.isTopOut()) {
					Platform.runLater(() -> {
						// Sets the highscore
						if (score > highScore) {
							highScore = score;
							highScoreLabel.setText("Highscore: " + highScore);
						}
						// Creates an alert object displaying the final score of the game
						Alert gameOverPage = new Alert(AlertType.INFORMATION);
						restart.setVisible(true);
						gameOverPage.setHeaderText("Game Over");
						gameOverPage.setContentText(PLAYERNAME + ". Your score: " + score);
						gameOverPage.showAndWait();
					});
					time.cancel(); // Cancels the timer
				}
				else {
					// Otherwise, if the block cannot move down, place the block when it reaches the bottom
					if (cannotMoveDown(block)) {
						// (For special cases) if a rectangle reaches the top and is to be placed, do not place the topmost block on the board
						if (block.r1.getY() < 0) {
							tetrisBoard.fillCell((int) block.r2.getY() / SIZE, (int) block.r2.getX() / SIZE);
							tetrisBoard.fillCell((int) block.r3.getY() / SIZE, (int) block.r3.getX() / SIZE);
							tetrisBoard.fillCell((int) block.r4.getY() / SIZE, (int) block.r4.getX() / SIZE);
						}
						// Otherwise place the whole block on the board
						else {
							tetrisBoard.fillCell((int) block.r1.getY() / SIZE, (int) block.r1.getX() / SIZE);
							tetrisBoard.fillCell((int) block.r2.getY() / SIZE, (int) block.r2.getX() / SIZE);
							tetrisBoard.fillCell((int) block.r3.getY() / SIZE, (int) block.r3.getX() / SIZE);
							tetrisBoard.fillCell((int) block.r4.getY() / SIZE, (int) block.r4.getX() / SIZE);
						}
						control.resetRotation(); // Resets the rotation number
						Platform.runLater(() -> deleteLines()); // Deletes a line, if possible
						go = false; // Sets "go" to false
						// tetrisBoard.displayBoard(); (This was used to check if the graphical board matches with the text board)
					}
					// If the block can move down, move the block down by 1 square
					else {
						if (go) {
							control.moveDown(block);
						}
						else {
							go = true;
						}
					}
				}
			}
		} , 0, fallSpeed);
	}
	
	/**
	 * Spawns a random shape and adds it to the next box area, adds droughtCounter by 1 for every piece other than "I" piece
	 * @return returns a random shape object
	 */
	public static Shape spawnShape() {

		// Selects a random number from 1 to 7
		Random rand = new Random();
		int shapeType = rand.nextInt(7);

		// The four rectangles for each block
		Rectangle a = new Rectangle(SIZE - 1, SIZE - 1);
		Rectangle b = new Rectangle(SIZE - 1, SIZE - 1);
		Rectangle c = new Rectangle(SIZE - 1, SIZE - 1);
		Rectangle d = new Rectangle(SIZE - 1, SIZE - 1);

		Shape block = null; // Initialized the block with "null"

		/*
		 *  Assigns the shape type based on "shapeType" value
		 *  Adds drought counter by one if the block is not "I", otherwise reset the counter
		 */
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
			block.setLocation(block.r4, 11 * SIZE, 8 * SIZE);
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

	/**
	 * Spawns the shape in the next box to the game board
	 * @param block the block to spawn from the next box
	 * @return returns the block in the next box
	 */
	public static Shape spawnShapeOnBoard(Shape block) {

		String shapeType = block.getShapeType();
		Shape nextShape = block;

		// Sets the location for different block types
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
			block.setLocation(block.r1, 5 * SIZE, 0 * SIZE);
			block.setLocation(block.r2, 4 * SIZE, 0 * SIZE);
			block.setLocation(block.r3, 4 * SIZE, 1 * SIZE);
			block.setLocation(block.r4, 3 * SIZE, 1 * SIZE);
		}
		return nextShape;
	}

	/*
	 * The algorithm that check through each row to see if it is filled up by blocks
	 * If a line is completely filled, it will be deleted from the screen and the cellstate will be set back to 0
	 * This Method also moves each line down every time a line is cleared
	 */
	public static void deleteLines() {
		time.cancel(); // Cancels the timer
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
		/*
		 *  Sets the scoring based on the current level
		 *  Scoring system retrieved from https://tetris.fandom.com/wiki/Scoring
		 */
		if (linesFilled.size() == 1) {
			score += 40 * (level + 1);
		} else if (linesFilled.size() == 2) {
			score += 100 * (level + 1);
		} else if (linesFilled.size() == 3) {
			score += 300 * (level + 1);
		} else if (linesFilled.size() == 4) {
			score += 1200 * (level + 1);
		}
		linesCleared += linesFilled.size(); // Adds lines cleared by the amount of objects in linesFilled
		
		/*
		 *  If the number of lines cleared divided by 10 is greater than the current level number
		 *  Set the level to the quotient of the lines cleared by a factor of 10
		 */
		if (linesCleared / 10 > level) {
			level = (linesCleared / 10);
		}
		
		// Changes the level, line, and score label
		levelLabel.setText("Level: " + (level));
		lineLabel.setText("Lines: " + linesCleared);
		scoreLabel.setText("Score: " + score);
		
		// TODO
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
		
		control.resetRotation(); // Resets the rotation number
		
		// Spawns the shape in the next box onto the board and spawns a new shape in the next box
		block = spawnShapeOnBoard(nextShape);
		droughtCount.setText("Longbar drought: " + droughtCounter);
		nextShape = spawnShape();
		screen.getChildren().addAll(nextShape.r1, nextShape.r2, nextShape.r3, nextShape.r4);
		
		changeSpeed(); // Restarts the timer
	}

	/**
	 * Clears the graphical game board
	 */
	public static void clearGraphicalBoard() {
		ArrayList<Rectangle> blocks = new ArrayList<Rectangle>();
		
		// Checks every node in the screen, if a node is a rectangle, add that node to "blocks"
		for (Node block : screen.getChildren()) {
			if (block instanceof Rectangle) {
				blocks.add((Rectangle) block);
			}
		}
		// Removes each block in blocks
		for (Node block : blocks) {
			screen.getChildren().remove(block);
		}
	}
	
	/**
	 * Checks if the current block is at the left edge of the screen, if it is, the block cannot move left
	 * The block cannot move left also when it is beside another block
	 * @param block the current block
	 * @return returns true if the block is at the left edge, otherwise false
	 */
	public boolean canMoveLeft(Shape block) {
		if ((block.r1.getX() - SIZE) >= 0 && (block.r2.getX() - SIZE) >= 0 && (block.r3.getX() - SIZE) >= 0
				&& (block.r4.getX() - SIZE) >= 0 && !tetrisBoard.checkLeft(block)) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if the current block is at the right edge of the screen, if it is, the block cannot move right
	 * The block cannot move right also when it is beside another block
	 * @param block the current block
	 * @return returns true if the block is at the right edge, otherwise false
	 */
	public boolean canMoveRight(Shape block) {
		if ((block.r1.getX() + SIZE) < WIDTH && (block.r2.getX() + SIZE) < WIDTH && (block.r3.getX() + SIZE) < WIDTH
				&& (block.r4.getX() + SIZE) < WIDTH && !tetrisBoard.checkRight(block)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if the current block is at the bottom edge of the screen, if it is, the block cannot move down
	 * The block cannot move down also when it is beside another block
	 * @param block the current block
	 * @return returns true if the block is at the bottom edge (meaning it cannot mode down), otherwise false
	 */
	public static boolean cannotMoveDown(Shape block) {
		if ((block.r1.getY() + SIZE) < HEIGHT && (block.r2.getY() + SIZE) < HEIGHT && (block.r3.getY() + SIZE) < HEIGHT
				&& (block.r4.getY() + SIZE) < HEIGHT && !tetrisBoard.checkDown(block)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if the current block can rotate based on its position
	 * If the rotation point (block.r2) is at any edge (except top edge), the block cannot rotate
	 * In addition, if the rotation point is beside an another block, the block cannot rotate (shown in checkRotationPoint())
	 * @param block the current block
	 * @return returns true if the block can rotate, otherwise false
	 */
	public static boolean isRotate(Shape block) {
		if (block.getShapeType().equals("I")) {
			return ((block.r2.getX() - SIZE) >= 0 && (block.r2.getX() + SIZE) < WIDTH && (block.r2.getY() + SIZE) < HEIGHT
					&& (block.r2.getX() - SIZE * 2) >= 0 && (block.r2.getY() + SIZE * 2) < HEIGHT && !tetrisBoard.checkRotationPoint(block));
		}
		else {
			return ((block.r2.getX() - SIZE) >= 0 && (block.r2.getX() + SIZE) < WIDTH && (block.r2.getY() + SIZE) < HEIGHT
					&& !tetrisBoard.checkRotationPoint(block));
		}
	}
	
	/**
	 * Plays the background music
	 */
	public static void playBGM() {
		String path = "Tetris.wav";
		File music = new File(path);
		
		try {
			// If the file exists in the program, play the music, otherwise display an error
			if (music.exists()) {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(music);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.loop(clip.LOOP_CONTINUOUSLY);
				System.out.println("PLAYING BGM");
			}
			else {
				System.out.println("File not found");
			}
		} catch (Exception ex) {
		}
	}
 }