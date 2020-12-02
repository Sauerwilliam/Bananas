package com.william.graphing;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

public class Building
{

    private int height;
    private int width;
    private Color color;

    public Building(int currentWidth, int currentHeight, Color currentColor)
    {
        height = currentHeight;
        width = currentWidth;
        color = currentColor;
    }

    public int getHeight()
    {
        return height;

    }

    public int getWidth()
    {
        return width;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color newColor)
    {
        color = newColor;
    }

    public void setWidth(int newWidth)
    {
        width = newWidth;
    }

    public void setHeight(int newHeight)
    {
        height = newHeight;
    }

    public int draw(Graphics graphics, int x, int y) throws IOException
    {
        graphics.fillRect(x, y - height, width, height);
        return width;
    }
}
