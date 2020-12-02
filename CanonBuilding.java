package com.william.graphing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CanonBuilding extends Building {

    public Building wrapped;
    public CanonBuilding(Building b) {
        super(b.getWidth(), b.getHeight() + 100, b.getColor());
        wrapped = b;
    }

    public int draw(Graphics g, int x, int y) throws IOException {

        final BufferedImage image = ImageIO.read(new File("/home/linuxlite/Downloads/Gorilla.png"));

        wrapped.draw(g, x, y);

        int width = getWidth();
        if(wrapped instanceof AlleyBuilding) {
            width = ((AlleyBuilding) wrapped).getNonAlleyWidth();
        }
        g.drawImage(image, x, y-(getHeight())+15, width,100, null);
        return getWidth();
    }
}
