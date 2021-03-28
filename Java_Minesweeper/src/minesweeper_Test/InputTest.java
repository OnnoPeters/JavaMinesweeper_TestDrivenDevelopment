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
    private int rand_size;
    private int rand_mines;

    @BeforeEach
    public void init()
    {
        input = new Input();
        Random rand = new Random();
        rand_size = rand.nextInt(90) + 10;
        rand_mines = rand.nextInt(rand_size * rand_size - 2) + 1;
    }

    @ParameterizedTest
    @CsvSource({"32,66", "5,2", "1,3", "123, 21", "77, 321", "0,0", "65,57", "-1,0", "5, -5", "-4, 129", "3,20"})
    public void testValidateInput(int size, int mines)
    {
        input.enterSize(size);
        input.enterMines(mines);
        boolean validInputs = input.validateInput();
        if (validInputs)
        {
            assertTrue(input.getSize() <= 100);
            assertTrue(input.getMines() < input.getSize() * input.getSize());
            assertTrue(input.getMines() > 0 && input.getSize() > 0);
        }
        else
        {
            assertTrue(input.getSize() > 100 || input.getMines() >= input.getSize() * input.getSize() || input.getMines() <= 0 || input.getSize() <= 0);
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
