package minesweeper_Code;

public class Game
{
    public Field field;
    private boolean winningStatus;
    private boolean endingStatus;

    public Game(int size, int mines)
    {
        this.field = new Field(size);
        this.field.setMines(mines);
        setEndingStatus(false);
        setWinningStatus(false);
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

    public boolean action(int loc1, int loc2)
    {
        if(!getEndingStatus())
        {
            if (loc1 < field.getSize() && loc2 < field.getSize() && loc1 >= 0 && loc2 >= 0)
            {
                this.field.printVisibleField();
                this.field.printHiddenField();
                int numberAtLoc = this.field.getNumberAtLocation(loc1, loc2);
                if (numberAtLoc == -1)
                {
                    this.field.setVisibleCellAtLocation(loc1, loc2, "X");
                    endGameUnsuccessfully();
                }
                else
                {
                    this.field.setVisibleCellAtLocation(loc1, loc2, String.valueOf(numberAtLoc));
                }
                this.field.printVisibleField();
                this.field.printHiddenField();
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public void endGameSuccessfully()
    {
        setEndingStatus(true);
        setWinningStatus(true);
    }

    public void endGameUnsuccessfully()
    {
        setEndingStatus(true);
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
