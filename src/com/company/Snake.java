package com.company;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Snake
{
    public static int length = 3;
    public static int x = 0;
    public static int y;


    public static int turningPointSize;
    public static ArrayList <TurningPoint> turningPoints = new ArrayList<>();

    public static char direction = 'R';
    public static char lastDirection ;
    public static boolean turning = false;

    public static int turningCounter;
    public static int count;

    public static Cube leadingCube;
    public static Cube lastCube;

    public static boolean dead = false;
    int i; //the id number for the cubes





    public static Graphics graphics;


    public static ArrayList<Cube> cubesOfSnake = new ArrayList<>();

    public Snake()
    {
        int cubex = x;
        for( i = 0; i < length; i++)
        {
            cubesOfSnake.add(new Cube(cubex,0, i));
            cubex += 25;
        }

        leadingCube = cubesOfSnake.get(length - 1);
        leadingCube.isLeading = true;

        lastCube = cubesOfSnake.get(0);
        lastCube.isLast = true;

        lastDirection = leadingCube.direction;

    }


    public void drawSnake(Graphics g)
    {

        checkCollision();
        leadingCube.checkAppleCollision();

        if(dead)
        {
            cubesOfSnake.clear();
            return;
        }

        graphics = g;


        lastDirection = leadingCube.direction;

        for(Cube cube : cubesOfSnake)
        {
            cube.drawCube(g);
        }





    }





    //////for getting turningpoints

    public static Cube getRightMost()
    {
        int x = 0;

        for(Cube cube : cubesOfSnake)
        {
            if(cube.x > x)
            {
                x = cube.x;
            }
        }

        for(Cube cube : cubesOfSnake)  //get the cube at this rightmost x
        {
            if(cube.x == x)
            {
                return cube;
            }
        }

        return null;
    }

    public static Cube getLeftMost()
    {

        int x = 100000;

        for(Cube cube : cubesOfSnake)
        {
            if(cube.x < x)
            {
                x = cube.x;
            }
        }

        for(Cube cube : cubesOfSnake)  //get the cube at this leftmost x
        {
            if(cube.x == x)
            {
                return cube;
            }
        }


        return null;
    }

    public static Cube getDownMost()
    {
        int y = 0;

        for(Cube cube : cubesOfSnake)
        {
            if(cube.y > y)
            {
                y = cube.y;
            }
        }

        for(Cube cube : cubesOfSnake)  //get the cube at this leftmost x
        {
            if(cube.y == y)
            {
                return cube;
            }
        }

        return null;
    }

    public static Cube getUpMost()
    {
        int y = 100000;

        for(Cube cube : cubesOfSnake)
        {
            if(cube.y < y)
            {
                y = cube.y;
            }
        }

        for(Cube cube : cubesOfSnake)  //get the cube at this leftmost x
        {
            if(cube.y == y)
            {
                return cube;
            }
        }

        return null;
    }




    ///gameplay functions
    public void grow()
    {
        switch(lastCube.direction)
        {
            case 'U':
                cubesOfSnake.add(0, new Cube(lastCube.x , lastCube.y + 25, ++i));
                break;
            case 'D':
                cubesOfSnake.add(0, new Cube(lastCube.x , lastCube.y - 25, ++i));
                break;
            case 'R':
                cubesOfSnake.add(0, new Cube(lastCube.x - 25 , lastCube.y, ++i));
                break;
            case 'L':
                cubesOfSnake.add(0, new Cube(lastCube.x + 25 , lastCube.y , ++i));
                break;
        }


        lastCube.isLast = false;
        cubesOfSnake.get(0).direction = lastCube.direction;

        lastCube = cubesOfSnake.get(0);
        lastCube.isLast = true;

        length = cubesOfSnake.size();



    }


    public void die()
    {
        dead = true;

        for(Cube cube : cubesOfSnake)
        {
            cube.explode();
        }


    }


    public void checkCollision()
    {
        //within the snake itself
        for(Cube cube1 : cubesOfSnake)
        {
            for(Cube cube2 : cubesOfSnake)
            {
                if((cube1.x == cube2.x) && (cube1.y == cube2.y) && (cube1.ID != cube2.ID))
                {
                    die();
                }
            }
        }

        //with the edges of the game
        if(leadingCube.x > (GamePanel.grid.width * 25 ) || leadingCube.x < 0|| leadingCube.y > (GamePanel.grid.height * 25) || leadingCube.y < 0)
        {
            die();
        }





    }


















   ///////////////// //class for each cube  ///////////////////
    public  class Cube
    {
        public  int width = 25;
        public  int height = 25;
        public int x;
        public int y;

        public char direction = 'R';

        public boolean isLeading = false;
        public boolean isLast = false;

        public int R = 255;
        public int G = 0;
        public int B = 0;

        public int step = 1;


        public int ID;


        public Cube(int xcoord, int ycoord, int id)
        {
            x = xcoord;
            y = ycoord;

            ID = id;
        }

        void drawCube(Graphics g)
        {
            move();

            g.setColor(rainbow());

            if(isLast) //make the tail a triangle
            {
                g.fillOval(x, y, 25, 25);

            }
            else
                {


                g.fillRect(x, y, width, height);

                g.setColor(Color.BLACK);
                g.drawLine(x, y, x + width, y);//top line
                g.drawLine(x, y, x, y + height); //left line
                g.drawLine(x + width, y, x + width, y + height);//right line
                g.drawLine(x, y + height, x + width, y + height);

                if (isLeading)
                    g.fillRect(x + 10, y + 10, 5, 5);
            }
        }

        void move()
        {

            for(Grid.GridCube cube : Grid.gridCubeArray)
            {
                if(cube.x == x && cube.y == y)
                {

                    if(cube.direction == 'N')
                    {

                    }
                    else
                    {

                        if(direction == 'D' && cube.direction == 'U')
                        return;

                        if(direction =='U' && cube.direction == 'D')
                        return;

                        if(direction == 'R' && cube.direction == 'L')
                            return;

                        if(direction == 'L' && cube.direction == 'R')
                            return;

                        direction = cube.direction;
                        
                            ///when the last cube goes over it make the direction of the gridcube be n
                            if(isLast)
                            {
                               direction = cube.direction;
                                cube.direction = 'N';
                            }


                    }

                }
            }


            switch (direction)
            {
                case 'U':
                    y -= height;
                    break;
                case 'D':
                    y += height;
                    break;
                case 'R':
                    x += width;
                    break;
                case 'L':
                    x -= width;
                    break;

            }
        }


        public  void checkAppleCollision()
        {
            if(GamePanel.apple.x == x && GamePanel.apple.y == y)
            {
                GamePanel.snake.grow();
                GamePanel.apple.Spawn();
                GamePanel.points+= GamePanel.apple.score;
            }

        }

        public void explode()
        {
            Graphics g = GamePanel.graphics;

            g.setColor(Color.red);
            g.fillRect(x, y, width, height);

            g.setColor(Color.BLACK);
            g.drawLine(x, y, x+width, y);//top line
            g.drawLine(x, y, x , y + height); //left line
            g.drawLine(x + width, y, x+ width, y + height);//right line
            g.drawLine(x, y + height, x + width, y + height);

        }


        public Color rainbow()  //this makes the snake's cubes have a rainbow pattern
        {



            if(R == 255 && G < 255  && B < 255 && step == 1)
            {

                G += 5;
                return  new Color(R, G, B);
            }

            if(R <= 255 && R != 0 && G == 255 && B < 255)
            {
                step = 2;

                if(R > 0) {
                    R -= 5;
                }

                return new Color(R,G,B);

            }

            if(R == 0 && G == 255 && B < 255 )
            {
                step = 3;

                B += 5;
                return  new Color(R, G, B);
            }

            if(R == 0 && G <= 255 && G != 0 && B == 255)
            {
                step = 4;

                if(G > 0)
                {
                    G -= 5;
                }
                return new Color(R,G,B);
            }

            if(R >= 0 && R != 255 && G == 0 && B == 255)
            {
                step = 5;

                if(R <= 250)
                R += 5;
                return new Color(R,G,B);
            }

            if(R == 255 && G == 0 && B <= 255)
            {
                step = 6;

                if(B > 0)
                {
                    B -= 5;
                }

                if(R == 255 && G == 0 && B == 0)
                {
                    step = 1;
                }

                return new Color(R,G,B);
            }


            return new Color(R,G,B);
        }



        }




    }


