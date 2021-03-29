package minesweeper_Code;

public class Game
{
    public Field field;
    private boolean winningStatus;
    private boolean endingStatus;
    private int cellsLeft;
    private int mines;

    public Game(int size, int mines)
    {
        this.field = new Field(size);
        this.field.setMines(mines);
        setEndingStatus(false);
        setWinningStatus(false);
        this.cellsLeft = size * size;
        this.mines = mines;
    }

    public boolean getEndingStatus()
    {
        return endingStatus;
    }

    public void setEndingStatus(boolean endingStatus)
    {
        this.endingStatus = endingStatus;
    }

    public boolean getWinningStatus()
    {
        return winningStatus;
    }

    public void setWinningStatus(boolean winningStatus)
    {
        this.winningStatus = winningStatus;
    }

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
            cellsLeft--;
            if(cellsLeft <= mines)
            {
                endGameSuccessfully();
            }
        }
        return numberAtLoc;
    }

    public void endGameSuccessfully()
    {
        setEndingStatus(true);
        setWinningStatus(true);
        revealField();
    }

    public void endGameUnsuccessfully()
    {
        setEndingStatus(true);
        revealField();
    }

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
