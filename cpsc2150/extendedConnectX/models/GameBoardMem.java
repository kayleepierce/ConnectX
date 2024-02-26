package cpsc2150.extendedConnectX.models;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
    Kaylee Pierce - kayleepierce
    AJ Hay - anthayjr
    Ashok Patel - ashokptl
    Ryan Nelson - Ryan_Nelson1
 */

/**
 *
 * @invariant MIN_COL < numRows AND numRows < MAX_ROW and MIN_ROW < numCols AND numCols < MAX_COL
              AND MIN_NUM_TO_WIN < numsToWin AND numsToWin < MAX_NUM_TO_WIN
              AND [All chosen player characters are unique]
              AND [No blank space between two players in the column]
 * @coorespondance self.numRows = numRows AND self.numCols = numCols AND self.numsToWin = numsToWin
 *                 AND self.gameBoard = gameBoard
 *
 */
public class GameBoardMem extends AbsGameBoard {
    private int numRows;
    private int numCols;
    private int numsToWin;
    HashMap<Character, List<BoardPosition>> gameBoard = new HashMap<>();

    /**
     * Constructor for GameBoardMem
     *
     * @param numRows [The number of rows]
     * @param numCols [The number of columns]
     * @param numToWin [The number to win]
     * @pre numRows > MIN_ROW AND numRows < MAX_ROW
     * @pre numCols > MIN_COL AND numCols < MAX_COL
     * @pre numToWin > MIN_NUM_TO_WIN AND numToWin > MAX_NUM_TO_WIN
     * @post [GameBoardMem = an empty hashmap(gameboard) is initialized]
     *
     */

    public GameBoardMem(int numRows, int numCols, int numToWin) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.numsToWin = numToWin;
    }

    /**
     * Places a token in the lowest available row in a column
     *
     * @param p [The token to be dropped in the column]
     * @param c [The column for the token to be placed in]
     * @pre p = Character AND c >= 0 AND c <= numCol
     * @post [self = #self AND dropToken = token added to hashmap as a value for p}
     *
     */

    @Override
    public void dropToken(char p, int c) {
        if (!gameBoard.containsKey(p)) {
            gameBoard.put(p, new ArrayList<>());
        }

        for (int r = 0; r < getNumRows(); r++) {
            if (whatsAtPos(new BoardPosition(r, c)) == ' ') {
                gameBoard.get(p).add(new BoardPosition(r, c));
                break;
            }
        }
    }

    /**
     * Checks to see what is at board position
     *
     * @param pos [the position to be checked on the game board]
     * @return [The character at the specified BoardPosition]
     * @pre pos [is a valid BoardPosition within the game board's dimensions]
     * @post [whatsAtPos returns the character at the specified BoardPosition on the game board]
     *
     */

    @Override
    public char whatsAtPos(BoardPosition pos) {
        for (Character player : gameBoard.keySet()) {
            if (isPlayerAtPos(pos, player)) {
                return player;
            }
        }
        return ' '; // Return blank space if no token is at the given position
    }

    /**
     * Checks to see if there is a character at board position
     *
     * @param pos [the position to be checked on the game board]
     * @param player [the player character to be checked for]
     * @return [True if the player's token is at the specified BoardPosition; false otherwise]
     * @pre pos [is a valid BoardPosition within the game board's dimensions]
     * @pre player [is a valid player character (X, O, etc.)]
     * @post [isPlayerAtPos returns true if the player's token is at the specified BoardPosition on the game board; false otherwise]
     *
     */

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        if (!gameBoard.containsKey(player)) {
            return false;
        }
        for (BoardPosition bpos : gameBoard.get(player)) {
            if (bpos.equals(pos)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Gets the number of rows
     *
     * @return [The number of rows on the game board]
     * @post [getNumRows returns the number of rows on the game board]
     *
     */

    public int getNumRows() {
        return numRows;
    }

    /**
     * Gets the number of columns
     *
     * @return [The number of columns on the game board]
     * @post [getNumColumns returns the number of columns on the game board]
     *
     */

    public int getNumColumns() {
        return numCols;
    }

    /**
     * Gets the number of tokens to win
     *
     * @return [The number of tokens in a row required to win the game]
     * @post [getNumToWin returns the number of tokens in a row required to win the game]
     *
     */

    public int getNumToWin() {
        return numsToWin;
    }
}
