package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
    Kaylee Pierce - kayleepierce
    AJ Hay - anthayjr
    Ashok Patel - ashokptl
    Ryan Nelson - Ryan_Nelson1
 */

/**
 * @invariant 0 < row <= MAX_ROW AND 0 < col <= MAX_COL
 *
 */

public class BoardPosition {
    private int row;
    private int col;

    /**
     * Parameterized constructor for BoardPosition
     *
     * @param aRow [X-axis on the board, row]
     * @param aColumn [Y-axis on the board, column]
     * @pre 0 < aRow <= MAX_ROW AND 0 < aColumn <= MAX_COL
     * @post row = #aRow AND col = #aColumn
     *
     */
    public BoardPosition(int aRow, int aColumn) {
        row = aRow;
        col = aColumn;
    }

    /**
     * Gets the row value
     *
     * @return [The row value]
     * @pre None
     * @post getRow = row
     *
     */
    public int getRow() {
        //Returns the row value
        return row;
    }

    /**
     * Gets the column value
     *
     * @return [The column value]
     * @pre None
     * @post getColumn = col
     *
     */
    public int getColumn() {
        //Return the column value
        return col;
    }

    /**
     * Compares the two BoardPosition objects for equality and returns the results
     *
     * @param obj [Object being compared]
     * @post [equals returns true if the board positions are equal; false otherwise]
     *
     */

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        BoardPosition posObj = (BoardPosition) obj;
        return row == posObj.row && col == posObj.col;
    }

    /**
     * Will provide a formatted string of coordinates, "<row>, <col>"
     *
     * @return [String in the format <row>, <column> ]
     * @post [toString = Result is a formatted string of coordinates <row,col> ]
     *
     */

    @Override
    public String toString() {
        String Coords = row + "," + col;
        return Coords;
    }
}
