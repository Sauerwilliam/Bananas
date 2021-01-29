package com.william.graphing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.File;
import java.io.IOException;

public abstract class ImageBuilding extends Building {
    Building wrapped;
    public BufferedImage image;
    public VolatileImage vImg;

    public ImageBuilding( Building b) {


        super(b.getWidth(), b.getHeight()+100, b.getColor());

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsConfiguration gc = ge.getDefaultScreenDevice().getDefaultConfiguration();


        wrapped = b;
        try {
            image = ImageIO.read(getFile());
            //g.drawImage(image, 100, 100, Color.MAGENTA, null);
            vImg = gc.createCompatibleVolatileImage(image.getWidth(),image.getHeight(), Transparency.TRANSLUCENT);
            Graphics2D g = vImg.createGraphics();
            g.drawImage(image, AffineTransform.getTranslateInstance(.25, .25), null);//, 100, 100);
        } catch (IOException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);

        }

    }

    abstract File getFile(); //
    abstract int getImageWidth();
    abstract int getImageHeight();
    @Override
    public int getXCoordinate() {
        return wrapped.getXCoordinate() - getImageHeight();
    }

    @Override
    public int getYCoordinate() {
        return wrapped.getYCoordinate();
    }

    public int draw(Graphics g, int currentX, int currentY) throws IOException {



        wrapped.draw(g, currentX, currentY);

        x = currentX;
        y = currentY;
        int width = getImageWidth();
        int height = getImageHeight();
        if(wrapped instanceof AlleyBuilding) {
            width = ((AlleyBuilding) wrapped).getNonAlleyWidth();
        }
        g.drawImage(vImg, currentX, currentY -(getHeight())+15, width,height, null);
        return width;
    }
}
