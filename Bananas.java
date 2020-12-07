
package com.william.graphing;

import java.awt.Color;
import java.io.IOException;
import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Graphics;
import java.util.List;


public class Bananas extends Canvas {
    private static List<Building>buildings;
    private static CityScape cityScapes;
    public int angle = 690;
    public double radians;
    public static final int WIDTH = 1100;
    public static final int HEIGHT = 500;
    public static final int WIDTHMIN = 20;
    public static final int WIDTHMAX = 100;
    public static final int HEIGHTMIN = 80;
    public static final int HEIGHTMAX = 100;



    public static void main(String[] args) {
        JFrame frame = new JFrame("My Drawing");
        Bananas canvas = new Bananas();
        canvas.setSize(WIDTH,HEIGHT);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);

    }


    public Bananas(){
        //CityScape cityScape = new CityScape(new FullSpectrumRandomColorGenerator());
        CityScape cityScape = new CityScape(new NumberOfColorsRandomGenerator());
        buildings = cityScape.buildBuildings();
        radians = Math.toRadians(angle);
        radians = Math.tan(radians);
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void paint(Graphics g) {
        int x = 0;

        for(Building b: buildings) {
            g.setColor(b.getColor());
            try {
                x += b.draw(g, x, HEIGHT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
/*
            if (x >= WIDTH - 100) {
                g.setColor(b.getColor());
                //g.fillRect( x,HEIGHT - rectHeight,rectWidth,rectHeight);
                g.fillRect(x,HEIGHT - b.getHeight(),WIDTH - x,b.getHeight());

                int lastWidth = WIDTH - x;
                x = WIDTH;
                System.out.println("x is: " + x);
            } else  {
                    //g.fillRect(x,HEIGHT - rectHeight,rectWidth,rectHeight);
                    g.setColor(b.getColor());
                    g.fillRect(x,HEIGHT - b.getHeight(),b.getWidth(),b.getHeight());
                    x += b.getWidth();
                    System.out.println("x is: " + x);
            }
        }

 */
        for(Building b: buildings) {

            if(b instanceof CanonBuilding) {
                double bIntercept = (b.getYCoordinate()-b.getHeight()) - b.getXCoordinate() * radians;
                for (int i = b.getXCoordinate(); i < 1000; i += 2) {
                    g.setColor(Color.BLACK);
                    g.fillOval(i + b.getWidth(), (int) ((radians * i) + bIntercept), 10, 10);
                }
            }
        }

    }




    private Color getRandomColor() {
        return new Color(getRandomNumber(0, 225), getRandomNumber(0, 225), getRandomNumber(0, 0));
    }
}
