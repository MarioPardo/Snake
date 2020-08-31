package com.company;

import java.awt.*;

public class Apple
{
    int x;
    int y;

    double score = 100;

    double spawnTime;
    double currentTime;


    public void Spawn()
    {
     int numSquares = GamePanel.grid.width * GamePanel.grid.height;   //30 as of now
     double  randomGridDouble = Math.random();
     randomGridDouble = randomGridDouble * numSquares + 1;
      randomGridDouble = randomGridDouble - 1; //makes 0 - 29 which is accurate to grid size
      int randomGridCube = (int)randomGridDouble;

       x = GamePanel.grid.gridCubeArray.get(randomGridCube).x;
       y = GamePanel.grid.gridCubeArray.get(randomGridCube).y;

       for(Snake.Cube scube : GamePanel.snake.cubesOfSnake)  //makes sure apple doesnt spawn on snake
       {
           if(scube.y == y && scube.x == x)
           {
               Spawn();
           }
       }

       spawnTime = (System.currentTimeMillis() - GamePanel.gameStartTime);

    }



    public void drawApple( Graphics g)
    {
        g.setColor(Color.red);
        g.fillRect(x, y, 25, 25);

        currentTime = (System.currentTimeMillis() - (GamePanel.gameStartTime + spawnTime)) / 1000;

        if(!GamePanel.snake.dead)
            score = 100 - (currentTime * 10); //makes it so that it takes 10s for the score to go down to 0

        if(score <= 0 ) //makes sure score isnt negative
            score = 0;




    }

}
