package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
    Kaylee Pierce - kayleepierce
    AJ Hay - anthayjr
    Ashok Patel - ashokptl
    Ryan Nelson - Ryan_Nelson1
 */

/**
 * @invariant: N/A
 *
 */

public abstract class AbsGameBoard implements IGameBoard {

    /**
     * Will provide a formatted string to represent the game board
     *
     * @return [A string representation of the game board]
     *
     * @pre None
     *
     * @post [toString gives a formatted string representing the current state of the game board]
     *
     **/

    @Override
    public String toString() {
        String boardString = "";

        for (int col = 0; col < getNumColumns(); col++) {
            if (col <= 9) {
                boardString += "| " + col;
            } else {
                boardString += "|" + col;
            }
        }
        boardString += "|\n";

        for (int row = getNumRows() - 1; row >= 0; row--) {
            boardString += "";
            for (int col = 0; col < getNumColumns(); col++) {
                BoardPosition pos = new BoardPosition(row, col);
                char token = whatsAtPos(pos);
                boardString += "|" + token + " ";
            }
            boardString += "|\n";
        }
        return boardString;
    }
}
