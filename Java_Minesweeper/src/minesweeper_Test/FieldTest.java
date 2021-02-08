package minesweeper_Test;

import minesweeper_Code.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
}
