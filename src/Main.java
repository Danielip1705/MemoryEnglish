import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

		/**
		 * Scanner activate
		 */
		static Scanner sc = new Scanner(System.in);

		public static void main(String[] args) {

			// Game board displayed on the screen
			int[][] boardGame;
			// Difficulty selected by the user
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
					// Ask for the first row
					firstRow = askNumeber("Choose a row");
					// Ask for the first column
					firstColumn = askNumeber("Choose a column");

					// If the chosen position corresponds to a number other than 0
					if (boardGame[firstRow][firstColumn] != 0) {
						// It means that position is already uncovered, it will indicate it
						System.out.println("This position has already been discovered");
					}

					// While the indicated position is not different from 0, it will ask for the row and the column again
				} while (boardGame[firstRow][firstColumn] != 0);

				// Once the correct values are chosen, uncover the hidden number
				Memory.discoverBoxBoard(firstRow, firstColumn);

				// Show the game board again with the number just uncovered
				Memory.showBoardGame();

				// Show a message indicating the chosen option
				System.out.println("ROW " + firstRow + ", COLUMN " + firstColumn + " = "
						+ boardGame[firstRow][firstColumn]);

				do {
					// Ask for the second row
					secondRow = askNumeber("Choose a row");
					// Ask for the first column
					secondColumn = askNumeber("Choose a column");

					// If the chosen position corresponds to a number other than 0
					if (boardGame[secondRow][secondColumn] != 0) {
						// It means that position is already uncovered, it will indicate it
						System.out.println("This position has already been discovered");
					}

					// While the indicated position is not different from 0, it will ask for the row and the column again
				} while (boardGame[secondRow][secondColumn] != 0);

				// Once the correct values are chosen, uncover the hidden number
				Memory.discoverBoxBoard(secondRow, secondColumn);

				// Show the game board again with the number just uncovered
				Memory.showBoardGame();

				// Show a message indicating the chosen option
				System.out.println("ROW " + secondRow + ", COLUMN " + secondColumn + " = "
						+ boardGame[secondRow][secondColumn]);

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
		 * Method to request a number from the user
		 * 
		 * @param petition Request message
		 * @return Returns the value of the requested number
		 */
		private static int askNumeber(String petition) {

			// Number to be returned by the function
			int number = -1;

			do {
				try {
					// Request message
					System.out.println(petition);
					// Assign the number entered by the user
					number = sc.nextInt();
					// Assign the number entered by the user
				} catch (InputMismatchException e) {
					// Display this message
					System.out.println("Only integers numbers are allowed");
				} finally {
					// Always clear the buffer
					sc.nextLine();
				}
				// While the number is not within the possible indices of the board, the instructions of the do will be repeated
			} while (number < 0 || number >= Memory.getBoard());

			// Return the assigned number
			return number;
		}

}
