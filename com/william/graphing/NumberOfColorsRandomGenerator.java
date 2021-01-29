package com.william.graphing;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class NumberOfColorsRandomGenerator implements RandomColorGenerator {
    List<Color> colorList;
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public NumberOfColorsRandomGenerator(){
       colorList = new ArrayList<>();
       colorList.add(new Color(255,0,0));
       colorList.add(new Color(0,255,0));
       colorList.add(new Color(0,0,255));
       colorList.add(new Color(255,255,255));
       colorList.add(new Color(0,255,255));
       colorList.add(new Color(255,255,0));
    }
    public Color getRandomColor() {


        return colorList.get(getRandomNumber(0, colorList.size()));


    }
}
