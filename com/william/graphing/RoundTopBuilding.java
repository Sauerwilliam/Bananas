package com.william.graphing;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

public class RoundTopBuilding extends Building{

    private int height;
    private int width;
    public RoundTopBuilding(int currentWidth, int currentHeight, Color currentColor) {
        super(currentWidth, currentHeight, currentColor);

    }


    @Override
    public int draw(Graphics graphics, int currentX, int currentY) throws IOException {

        x = currentX;
        y = currentY;
        graphics.fillOval(currentX, currentY -getHeight() - (getHeight() / 2),getWidth(),getHeight());
        return super.draw(graphics, currentX, currentY);
    }
}
