package com.company;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main
{

    public static MainFrame frame = new MainFrame("Snake");


    public static void main(String[] args)
    {
	frame.setSize(1000,1000);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);


    }



}
