package com.william.graphing;

import java.awt.Color;

public class FullSpectrumRandomColorGenerator implements RandomColorGenerator {

    public Color getRandomColor() {
        return new Color(Bananas.getRandomNumber(0, 225), Bananas.getRandomNumber(0, 225), Bananas.getRandomNumber(0, 0));
    }
}
