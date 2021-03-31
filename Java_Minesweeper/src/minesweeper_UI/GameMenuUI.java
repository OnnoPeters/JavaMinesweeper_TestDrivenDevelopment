package minesweeper_UI;

import minesweeper_Code.Game;
import minesweeper_Code.Input;

import javax.swing.*;
import javax.swing.text.BoxView;
import java.awt.*;
import java.awt.event.*;

public class GameMenuUI
{
    Input input;
    Game game;

    public GameMenuUI()
    {
        input = new Input();
        MainFrame frame = new MainFrame();
        frame.setTitle("Minesweeper");
        frame.setSize(1000, 620);
        frame.setResizable(false);
        frame.setLocation(50,50);
        frame.initComponents();
        frame.setVisible(true);
    }

    private class MainFrame extends JFrame
    {

        JTextField txtFieldSize;
        JTextField txtFieldMines;
        JButton enterButton;

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

        private class ActionHandler implements ActionListener
        {
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




}
