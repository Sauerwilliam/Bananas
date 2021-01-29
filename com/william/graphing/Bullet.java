package com.william.graphing;

import java.awt.*;

public class Bullet {
    public double bulletX;
    public double bulletY;
    public int speedX;
    public double speedY;
    public long time;
    public static double GRAVITY = 10; //1px /s /s
    public double timeDelta;
    public double radians;
    public double calculatedY;


    public void draw(Graphics g){
        //first move the bullet from where it was to where it should be
        // then draw it
        timeDelta = ((double)System.currentTimeMillis() - time)/1000;
        time = System.currentTimeMillis();

        //test comment

        g.setColor(Color.black);
        g.drawOval((int)bulletX, (int)bulletY, 10, 10);
        g.fillOval((int)bulletX, (int)bulletY, 10, 10);

        //System.out.println(speedY);
        double oldSpeedY = speedY;
        double newSpeedY = speedY + GRAVITY * timeDelta;
        speedY = (oldSpeedY + newSpeedY)/2;
        bulletY =  (timeDelta * speedY) + bulletY;
        bulletX = (timeDelta * speedX) + bulletX;
        speedY = newSpeedY;

        //System.out.println("X is now " + bulletX + " bulletY is" + (bulletY) + " timeDelta was" + timeDelta + "\n");


    }
    public Bullet(double currentBulletX,double currentBulletY,int currentSpeedY,int currentSpeedX,long currentTime){

            bulletX = currentBulletX;
            bulletY = currentBulletY;
            speedX = currentSpeedX;
            speedY = currentSpeedY;
            time = currentTime;

    }
    public Bullet(double angle,double speed, double currentBulletY,double currentBulletX,long currentTime){

        speedY =  -1 * (speed * (Math.sin(Math.toRadians(angle))));
        bulletX = currentBulletX;
        bulletY = currentBulletY;
        speedX = (int) (   (speed * (Math.cos(Math.toRadians(angle)))));
        time = currentTime;

    }
    public double getBulletX(){
        return bulletX;
    }
    public double getBulletY(){
        return bulletY;
    }

}
