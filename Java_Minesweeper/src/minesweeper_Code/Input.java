package minesweeper_Code;

public class Input
{
    private String size;
    private String mines;
    private boolean validated = false;

    public void enterSize(String size)
    {
        this.size = size;
        this.validated = false;
    }

    public String getSize()
    {
        return size;
    }

    public void enterMines(String mines)
    {
        this.mines = mines;
        this.validated = false;
    }

    public String getMines()
    {
        return mines;
    }

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
