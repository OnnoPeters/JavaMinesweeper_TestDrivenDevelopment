package minesweeper_UI;

import minesweeper_Code.Game;
import minesweeper_Code.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Extension of <code>JFrame</code> that contains a UI where settings can be entered and the game can be started
 *
 * @see JFrame
 */
public class GameMenuUI extends JFrame
{
    /**
     * <code>Input</code> instance that takes and validates the user inputs
     *
     * @see Input
     */
    Input input;

    /**
     * <code>Game</code> instance of the currently played game, if there is one.
     *
     * @see Game
     */
    Game game;

    /**
     * Creates a new <code>GameMenuUI</code>.
     *
     * @see GameMenuUI
     */

    /**
     * <code>JTextField</code> where the user can enter the preferred size.
     *
     * @see JTextField
     */
    JTextField txtFieldSize;

    /**
     * <code>JTextField</code> where the user can enter the preferred number of mines.
     *
     * @see JTextField
     */
    JTextField txtFieldMines;

    /**
     * <code>JButton</code> which the user can click to confirm the choices and start the game. Uses the
     * nested <code>ActionHandler</code> class to handle pressing of this button.
     *
     * @see JButton
     */
    JButton enterButton;

    /**
     * Creates a new <code>GameMenuUI</code>.
     *
     * @see GameMenuUI
     */
    public GameMenuUI()
    {
        input = new Input();
        this.setTitle("Minesweeper");
        this.setSize(1000, 620);
        this.setResizable(false);
        this.setLocation(50,50);
        this.initComponents();
        this.setVisible(true);
    }

    /**
     * Initializes the components and the layout of this menu.
     */
    private void initComponents()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        JPanel mainPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        txtFieldSize = new JTextField();
        txtFieldMines = new JTextField();
        JLabel lblSize = new JLabel();
        JLabel lblMines = new JLabel();
        enterButton = new JButton();
        JLabel enterlbl = new JLabel();

        mainPanel.setLayout(new FlowLayout());
        buttonPanel.setLayout(new GridBagLayout());
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

        txtFieldSize.setPreferredSize(new Dimension(100, 20));
        lblSize.setText("Enter size (1-100)");
        lblMines.setText("Enter amount of mines (smaller than size * size)");
        enterButton.setText("Start game");

        ActionHandler handler = new ActionHandler();
        txtFieldSize.addActionListener(handler);
        txtFieldMines.addActionListener(handler);
        enterButton.addActionListener(handler);

        panel1.add(txtFieldSize);
        panel1.add(txtFieldMines);
        panel2.add(lblSize);
        panel2.add(lblMines);
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        buttonPanel.add(enterButton);
        buttonPanel.add(enterlbl);

        getContentPane().add(mainPanel);
        getContentPane().add(buttonPanel);


        pack();
    }

    /**
     * Handles the clicking of the {@link #enterButton}.
     */
    private class ActionHandler implements ActionListener
    {
        /**
         * Starts the game and creates a <code>Game</code> instance in {@link #game} and also
         * creates a <code>GameUI</code>, where the actual game is played.
         *
         * @param event  <code>ActionEvent</code>, the enterbutton is clicked.
         */
        @Override
        public void actionPerformed(ActionEvent event)
        {

            if(event.getSource()==enterButton)
            {
                input.enterSize(txtFieldSize.getText());
                input.enterMines(txtFieldMines.getText());
                if(input.validateInput())
                {
                    game = input.startGame();
                    GameUI gameUI = new GameUI(game);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Entered data is not valid! Must be number above 0 and in the bounds described");
                }
            }

        }
    }
}
