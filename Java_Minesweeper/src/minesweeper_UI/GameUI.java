package minesweeper_UI;

import minesweeper_Code.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameUI
{
    Game game;

    public GameUI(Game game)
    {
        this.game = game;
        GameUI.MainFrame frame = new GameUI.MainFrame();
        frame.setTitle("Minesweeper");
        frame.setSize(1000, 620);
        frame.setResizable(false);
        frame.setLocation(50, 50);
        frame.initComponents();
        frame.setVisible(true);
    }

    private class MainFrame extends JFrame
    {
        JButton[][] buttonArray = new JButton[game.field.getSize()][game.field.getSize()];

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


        private class ActionHandler implements ActionListener
        {
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
}

