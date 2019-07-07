package com.company;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame implements KeyListener
{

    public static GamePanel gamepanel = new GamePanel();
    public static int moves;

    //store here so easier to access
    Snake snake = gamepanel.snake;

    public MainFrame(String title)
    {
        super(title);

        addKeyListener(this);

        add(gamepanel);
    }




    public void keyTyped(KeyEvent e)
    {
        //not used
    }

    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_W) //if w is pressed
        {
            if(GamePanel.snake.lastDirection == 'D') //these make sure the snake doesnt 'eat itself' by going against itself
            {
                return;
            }
            gamepanel.addTurningPoint('U');
        }

        else if(e.getKeyCode() == KeyEvent.VK_A) //if a pressed
        {
            if(GamePanel.snake.lastDirection == 'R')
            {
                return;
            }
            gamepanel.addTurningPoint('L');
        }
        else if(e.getKeyCode() == KeyEvent.VK_S) //if s pressed
        {
            if(GamePanel.snake.lastDirection == 'U')
            {
                return;
            }
            gamepanel.addTurningPoint('D');

        }

        else if(e.getKeyCode() ==KeyEvent.VK_D) //if d is pressed
        {
            if(GamePanel.snake.lastDirection == 'L')
            {
                return;
            }
            gamepanel.addTurningPoint('R');
        }

        //after first movement



    }

    public void keyReleased(KeyEvent e)
    {
        //not used
    }

}
