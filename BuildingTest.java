package com.william.graphing;

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
        Building underTest = new Building(200,200, Color.BLACK);
        underTest.draw(mockGraphics, 0, 0);
        assertTrue(underTest.buildingHit(49, 49));
    }
}