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


        wrapped.draw(g, x, y);

        int width = getWidth();
        if(wrapped instanceof AlleyBuilding) {
            width = ((AlleyBuilding) wrapped).getNonAlleyWidth();
        }
        g.setColor(Color.black);
        g.fillRect( x, y-(getHeight())+15, width,width);
        return getWidth();
    }
}
