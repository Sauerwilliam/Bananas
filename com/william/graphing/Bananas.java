
package com.william.graphing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Bananas extends Canvas implements ActionListener {
    private final List<Building> buildings;
    private static final List<Bullet> bullets = new ArrayList<>();
    private static CityScape cityScape;

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
    public Building hitBuilding;
    public int calculatedY;
    public Building buildingHit;
    public static boolean destroyBullet;
    public Building cannonBuilding;

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws InterruptedException, IOException {

        JFrame frame = new JFrame("My Drawing");

        Bananas canvas = new Bananas();
        JTextField testText = new JTextField("Hello ag ahsfjdhaglkdjghalskdjghalsdkg ");
        testText.setSize(WIDTH, 50);
        testText.addActionListener(canvas);
        frame.getContentPane().setLayout(new FlowLayout());
        canvas.setSize(WIDTH, HEIGHT);
        frame.add(testText);
        frame.add(canvas);
        frame.pack();
        frame.setSize(frame.getWidth()-30, frame.getHeight());

        //frame.setSize(frame.getWidth(), frame.getHeight() + 100);
        frame.setVisible(true);



        for (Building b : canvas.buildings){
            if (b instanceof CannonBuilding){
                canvas.cannonBuilding = b;
            }

        }
        if(canvas.cannonBuilding == null) {
            throw new RuntimeException("Huh");
        }

    }


    public Bananas() throws IOException {
        //CityScape cityScape = new CityScape(new FullSpectrumRandomColorGenerator());
        CityScape cityScape = new CityScape(new NumberOfColorsRandomGenerator());
        //bullets.add(new Bullet(250,250,-25,25,System.currentTimeMillis()));
        //bullets.add(new Bullet(250,250,-45,15,System.currentTimeMillis()));

        buildings = cityScape.buildBuildings();
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


        if (destroyBullet) {
            return;
        }
        double timeDelta = System.currentTimeMillis() - time;


        for (Building b : buildings) {
/*
            if (b instanceof CannonBuilding) {
                cannonBuilding = b;

                System.out.println(cannonBuilding.getXCoordinate());
            }
*/

            for (Bullet bullet : bullets) {
                hitBuilding = isAnyBuildingHit((int) bullet.getBulletX(), (int) bullet.getBulletY());
                System.out.println(bullet.getBulletX() + "  " + bullet.getBulletY());
                //bullets.remove(0);


                bullet.draw(g);

                if (hitBuilding != null) {
                    if (hitBuilding instanceof AlleyBuilding) {
                        buildings.set(buildings.indexOf(buildingHit), new AlleyBuilding(new RubbleBuilding(buildingHit), ((AlleyBuilding) hitBuilding).getAlleyWidth()));
                        bullets.remove(bullet);
                        System.out.println("The thing is YAH: " + bullets.contains(bullet));
                    } else {
                        //System.out.println("Building hit " + hitBuilding.getXCoordinate() + "," + hitBuilding.getYCoordinate());
                        buildings.set(buildings.indexOf(buildingHit), new RubbleBuilding(buildingHit));
                        bullets.remove(bullet);
                        System.out.println("The thing is YAH: " + bullets.contains(bullet));
                    }
                }

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
        Object source = e.getSource();
        String actionCommand = e.getActionCommand();
        //now take input in a while
        if(actionCommand != null) {
            bullets.add(new Bullet((double)Integer.parseInt(actionCommand), 15, this.cannonBuilding.getXCoordinate(), cannonBuilding.getYCoordinate()-250, System.currentTimeMillis()));
        }
        repaint();
    }
}
