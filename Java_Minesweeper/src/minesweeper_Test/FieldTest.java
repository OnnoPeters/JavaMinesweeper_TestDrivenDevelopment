package minesweeper_Test;

import minesweeper_Code.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class FieldTest
{

    private Field field;

    @ParameterizedTest
    @ValueSource(ints = {12,20,43,67,78,99,100})
    public void testCorrectInitialization(int size)
    {
        field = new Field(size);

        assertSame(field.cellsVisible.length, field.getSize());
        for (int i = 0; i < field.cellsVisible.length; i++)
        {
            assertSame(field.cellsVisible.length, field.cellsVisible[i].length);
            for (int j = 0; j < field.cellsVisible.length; j++)
            {
                assertEquals("*", field.cellsVisible[i][j]);
            }
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {-12, 10000, -5354})
    public void testInvalidValues(int size)
    {
        field = new Field(size);

        assertTrue(field.getSize() == field.getMax() || field.getSize() == field.getMin());
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,6,21,45,53,74,123,5654,4323443,-543})
    public void testMines(int mines)
    {
        Random rand = new Random();
        testCorrectInitialization(rand.nextInt(90) + 10);

        field.setMines(mines);

        int countTotalMines = 0;
        for (int i = 0; i < field.cellsVisible.length; i++) {
            for (int j = 0; j < field.cellsVisible[i].length; j++) {
                if(field.getNumberAtLocation(i,j) == -1)
                {
                    countTotalMines++;
                }
                else
                {
                   int countNeighbouringMines = 0;
                   for(int k = i-1; k <= i+1; k++)
                   {
                       for(int m = j-1; m <= j+1; m++)
                       {
                           if(k >= 0 && m >= 0 && k < field.cellsVisible.length && m < field.cellsVisible[k].length && field.getNumberAtLocation(k,m) == -1)
                           {
                               countNeighbouringMines++;
                           }
                       }
                   }
                    assertEquals(countNeighbouringMines, field.getNumberAtLocation(i, j));
                }

            }
        }
        assertTrue(countTotalMines > 0 && countTotalMines < field.getSize() * field.getSize());
    }

    @Test
    public void testRevealEmptyCells()
    {
        Random rand = new Random();
        testCorrectInitialization(rand.nextInt(90) + 10);
        field.setMines(1);
        int mine_coordinate_x = 0;
        int mine_coordinate_y = 0;
        boolean actionPerformed = false;
        for(int i = 0; i < field.getSize(); i++)
        {
            for(int j = 0; j < field.getSize(); j++)
            {
                if (field.getNumberAtLocation(i, j) == -1)
                {
                    mine_coordinate_x = i;
                    mine_coordinate_y = j;
                    break;
                }
                else if(field.getNumberAtLocation(i,j) == 0)
                {
                    if(!actionPerformed)
                    {
                        field.revealEmptyCells(i,j);
                        actionPerformed = true;
                    }
                }
            }
        }

        for(int i = 0; i < field.getSize(); i++)
        {
            for (int j = 0; j < field.getSize(); j++)
            {
                if(i == mine_coordinate_x && j == mine_coordinate_y)
                {
                    assertEquals(field.getVisibleCellAtLocation(i,j), "*");
                }
                else if(i >= mine_coordinate_x - 1 && i <= mine_coordinate_x + 1 && j >= mine_coordinate_y - 1 && j <= mine_coordinate_y + 1)
                {
                    boolean neighborOfEmptyCell = false;
                    for(int k = i - 1; k <= i + 1; k++)
                    {
                        for(int l = j - 1; l <= j + 1; l++)
                        {
                            if(k >= 0 && l >= 0 && k < field.getSize() && l < field.getSize() && field.getNumberAtLocation(k,l) == 0)
                            {
                                neighborOfEmptyCell = true;
                                break;
                            }
                        }
                        if(neighborOfEmptyCell)
                        {
                            break;
                        }
                    }
                    if(neighborOfEmptyCell)
                    {
                        assertEquals(field.getVisibleCellAtLocation(i,j), "1");
                    }
                    else
                    {
                        assertEquals(field.getVisibleCellAtLocation(i,j), "*");
                    }
                }
                else
                {
                    assertEquals(field.getVisibleCellAtLocation(i,j), "0");
                }

            }
        }





    }
}
