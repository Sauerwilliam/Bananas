package com.william.graphing;

import java.awt.Color;
import java.awt.Graphics;

public class TriangleTopBuilding extends Building {

    public TriangleTopBuilding(int currentWidth, int currentHeight, Color currentColor) {
        super(currentWidth, currentHeight, currentColor);
    }

    @Override
    public int draw(Graphics graphics, int x, int y) {
        int xs[] = new int[5];
        int ys[] = new int[5];
        xs[0] = x;
        xs[1] = x + getWidth();
        xs[2] = x + getWidth();
        xs[3] = x + getWidth() /3;
        xs[4] = x;

        ys[0] = y;
        ys[1] = y;
        ys[2] = (int)(y - getHeight() * .67);
        ys[3] = y - getHeight();
        ys[4] = ys[2];
        graphics.fillPolygon(xs, ys, 5);
        return getWidth();
    }
}
