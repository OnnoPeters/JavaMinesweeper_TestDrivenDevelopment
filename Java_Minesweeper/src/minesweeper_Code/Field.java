package minesweeper_Code;

import java.util.ArrayList;
import java.util.Random;

/**
 * Contains the arrays that define the minesweeper field and related information, as well as methods to
 * interact with the field.
 */
public class Field
{
    /**
     * The maximum {@link #size} of this field
     */
    private int max;

    /**
     * The minimum {@link #size} of this field
     */
    private int min;

    /**
     * Width and height of this field, so also the size of each dimension of the two dimensional arrays {@link #cellsVisible}
     * and {@link #cellsHidden}
     */
    private int size;

    /**
     * Two dimensional <code>String</code> array, depicting what the user can see of this field.
     */
    public String[][] cellsVisible;

    /**
     * Two dimensional <code>int</code> array, depicting the mines and the mine proximity in this field. Hidden
     * to the user.
     */
    private int[][] cellsHidden;

    /**
     * Creates a new <code>Field</code> with the given {@link #size}.
     *
     * @param size  int, the size this field should have
     *
     * @see Field
     */
    public Field(int size)
    {
        this.setMax(100);
        this.setMin(10);
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

    /**
     * Get {@link #max} value.
     *
     * @return max  int, {@link #max}
     */
    public int getMax()
    {
        return max;
    }

    /**
     * Set {@link #max} value.
     *
     * @param max  int, {@link #max}
     */
    public void setMax(int max)
    {
        this.max = max;
    }

    /**
     * Get {@link #min} value.
     *
     * @return min  int, {@link #min}
     */
    public int getMin()
    {
        return min;
    }

    /**
     * Set {@link #min} value.
     *
     * @param min  int, {@link #min}
     */
    public void setMin(int min)
    {
        this.min = min;
    }

    /**
     * Get {@link #size} value.
     *
     * @return size  int, {@link #size}
     */
    public int getSize()
    {
        return size;
    }

    /**
     * Set {@link #size} value.
     *
     * @param size  int, {@link #size}
     */
    public void setSize(int size)
    {
        this.size = size;
    }

    /**
     * Set the number of mines that should be generated on this field.
     * <p>
     * Minimum is 1 and maximum is size^2 - 1.
     * </p>
     * They are then randomly placed on the field and represented in {@link #cellsHidden}.
     *
     * @param mines  int, number of mines on this field
     */
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

        Random rand = new Random();
        int numberOfMinesSet = 0;
        while(mines > numberOfMinesSet)
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

    /**
     * Get the information on the cell specified by loc1 and loc2 from {@link #cellsHidden}.
     *
     * @param loc1  int, x coordinate of cell
     * @param loc2  int, y coordinate of cell
     * @return  int, the value from cellsHidden
     */
    public int getNumberAtLocation(int loc1, int loc2)
    {
        return this.cellsHidden[loc1][loc2];
    }

    /**
     * Set the information on the cell specified by loc1 and loc2 on {@link #cellsHidden}.
     *
     * @param loc1  int, x coordinate of cell
     * @param loc2  int, y coordinate of cell
     * @param numberToBeSet  int, information to be set on the specified cell
     */
    public void setNumberAtLocation(int loc1, int loc2, int numberToBeSet)
    {
        this.cellsHidden[loc1][loc2] = numberToBeSet;
    }

    /**
     * Increase the value of the cells around the cell specified by loc1 and loc2 by 1 in {@link #cellsHidden}
     * to show mine proximity.
     *
     * @param loc1  int, x coordinate of the cell
     * @param loc2  int, y coordinate of the cell
     */
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

    /**
     * Get the <code>String</code> showing what the user sees of the cell specified by loc1 and loc2 in {@link #cellsVisible}.
     *
     * @param loc1  int, x coordinate of the cell
     * @param loc2  int, y coordinate of the cell
     * @return  String, what the user sees for this cell
     */
    public String getVisibleCellAtLocation(int loc1, int loc2)
    {
        return this.cellsVisible[loc1][loc2];
    }

    /**
     * Set the <code>String</code> defining what the user should see in the cell specified by loc1 and loc2 in {@link #cellsVisible}.
     *
     * @param loc1  int, x coordinate of the cell
     * @param loc2  int, y coordinate of the cell
     * @param valueToBeSet  String, what the user should see for this cell
     */
    public void setVisibleCellAtLocation(int loc1, int loc2, String valueToBeSet)
    {
        this.cellsVisible[loc1][loc2] = valueToBeSet;
    }

    /**
     * Reveals empty cells (Cells with no mines and no mines neighboring it in any way) to the user in {@link #cellsVisible}
     * around the cell specified by loc1 and loc2.
     *
     * @param loc1  int, x coordinate of the cell
     * @param loc2  int, y coordinate of the cell
     */
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
                        else if(this.getNumberAtLocation(listOfx_values.get(0), listOfy_values.get(0)) == 0)
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

    /**
     * Checks if there are cells around the cell specified by loc1 and loc2 that are empty and therefore need
     * to be revealed.
     *
     * @param loc1  int, x coordinate of the cell
     * @param loc2  int, y coordinate of the cell
     * @return  boolean, if there are cells around the specified cell that are empty
     */
    public boolean needForReveal(int loc1, int loc2)
    {
        boolean needReveal = false;
        for (int i = loc1 - 1; i <= loc1 + 1; i++)
        {
            for (int j = loc2 - 1; j <= loc2 + 1; j++)
            {
                if(i >= 0 && j >= 0 && i < this.getSize() && j < this.getSize())
                {
                    if(this.getNumberAtLocation(i,j) == 0)
                    {
                        needReveal = true;
                    }
                }
            }
        }
        return needReveal;
    }

    /**
     * Counts how many cells have not yet been uncovered by the user.
     * In other words, how many cells have a "*" in {@link #cellsVisible}).
     *
     * @return  int, how many cells are not yet uncovered
     */
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
