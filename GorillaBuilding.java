package com.william.graphing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class GorillaBuilding extends Building {
    Building wrapped;

    public GorillaBuilding( Building b) {

        super(b.getWidth(), b.getHeight(), b.getColor());
        wrapped = b;
    }
    @Override
    public int draw(Graphics g, int x, int y) throws IOException {

        final BufferedImage image = ImageIO.read(new File("/home/linuxlite/Downloads/Gorilla.png"));


        g.drawImage(image, x, y-(getHeight() * 2)+ getHeight() - getHeight(),getWidth(),getHeight(), null);
        return getWidth();
    }
}
