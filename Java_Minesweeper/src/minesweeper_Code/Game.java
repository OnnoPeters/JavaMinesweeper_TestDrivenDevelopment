package minesweeper_Code;

/**
 * Contains methods to control and progress the game, as well as information about the game status.
 */
public class Game
{

    /**
     * The <code>Field</code> on which this game is played.
     *
     * @see Field
     */
    public Field field;

    /**
     * If this game is won by the user.
     */
    private boolean winningStatus;

    /**
     * If this game has ended.
     */
    private boolean endingStatus;

    /**
     * How many cells are left uncovered, so are visible as "*" to the user.
     *
     * @see Field#cellsVisible
     */
    private int cellsLeft;

    /**
     * How many mines are in this game.
     */
    private int mines;

    /**
     * Creates a new <code>Game</code> with given {@link Field#size} and {@link #mines}.
     *
     * @param size  int, size of the <code>Field</code>
     * @param mines  int, number of mines
     */
    public Game(int size, int mines)
    {
        this.field = new Field(size);
        this.field.setMines(mines);
        setEndingStatus(false);
        setWinningStatus(false);
        this.cellsLeft = size * size;
        this.mines = mines;
    }

    /**
     * Get the {@link #endingStatus}.
     *
     * @return  boolean, {@link #endingStatus}
     */
    public boolean getEndingStatus()
    {
        return endingStatus;
    }

    /**
     * Set the {@link #endingStatus}.
     *
     * @param endingStatus  boolean, {@link #endingStatus}
     */
    public void setEndingStatus(boolean endingStatus)
    {
        this.endingStatus = endingStatus;
    }

    /**
     * Get the {@link #winningStatus}.
     *
     * @return  boolean, {@link #winningStatus}
     */
    public boolean getWinningStatus()
    {
        return winningStatus;
    }

    /**
     * Set the {@link #winningStatus}.
     *
     * @param winningStatus  boolean, {@link #winningStatus}.
     */
    public void setWinningStatus(boolean winningStatus)
    {
        this.winningStatus = winningStatus;
    }

    /**
     * Checks if a cell specified by loc1 and loc2 is inside the correct bounds and if the game is still ongoing.
     *
     * @param loc1  int, x coordinate of cell
     * @param loc2  int, y coordinate of cell
     * @return  boolean, if the interaction is valid
     */
    public boolean isValid(int loc1, int loc2)
    {
        if(!getEndingStatus() && loc1 < field.getSize() && loc2 < field.getSize() && loc1 >= 0 && loc2 >= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Interaction of the user with the cell specified by loc1 and loc2. If there is a mine in this cell, {@link #endGameUnsuccessfully()}
     * is executed, otherwise, the cell and possibly surrounding cells are revealed. If {@link #cellsLeft} is equal
     * to {@link #mines}, {@link #endGameSuccessfully()} is executed.
     * <p>
     * Returns the number at the specified cell using {@link Field#getNumberAtLocation(int, int)}.
     * </p>
     *
     * @param loc1  int, x coordinate of cell
     * @param loc2  int, y coordinate of cell
     * @return  int, number at specified cell
     *
     * @see Field
     */
    public int action(int loc1, int loc2)
    {
        int numberAtLoc = this.field.getNumberAtLocation(loc1, loc2);
        if (numberAtLoc == -1)
        {
            this.field.setVisibleCellAtLocation(loc1, loc2, "X");
            endGameUnsuccessfully();
        }
        else
        {
            this.field.setVisibleCellAtLocation(loc1, loc2, String.valueOf(numberAtLoc));
            if(field.needForReveal(loc1,loc2))
            {
                this.field.revealEmptyCells(loc1,loc2);
                this.cellsLeft = field.countCellsLeft();
            }
            else
            {
                cellsLeft--;
            }
            if(cellsLeft <= mines)
            {
                endGameSuccessfully();
            }
        }
        return numberAtLoc;
    }

    /**
     * Makes the user win the game and reveals the entire field.
     */
    public void endGameSuccessfully()
    {
        setEndingStatus(true);
        setWinningStatus(true);
        revealField();
    }

    /**
     * Makes the user lose the game and reveals the entire field
     */
    public void endGameUnsuccessfully()
    {
        setEndingStatus(true);
        revealField();
    }

    /**
     * Reveals the entire field.
     */
    public void revealField()
    {
        for(int i = 0; i < field.getSize(); i++)
        {
            for (int j = 0; j < field.getSize(); j++)
            {
                int number = field.getNumberAtLocation(i,j);
                if(number != -1)
                {
                    field.setVisibleCellAtLocation(i,j, String.valueOf(number));
                }
                else
                {
                    field.setVisibleCellAtLocation(i,j, "X");
                }
            }
        }
    }
}
