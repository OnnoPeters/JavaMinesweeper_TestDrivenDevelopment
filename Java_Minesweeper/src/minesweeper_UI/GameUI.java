package minesweeper_UI;

import minesweeper_Code.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * UI where the actual Minesweeper game is played.
 */
public class GameUI extends JFrame
{
    /**
     * Game that is played in this UI.
     */
    Game game;

    /**
     * Visible grid of <code>JButtons</code>, each button representing one cell in the field of the {@link #game}.
     */
    JButton[][] buttonArray;

    /**
     * Creates a new GameUI with the specified <code>Game</code> to be set as {@link #game}.
     *
     * @param game  Game, game to be played
     */
    public GameUI(Game game)
    {
        this.game = game;
        this.buttonArray = new JButton[game.field.getSize()][game.field.getSize()];
        this.setTitle("Minesweeper");
        this.setSize(1000, 620);
        this.setResizable(false);
        this.setLocation(50, 50);
        this.initComponents();
        this.setVisible(true);
    }

    /**
     * Initializes components for the game.
     */
    private void initComponents()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(game.field.getSize(), game.field.getSize()));

        ActionHandler handler = new ActionHandler();

        for (int i = 0; i < game.field.getSize(); i++) {
            for (int j = 0; j < game.field.getSize(); j++) {
                JButton jbutton = new JButton();
                jbutton.setPreferredSize(new Dimension(20, 20));
                jbutton.setName(i + "," + j);
                jbutton.setMargin(new Insets(0,0,0,0));
                jbutton.setText(game.field.getVisibleCellAtLocation(i,j));
                jbutton.addActionListener(handler);
                jbutton.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseReleased(MouseEvent e)
                    {
                        if(SwingUtilities.isRightMouseButton(e))
                        {
                            jbutton.setEnabled(!jbutton.isEnabled());
                            if(jbutton.getBackground() == Color.RED)
                            {
                                jbutton.setBackground(null);
                            }
                            else
                            {
                                jbutton.setBackground(Color.RED);
                            }
                        }
                        else
                        {
                                super.mouseReleased(e);
                        }
                    }
                });
                buttonArray[i][j] = jbutton;
                getContentPane().add(buttonArray[i][j]);
                getContentPane().add(jbutton);
            }
        }

        pack();
    }

    /**
     * Handles interactions of the user.
     */
    private class ActionHandler implements ActionListener
    {
        /**
         * Handles a click of the user and reacts according to the rules of the game, depending on what type of
         * cell was clicked.
         *
         * @param event  <code>ActionEvent</code>, the user clicks a button of the {@link #buttonArray}.
         */
        @Override
        public void actionPerformed(ActionEvent event)
        {

            if (event.getSource() instanceof JButton) {
                String identity = (((JButton) event.getSource()).getName());
                String[] x_y = identity.split(",");
                int x = Integer.parseInt(x_y[0]);
                int y = Integer.parseInt(x_y[1]);
                if(game.isValid(x,y))
                {
                    int numberAtLocation = game.action(x,y);
                    if(game.field.needForReveal(x,y))
                    {
                        for (int i = 0; i < game.field.getSize(); i++)
                        {
                            for (int j = 0; j < game.field.getSize(); j++)
                            {
                                if(game.field.getVisibleCellAtLocation(i, j).equals("0"))
                                {
                                    buttonArray[i][j].setText("");
                                }
                                else
                                {
                                    buttonArray[i][j].setText(game.field.getVisibleCellAtLocation(i, j));
                                }
                            }
                        }
                    }
                    else
                    {
                        if(game.field.getVisibleCellAtLocation(y, y).equals("0"))
                        {
                            buttonArray[x][y].setText("");
                        }
                        else
                        {
                            buttonArray[x][y].setText(String.valueOf(numberAtLocation));
                        }
                    }
                    for (int i = 0; i < game.field.getSize(); i++)
                    {
                        for (int j = 0; j < game.field.getSize(); j++)
                        {
                            if(game.field.getVisibleCellAtLocation(i, j).equals("0"))
                            {
                                buttonArray[i][j].setText("");
                            }
                            else
                            {
                                buttonArray[i][j].setText(game.field.getVisibleCellAtLocation(i, j));
                            }
                        }
                    }
                    if(game.getEndingStatus())
                    {
                        for (int i = 0; i < game.field.getSize(); i++) {
                            for (int j = 0; j < game.field.getSize(); j++) {
                                if(game.field.getVisibleCellAtLocation(i, j).equals("0"))
                                {
                                    buttonArray[i][j].setText("");
                                }
                                else
                                {
                                    buttonArray[i][j].setText(game.field.getVisibleCellAtLocation(i, j));
                                }
                            }
                        }
                        if(game.getWinningStatus())
                        {
                            JOptionPane.showMessageDialog(null, "You have won the game!");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "You hit a mine!");
                        }
                        setVisible(false);
                        dispose();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Something went wrong (Invalid cell position)!");
                }
            }

        }
    }
}

