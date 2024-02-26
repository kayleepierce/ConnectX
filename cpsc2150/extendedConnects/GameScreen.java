package cpsc2150.extendedConnects;
import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.GameBoardMem;
import cpsc2150.extendedConnectX.models.IGameBoard;
import java.util.Scanner;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
    Kaylee Pierce - kayleepierce
    AJ Hay - anthayjr
    Ashok Patel - ashokptl
    Ryan Nelson - Ryan_Nelson1
 */

public class GameScreen {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int numCols;

        do {
            int numPlayers = getNumPlayers(input);
            char[] playerTokens = new char[numPlayers];

            for (int i = 0; i < numPlayers; i++) {
                playerTokens[i] = playerCharacter(i + 1, playerTokens, input);
            }
            input.nextLine();

            int numRows = numOfPlayerRows(input);
            numCols = numOfPlayerColumns(input, null);
            int numToWin = numOfTokens(input);
            char gameType = typeOfGame(input);

            IGameBoard board = null;

            if (gameType == 'f') {
                board = new GameBoard(numRows, numCols, numToWin);
            } else if (gameType == 'm') {
                board = new GameBoardMem(numRows, numCols, numToWin);
            }

            boolean gameOver = false;
            int currentPlayer = 0;

            while (!gameOver) {
                System.out.println(board);

                int column;
                boolean validInput = false;

                while (!validInput) {
                    char token = playerTokens[currentPlayer];
                    System.out.println("Player " + token + ", what column would you like to place your marker in?");
                    column = input.nextInt();

                    if (column < 0 || column >= numCols) {
                        System.out.println("Column must be between 0 and " + (numCols - 1));
                    } else if (board.checkIfFree(column)) {
                        placeToken(board, token, column);

                        if (board.checkForWin(column)) {
                            System.out.println(board);
                            System.out.println("Player " + token + " won!");
                            gameOver = true;
                        } else if (board.checkTie()) {
                            System.out.println(board);
                            System.out.println("It's a tie!");
                            gameOver = true;
                        }

                        validInput = true;
                    } else {
                        System.out.println("Column is full.");
                    }
                }

                currentPlayer = (currentPlayer + 1) % numPlayers;
            }

        } while (playAgain(input));
    }

    /**
     * Places a new token at the topmost blank spot in a selected column
     *
     * @param board  [The board that the game is currently being played on]
     * @param player [The current player]
     * @param column [The column to place the token in]
     * @pre player == 1 OR player == 0 AND column > 0 AND column < MAX_COL
     * @post [placeToken = new token placed at the topmost blank spot in the selected column]
     */

    public static final void placeToken(IGameBoard board, char player, int column) {
        board.dropToken(player, column);
    }


    /**
     * Asks the player if they want to play again after a game is completed
     *
     * @param input [Scanner object for user input]
     * @return [true if player enters Y or y, and false otherwise]
     * @pre choice == 'Y' OR choice == 'y'
     * @pre choice == 'N' OR choice == 'n'
     * @post [playAgain = true if player enters Y or y, and false otherwise]
     */

    public static final boolean playAgain(Scanner input) {
        System.out.println("Would you like to play again? Enter Y for Yes or N for No:");
        char choice = input.next().charAt(0);
        return choice == 'Y' || choice == 'y';
    }


    /**
     * Gets the valid number of players
     *
     * @param scanner [A Scanner object used to read input from the user]
     * @return [An integer representing the number of players]
     * @pre numPlayers >= MIN_NUM_PLAYERS AND numPlayers <= MAX_NUM_PLAYERS
     * @post [getNumPlayers = valid number of players as an integer]
     */

    public static final int getNumPlayers(Scanner scanner) {
        int numPlayers;

        while (true) {
            System.out.println("How many players?");
            numPlayers = scanner.nextInt();
            if (numPlayers >= IGameBoard.MIN_NUM_PLAYERS && numPlayers <= IGameBoard.MAX_NUM_PLAYERS) {
                break;
            }

            if (numPlayers < IGameBoard.MIN_NUM_PLAYERS) {
                System.out.println("Must be at least " + IGameBoard.MIN_NUM_PLAYERS + " players");
            } else if (numPlayers > IGameBoard.MAX_NUM_PLAYERS) {
                System.out.println("Must be " + IGameBoard.MAX_NUM_PLAYERS + " players or fewer");
            }
        }
        return numPlayers;
    }

    /**
     * Gets a valid character to represent the player
     *
     * @param player    [The character to represent player]
     * @param usedChars [Characters already used by other players]
     * @param scanner   [A Scanner object to read user input]
     * @return [The character chosen by the player]
     * @pre player > 1 AND player <= MAX_NUM_PLAYERS
     * @pre [usedChars is not null and contains characters representing already used player tokens]
     * @post [playerCharacter = chosen character for the player]
     */

    public static final char playerCharacter(int player, char[] usedChars, Scanner scanner) {
        char playerChar;
        while (true) {
            System.out.println("Enter the character to represent player " + player);
            String input = scanner.next();

            if (!input.isEmpty()) {
                playerChar = Character.toUpperCase(input.charAt(0));

                int i;
                for (i = 0; i < usedChars.length; i++) {
                    if (usedChars[i] == playerChar) {
                        System.out.println(playerChar + " is already taken as a player token!");
                        break;
                    }
                }

                if (i == usedChars.length) {
                    usedChars[player - 1] = playerChar;
                    break;
                }
            }
        }
        return playerChar;
    }


    /**
     * Gets a valid number of rows from the player
     *
     * @param scanner [Scanner object used to read user input]
     * @return [Number of rows on the board]
     * @pre numOfRows >= MIN_ROW AND numOfRows <= MAX_ROW
     * @post [numOfPlayerRows = valid number of rows]
     */

    public static final int numOfPlayerRows(Scanner scanner) {
        int numOfRows;

        while (true) {
            System.out.println("How many rows should be on the board?");
            numOfRows = scanner.nextInt();
            if (numOfRows >= IGameBoard.MIN_ROW && numOfRows <= IGameBoard.MAX_ROW) {
                break;
            }
            if (numOfRows < IGameBoard.MIN_ROW) {
                System.out.println("Number of rows must be greater than " + IGameBoard.MIN_ROW);
            } else if (numOfRows > IGameBoard.MAX_ROW) {
                System.out.println("Number of rows must be less than " + IGameBoard.MAX_ROW);
            }
        }
        return numOfRows;
    }

    /**
     * Gets a valid number of columns from the player
     *
     * @param input [Scanner object used for user input]
     * @param board [The game board]
     * @return [Number of columns on the board]
     * @pre numOfCols >= MIN_COL AND numOfCols <= MAX_COL
     * @post [numOfPlayerColumns = valid number of columns]
     */

    public static final int numOfPlayerColumns(Scanner input, IGameBoard board) {
        int numOfCols;

        while (true) {
            System.out.println("How many columns should be on the board?");
            numOfCols = input.nextInt();
            if (numOfCols >= IGameBoard.MIN_COL && numOfCols <= IGameBoard.MAX_COL) {
                break;
            }

            if (numOfCols < IGameBoard.MIN_COL) {
                System.out.println("Number of columns must be greater than " + IGameBoard.MIN_COL);
            } else if (numOfCols > IGameBoard.MAX_COL) {
                System.out.println("Number of columns must be less than " + IGameBoard.MAX_COL);
            }
        }
        return numOfCols;
    }

    /**
     * Gets a valid number of tokens to win from the player
     *
     * @param scanner [Scanner object used for player input]
     * @return [Number of tokens to win the game]
     * @pre numToWin >= MIN_NUM_TO_WIN AND numToWin <= MAX_NUM_TO_WIN
     * @post [numOfTokens = valid number of tokens to win]
     */

    public static final int numOfTokens(Scanner scanner) {
        int numToWin;

        while (true) {
            System.out.println("How many tokens in a row to win the game?");
            numToWin = scanner.nextInt();

            if (numToWin >= IGameBoard.MIN_NUM_TO_WIN && numToWin <= IGameBoard.MAX_NUM_TO_WIN) {
                break;
            } else if (numToWin < IGameBoard.MIN_NUM_TO_WIN) {
                System.out.println("Number must be at least " + IGameBoard.MIN_NUM_TO_WIN);
            } else if (numToWin > IGameBoard.MAX_NUM_TO_WIN) {
                System.out.println("Number must be less than or equal to " + IGameBoard.MAX_NUM_TO_WIN);
            }
        }
        return numToWin;
    }

    /**
     * Gets the type of game from the player (fast or memory efficient)
     *
     * @param scanner [Scanner object used to read player input]
     * @return [The type of game chosen]
     * @pre playerChoice == 'f' OR payerChoice == 'F'
     * @pre playerChoice == 'm' OR playerChoice == 'M'
     * @post [typeOfGame = valid type of game chosen]
     */

    public static final char typeOfGame(Scanner scanner) {
        System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
        char playerChoice;
        while (true) {
            playerChoice = Character.toLowerCase(scanner.next().charAt(0));
            if (playerChoice == 'f' || playerChoice == 'm') {
                return playerChoice;
            } else {
                System.out.println("Please choose either F/f or M/m");
            }
        }
    }
}
