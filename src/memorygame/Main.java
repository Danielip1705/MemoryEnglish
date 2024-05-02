package memorygame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	/**
	 * Scanner activated
	 */
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		// Game board displayed on the console
		int[][] boardGame;
		// Difficulty selected by the player
		String difficulty = "";
		// Variable that determines if a pair of numbers are equal
		boolean sameCouple;

		// First row selected by the player
		int firstRow;
		// First column selected by the player
		int firstColumn;
		// Second row selected by the player
		int secondRow;
		// Second column selected by the player
		int secondColumn;

		// Activates the function welcome thats shows the welcome and the rules
		welcome();

		// While the difficulty is not easy, medium, or hard
		while (!difficulty.toLowerCase().equals("easy") && !difficulty.toLowerCase().equals("medium")
				&& !difficulty.toLowerCase().equals("hard")) {
			// Ask the user for a difficulty
			System.out.println("Choose difficulty: easy, medium or hard.");
			// And assign it
			difficulty = sc.nextLine();
		}

		// Once the difficulty is selected, initialize the properties
		Memory.initialize(difficulty.toLowerCase());
		// Assign the values of the solution board property
		Memory.generateSolution();
		// Assign the value of the gameBoard
		boardGame = Memory.getGameBoard();

		// While the game is not over
		while (!Memory.endGame()) {

			// Press Enter to continue
			System.out.println("Press enter to continue");
			sc.nextLine();

			// Show the game board
			Memory.showBoardGame();

			do {
				// Ask for the row of the first number
				firstRow = askNumber("Choose a row");
				// Ask for the column of the first number
				firstColumn = askNumber("Choose a column");

				// If the chosen position corresponds to a number other than 0
				if (boardGame[firstRow][firstColumn] != 0) {
					// It means that position is already uncovered, it will indicate it
					System.out.println("This position has already been discovered");
				}

				// While the indicated position is not different from 0, it will ask for the row
				// and the column again
			} while (boardGame[firstRow][firstColumn] != 0);

			// Once the correct values are chosen, uncover the hidden number
			Memory.discoverBoxBoard(firstRow, firstColumn);

			// Show the game board again with the number just uncovered
			Memory.showBoardGame();

			// Show a message indicating the chosen option
			System.out
					.println("ROW " + firstRow + ", COLUMN " + firstColumn + " = " + boardGame[firstRow][firstColumn]);

			do {
				// Ask for the row of the second number
				secondRow = askNumber("Choose a row");
				// Ask for the column of the second number
				secondColumn = askNumber("Choose a column");

				// If the chosen position corresponds to a number other than 0
				if (boardGame[secondRow][secondColumn] != 0) {
					// It means that position is already uncovered, it will indicate it
					System.out.println("This position has already been discovered");
				}

				// While the indicated position is not different from 0, it will ask for the row
				// and the column again
			} while (boardGame[secondRow][secondColumn] != 0);

			// Once the correct values are chosen, uncover the hidden number
			Memory.discoverBoxBoard(secondRow, secondColumn);

			// Show the game board again with the number just uncovered
			Memory.showBoardGame();

			// Show a message indicating the chosen option
			System.out.println(
					"ROW " + secondRow + ", COLUMN " + secondColumn + " = " + boardGame[secondRow][secondColumn]);

			// Check the pair and assign true or false to equalPair
			sameCouple = Memory.checkCouple(firstRow, firstColumn, secondRow, secondColumn);
			// According to its value, it will indicate if they are equal or not
			System.out.println(sameCouple ? "Great, you've found a couple" : "Failure, they are different");

		}

		// End of game message
		System.out.println("CONGRATULATIONS, you have completed the game");

		// Close the Scanner
		sc.close();
	}

	/**
	 * Function that will show the rules of the game
	 */
	public static void welcome() {
		System.out.println("WELCOME TO MEMORY GAME!");
		System.out.println("---------------------------\n");
		System.out.println("RULES:");
		System.out.println("-----");
		System.out.println("\t-Find all the couples of numbers until complete the board game");
		System.out.println("\t-There are 3 levels of difficulty that determinate the size of the board");
		System.out.println("\t\tEasy: Size board 4x4");
		System.out.println("\t\tMedium: Size board 6x6");
		System.out.println("\t\tHard: Size board 8x8");
		System.out.println(
				"\t-Select the number of the row and later of the column to discover the number of the choose position");
		System.out.println("\t-If you discover two diferents numbers, they will be hidden again");
		System.out.println("\t-If they are equals, the numbers selecteds will always be displayed");
		System.out.println("\t-The game will be end when you discover all the couples of numbers");
		System.out.println("\t\tGOOD LUCK!\n");
	}

	/**
	 * Method to request a number from the user
	 * 
	 * @param petition Request message
	 * @return Returns the value of the requested number
	 */
	private static int askNumber(String petition) {

		// Number to be returned by the function
		int number = -1;

		do {
			try {
				// Request message
				System.out.println(petition);
				// Assign the number entered by the user
				number = sc.nextInt();
				// If an exception occurs
			} catch (InputMismatchException e) {
				// Display this message
				System.out.println("Only integers numbers are allowed");
			} finally {
				// Always clear the buffer
				sc.nextLine();
			}
			// While the number is not within the possible indices of the board, the
			// instructions of the do will be repeated
		} while (number < 0 || number >= Memory.getSizeBoard());

		// Return the assigned number
		return number;
	}

}
