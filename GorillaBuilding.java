package com.william.graphing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class GorillaBuilding extends ImageBuilding {
    Building wrapped;

    public GorillaBuilding( Building b) {

        super(b);
        wrapped = b;
    }

    @Override
    File getFile() {
        return new File("/home/linuxlite/Downloads/Gorilla.png");
    }

    @Override
    int getImageWidth() {
        return wrapped.getWidth();
    }

    @Override
    int getImageHeight() {
        return wrapped.getHeight();
    }

}
