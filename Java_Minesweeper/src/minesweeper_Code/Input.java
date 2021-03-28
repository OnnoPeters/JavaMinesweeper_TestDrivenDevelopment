package minesweeper_Code;

public class Input
{
    private int size;
    private int mines;
    private boolean validated = false;

    public void enterSize(int size)
    {
        this.size = size;
        this.validated = false;
    }

    public int getSize()
    {
        return size;
    }

    public void enterMines(int mines)
    {
        this.mines = mines;
        this.validated = false;
    }

    public int getMines()
    {
        return mines;
    }

    public boolean validateInput()
    {
        if(getSize() <= 100 && getMines() < getSize() * getSize() && getMines() > 0 && getSize() > 0)
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

    public Game startGame()
    {
        if(validated)
        {
            return new Game(size, mines);
        }
        else
        {
            return null;
        }
    }
}
