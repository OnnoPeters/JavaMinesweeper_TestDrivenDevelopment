package minesweeper_Test;

import minesweeper_Code.Game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest
{
    @ParameterizedTest
    @CsvSource({"10,65", "5,2", "1,3", "54, 21", "77, 0", "0,0", "65,57"})
    public void testAction(int loc1, int loc2)
    {
        Random rand = new Random();
        int size = rand.nextInt(90) + 10;
        int mines = rand.nextInt(size * size - 2) + 1;
        Game game = new Game(size, mines);
        boolean valid = game.action(loc1, loc2);
        if(valid)
        {
            if (game.field.getNumberAtLocation(loc1, loc2) != - 1)
            {
                assertEquals(String.valueOf(game.field.getNumberAtLocation(loc1, loc2)), game.field.getVisibleCellAtLocation(loc1, loc2));
            }
            else
            {
                assertEquals(game.field.getVisibleCellAtLocation(loc1,loc2), "X");
            }
        }
        else
        {
            assertTrue(loc1 >= game.field.getSize() || loc2 >= game.field.getSize() || loc1 < 0 || loc2 < 0);
        }
    }

    @Test
    public void testRandomActions100Times()
    {
        Random rand = new Random();
        int size = rand.nextInt(90) + 10;
        int mines = rand.nextInt(size * size - 2) + 1;
        Game game = new Game(size, mines);
        Random randloc = new Random();
        int loc1, loc2;
        for (int i = 0; i < 100; i++) {
            loc1 = randloc.nextInt(size);
            loc2 = randloc.nextInt(size);
            boolean valid = game.action(loc1, loc2);
            if (valid) {
                if (game.field.getNumberAtLocation(loc1, loc2) != -1) {
                    assertEquals(String.valueOf(game.field.getNumberAtLocation(loc1, loc2)), game.field.getVisibleCellAtLocation(loc1, loc2));
                } else {
                    assertEquals(game.field.getVisibleCellAtLocation(loc1, loc2), "X");
                }
            } else {
                assertTrue(loc1 >= game.field.getSize() || loc2 >= game.field.getSize() || loc1 < 0 || loc2 < 0);
            }
        }
    }
}
