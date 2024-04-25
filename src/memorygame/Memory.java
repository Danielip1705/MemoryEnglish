package memorygame;

import java.util.Random;

public class Memory {
	/**
	 * Size of the board
	 */
	private static int sizeBoard;

	/**
	 * Array that represents the solution of the game
	 */
	private static int[][] solution;

	/**
	 * Array that represents the board that's shown in the game
	 */
	private static int[][] gameBoard;

	/**
	 * Function that return the value of field sizeBoard
	 * 
	 * @return Value of sizeBoard
	 */
	public static int getBoard() {
		return sizeBoard;
	}

	/**
	 * Function that return the value of field gameBoard
	 * 
	 * @return Value of gameBoard
	 */
	public static int[][] getGameBoard() {
		return gameBoard;
	}

	/**
	 * Function thats initializes all the fields, assigning the corresponding values
	 * according to the chosen difficulty
	 * 
	 * @param difficulty Difficulty chosen by the User
	 */
	public static void initialize(String difficulty) {

		// According to the difficulty's value
		switch (difficulty) {
		// If the value is "easy"
		case "easy":
			// The size of the board will be 4
			sizeBoard = 4;
			break;
		// If the value is "medium"
		case "medium":
			// The size of the board will be 6
			sizeBoard = 6;
			break;
		// If the value is "hard"
		case "hard":
			// The size of the board will be 8
			sizeBoard = 8;
			break;

		}

		// Initializes the two arrays with the size of the assigned board
		solution = new int[sizeBoard][sizeBoard];
		gameBoard = new int[sizeBoard][sizeBoard];

	}

	/**
	 * Function that modifies the field "solution", generating the couple of numbers
	 * in random positions, this will be not visible for the player
	 */
	public static void generateSolution() {

		// Creates an object with the class random
		Random rand = new Random();

		// Number that will occupy the place on the board, initialized in 1
		int numFill = 1;

		// Corresponding position to the row initialized randomly
		int row;
		// Corresponding position to the column initialized randomly
		int column;

		// Auxiliary value to do the exchanges
		int aux;

		// FILL THE ARRAY WITH THE SOLUTION
		// Go through each row to the array "solution"
		for (int i = 0; i < solution.length; i++) {
			// Go through each column to the array "solution"
			for (int j = 0; j < solution[i].length; j++) {

				// If the number to fill exceeds the maximum limit
				if (numFill > Math.pow(sizeBoard, 2) / 2) {
					// It is assigned 1
					numFill = 1;
				}

				// Assign the number to the corresponding position
				solution[i][j] = numFill;
				// Increment numFill in 1
				numFill++;
			}
		}

		// SHUFFLE THE TABLE
		// Go through each row to the array "solution"
		for (int i = 0; i < solution.length; i++) {
			// Go through each column to the array "solution"
			for (int j = 0; j < solution[i].length; j++) {
				// Assign to row a random number between 0 and the length of the row
				row = rand.nextInt(0, solution.length);
				// Assign to column a random number between 0 and the length of the column
				column = rand.nextInt(0, solution[i].length);

				// Assign the auxiliary value as a copy of the number to the corresponding position
				aux = solution[i][j];
				// Swap the values of the position we are in and the randomly generated position
				solution[i][j] = solution[row][column];
				solution[row][column] = aux;

			}
		}

	}

	/**
	 * Function showing the game board
	 */
	public static void showBoardGame() {
		System.out.println("\t\tCOLUMN");
		System.out.print("\t\t");

		// Numbers representing column positions
		for (int j = 0; j < gameBoard[0].length; j++) {
			System.out.print(j + "\t");
		}

		System.out.println();

		// Dashed line depending on the size of the board
		switch (sizeBoard) {
		case 4:
			System.out.println("\t\t-------------------------");
			break;
		case 6:
			System.out.println("\t\t-----------------------------------------");
			break;
		case 8:
			System.out.println("\t\t---------------------------------------------------------");
			break;
		}

		// SHOW THE GAME BOARD
		// Go through each row to the array "gameBoard"
		for (int i = 0; i < gameBoard.length; i++) {
			// If it is in the first row, it shows the word "ROW"
			System.out.print((i == 0 ? "ROW" : "") + "\t");
			// Go through each column to the array "gameBoard"
			for (int j = 0; j < gameBoard[i].length; j++) {
				// If it is in the first column
				if (j == 0) {
					// Before, it show the row number it is in
					System.out.print(i + "|\t");
				}
				// Show the number only if it is different from 0, otherwise it shows a square ("\u25A0")
				System.out.print((gameBoard[i][j] != 0 ? gameBoard[i][j] : "\u25A0") + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * Function that modifies the game board, showing the position marked in the
	 * parameter
	 *
	 * @param position Position of the table that will be visible
	 */
	public static void discoverBoxBoard(int row, int column) {

		// The element of the game board will be equal to the element that is in the //
		// same position in the solution table.
		gameBoard[row][column] = solution[row][column];

	}

	/**
	 * Function that indicates if the pair of numbers that we have discovered are
	 * equal. If they are not, it "hides" them again by assigning them 0.
	 * 
	 * @param firstRow     First number row
	 * @param firstColumn  First number column
	 * @param secondRow    Second number row
	 * @param secondColumn Second number column
	 * @return True or false depending on whether the pair of numbers are equal or
	 *         not
	 */
	public static boolean checkCouple(int firstRow, int firstColumn, int secondRow, int secondColumn) {
		// Variable that indicates if the numbers are equal
		boolean equals = true;

		// If both discovered numbers are different
		if (gameBoard[firstRow][firstColumn] != gameBoard[secondRow][secondColumn]) {
			// Assigning 0 to both numbers
			gameBoard[firstRow][firstColumn] = 0;
			gameBoard[secondRow][secondColumn] = 0;
			// And the boolean as false
			equals = false;
		}

		// Will return the value of the boolean
		return equals;

	}

	/**
	 * Function that indicates if we have finished the game, it is determined by
	 * looking for a 0 on the game board
	 * 
	 * @return True or false depending on whether the game is over or not
	 */
	public static boolean endGame() {
		// Variable that determines whether the game is over, for now true
		boolean gameOver = true;

		// Number to find on the board: 0
		int findNum = 0;

		// Row counter
		int i = 0;
		// As long as the row counter is less than the number of rows on the game board
		// and the boolean is true
		while (i < gameBoard.length && gameOver) {
			// Row counter
			int j = 0;
			// As long as the column counter is less than the number of columns on the
			// Game board and the boolean is true
			while (j < gameBoard[i].length && gameOver) {
				// If the number we are looking for is present on the Gameboard
				if (gameBoard[i][j] == findNum) {
					// We assign the boolean as false
					gameOver = false;
				}
				// We increase the column counter
				j++;
			}
			// We increase the row counter
			i++;
		}

		// return the value of juegoTerminado
		return gameOver;

	}
}
