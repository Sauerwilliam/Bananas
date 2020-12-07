package com.william.graphing;

import java.awt.Color;
import java.awt.Graphics;

public class TriangleTopBuilding extends Building {

    public TriangleTopBuilding(int currentWidth, int currentHeight, Color currentColor) {
        super(currentWidth, currentHeight, currentColor);
    }

    @Override
    public int draw(Graphics graphics, int currentX, int currentY) {
        int xs[] = new int[5];
        int ys[] = new int[5];
        xs[0] = currentX;
        xs[1] = currentX + getWidth();
        xs[2] = currentX + getWidth();
        xs[3] = currentX + getWidth() /3;
        xs[4] = currentX;

        x = currentX;
        y = currentY;
        ys[0] = currentY;
        ys[1] = currentY;
        ys[2] = (int)(currentY - getHeight() * .67);
        ys[3] = currentY - getHeight();
        ys[4] = ys[2];
        graphics.fillPolygon(xs, ys, 5);
        return getWidth();
    }
}
