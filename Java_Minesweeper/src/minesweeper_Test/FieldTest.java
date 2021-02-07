package minesweeper_Test;

import minesweeper_Code.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class FieldTest
{

    private Field field;

    @ParameterizedTest
    @ValueSource(ints = {0,1,5,8,12,20,43,67,78,99,100})
    public void testCorrectInitialization(int size)
    {
        field = new Field(size);

        assertSame(field.cellsVisible.length, size);
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

        assertTrue(size == field.max || size == field.min);
    }
}
