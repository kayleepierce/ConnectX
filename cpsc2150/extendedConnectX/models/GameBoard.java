package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
    Kaylee Pierce - kayleepierce
    AJ Hay - anthayjr
    Ashok Patel - ashokptl
    Ryan Nelson - Ryan_Nelson1
 */

/**
 *
 * @invariant MIN_COL < numRows < MAX_COL AND MIN_ROW < numCols < MAX_ROW
 * @coorespondance self.numRows = numRows AND self.numCols = numCols AND self.numToWin = numToWin
 *
 */

public class GameBoard extends AbsGameBoard
{
    private char[][] board;
    private int numRows;
    private int numCols;
    private int numToWin;

    /**
     * Constructor for the game board
     *
     * @param row [The number of rows]
     * @param col [The number of columns]
     * @param tokenToWin [The number to win]
     * @pre row > MIN_ROW AND row < MAX_ROW
     * @pre col > MIN_COL AND col < MAX_COL
     * @pre tokenToWin > MIN_NUM_TO_WIN AND tokenToWin > MAX_NUM_TO_WIN
     * @post [GameBoard = an empty GameBoard is initialized]
     *
     */

    public GameBoard(int row, int col, int tokenToWin)
    {
        numRows = row;
        numCols = col;
        numToWin = tokenToWin;

        board = new char[numRows][numCols];

        for(int r = 0; r < numRows; r++) {
            for(int c = 0; c < numCols; c++) {
                board[r][c] = ' ';
            }
        }
    }

    /**
     * Checks to see if the column can accept another token
     *
     * @param c [The column number to check]
     * @pre c >= MIN_COL AND c <= MAX_COL
     * @return [true if there is space; false if there is not]
     * @post self = #self AND [checkIfFree returns true if the top-most space in the column is empty]
     */

    @Override
    public boolean checkIfFree(int c)
    {
        for(int r = 0; r < numRows; r++) {
            if(board[r][c] == ' ') {
                return true;
            }
        }
        return false;
    }

    /**
     * Places a token in the lowest available row in a column
     *
     * @param p [is the token to be dropped in the column]
     * @param c [is the column to for the token to be placed in]
     * @pre p = [Chosen player token] AND c >= MIN_COL AND c <= MAX_COL
     * @post [self = #self AND dropToken = the new char at the lowest available row in column c]
     *
     */

    @Override
    public void dropToken(char p, int c)
    {
        for(int r = 0; r < getNumRows(); r++) {
            if(board[r][c] == ' ') {
                board[r][c] = p;
                return;
            }
        }
    }

    /**
     * Checks to see if the game has resulted in a tie
     *
     * @return [true if every board position has a char, and false otherwise]
     * @post self = #self AND [checkTie returns true if every position contains a character, and false otherwise]
     *
     */

    @Override
    public boolean checkTie()
    {
        for(int r = 0; r < getNumRows(); r++) {
            for(int c = 0; c < getNumColumns(); c++) {
                if(board[r][c] == ' ')
                    return false;
            }
        }
        return true; // All positions are occupied
    }

    /**
     * Checks to see what is at board position
     *
     * @param pos [Represents the position to be checked on the gameboard]
     * @return [what character is at BoardPosition pos]
     * @post self = #self AND [whatsAtPos returns the character as BoardPosition pos]
     *
     */

    @Override
    public char whatsAtPos(BoardPosition pos)
    {
        //returns what is in the GameBoard at position pos If no marker is there, it returns a blank space char.

        return board[pos.getRow()][pos.getColumn()];
    }

    /**
     * Gets the number of rows
     *
     * @return [The number of rows]
     * @post getNumRows = #numRows
     *
     */

    @Override
    public int getNumRows() {
        return numRows;
    }

    /**
     * Gets the number of columns
     *
     * @return [The number of columns]
     * @post getNumColumns = #numColumns
     *
     */

    @Override
    public int getNumColumns() {
        return numCols;
    }

    /**
     * Gets the number of tokens to win
     *
     * @return [The number to win]
     * @post getNumToWin = #numToWin
     *
     */

    @Override
    public int getNumToWin() {
        return numToWin;
    }
}
