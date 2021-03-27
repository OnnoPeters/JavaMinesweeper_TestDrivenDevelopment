package minesweeper_Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Random;

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
        game.action(loc1, loc2);
        if (game.field.getNumberAtLocation(loc1, loc2) != - 1)
        {
            assertEquals((String) game.field.getNumberAtLocation(loc1, loc2), game.field.getVisibleCellAtLocation(loc1, loc2));
        }
        else
        {
            assertEquals(game.field.getVisibleCellAtLocation(loc1,loc2), "X");
        }
    }
}
