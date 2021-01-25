package com.william.graphing;

import java.awt.*;
import java.io.File;

public class RubbleBuilding extends ImageBuilding{

    public Building wrapped;

    public RubbleBuilding(Building b) {
        super(b);
        wrapped = b;
    }

    @Override
    File getFile() {
        return new File("/home/william/Downloads/Rubble.png");
    }

    @Override
    int getImageWidth() {
        return wrapped.getWidth();
    }

    @Override
    int getImageHeight() {
        return wrapped.getHeight() + wrapped.getHeight();
    }

    @Override
    public boolean buildingHit(int bananaX, int bananaY) {
        return false;
    }

}
