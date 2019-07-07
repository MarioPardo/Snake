package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener
{

   static  Snake snake = new Snake();

    static Grid grid = new Grid(30,30);

    Timer timer = new Timer(50,this);  //creates timer that updates every second

    static Graphics graphics;

    static Apple apple = new Apple();


    public static int points = 0;


    public static double gameStartTime;


    public GamePanel()
    {

        gameStartTime = System.currentTimeMillis();
        timer.start();
        apple.Spawn();
    }








    public void paintComponent(Graphics g)
    {
        graphics = g;

        super.paintComponent(g);

        setBackground(Color.black);

        grid.drawGrid(g);

        snake.drawSnake(g);

        apple.drawApple(g);


        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));  //creates new font
        g.drawString("Points: " + points,800,  50);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));  //creates new font
        g.drawString("Length : " + snake.length, 800, 100);

        g.drawString("Apple Score : " + apple.score, 800, 250);




    }

    public void actionPerformed(ActionEvent e)  //called by timer, so every frame
    {
        repaint();  //calls the painComponent Method




    }


    public void  addTurningPoint(char d)
    {

        int cubex, cubey;

        if(snake.lastDirection == 'U')
        {
            cubex = snake.leadingCube.x;
            cubey = snake.leadingCube.y;

            for(Grid.GridCube cube : grid.gridCubeArray)
            {
                if(cube.x == cubex && cube.y == cubey)
                {
                    cube.direction = d;
                }
            }

            return;
        }

        if(snake.lastDirection == 'D')
        {
            cubex = snake.leadingCube.x;
            cubey = snake.leadingCube.y;

            for(Grid.GridCube cube : grid.gridCubeArray)
            {
                if(cube.x == cubex && cube.y == cubey)
                {
                    cube.direction = d;
                }
            }

            return;
        }

        if(snake.lastDirection == 'R')
        {
            cubex = snake.leadingCube.x;
            cubey = snake.leadingCube.y;

            for(Grid.GridCube cube : grid.gridCubeArray)
            {
                if(cube.x == cubex && cube.y == cubey)
                {
                   cube.direction = d;
                }
            }

            return;
        }

        if(snake.lastDirection == 'L')
        {

            cubex = snake.leadingCube.x;
            cubey = snake.leadingCube.y;

            for(Grid.GridCube cube : grid.gridCubeArray)
            {
                if(cube.x == cubex && cube.y == cubey)
                {
                    cube.direction = d;
                }
            }

            return;
        }

    }


}
