package com.william.graphing;

import java.io.File;

public class CannonBuilding extends ImageBuilding {

    public Building wrapped;

    public CannonBuilding(Building b) {
        super(b);
        wrapped = b;
    }

    @Override
    File getFile() {
        return new File("/home/william/Downloads/cannon.jpeg");
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
