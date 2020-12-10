package com.william.graphing;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

public class Building {

    private int height;
    private int width;
    private Color color;
    protected int y;
    protected int x;

    public Building(int currentWidth, int currentHeight, Color currentColor) {
        height = currentHeight;
        width = currentWidth;
        color = currentColor;

    }

    public int getHeight() {
        return height;

    }

    public int getXCoordinate() {
        return x;
    }

    public int getYCoordinate() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color newColor) {
        color = newColor;
    }

    public void setWidth(int newWidth) {
        width = newWidth;
    }

    public void setHeight(int newHeight) {
        height = newHeight;
    }

    public int draw(Graphics graphics, int currentX, int currentY) throws IOException {
        x = currentX;
        y = currentY;
        graphics.fillRect(currentX, currentY - height, width, height);
        return width;
    }

    public boolean buildingHit(int bananaX, int bananaY) {
        if (bananaX >= getXCoordinate() && bananaX <= getXCoordinate() + getWidth() && bananaY < getHeight()) {

            return true;

        } else {
            return false;
        }
    }
    
}
