
package com.william.graphing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import java.awt.Canvas;
import java.awt.Graphics;
import java.util.List;


public class Bananas extends Canvas implements ActionListener  {
    private static List<Building>buildings;
    private static CityScape cityScapes;
    public int angle = 690;
    public double radians;
    public double velocity;
    public long time;
    public static final int WIDTH = 1100;
    public static final int HEIGHT = 500;
    public static final int WIDTHMIN = 20;
    public static final int WIDTHMAX = 100;
    public static final int HEIGHTMIN = 80;
    public static final int HEIGHTMAX = 100;
    public int publicI;
    public int calculatedY;
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
        time = System.currentTimeMillis();
        velocity = 50d / 1000d;
        new Timer(125, this).start();
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
                throw new RuntimeException(e);
            }
        }
   double timeDelta = System.currentTimeMillis() - time;
        for(Building b: buildings) {

            if (b.buildingHit(publicI,calculatedY)){
                System.out.println("Building hit");
            }
            if(b instanceof CanonBuilding) {
                double bIntercept = (b.getYCoordinate()-b.getHeight()) - b.getXCoordinate() * radians;
                int initX = (int)(b.getXCoordinate() + timeDelta * velocity);
                System.out.println(timeDelta + "::" + initX);
                for (int i = initX; i < initX + 10; i += 2) {
                    g.setColor(Color.BLACK);
                    calculatedY = (int) ((radians * i) + bIntercept + .5 * .001 * timeDelta * timeDelta / 100);
                    g.fillOval(i + b.getWidth(), calculatedY, 10, 10);
                    publicI = i;
                    //if(i >= WIDTH){
                    //    System.out.println("Hello");
                    //}
                    //if(calculatedY >= HEIGHT){
                    //    System.out.println("Down we go");
                    //}

                }

            }

        }



    }




    private Color getRandomColor() {
        return new Color(getRandomNumber(0, 225), getRandomNumber(0, 225), getRandomNumber(0, 0));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
