/*package com.william.graphing;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;


class BuildingTest {
    @Mock
    private Graphics mockGraphics;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }


    @org.junit.jupiter.api.Test
    void buildingHit() throws IOException {
        //make a new building
        Building underTest = new Building(53,87, Color.BLACK);
        underTest.draw(mockGraphics, 500, 451);
        assertTrue(underTest.buildingHit(513, 460));
        assertFalse(underTest.buildingHit(499, 460));
    }
    @org.junit.jupiter.api.Test
    void alleyBuildingHit() throws IOException {
        Building testWrapped = new Building(53,87,Color.black);
        Building test = new AlleyBuilding(testWrapped,0);
        test.draw(mockGraphics,500,451);
        assertTrue(test.buildingHit(513,460));
    }
}
 */