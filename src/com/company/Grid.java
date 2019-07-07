package com.company;

import java.awt.*;
import java.util.ArrayList;

public class Grid
{
    static int width;
    static int height;

    static ArrayList<GridCube> gridCubeArray = new ArrayList<>();


    public Grid(int w, int h)
    {
        width = w;
        height = h;

        createGrid();
    }


    public void createGrid()
    {
        int x = 0;

        for(int i = 0; i < width; i ++)
        {
            int y = 0;
            for(int b = 0; b < height; b ++)
            {
                gridCubeArray.add(new GridCube(x, y ));
                y += 25;
            }
            x += 25;
        }
    }


    public void drawGrid(Graphics g)
    {
    for(GridCube cube : gridCubeArray)
    {
        cube.drawCube(g);
    }

    }


    public class GridCube
    {
        final int width = 25;
        final int height = 25;

        char direction = 'N';


        int x;
        int y;

        public GridCube(int xcoord, int ycoord)
        {
            x = xcoord;
            y = ycoord;
        }

        public void drawCube(Graphics g)
        {


            g.setColor(Color.black);
            g.fillRect(x, y, width, height);

            g.setColor(Color.darkGray);
            g.drawLine(x, y, x, y + height);//left
            g.drawLine(x + width,y, x+ width, y + height ); //right
            g.drawLine(x, y, x+ width, y);//top
            g.drawLine(x , y + 25, x + 25, y + 25); //bottom



        }




        }





    }

