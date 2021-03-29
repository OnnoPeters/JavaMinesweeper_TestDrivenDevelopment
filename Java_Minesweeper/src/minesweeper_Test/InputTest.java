package minesweeper_Test;

import minesweeper_Code.Game;
import minesweeper_Code.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class InputTest
{
    private Input input;
    private String rand_size;
    private String rand_mines;

    @BeforeEach
    public void init()
    {
        input = new Input();
        Random rand = new Random();
        rand_size = String.valueOf(rand.nextInt(90) + 10);
        rand_mines = String.valueOf(rand.nextInt(Integer.parseInt(rand_size) * Integer.parseInt(rand_size) - 2) + 1);
    }

    @ParameterizedTest
    @CsvSource({"32,66", "5,2", "1,3", "123, 21", "77, 321", "0,0", "65,57", "-1,0", "5, -5", "-4, 129", "3,20", "3F,6", "hgfdgdf,hgfddh", "4s, 5s", "10,5g"})
    public void testValidateInput(String size, String mines)
    {
        input.enterSize(size);
        input.enterMines(mines);
        boolean validInputs = input.validateInput();
        if (validInputs)
        {
            int intSize = Integer.parseInt(input.getSize());
            int intMines = Integer.parseInt(input.getMines());
            assertTrue(intSize <= 100);
            assertTrue(intMines < intSize * intSize);
            assertTrue(intMines > 0 && intSize > 0);
        }
        else
        {
            if(input.convertToIntPossible())
            {
                int intSize = Integer.parseInt(input.getSize());
                int intMines = Integer.parseInt(input.getMines());
                assertTrue(intSize > 100 || intMines >= intSize * intSize || intMines <= 0 || intSize <= 0);
            }
            else
            {
                assertFalse(input.convertToIntPossible());
            }
        }
    }

    @Test
    public void testStartGame()
    {
        input.enterSize(rand_size);
        input.enterMines(rand_mines);
        if(input.validateInput())
        {
            Game game = input.startGame();
            assertNotNull(game);
        }
        else
        {
            Game game = input.startGame();
            assertNull(game);
        }
    }
}
