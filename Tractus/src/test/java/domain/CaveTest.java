/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author juhojuutilainen
 */
public class CaveTest {
    Tile tile;
    Cave testCaveOne;
    Cave testCaveTwo;
    private Cave testCaveNull;
    
    @Before
    public void setUp() {
        ArrayList<Tile> twoTiles = new ArrayList<>();
        twoTiles.add(mock(Tile.class));
        twoTiles.add(mock(Tile.class));
        ArrayList<Tile> oneTile = new ArrayList<>();
        oneTile.add(mock(Tile.class));
        this.testCaveOne = new Cave(oneTile,1,1);
        this.testCaveTwo = new Cave(twoTiles,1,1);
    }

    @Test
    public void caveInitialized() {
        assertNotNull(this.testCaveOne);
    }
    
    
    
    @Test
    public void getSizeWorks() {
        assertEquals(1, this.testCaveOne.getSize());
    }
    
    
    @Test
    public void cannotInitializeWithNegativeCoordinates() {
        ArrayList<Tile> oneTile = new ArrayList<>();
        oneTile.add(mock(Tile.class));
        try {
            Cave testCave = new Cave(oneTile, -1, 1);
            fail("Map has no negative coordinates");
        } catch (IllegalArgumentException e) { }
    }

    
    @Test
    public void getTilesWorks() {
        ArrayList<Tile> testArrayList = testCaveTwo.getTiles();
        assertEquals(2, testArrayList.size());
    }
    
    @Test
    public void comparatorWorks() {
    
    }
}
