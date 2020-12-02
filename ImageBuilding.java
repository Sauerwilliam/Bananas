package com.william.graphing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class ImageBuilding extends Building {
    Building wrapped;

    public ImageBuilding( Building b) {

        super(b.getWidth(), b.getHeight()+100, b.getColor());
        wrapped = b;
    }

    abstract File getFile(); //
    abstract int getImageWidth();
    abstract int getImageHeight();


    public int draw(Graphics g, int x, int y) throws IOException {

        final BufferedImage image = ImageIO.read(getFile());

        wrapped.draw(g, x, y);

        int width = getImageWidth();
        int height = getImageHeight();
        if(wrapped instanceof AlleyBuilding) {
            width = ((AlleyBuilding) wrapped).getNonAlleyWidth();
        }
        g.drawImage(image, x, y-(getHeight())+15, width,height, null);
        return width;
    }
}
