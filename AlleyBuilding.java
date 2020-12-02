package com.william.graphing;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

public class AlleyBuilding extends Building {
    Building wrapped;
    int alleyWidth;

    public AlleyBuilding(Building b, int alleyWidth) {
        super(b.getWidth() +alleyWidth, b.getHeight(), b.getColor());
        wrapped = b;
        this.alleyWidth = alleyWidth;
    }

    @Override
    public int draw(Graphics graphics, int x, int y) throws IOException {
        return alleyWidth + wrapped.draw(graphics, x, y);
    }
}
