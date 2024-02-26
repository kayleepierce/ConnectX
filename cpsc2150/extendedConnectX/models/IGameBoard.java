package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
    Kaylee Pierce - kayleepierce
    AJ Hay - anthayjr
    Ashok Patel - ashokptl
    Ryan Nelson - Ryan_Nelson1
 */

/** IGameBoardContract
 *
 * @initialization [GameBoard is initialized and to be filled with blank positions]
 *
 * @defines
 *           MAX_ROW, MAX_COL, MAX_NUM_TO_WIN, MIN_ROW, MIN_COL, MIN_NUM_TO_WIN, MIN_NUM_PLAYERS, MAX_NUM_PLAYERS
 *           MAX_ROW [The maximum number of rows on the game board]
 *           MAX_COL [The maximum number of columns on the game board]
 *           MAX_NUM_TO_WIN [The maximum number of tokens in a row required to win the game]
 *           MIN_ROW [The minimum number of rows on the game board]
 *           MIN_COL [The minimum number of columns on the game board]
 *           MIN_NUM_TO_WIN [The minimum number of tokens in a row required to win the game]
 *           MAX_NUM_PLAYERS [The maximum number of players]
 *           MIN_NUM_PLAYERS [The minimum number of players]

 *
 * @constraints
 *               MIN_ROW >= 3 AND MAX_ROW <= 100
 *               MIN_COL >= 3 AND MAX_COL <= 100
 *               MIN_NUM_TO_WIN >= 3 AND MAX_NUM_TO_WIN <= 25
 *               MIN_NUM_PLAYERS >= 2 AND MAX_NUM_PLAYERS <= 10
 */

public interface IGameBoard {
    int MAX_ROW = 100;
    int MAX_COL = 100;
    int MAX_NUM_TO_WIN = 25;
    int MIN_ROW = 3;
    int MIN_COL = 3;
    int MIN_NUM_TO_WIN = 3;
    int MAX_NUM_PLAYERS = 10;
    int MIN_NUM_PLAYERS = 2;

    /**
     * Checks to see if there is space in a column
     *
     * @param c [The column number to check]
     * @pre c >= MIN_COL AND c <= MAX_COL
     * @return [True if there is space; false otherwise]
     * @post self = #self AND [checkIfFree = returns true if the top-most space in column is empty]
     *
     */

    default boolean checkIfFree(int c) {
        //Iterate through c and check if space is free
        BoardPosition temp;
        for(int r = 0; r < this.getNumRows(); r++) {
            temp = new BoardPosition(r, c);
            if(this.whatsAtPos(temp) == ' ') {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks to see if the last token placed in a column results in a win
     *
     * @param c [column to be checked]
     * @return [true if a  player has won and false otherwise]
     * @pre c >= 0 AND c <= 6
     * @post self = #self AND [checkForWin = true if the last move has resulted in either a vertical, horizontal, or diagonal win]
     *
     */

    default boolean checkForWin(int c) {
        int pos = -1;

        for(int r = 0; r < getNumRows(); r++) {
            if(whatsAtPos(new BoardPosition(r, c)) != ' ') {
                pos = r;
            } else {
                break;
            }
        }
        char player = whatsAtPos(new BoardPosition(pos, c));

        if(checkHorizWin(new BoardPosition(pos, c), player)) {
            return true; // horizontal win
        }

        if(checkDiagWin(new BoardPosition(pos, c), player)) {
            return true; // diagonal win
        }

        return checkVertWin(new BoardPosition(pos, c), player); // vertical win
// no win
    }

    /**
     * Checks to see if there is a tie
     *
     * @return [True if every board position has a char; false otherwise]
     * @post self = #self AND [checkTie = True if every BoardPosition pos contains a value; false otherwise]
     *
     */

    default boolean checkTie() {
        // Loop through board and check if any positions are empty
        BoardPosition temp;
        for(int i = 0; i < getNumColumns(); i++) {
            for(int j = 0; j < getNumRows(); j++) {
                temp = new BoardPosition(j, i);
                if(whatsAtPos(temp) ==' ') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks to see if there are the specified number of tokens in a row horizontally to win
     *
     * @param pos [represents position of the last token placed]
     * @param p [indicates player that placed the last token]
     * @return [true if there are specified number of matching tokens horizontally]
     * @pre p == X OR p == O
     * @post self = #self AND [checkHorizWin = true if any specified number of horizontally matching characters, where one of which is p, is detected, and false otherwise]
     *
     */

    default boolean checkHorizWin(BoardPosition pos, char p) {
        int row = pos.getRow();
        int col = pos.getColumn();
        int inARowTokens = 0;

        for(int c = col; c >= 0; c--) { // checking to the left
            if(whatsAtPos(new BoardPosition(row, c)) == p) {
                inARowTokens++;
            } else {
                break;
            }
        }

        for(int c = col + 1; c < getNumColumns(); c++) { // checking to the right
            if(whatsAtPos(new BoardPosition(row, c)) == p) {
                inARowTokens++;
            } else {
                break;
            }
        }

        return inARowTokens >= getNumToWin(); // Return true if there are specified number of tokens in a row
    }

    /**
     * Checks to see if there are the specified number of tokens in a row vertically to win
     *
     * @param pos [represents the position of the last token placed]
     * @param p [indicates player that placed the last token]
     * @return [true if there are specified number of matching tokens vertically]
     * @pre p == X OR p == O
     * @post self = #self AND [checkVertWin = true if any specified number of vertically matching characters, where one of which is p, is detected, and false otherwise]
     *
     */

    default boolean checkVertWin(BoardPosition pos, char p) {
        int row = pos.getRow();
        int col = pos.getColumn();
        int inARowTokens = 0;

        for(int r = row; r >= 0; r--) { // Checking upwards
            if(whatsAtPos(new BoardPosition(r, col)) == p) {
                inARowTokens++;
            } else {
                break;
            }
        }

        for(int r = row + 1; r < getNumRows(); r++) { // Checking downwards
            if(whatsAtPos(new BoardPosition(r, col)) == p) {
                inARowTokens++;
            } else {
                break;
            }
        }
        return inARowTokens >= getNumToWin(); // Return true if there are specified number of tokens in a row
    }

    /**
     * Checks to see if there are the specified number of tokens in a row diagonally to win
     *
     * @param pos [represents the position of the last token placed]
     * @param p [indicates player that placed the last token]
     * @return [true if there are specified number of matching tokens diagonally]
     * @pre p = [The chosen player token]
     * @post self = #self AND [checkDiagWin = true if any specified number of diagonally matching characters, where one of which is p, is detected, and false otherwise]
     *
     */

    default boolean checkDiagWin(BoardPosition pos, char p) {
        int row = pos.getRow();
        int col = pos.getColumn();
        int inARowTokens = 1;

        for(int r = row - 1, c = col + 1; r >= 0 && c < getNumColumns(); r--, c++) { // Checking downwards and right
            if(whatsAtPos(new BoardPosition(r, c)) == p) {
                inARowTokens++;
            } else {
                break;
            }
        }

        if(inARowTokens >= getNumToWin()) {
            return true;
        }

        for(int r = row + 1, c = col - 1; r < getNumRows() && c >= 0; r++, c--) { // Checking upwards and left
            if(whatsAtPos(new BoardPosition(r, c)) == p) {
                inARowTokens++;
            } else {
                break;
            }
        }

        if(inARowTokens >= getNumToWin()) {
            return true;
        }

        inARowTokens = 1;

        for(int r = row - 1, c = col - 1; r >= 0 && c >= 0; r--, c--) { // Checking downwards and left
            if(whatsAtPos(new BoardPosition(r, c)) == p) {
                inARowTokens++;
            } else {
                break;
            }
        }

        if(inARowTokens >= getNumToWin()) {
            return true;
        }

        for(int r = row + 1, c = col + 1; r < getNumRows() && c < getNumColumns(); r++, c++) { // Checking upwards and right
            if(whatsAtPos(new BoardPosition(r, c)) == p) {
                inARowTokens++;
            } else {
                break;
            }
        }
        return inARowTokens >= getNumToWin(); // Return true if there are specified number of tokens in a row
    }

    /**
     * Checks to see if there is a character at board position
     *
     * @param pos [Represents the position to be checked]
     * @param player [Represents the player that is at the position]
     * @return [True if the players token is at BoardPosition pos]
     * @pre player == X OR player == O
     * @post self = #self AND [isPlayerAtPos = true if the char player is at BoardPosition pos]
     *
     */

    default boolean isPlayerAtPos(BoardPosition pos, char player) {
        return whatsAtPos(pos) == player;
    }

    /**
     * Places a token in the lowest empty space in chosen column
     *
     * @param p [Token to place]
     * @param c [Column to place token in]
     * @pre p == 'X' or p == 'O' AND checkIfFree(c) == [true]
     * @post [dropToken = Token is placed in lowest empty space in column]
     *
     */

    void dropToken(char p, int c);

    /**
     * Checks to see what is at board position
     *
     * @param pos [Represents the position to be checked on the gameboard]
     * @return [What character is at BoardPosition pos]
     * @post self = #self AND [whatsAtPos = the character at BoardPosition pos]
     *
     */

    public char whatsAtPos(BoardPosition pos);

    /**
     * Gets the number of rows
     *
     * @return numRows = [The number of rows]
     * @post getNumRows = #numRows
     *
     */

    int getNumRows();

    /**
     * Gets the number of columns
     *
     * @return numColumns = [The number of columns]
     * @post getNumColumns = #numColumns
     *
     */

    int getNumColumns();

    /**
     * Gets the number of tokens to win
     *
     * @return numToWin = [The number to win]
     * @post getNumToWin = #numToWin
     *
     */

    int getNumToWin();
}
