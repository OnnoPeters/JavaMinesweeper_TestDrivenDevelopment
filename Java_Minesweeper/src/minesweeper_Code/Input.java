package minesweeper_Code;

/**
 * Provides methods to input information and also receive outputs. Basically this is the class responsible for
 * user interaction.
 */
public class Input
{
    /**
     * The preferred size of the field.
     */
    private String size;

    /**
     * The preferred number of mines.
     */
    private String mines;

    /**
     * If the inputs ({@link #size}, {@link #mines}) have been validated yet.
     */
    private boolean validated = false;

    /**
     * Set the {@link #size} and sets {@link #validated} to false.
     *
     * @param size  String, the preferred size
     */
    public void enterSize(String size)
    {
        this.size = size;
        this.validated = false;
    }

    /**
     * Gets the {@link #size}.
     *
     * @return  String, {@link #size}
     */
    public String getSize()
    {
        return size;
    }

    /**
     * Sets the {@link #mines} and sets {@link #validated} to false.
     *
     * @param mines  String, the preferred number of mines
     */
    public void enterMines(String mines)
    {
        this.mines = mines;
        this.validated = false;
    }

    /**
     * Gets the {@link #mines}.
     *
     * @return  String, {@link #mines}
     */
    public String getMines()
    {
        return mines;
    }

    /**
     * Checks if {@link #mines} and {@link #size} can both be converted to integers and if the
     * numbers are inside the predefined bounds.
     *
     * @return  boolean, if the input is numeric and inside the bounds
     */
    public boolean validateInput()
    {
        if(convertToIntPossible())
        {
            int intSize = Integer.parseInt(getSize());
            int intMines = Integer.parseInt(getMines());
            if (intSize <= 100 && intMines < intSize * intSize && intMines > 0 && intSize > 0)
            {
                this.validated = true;
                return true;
            }
            else
            {
                this.validated = false;
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * Starts a new  <code>Game</code> with {@link #size} and {@link #mines} as input.
     *
     * @return  Game, the game instance
     *
     * @see Game
     */
    public Game startGame()
    {
        if(validated)
        {
            return new Game(Integer.parseInt(size), Integer.parseInt(mines));
        }
        else
        {
            return null;
        }
    }

    /**
     * Checks if {@link #size} and {@link #mines} can be converted to integers.
     *
     * @return  boolean, if conversion to integers is possible for both strings.
     */
    public boolean convertToIntPossible()
    {
        try
        {
            int i = Integer.parseInt(size);
            int j = Integer.parseInt(mines);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
}
