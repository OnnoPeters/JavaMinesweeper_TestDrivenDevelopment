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
        for (int i = 0; i < field.cellsVisible.length; i++)
        {
            assertSame(field.cellsVisible.length, field.cellsVisible[i].length);
            for (int j = 0; j < field.cellsVisible.length; j++)
            {
                assertEquals("*", field.cellsVisible[i][j]);
            }
        }
    }
}
