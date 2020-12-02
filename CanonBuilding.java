package com.william.graphing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CanonBuilding extends ImageBuilding {

    public Building wrapped;

    public CanonBuilding(Building b) {
        super(b);
        wrapped = b;
    }

    @Override
    File getFile() {
        return new File("/home/william/Downloads/index.png");
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
