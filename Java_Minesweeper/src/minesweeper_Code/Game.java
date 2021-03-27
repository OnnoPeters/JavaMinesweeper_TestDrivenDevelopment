package minesweeper_Code;

public class Game
{
    public Field field;

    public Game(int size, int mines)
    {
        this.field = new Field(size);
        this.field.setMines(mines);
    }

    public boolean action(int loc1, int loc2)
    {
        if(loc1 < field.getSize() && loc2 < field.getSize() && loc1 >= 0 && loc2 >= 0)
        {
            this.field.printVisibleField();
            this.field.printHiddenField();
            int numberAtLoc = this.field.getNumberAtLocation(loc1, loc2);
            if(numberAtLoc == -1)
            {
                this.field.setVisibleCellAtLocation(loc1, loc2, "X");
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

}
