package com.william.graphing;

import java.io.File;


public class GorillaBuilding extends ImageBuilding {
    Building wrapped;

    public GorillaBuilding( Building b) {

        super(b);
        wrapped = b;
    }

    @Override
    File getFile() {
        return new File("/home/william/Downloads/gorilla.jpg");
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
