package minesweeper_Test;

import minesweeper_Code.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FieldTest
{

    private Field field;

    @BeforeEach
    public void initField()
    {
        field = new Field();
    }

    @Test
    public void testCorrectInitialization()
    {
        assertSame(field.cellsHidden.length == field.cellsVisible.length)
        for (int i = 0; i < field.cellsHidden.length; i++)
        {
            assertSame(field.cellsHidden.length, field.cellsHidden[i].length);
            assertSame(field.cellsVisible.length, field.cellsVisible[i].length);
            for (int j = 0; j < field.cellsHidden.length; j++)
            {
                assertEquals(0, field.cellsHidden[i][j]);
                assertEquals("*", field.cellsVisible[i][j]);
            }
        }
    }
}
