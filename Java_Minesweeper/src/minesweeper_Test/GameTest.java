package minesweeper_Test;

import minesweeper_Code.Game;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest
{
    public Game game;
    public int size;
    public int mines;

    @BeforeEach
    public void init()
    {
        Random rand = new Random();
        size = rand.nextInt(90) + 10;
        mines = rand.nextInt(size * size - 2) + 1;
        this.game = new Game(size, mines);
    }
    @ParameterizedTest
    @CsvSource({"10,65", "5,2", "1,3", "54, 21", "77, 0", "0,0", "65,57"})
    public void testAction(int loc1, int loc2)
    {
        boolean valid = game.isValid(loc1, loc2);
        if(valid)
        {
            int numberAtLocation = game.action(loc1,loc2);
            if (numberAtLocation != - 1)
            {
                assertEquals(String.valueOf(numberAtLocation), game.field.getVisibleCellAtLocation(loc1, loc2));
            }
            else
            {
                assertEquals(game.field.getVisibleCellAtLocation(loc1,loc2), "X");
                assertTrue(game.getEndingStatus());
                assertFalse(game.getWinningStatus());
            }
        }
        else
        {
            assertTrue(loc1 >= game.field.getSize() || loc2 >= game.field.getSize() || loc1 < 0 || loc2 < 0 || game.getEndingStatus());
        }
    }

    @Test
    public void testRandomActions100Times()
    {
        Random randloc = new Random();
        int loc1, loc2;
        for (int i = 0; i < 100; i++) {
            loc1 = randloc.nextInt(size);
            loc2 = randloc.nextInt(size);
            boolean valid = game.isValid(loc1, loc2);
            int numberAtLocation = game.action(loc1,loc2);
            if (valid) {
                if (numberAtLocation != -1) {
                    assertEquals(String.valueOf(numberAtLocation), game.field.getVisibleCellAtLocation(loc1, loc2));
                }
                else
                {
                    assertEquals(game.field.getVisibleCellAtLocation(loc1, loc2), "X");
                    assertTrue(game.getEndingStatus());
                    assertFalse(game.getWinningStatus());
                }
            }
            else
            {
                assertTrue(loc1 >= game.field.getSize() || loc2 >= game.field.getSize() || loc1 < 0 || loc2 < 0 || game.getEndingStatus());
            }
        }
    }

    @Test
    public void testUnsuccessfulEndingOfGame()
    {
        game.endGameUnsuccessfully();
        assertTrue(game.getEndingStatus());
        assertFalse(game.getWinningStatus());
    }

    @Test
    public void testSuccessfulEndingOfGame()
    {
        game.endGameSuccessfully();
        assertTrue(game.getEndingStatus());
        assertTrue(game.getWinningStatus());
    }

    @Test
    public void testRevealField()
    {
        game.revealField();
        for(int i = 0; i < game.field.getSize(); i++)
        {
            for(int j = 0; j < game.field.getSize(); j++)
            {
                if(game.field.getNumberAtLocation(i,j) != -1)
                {
                    assertEquals(String.valueOf(game.field.getVisibleCellAtLocation(i,j)), game.field.getVisibleCellAtLocation(i,j));
                }
                else
                {
                    assertEquals(String.valueOf(game.field.getVisibleCellAtLocation(i,j)), "X");
                }
            }
        }

    }
}
