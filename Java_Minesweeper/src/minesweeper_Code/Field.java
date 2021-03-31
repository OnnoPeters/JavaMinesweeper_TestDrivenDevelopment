package minesweeper_Code;

import java.util.ArrayList;
import java.util.Random;

public class Field
{

    private int max;
    private int min;
    private int size;
    private int mines;
    public String[][] cellsVisible;
    private int[][] cellsHidden;

    public Field(int size)
    {
        this();
        if(size > this.getMax())
        {
            size = this.getMax();
        }
        else if(size < this.getMin())
        {
            size = this.getMin();
        }
        this.setSize(size);

        this.cellsVisible = new String[size][size];
        this.cellsHidden = new int[size][size];
        for(int i = 0; i < this.getSize(); i++)
        {
            for(int j = 0; j < this.getSize(); j++)
            {
                this.cellsVisible[i][j] = "*";
                this.cellsHidden[i][j] = 0;
            }
        }
    }

    private Field()
    {
        this.setMax(100);
        this.setMin(10);
    }


    public int getMax()
    {
        return max;
    }

    public void setMax(int max)
    {
        this.max = max;
    }

    public int getMin()
    {
        return min;
    }

    public void setMin(int min)
    {
        this.min = min;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public void setMines(int mines)
    {
        if(mines < 1)
        {
            mines = 1;
        }
        else if (mines >= this.size * this.size)
        {
            mines = this.size * this.size - 1;
        }
        this.mines = mines;

        Random rand = new Random();
        int numberOfMinesSet = 0;
        while(this.mines > numberOfMinesSet)
        {
            int num1 = rand.nextInt(this.size);
            int num2 = rand.nextInt(this.size);
            if (this.getNumberAtLocation(num1, num2) != -1)
            {
                this.setNumberAtLocation(num1, num2, -1);
                this.increaseNumbersAtSurroundingLocations(num1, num2);
                numberOfMinesSet++;
            }
        }

    }

    public int getNumberAtLocation(int loc1, int loc2)
    {
        return this.cellsHidden[loc1][loc2];
    }

    public void setNumberAtLocation(int loc1, int loc2, int numberToBeSet)
    {
        this.cellsHidden[loc1][loc2] = numberToBeSet;
    }

    public void increaseNumbersAtSurroundingLocations(int loc1, int loc2)
    {
        for(int i = loc1 - 1; i <= loc1 + 1; i++)
        {
            for(int j = loc2 - 1; j <= loc2 + 1; j++)
            {
                if(i >= 0 && j >= 0 && i < this.cellsHidden.length && j < this.cellsHidden[i].length && (i != loc1 || j != loc2) && this.cellsHidden[i][j] != -1)
                {
                    this.cellsHidden[i][j]++;
                }
            }
        }
    }

    public void printHiddenField()
    {
        for(int i = 0; i < this.getSize(); i++)
        {
            for(int j = 0; j < this.getSize(); j++)
            {
                System.out.print(this.cellsHidden[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public void printVisibleField()
    {
        for(int i = 0; i < this.getSize(); i++)
        {
            for(int j = 0; j < this.getSize(); j++)
            {
                System.out.print(this.cellsVisible[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public String getVisibleCellAtLocation(int loc1, int loc2)
    {
        return this.cellsVisible[loc1][loc2];
    }

    public void setVisibleCellAtLocation(int loc1, int loc2, String valueToBeSet)
    {
        this.cellsVisible[loc1][loc2] = valueToBeSet;
    }

    public void revealEmptyCells(int loc1, int loc2)
    {
        ArrayList<Integer> listOfx_values = new ArrayList<>();
        ArrayList<Integer> listOfy_values = new ArrayList<>();
        listOfx_values.add(loc1);
        listOfy_values.add(loc2);
        while(listOfx_values.size() > 0 && listOfy_values.size() > 0)
        {
            this.setVisibleCellAtLocation(listOfx_values.get(0), listOfy_values.get(0), String.valueOf(this.getNumberAtLocation(listOfx_values.get(0), listOfy_values.get(0))));
            for(int i = listOfx_values.get(0) - 1; i <= listOfx_values.get(0) + 1; i++)
            {
                for(int j = listOfy_values.get(0) - 1; j <= listOfy_values.get(0) + 1; j++)
                {

                    if(i >= 0 && j >= 0 && i < this.getSize() && j < this.getSize() && getVisibleCellAtLocation(i,j).equals("*"))
                    {
                        if(this.getNumberAtLocation(i,j) == 0)
                        {
                            listOfx_values.add(i);
                            listOfy_values.add(j);
                            this.setVisibleCellAtLocation(i, j, String.valueOf(this.getNumberAtLocation(i, j)));
                        }
                        else
                        {
                            this.setVisibleCellAtLocation(i, j, String.valueOf(this.getNumberAtLocation(i, j)));
                        }
                    }

                }
            }
            listOfx_values.remove(0);
            listOfy_values.remove(0);
        }
    }

    public int countCellsLeft()
    {
        int countCellsleft = 0;
        for(int i = 0; i < this.getSize(); i++)
        {
            for(int j = 0; j < this.getSize(); j++)
            {
                if(this.cellsVisible[i][j].equals("*"))
                {
                    countCellsleft++;
                }
            }
        }
        return countCellsleft;
    }
}
