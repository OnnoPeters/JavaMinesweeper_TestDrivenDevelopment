package minesweeper_Code;

public class Field
{

    public int max;
    public int min;
    public int size;
    public String[][] cellsVisible;

    public Field(int size)
    {
        this();
        if(size > this.max)
        {
            size = this.max;
        }
        else if(size < this.min)
        {
            size = this.min;
        }
        this.size = size;

        this.cellsVisible = new String[size][size];
        for(int i = 0; i < this.size; i++)
        {
            for(int j = 0; j < this.size; j++)
            {
                this.cellsVisible[i][j] = "*";
            }
        }
    }

    private Field()
    {
        this.max = 100;
        this.min = 10;
    }

}
