package com.william.graphing;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class CityScape
{
    public static final int MAX = 10;
    public static final int MIN = 0;
    List<Building> buildings = new ArrayList<>();
    public RandomColorGenerator colorGenerator;
    int randGorilla;
    int rectWidth;
    int rectHeight;


    public CityScape(RandomColorGenerator generator)
    {
        colorGenerator = generator;
    }

    public static int getRandomNumber(int min, int max)
    {
        return (int) ((Math.random() * (max - min)) + min);
    }


    private Building buildBuilding(int width, int height, Color color)
    {

        int rand = getRandomNumber(1,3);

        boolean shouldBeAlley = false;
        Building returnValue;
        int alleyRand;
        switch(rand){
            case 1 :
                returnValue = new Building(width,height,color);
                break;

            case 2 :
                returnValue = new TriangleTopBuilding(width,height,color);
                break;
            case 3 :
                returnValue = new RoundTopBuilding(width,height,color);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + rand);
        }
        alleyRand = getRandomNumber(MIN,MAX);

        if(getRandomNumber(0,2) == 0) {

            //build a new alley building wrapped around the current return value
            returnValue = new AlleyBuilding(returnValue,50);
        }
        return returnValue;
    }

    public List<Building> buildBuildings()
    {
        //declare the height and width and randomize them
        //keep track of x
        int x = 0;
        while (x < Bananas.WIDTH) {
            rectWidth = getRandomNumber(Bananas.WIDTHMIN, Bananas.WIDTHMAX);
            rectHeight = getRandomNumber(Bananas.HEIGHTMIN, Bananas.HEIGHTMAX);

            if (x >= Bananas.WIDTH - 100) {
                int remainingWidth = Bananas.WIDTH - x; //todo
                buildings.add(buildBuilding(remainingWidth, rectHeight, colorGenerator.getRandomColor()));
                x = Bananas.WIDTH;
                randGorilla = getRandomNumber(0,buildings.size());
                buildings.set(randGorilla,new GorillaBuilding(buildings.get(randGorilla)));

            }

            if (x <= Bananas.WIDTH - 100) {
                buildings.add(buildBuilding(rectWidth, rectHeight, colorGenerator.getRandomColor()));


                x += buildings.get(buildings.size() - 1).getWidth();
            }


        }


        //pick one building at random, turn it into a gorilla building
        return buildings;
    }
}
