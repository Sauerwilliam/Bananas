
package com.william.graphing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.Canvas;
import java.awt.Graphics;
import java.io.InputStreamReader;
import java.util.*;


public class Bananas extends Canvas implements ActionListener {
    private List<Building> buildings;
    private List<Bullet> bullets = new ArrayList<>();
    private static CityScape cityScapes;

    public Bullet test;
    public int angle = 690;//Integer.parseInt(reader.readLine());
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
    public Building buildingHit;
    public static boolean destroyBullet;

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws InterruptedException, IOException {

        JFrame frame = new JFrame("My Drawing");
        Bananas canvas = new Bananas();
        canvas.setSize(WIDTH, HEIGHT);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);

        while(true) {
            if(destroyBullet) {
                Thread.sleep(1000l);
            } else {
                canvas.angle = 690; //Integer.parseInt(reader.readLine());
                canvas.time = System.currentTimeMillis();
                destroyBullet = false;

            }
        }

    }


    public Bananas() throws IOException {
        //CityScape cityScape = new CityScape(new FullSpectrumRandomColorGenerator());
        CityScape cityScape = new CityScape(new NumberOfColorsRandomGenerator());
        test = new Bullet(250,250,-25,25,System.currentTimeMillis());
        bullets.add(test);
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

        for (Building b : buildings) {
            g.setColor(b.getColor());
            try {
                x += b.draw(g, x, HEIGHT);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        for (Bullet b: bullets) {
            b.draw(g);
        }
        if (destroyBullet){
            return;
        }
        double timeDelta = System.currentTimeMillis() - time;
        Building hitBuilding = isAnyBuildingHit((int) test.getBulletX(), (int) test.getBulletY());

        for (Building b : buildings) {

            if(hitBuilding != null) {
                System.out.println("Buildinghit");
                System.out.println("Building hit " + hitBuilding.getXCoordinate() + "," + hitBuilding.getYCoordinate());
                buildings.set(buildings.indexOf(buildingHit), new RubbleBuilding(buildingHit));
                destroyBullet = true;
            }
            //
//            if (b instanceof CanonBuilding) {
//                double bIntercept = (b.getYCoordinate() - b.getHeight()) - b.getXCoordinate() * radians;
//                int initX = (int) (b.getXCoordinate() + timeDelta * velocity) + b.getWidth();
//                System.out.println(timeDelta + "::" + initX);
//                for (int i = initX; i < initX + 10; i += 2) {
//                    g.setColor(Color.BLUE);
//                    calculatedY = (int) ((radians * i) + bIntercept + .5 * .001 * timeDelta * timeDelta / 100);
//                    g.fillOval(i, calculatedY, 1, 1);
//
//                    //if(i >= WIDTH){
//                    //    System.out.println("Hello");
//                    //}
//                    //if(calculatedY >= HEIGHT){
//                    //          if ()
//                    //}
//                    Building hitBuilding = isAnyBuildingHit(i, calculatedY);
//                    if(i >= HEIGHT){
//                        destroyBullet = true;
//                    }
//                    if (hitBuilding != null) {
//                        System.out.println("Building hit " + hitBuilding.getXCoordinate() + "," + hitBuilding.getYCoordinate());
//                        buildings.set(buildings.indexOf(buildingHit), new RubbleBuilding(buildingHit));
//                        destroyBullet = true;
//
//                    }
//
//                }

//            }

        }


    }


    public Building isAnyBuildingHit(int startX, int startY) {
        for (Building b : buildings) {
            if (b.buildingHit(startX, startY)) {
                buildingHit = b;
                return b;

            }
        }
        return null;
    }


    private Color getRandomColor() {
        return new Color(getRandomNumber(0, 225), getRandomNumber(0, 225), getRandomNumber(0, 0));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
