import java.util.Random;

public class Memory {
	/**
	 * Size of the board
	 */
	private static int board;

	/**
	 * Board with the complete solution
	 */
	private static int[][] solution;

	/**
	 * board thats shown in the game
	 */
	private static int[][] gameBoard;

	/**
	 * Fuction that return the atribute with the size of the board
	 * 
	 * @return value with the size of the board
	 */
	public static int getBoard() {
		return board;
	}

//	public static void setTamTablero(int tamTablero) {
//		Memory.tamTablero = tamTablero;
//	}

	/**
	 * Fuction thats return the value of the atribute called gameBoard
	 * 
	 * @return value of the gameBoard
	 */
	public static int[][] getGameBoard() {
		return gameBoard;
	}

	/**
	 * Function thats initialises all the fields, assigning the corresponding
	 * values according to the chosen difficulty
	 * 
	 * @param difficulty Difficulty chosen by the User
	 */
	public static void initialize(String difficulty) {
		// According to the Difficulty's value
		switch (difficulty) {
		// if the value is "easy"
		case "easy":
			// The size of the board will be 4
			board = 4;
			break;
		// if the value is "medium"
		case "medium":
			// The size of the board will be 6
			board = 6;
			break;
		// if the value is "hard"
		case "hard":
			// The size of the board will be 8
			board = 8;
			break;

		}

		// Inicialises the two boards with the size of the assigned board
		solution = new int[board][board];
		gameBoard = new int[board][board];

	}

	/**
	 * Function that modifies the atribute "solution", generating the couple of
	 * numbers in random positions, but it will not visible
	 */
	public static void generateSolution() {

		// Creates an object with the class random
		Random rand = new Random();

		// Number that will occupy the place on the board, inicializate in 1
		int numFill = 1;

		// Corresponding position to the row inicializated randomly
		int row;
		// Corresponding position to the column inicializated aleatory
		int column;

		// auxiliary value  to do the exchanges
		int aux;

		// we fill the solution
		// We go through each row to the board solucion
		for (int i = 0; i < solution.length; i++) {
			// We go through each column to the board solucion
			for (int j = 0; j < solution[i].length; j++) {


				// If the number to fill exceeds the maximum limit
				if (numFill > Math.pow(board, 2) / 2) {
					// It is assigned 1
					numFill = 1;
				}

				// Assign the number to the corresponding position
				solution[i][j] = numFill;
				// Increment the number to fill
				numFill++;
			}
		}



		// Shuffle the table
		// Go through each row of the solution board
		for (int i = 0; i < solution.length; i++) {
			// Go through each column of the solution board
			for (int j = 0; j < solution[i].length; j++) {
				// Assign to row a random number between 0 and the length of the row
				row = rand.nextInt(0, solution.length);
				// Assign to column a random number between 0 and the length of the column
				column = rand.nextInt(0, solution[i].length);

			    // Assign aux as a copy of the element of the table we are in
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
		switch (board) {
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

		// We show the game board
		// For loop that loops through each row
		for (int i = 0; i < gameBoard.length; i++) {
			// If we are in the first row, it shows the word ROW
			System.out.print((i == 0 ? "ROW" : "") + "\t");
			// For loop that loops through each column
			for (int j = 0; j < gameBoard[i].length; j++) {
				// If we are in the first column
				if (j == 0) {
					// Before showing the row number we are in
					System.out.print(i + "|\t");
				}
				// Shows the number only if it is different from 0, otherwise it shows a square
				System.out.print((gameBoard[i][j] != 0 ? gameBoard[i][j] : "\u25A0") + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

//	public static boolean numDescubierto(int fila, int columna) {
//
//		int[][] tableroDejuego = tableroDeJuego;
//
//		boolean descubierto = false;
//		
//		if (fila >= 0 && columna >= 0 && tableroDejuego[fila][columna] != 0) {
//			descubierto = true;
//		}
//		return descubierto;
//	}

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
	 * @param firstRow    First number row
	 * @param firstColumn First number column
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
