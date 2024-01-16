import java.util.Random;
import java.util.Scanner;

public class Tic_Tac_Toe {

	static char play1, play2; // character static variables
	static String n1, n2; // string static variables
	static int win1 = 0, win2 = 0, win = 1;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Scanner d = new Scanner(System.in);

		char[][] gameBoard = { { ' ', '|', ' ', '|', ' ' }, // making the gameboard using a 2d character array
				{ '-', '+', '-', '+', '-' },
				{ ' ', '|', ' ', '|', ' ' },
				{ '-', '+', '-', '+', '-' },
				{ ' ', '|', ' ', '|', ' ' } };

		System.out.println();
		System.out.println();
		System.out.println("--------------------WELCOME TO THE ULTIMATE GAME OF TIC-TAC-TOE!--------------------");
		System.out.println();
		System.out.println();

		System.out.print("Enter 1 to play with mighty computer and 2 for a multiplayer game : "); // giving user choice
																									// to play with
																									// computer or
																									// multiplayer
		int n = sc.nextInt();

		switch (n) {

			case 1:
				comComplete(gameBoard); // comComplete function for player v/s computer
				break;
			case 2:
				System.out.println();
				System.out.print("Please enter your name player 1 : "); // taking names of users as input
				n1 = d.nextLine();
				System.out.print("Please enter your name player 2 : ");
				n2 = d.nextLine();
				System.out.println();
				System.out.print("Enter any symbol to play with for " + n1 + " : "); // taking characters or digits or
																						// anything user wanna play the
																						// game with
				play1 = sc.next().charAt(0);
				System.out.print("Enter any symbol to play with for " + n2 + " : ");
				play2 = sc.next().charAt(0);
				System.out.println();
				multiComplete(gameBoard); // multiComplete function for player v/s player
		}
		sc.close();
		d.close();

	}

	// player v/s computer algorithms!

	private static void printGameBoard(char[][] gameBoard) {
		for (char[] row : gameBoard) {
			for (char c : row) { // printing gameboard using for each loop
				System.out.print(c);

			}
			System.out.println();
		}

	}

	private static void playerTurn(char gameBoard[][]) {

		int pos;

		while (true) {
			Scanner scan = new Scanner(System.in);

			System.out.print("Enter your position (1-9) : "); // taking player input in a loop to take continuous inputs
			pos = scan.nextInt();

			if (isValidMove(gameBoard, pos)) { // checking whether the user entered move is valid or not using a
												// function
				break;
			} else {
				System.out.println("Its an invalid position please try again!");
			}
		}
		placePiece(gameBoard, pos, "player"); // function to place the given move at user given spot

	}

	private static void computerTurn(char gameBoard[][]) {
		int cpos;

		while (true) { // making the input process in a while loop to make the computer play
						// continuously

			Random rand = new Random(); // using a random function to make computer play
			cpos = rand.nextInt(9) + 1; // defining random function

			if (isValidMove(gameBoard, cpos)) { // checking if the computer has entered a valid move or not using a
												// function
				break;
			}
		}
		System.out.println("Computer played : " + cpos);
		placePiece(gameBoard, cpos, "cpu"); // placing the valid move on board

	}

	private static boolean isValidMove(char gameBoard[][], int position) {

		switch (position) {
			case 1:
				return (gameBoard[0][0] == ' '); // checking if the positions of boards are empty or not by returning
													// true or false
			case 2:
				return (gameBoard[0][2] == ' ');
			case 3:
				return (gameBoard[0][4] == ' ');
			case 4:
				return (gameBoard[2][0] == ' ');
			case 5:
				return (gameBoard[2][2] == ' ');
			case 6:
				return (gameBoard[2][4] == ' ');
			case 7:
				return (gameBoard[4][0] == ' ');
			case 8:
				return (gameBoard[4][2] == ' ');
			case 9:
				return (gameBoard[4][4] == ' ');
			default:
				return false;
		}

	}

	private static void placePiece(char[][] gameBoard, int pos, String user) {

		char symbol;
		if (user.equals("player")) {
			symbol = 'X'; // giving user the default sign of X and computer the sign of O
		} else {
			symbol = 'O';
		}

		switch (pos) {
			case 1:
				gameBoard[0][0] = symbol; // getting the position on board using switch case
				break;

			case 2:
				gameBoard[0][2] = symbol;
				break;
			case 3:
				gameBoard[0][4] = symbol;
				break;
			case 4:
				gameBoard[2][0] = symbol;
				break;
			case 5:
				gameBoard[2][2] = symbol;
				break;
			case 6:
				gameBoard[2][4] = symbol;
				break;
			case 7:
				gameBoard[4][0] = symbol;
				break;
			case 8:
				gameBoard[4][2] = symbol;
				break;
			case 9:
				gameBoard[4][4] = symbol;
				break;
			default:
				break;
		}
	}

	private static void checkRowWin(char gameBoard[][]) {

		for (int i = 0; i <= 4; i += 2) {
			if (gameBoard[0][i] != ' ' && gameBoard[0][i] == 'X' && gameBoard[2][i] == 'X' && gameBoard[4][i] == 'X') { // checking
																														// the
																														// if
																														// any
																														// of
																														// the
																														// rows
																														// of
																														// board
																														// has
																														// a
																														// winning
																														// pattern
				printGameBoard(gameBoard);
				System.out.println("Congratulations! You win :)");
				System.out.println("Your total wins are : " + win);
				win++;
				playAgain(gameBoard);
			} else if (gameBoard[0][i] != ' ' && gameBoard[0][i] == 'O' && gameBoard[2][i] == 'O'
					&& gameBoard[4][i] == 'O') { // same for the computer

				System.out.println("It's a bad luck! Try again the mighty Computer wins :(");
				playAgain(gameBoard);
			}
		}

	}

	private static boolean checkDraw(char gameBoard[][]) {

		for (int i = 0; i < gameBoard.length; i += 2) { // a boolean function to return true if board is empty anywhere
														// else we get a position of draw
			for (int j = 0; j < gameBoard[0].length; j += 2) {
				if (gameBoard[i][j] == ' ') {
					return false;
				}
			}
		}
		printGameBoard(gameBoard);
		System.out.println("The game ended in a draw! Better luck next time :)");
		return true;

	}

	private static void checkColWin(char gameBoard[][]) {

		for (int i = 0; i <= 4; i += 2) {
			if (gameBoard[i][0] != ' ' && gameBoard[i][0] == 'X' && gameBoard[i][2] == 'X' && gameBoard[i][4] == 'X') { // checking
																														// if
																														// any
																														// of
																														// the
																														// columns
																														// has
																														// winning
																														// pattern
																														// on
																														// board
				printGameBoard(gameBoard);
				System.out.println("Congratulations! You win :)");
				System.out.println("Your total wins are : " + win);
				win++;
				playAgain(gameBoard);
			} else if (gameBoard[i][0] == 'O' && gameBoard[i][2] == 'O' && gameBoard[i][4] == 'O') { // same for the
																										// computer

				System.out.println("It's a bad luck! Try again the mighty Computer wins :(");
				playAgain(gameBoard);
			}
		}

	}

	private static void checkDigWin(char gameBoard[][]) {

		if ((gameBoard[0][0] == 'X' && gameBoard[2][2] == 'X' && gameBoard[4][4] == 'X')
				|| (gameBoard[0][4] == 'X' && gameBoard[2][2] == 'X' && gameBoard[4][0] == 'X')) { // checking the
																									// diagonals for
																									// winning pattern
			printGameBoard(gameBoard);
			System.out.println("Congratulations! You win :)");
			System.out.println("Your total wins are : " + win);
			win++;
			playAgain(gameBoard);
		} else if ((gameBoard[0][0] == 'O' && gameBoard[2][2] == 'O' && gameBoard[4][4] == 'O')
				|| (gameBoard[0][4] == 'O' && gameBoard[2][2] == 'O' && gameBoard[4][0] == 'O')) { // doing same for the
																									// computer

			System.out.println("It's a bad luck! Try again the mighty Computer wins :(");
			playAgain(gameBoard);
		}
	}

	private static void comComplete(char gameBoard[][]) {

		while (true) { // keeping in a while loop makes it continuous
			System.out.println();
			playerTurn(gameBoard); // function for player to play

			checkColWin(gameBoard); // checking all possible win ways and draw ways everytime to make a precise
									// output

			checkDigWin(gameBoard);
			checkRowWin(gameBoard);
			if (checkDraw(gameBoard))
				break;

			computerTurn(gameBoard);
			System.out.println();
			if (checkDraw(gameBoard))
				break;
			printGameBoard(gameBoard);
			System.out.println();
			checkColWin(gameBoard);
			checkDigWin(gameBoard);
			checkRowWin(gameBoard);
			if (checkDraw(gameBoard))
				break;

		}

	}

	private static void playAgain(char gameBoard[][]) {

		Scanner sc = new Scanner(System.in);
		Scanner d = new Scanner(System.in);

		System.out.println();
		System.out.print("Wanna play again? (Y/N) : "); // asking user if they want to continue or not
		String s = sc.nextLine().toLowerCase();

		while (true) {
			if (s.equals("y")) { // case for continuation

				System.out.print("Enter 1 to play with mighty computer and 2 for a multiplayer game : "); // giving user
																											// choice to
																											// play with
																											// computer
																											// or
																											// multiplayer
				int n = sc.nextInt();

				for (int i = 0; i < gameBoard.length; i += 2) {
					for (int j = 0; j < gameBoard[0].length; j += 2) {
						gameBoard[i][j] = ' ';
					}
				}

				switch (n) {

					case 1:
						comComplete(gameBoard); // comComplete function for whole player v/s computer
						break;
					case 2:
						System.out.println();
						System.out.print("Please enter your name player 1 : "); // taking names of users as input
						n1 = d.nextLine();
						System.out.print("Please enter your name player 2 : ");
						n2 = d.nextLine();
						System.out.println();
						System.out.println(n1 + " your total wins are : " + win1);
						System.out.println(n2 + " your total wins are : " + win2);

						System.out.println();
						System.out.print("Enter any symbol to play with for " + n1 + " : "); // taking characters or
																								// digits or anything
																								// user wanna play the
																								// game with
						play1 = sc.next().charAt(0);
						System.out.print("Enter any symbol to play with for " + n2 + " : ");
						play2 = sc.next().charAt(0);
						System.out.println();
						multiComplete(gameBoard); // multiComplete function for whole player v/s player
				}

			} else if (s.equals("n")) { // case for non continuation
				System.out.println("Thank you for playing this game! :)");
				System.exit(0);
			} else { // invalid input case
				System.out.print("Invalid input. Please enter again : ");
				s = sc.nextLine().toLowerCase();
			}

		}
	}

	// player v/s player algorithms!

	private static void playerTurn1(char gameBoard[][]) {
		Scanner scan = new Scanner(System.in);
		int pos;

		while (true) {

			System.out.print("Enter your position " + n1 + " (1-9) : "); // taking input from user
			pos = scan.nextInt();

			if (isValidMove(gameBoard, pos)) { // checking if the move entered is valid or not
				break;
			} else {
				System.out.println("Its an invalid position please try again!");
			}
		}
		placeMove(gameBoard, pos, play1); // placing the valid move on board

	}

	private static void playerTurn2(char gameBoard[][]) {

		int pos;

		while (true) {
			Scanner scan = new Scanner(System.in);

			System.out.print("Enter your position " + n2 + " (1-9) : "); // taking user 2 input
			pos = scan.nextInt();

			if (isValidMove(gameBoard, pos)) { // checking if the entered move is valid or not using function
				break;
			} else {
				System.out.println("Its an invalid position please try again!");
			}
		}
		placeMove(gameBoard, pos, play2); // placing valid move on board using function

	}

	private static void checkRowWin1(char gameBoard[][]) {

		for (int i = 0; i <= 4; i += 2) {
			if (gameBoard[0][i] != ' ' && gameBoard[0][i] == play1 && gameBoard[2][i] == play1
					&& gameBoard[4][i] == play1) { // checking row wins possible for player 1
				System.out.println();
				System.out.println("Congratulations! " + n1 + " wins :)");
				win1++;
				playAgain(gameBoard);
			} else if (gameBoard[0][i] != ' ' && gameBoard[0][i] == play2 && gameBoard[2][i] == play2
					&& gameBoard[4][i] == play2) { // checking row wins possible for plyer 2
				System.out.println();
				System.out.println("Congratulations! " + n2 + " wins :)");
				win2++;
				playAgain(gameBoard);
			}
		}

	}

	private static void checkColWin1(char gameBoard[][]) {

		for (int i = 0; i <= 4; i += 2) {
			if (gameBoard[i][0] != ' ' && gameBoard[i][0] == play1 && gameBoard[i][2] == play1
					&& gameBoard[i][4] == play1) { // checking columns wins possible for player 1
				System.out.println();
				System.out.println("Congratulations! " + n1 + " wins :)");
				win1++;
				playAgain(gameBoard);
			} else if (gameBoard[i][0] == play2 && gameBoard[i][2] == play2 && gameBoard[i][4] == play2) { // checking
																											// column
																											// wins
																											// possible
																											// for
																											// player 2
				System.out.println();
				System.out.println("Congratulations! " + n2 + " wins :)");
				win2++;
				playAgain(gameBoard);
			}
		}

	}

	private static boolean checkDraw1(char gameBoard[][]) {

		for (int i = 0; i < gameBoard.length; i += 2) { // checking if the board is empty or else we get a draw position
			for (int j = 0; j < gameBoard[0].length; j += 2) {
				if (gameBoard[i][j] == ' ') {
					return false;
				}
			}
		}
		System.out.println("The game ended in a draw! Better luck next time :)");
		return true;

	}

	private static void checkDigWin1(char gameBoard[][]) {

		if ((gameBoard[0][0] == 'X' && gameBoard[2][2] == play1 && gameBoard[4][4] == play1)
				|| (gameBoard[0][4] == play1 && gameBoard[2][2] == play1 && gameBoard[4][0] == play1)) { // checking
																											// diagonal
																											// wins
																											// possible
																											// for
																											// player 1
			System.out.println();
			System.out.println("Congratulations! " + n1 + " wins :)");
			win1++;
			playAgain(gameBoard);
		} else if ((gameBoard[0][0] == play2 && gameBoard[2][2] == play2 && gameBoard[4][4] == play2)
				|| (gameBoard[0][4] == play2 && gameBoard[2][2] == play2 && gameBoard[4][0] == play2)) { // checking
																											// diagonal
																											// wins
																											// possible
																											// for
																											// player 2
			System.out.println();
			System.out.println("Congratulations! " + n2 + " wins :)");
			win2++;
			playAgain(gameBoard);
		}

	}

	private static void placeMove(char gameBoard[][], int pos, char playmove) {

		char symbol = playmove;

		switch (pos) {
			case 1:
				gameBoard[0][0] = symbol; // function to place a valid move at given position
				break;

			case 2:
				gameBoard[0][2] = symbol;
				break;
			case 3:
				gameBoard[0][4] = symbol;
				break;
			case 4:
				gameBoard[2][0] = symbol;
				break;
			case 5:
				gameBoard[2][2] = symbol;
				break;
			case 6:
				gameBoard[2][4] = symbol;
				break;
			case 7:
				gameBoard[4][0] = symbol;
				break;
			case 8:
				gameBoard[4][2] = symbol;
				break;
			case 9:
				gameBoard[4][4] = symbol;
				break;
			default:
				break;
		}

	}

	private static void multiComplete(char gameBoard[][]) {

		while (true) { // keeping everything in a while loop for making it continuous
			System.out.println();
			playerTurn1(gameBoard); // player 1 input method
			System.out.println();
			printGameBoard(gameBoard);
			checkColWin1(gameBoard); // checking all possible win ways and valid moves for player 1

			checkDigWin1(gameBoard);
			checkRowWin1(gameBoard);
			if (checkDraw1(gameBoard))
				break;
			System.out.println();
			playerTurn2(gameBoard); // player 2 input method
			System.out.println();
			if (checkDraw1(gameBoard))
				break;
			printGameBoard(gameBoard);
			System.out.println();
			checkColWin1(gameBoard); // checking all possible win ways and valid moves for player 2
			checkDigWin1(gameBoard);
			checkRowWin1(gameBoard);
			if (checkDraw1(gameBoard))
				break;

		}

	}

}
